package tech.huning.wall.e.springframework.support.log;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamSource;
import org.springframework.validation.BindingResult;
import tech.huning.wall.e.specs.constant.SymbolConstant;
import tech.huning.wall.e.springframework.support.util.WebContextUtil;
import tech.huning.wall.e.util.StringUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.*;

/**
 * 日志处理器
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
@Aspect
public class LogProcessor {

    private final Logger logger = LoggerFactory.getLogger(LogProcessor.class);

    public static final String BEAN_ID = "_AURORA_LOG_PROCESSOR_";

    private final LogConfig logConfig;

    public LogProcessor(LogConfig logConfig) {
        this.logConfig = logConfig;
    }

    @Around("@within(tech.huning.wall.e.specs.annotation.Log)")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        LogUO logUO = new LogUO();

        // 设置唯一标识
        setId(logUO);

        // 解析url
        resolveUrl(logUO);

        // 解析类名和方法名
        resolveClassAndMethodName(joinPoint, logUO);

        // 解析入参
        resolveParams(joinPoint, logUO);

        // 打印入参
        printParams(logUO);

        // 执行业务逻辑
        try {
            logUO.setResult(joinPoint.proceed());
        }catch (Throwable e) {
            logUO.setError(e);
            throw e;
        }

        // 记录耗时
        logUO.setCostTimeMillis();

        // 打印结果
        printResult(logUO);

        // 打印错误
        printError(logUO);

        return logUO.getResult();
    }

    private void setId(LogUO logUO){
        try{
            logUO.setId(UUID.randomUUID().toString());
        }catch (Exception ignored){

        }
    }

    private void resolveUrl(LogUO logUO) {
        try{
            String requestURI = WebContextUtil.getRequest().getRequestURI();
            logUO.setUrl(requestURI);
        }catch (Exception ignored){

        }
    }

    private void resolveClassAndMethodName(ProceedingJoinPoint joinPoint, LogUO logUO) {
        try{
            Signature signature = joinPoint.getSignature();
            if(Objects.nonNull(signature)) {
                logUO.setClassName(signature.getDeclaringTypeName());
                logUO.setMethodName(logUO.getClassName() + SymbolConstant.FULL_STOP + signature.getName());
            }
        }catch (Exception ignored){

        }
    }

    private void resolveParams(ProceedingJoinPoint joinPoint, LogUO logUO) {
        try{
            if(isExcludeParams(logUO.getClassName(), logUO.getMethodName())) {
                return;
            }
            Object[] args = joinPoint.getArgs();
            if(Objects.nonNull(args) && args.length > 0) {
                logUO.setParams(Arrays.asList(args));
            }
        }catch (Exception ignored){

        }
    }

    private void printParams(LogUO logUO){
        try{
            if(isExcludeParams(logUO.getClassName(), logUO.getMethodName())) {
                return;
            }
            if(logConfig.isPrintParams() && Objects.nonNull(logUO.getParams()) && !(logUO.getParams().isEmpty())) {
                StringJoiner joiner = new StringJoiner(SymbolConstant.COMMA);
                for(int i = 0; i < logUO.getParams().size(); i++) {
                    joiner.add("arg[" + i + "]:" + stringifyObject(logUO.getParams().get(i)));
                }
                logger.info("[{}] - {} - PARAMS:{}", logUO.getId(), logUO.getUrl(), joiner);
            }
        }catch (Exception ignored){

        }
    }

    private void printResult(LogUO logUO){
        try{
            if(isExcludeResult(logUO.getClassName(), logUO.getMethodName())) {
                return;
            }
            if(logConfig.isPrintResult()) {
                logger.info("[{}] - {} - RESULT:{}, COSTS:{}", logUO.getId(), logUO.getUrl(), stringifyObject(logUO.getResult()), logUO.getCostTimeMillis());
            }
        }catch (Exception ignored){

        }
    }

    private void printError(LogUO logUO){
        try{
            if(logConfig.isPrintError()) {
                StringBuilder error = new StringBuilder();
                error.append("[")
                     .append(logUO.getId())
                     .append("]")
                     .append(" - ERROR:")
                     .append(logUO.getUrl())
                     .append(logUO.getError().getMessage());
                logger.error(error.toString(), logUO.getError());
            }
        }catch (Exception ignored){

        }
    }

    private String stringifyObject(Object object) {
        if(Objects.isNull(object) ||
                object instanceof File ||
                object instanceof InputStream ||
                object instanceof OutputStream ||
                object instanceof Reader ||
                object instanceof Writer ||
                object instanceof ServletRequest ||
                object instanceof ServletResponse ||
                object instanceof BindingResult ||
                object instanceof InputStreamSource) {
            return "null";
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return JSON.toJSONString(object);
        }
    }

    private boolean isExcludeParams(final String className, final String methodName) {
        if(Objects.isNull(logConfig.getExcludeParams())) {
            return false;
        }
        if(Objects.isNull(logConfig.getExcludeParams().getClassNames())) {
            return false;
        }
        if(Objects.isNull(logConfig.getExcludeParams().getMethodNames())) {
            return false;
        }
        List<String> classNames = logConfig.getExcludeParams().getClassNames();
        if(classNames.stream().anyMatch(clazz -> StringUtil.isEqual(className, clazz))) {
            return true;
        }
        List<String> methodNames = logConfig.getExcludeParams().getMethodNames();
        if(methodNames.stream().anyMatch(method -> StringUtil.isEqual(methodName, method))) {
            return true;
        }
        return false;
    }

    private boolean isExcludeResult(final String className, final String methodName) {
        if(Objects.isNull(logConfig.getExcludeResult())) {
            return false;
        }
        if(Objects.isNull(logConfig.getExcludeResult().getClassNames())) {
            return false;
        }
        if(Objects.isNull(logConfig.getExcludeResult().getMethodNames())) {
            return false;
        }
        List<String> classNames = logConfig.getExcludeResult().getClassNames();
        if(classNames.stream().anyMatch(clazz -> StringUtil.isEqual(className, clazz))) {
            return true;
        }
        List<String> methodNames = logConfig.getExcludeResult().getMethodNames();
        if(methodNames.stream().anyMatch(method -> StringUtil.isEqual(methodName, method))) {
            return true;
        }
        return false;
    }

}

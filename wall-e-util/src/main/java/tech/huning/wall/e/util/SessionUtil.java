package tech.huning.wall.e.util;

import tech.huning.wall.e.specs.constant.CommonConstant;
import tech.huning.wall.e.specs.constant.SymbolConstant;
import tech.huning.wall.e.specs.exception.CheckException;
import tech.huning.wall.e.specs.exception.SystemException;
import tech.huning.wall.e.specs.model.ResultCode;
import tech.huning.wall.e.specs.model.pojo.Tenant;
import tech.huning.wall.e.specs.model.session.UserSession;
import tech.huning.wall.e.util.constant.UtilResultCode;
import org.apache.commons.codec.binary.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Session工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class SessionUtil {

    /**
     * 设置当前图片验证码
     * @param request  请求
     * @param captcha  验证码
     * @throws SystemException 系统异常
     */
    public static void setCurrentCaptcha(HttpServletRequest request, String captcha) throws SystemException {
        try {
            request.getSession().setAttribute(CommonConstant.SESSION_CURRENT_CAPTCHA, captcha);
        }catch (Exception e){
            throw new SystemException(ResultCode.FAILURE);
        }
    }

    /**
     * 验证当前图片验证码
     * @param request  请求
     * @param captcha  验证码
     * @throws CheckException 校验异常
     * @throws SystemException 系统异常
     */
    public static void verifyCurrentCaptcha(HttpServletRequest request, String captcha) throws CheckException, SystemException {
        try {
            Object captchaObj = request.getSession().getAttribute(CommonConstant.SESSION_CURRENT_CAPTCHA);
            if(!(captchaObj instanceof String)) {
                throw new CheckException(UtilResultCode.CAPTCHA_IS_EXPIRED);
            }
            String realCaptcha = (String)captchaObj;
            if(!StringUtils.equals(realCaptcha, captcha)){
                throw new CheckException(UtilResultCode.CAPTCHA_IS_NOT_MATCHED);
            }
            request.getSession().removeAttribute(CommonConstant.SESSION_CURRENT_CAPTCHA);
        }catch (CheckException e) {
            throw e;
        }catch (Exception e){
            throw new SystemException(ResultCode.FAILURE);
        }
    }

    /**
     * 设置当前用户
     * @param request      请求
     * @param userSession  用户会话数据
     * @throws SystemException 系统异常
     */
    public static void setCurrentUser(HttpServletRequest request, UserSession userSession) throws SystemException {
        try {
            request.getSession().setAttribute(generateUserSessionKey(userSession.getTenant()), userSession);
        }catch (Exception e){
            throw new SystemException(ResultCode.FAILURE);
        }
    }

    /**
     * 获取当前账号
     * @param request 请求
     * @param tenant 租户
     * @return 用户会话数据
     * @throws CheckException 校验异常
     * @throws SystemException 系统异常
     */
    public static UserSession getCurrentUser(HttpServletRequest request, Tenant tenant) throws CheckException, SystemException {
        try {
            Object userObj = request.getSession().getAttribute(generateUserSessionKey(tenant));
            if(!(userObj instanceof UserSession)) {
                throw new CheckException(UtilResultCode.USER_LOGOUT_OR_NOT_LOGIN);
            }
            return (UserSession)userObj;
        }catch (CheckException e) {
            throw e;
        }catch (Exception e){
            throw new SystemException(ResultCode.FAILURE);
        }
    }

    /**
     *  删除当前账户
     * @param request 请求
     * @param tenant 租户
     * @throws SystemException 系统异常
     */
    public static void removeCurrentUser(HttpServletRequest request, Tenant tenant) throws SystemException {
        try {
            request.getSession().removeAttribute(generateUserSessionKey(tenant));
        }catch (Exception e){
            throw new SystemException(ResultCode.FAILURE);
        }
    }

    private static String generateUserSessionKey(Tenant tenant) {
        String key = CommonConstant.SESSION_CURRENT_USER;
        if(Objects.isNull(tenant)) {
            return key;
        }
        if(StringUtil.isNotEmpty(tenant.getName())) {
            return key + SymbolConstant.UNDERLINE + tenant.getName();
        }
        if(Objects.nonNull(tenant.getId())) {
            return key + SymbolConstant.UNDERLINE + tenant.getId();
        }
        return key + SymbolConstant.UNDERLINE + tenant.hashCode();
    }

}

package tech.huning.wall.e.util;

import com.alibaba.fastjson.JSON;
import tech.huning.wall.e.specs.exception.CommonException;
import tech.huning.wall.e.specs.exception.SystemException;
import tech.huning.wall.e.specs.model.ResponseModel;
import tech.huning.wall.e.specs.model.ResultCode;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * HTTP响应工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class ResponseUtil {

	/**
	 * 根据响应模型数据输出JSON数据到客户端
	 *
	 * @param request            请求
	 * @param response           响应
	 * @param responseModel      响应模型
	 * @throws SystemException 系统异常
	 */
	@SuppressWarnings("rawtypes")
    public static void outputJson(ServletRequest request, ServletResponse response, ResponseModel responseModel) throws SystemException {
    	PrintWriter pw = null;
    	try {
    		HttpServletResponse resp = (HttpServletResponse)response;
    		HttpServletRequest req = (HttpServletRequest)request;
    		
    		req.setCharacterEncoding("utf-8");
    		resp.setHeader("Content-type", "application/json;charset=UTF-8");
    		resp.setCharacterEncoding("utf-8");
    		pw = resp.getWriter();
    		pw.println(JSON.toJSONString(responseModel));
    		pw.flush();
    	}catch(Exception e) {
    		throw new SystemException(e, ResultCode.FAILURE);
    	}finally {
    		if(null != pw) {
    			pw.close();
    		}
    	}
    }

	/**
	 * 根据结果码输出JSON数据到客户端
	 *
	 * @param request   请求
	 * @param response  响应
	 * @param blogEngineResultCode 结果码
	 * @throws SystemException 系统异常
	 */
    public static void outputJson(ResultCode blogEngineResultCode, ServletRequest request, ServletResponse response) throws SystemException {
    	ResponseModel<Boolean> responseModel = new ResponseModel<Boolean>();
		responseModel.failure(blogEngineResultCode);
		outputJson(request, response, responseModel);
    }

	/**
	 * 根据异常信息输出JSON数据到客户端
	 * @param request   请求
	 * @param response  响应
	 * @param exception 异常
	 * @throws SystemException 系统异常
	 */
	public static void outputJson(CommonException exception, ServletRequest request, ServletResponse response) throws SystemException {
    	ResponseModel<Boolean> responseModel = new ResponseModel<Boolean>();
		// responseModel.failure(ResultCode.getByCode(exception.getCode()));
		responseModel.setCode(exception.getCode());
		responseModel.setMsg(exception.getMsg());
		outputJson(request, response, responseModel);
    }

	/**
	 * 根据结果码静默输出JSON数据到客户端
	 * @param request   请求
	 * @param response  响应
	 * @param blogEngineResultCode 结果码
	 */
	public static void outputJsonInSilence(ResultCode blogEngineResultCode, ServletRequest request, ServletResponse response) {
    	try {
    		outputJson(blogEngineResultCode, request, response);
    	}catch(Exception e) {
    		
    	}
    }

	/**
	 * 根据异常信息静默输出JSON数据到客户端
	 * @param request   请求
	 * @param response  响应
	 * @param exception 异常
	 */
	public static void outputJsonInSilence(CommonException exception, ServletRequest request, ServletResponse response) {
    	try {
    		outputJson(exception, request, response);
    	}catch(Exception e) {
    		
    	}
    }

	public static <T> ResponseModel<T> success(){
		return ResponseModel.success();
	}

	public static <T> ResponseModel<T> success(T data){
		return ResponseModel.success(data);
	}

	public static <T> ResponseModel<T> failure(){
		return ResponseModel.failure();
	}

	public static <T> ResponseModel<T> failure(ResultCode resultCode){
		ResponseModel<T> responseModel = new ResponseModel<T>();
		responseModel.setCode(resultCode.getCode());
		responseModel.setMsg(resultCode.getMsg());
		return responseModel;
	}

	public static <T> ResponseModel<T> failure(CommonException e){
		ResponseModel<T> responseModel = new ResponseModel<T>();
		responseModel.setCode(e.getCode());
		responseModel.setMsg(e.getMsg());
		return responseModel;
	}

}

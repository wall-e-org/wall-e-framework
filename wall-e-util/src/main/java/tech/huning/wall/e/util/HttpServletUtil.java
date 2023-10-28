package tech.huning.wall.e.util;

import tech.huning.wall.e.specs.exception.SystemException;
import tech.huning.wall.e.specs.model.ResultCode;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HttpServlet工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class HttpServletUtil {

    /**
     *  获取URI
     * @param servletRequest 请求
     * @return uri
     * @throws SystemException 系统异常
     */
    public static String getUri(ServletRequest servletRequest) throws SystemException {
        if(servletRequest instanceof HttpServletRequest){
            HttpServletRequest req = (HttpServletRequest)servletRequest;
            return req.getRequestURI();
        }
        else{
            throw new SystemException(ResultCode.FAILURE);
        }
    }

    /**
     * 获取HttpServletRequest
     * @param servletRequest 请求
     * @return 请求
     * @throws SystemException 系统异常
     */
    public static HttpServletRequest getHttp(ServletRequest servletRequest) throws SystemException {
        if(servletRequest instanceof HttpServletRequest){
            return (HttpServletRequest)servletRequest;
        }
        else{
            throw new SystemException(ResultCode.FAILURE);
        }
    }

    /**
     * 重定向
     * @param response  响应
     * @param url       地址
     * @throws SystemException 系统异常
     */
    public static void sendRedirect(HttpServletResponse response, String url) throws SystemException {
        try {
            response.sendRedirect(url);
        } catch (IOException ex) {
            throw new SystemException(ResultCode.FAILURE);
        }
    }

}

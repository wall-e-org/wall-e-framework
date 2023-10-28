package tech.huning.wall.e.util;

import tech.huning.wall.e.specs.exception.SystemException;
import tech.huning.wall.e.specs.model.ResultCode;
import tech.huning.wall.e.specs.model.session.UserSession;
import tech.huning.wall.e.util.uo.ThreadUO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 线程工具
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class ThreadUtil {

    private static final ThreadLocal<ThreadUO> THREADS = ThreadLocal.withInitial(ThreadUO::new);

    /**
     * 设置当前用户
     * @param userSession  用户会话数据
     * @throws SystemException 系统异常
     */
    public static void setCurrentUser(UserSession userSession) throws SystemException {
        try {
            ThreadUO threadUO = THREADS.get();
            threadUO.setUserSession(userSession);
        }catch (Exception e){
            throw new SystemException(ResultCode.FAILURE);
        }
    }

    /**
     * 获取当前用户
     * @return  userSession  用户会话数据
     * @throws SystemException 系统异常
     */
    public static UserSession getCurrentUser() throws SystemException {
        try {
            ThreadUO threadUO = THREADS.get();
            return threadUO.getUserSession();
        }catch (Exception e){
            throw new SystemException(ResultCode.FAILURE);
        }
    }

    /**
     * 清除当前用户
     */
    public static void removeCurrentUser() {
        THREADS.get().setUserSession(null);
    }

    /**
     * 设置当前请求和响应
     * @param request   当前请求
     * @param response  当前响应
     * @throws SystemException 系统异常
     */
    public static void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response) throws SystemException {
        try {
            ThreadUO threadUO = THREADS.get();
            threadUO.setRequest(request);
            threadUO.setResponse(response);
        }catch (Exception e){
            throw new SystemException(ResultCode.FAILURE);
        }
    }

    /**
     * 获取当前请求
     * @return 当前请求
     */
    public static HttpServletRequest getCurrentRequest() {
        return THREADS.get().getRequest();
    }

    /**
     * 获取当前响应
     * @return 当前响应
     */
    public static HttpServletResponse getCurrentResponse() {
        return THREADS.get().getResponse();
    }

    /**
     * 清空线程副本数据
     */
    public static void remove() {
        THREADS.remove();
    }

}

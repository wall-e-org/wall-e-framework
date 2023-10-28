package tech.huning.wall.e.specs.exception;

import tech.huning.wall.e.specs.model.ResultCode;

/**
 * 自定义业务处理异常
 * 1.用于Service层处理异常时抛出
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class ServiceException extends CommonException {

	private static final long serialVersionUID = 1284747576447458506L;

    public ServiceException(ResultCode resultCode){
        super(resultCode);
    }

    public ServiceException(String code, String msg){
        super(code, msg);
    }

    public ServiceException(CommonException e){
        super(e);
    }

    public ServiceException(Exception e, ResultCode resultCode){
        super(e, resultCode);
    }

    public ServiceException(Throwable throwable, ResultCode resultCode){
        super(throwable, resultCode);
    }

    public ServiceException(Throwable throwable, String code, String msg){
        super(throwable, code, msg);
    }
	
}

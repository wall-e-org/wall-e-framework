package tech.huning.wall.e.specs.exception;

import tech.huning.wall.e.specs.model.ResultCode;

/**
 * 系统异常
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class SystemException extends CommonException {

	private static final long serialVersionUID = 1284747576447458506L;

    public SystemException(ResultCode resultCode){
        super(resultCode);
    }

    public SystemException(String code, String msg){
        super(code, msg);
    }

    public SystemException(CommonException e){
        super(e);
    }

    public SystemException(Exception e, ResultCode resultCode){
        super(e, resultCode);
    }

    public SystemException(Throwable throwable, ResultCode resultCode){
        super(throwable, resultCode);
    }

    public SystemException(Throwable throwable, String code, String msg){
        super(throwable, code, msg);
    }
	
}

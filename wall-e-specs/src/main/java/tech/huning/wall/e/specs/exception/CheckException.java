package tech.huning.wall.e.specs.exception;

import tech.huning.wall.e.specs.model.ResultCode;

/**
 * 自定义校验异常
 * 1.用于数据校验失败时抛出异常
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class CheckException extends CommonException {

	private static final long serialVersionUID = 1284747576447458506L;

    public CheckException(ResultCode resultCode){
        super(resultCode);
    }

    public CheckException(String code, String msg){
        super(code, msg);
    }

    public CheckException(CommonException e){
        super(e);
    }

    public CheckException(Exception e, ResultCode resultCode){
        super(e, resultCode);
    }

    public CheckException(Throwable throwable, ResultCode resultCode){
        super(throwable, resultCode);
    }

    public CheckException(Throwable throwable, String code, String msg){
        super(throwable, code, msg);
    }
	
}

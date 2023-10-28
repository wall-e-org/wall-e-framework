package tech.huning.wall.e.specs.exception;

import tech.huning.wall.e.specs.model.ResultCode;

/**
 * 自定义异常基类
 * 1.用于自定义异常模板类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class CommonException extends RuntimeException {

	private static final long serialVersionUID = -8725011160174071865L;
	
	private String code;
    private String msg;

    public CommonException(ResultCode resultCode){
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }
    
    public CommonException(String code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CommonException(CommonException e){
        super(e);
        this.code = e.getCode();
        this.msg = e.getMsg();
    }

    public CommonException(Exception e, ResultCode resultCode){
        super(e);
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public CommonException(Throwable throwable, ResultCode resultCode){
    	super(throwable);
    	this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }
    
    public CommonException(Throwable throwable, String code, String msg){
        super(throwable);
        this.code = code;
        this.msg = msg;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}


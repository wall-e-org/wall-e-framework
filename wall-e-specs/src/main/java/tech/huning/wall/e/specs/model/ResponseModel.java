package tech.huning.wall.e.specs.model;

import tech.huning.wall.e.specs.exception.CommonException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 响应模型
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
@ApiModel("响应模型")
public class ResponseModel<T> extends CommonModel<T> {

    private static final long serialVersionUID = 3260393285890827610L;

    @ApiModelProperty("错误码")
    private String code;
    @ApiModelProperty("错误消息")
    private String msg;

    public ResponseModel(){
        super();
    }

    public static <T> ResponseModel<T> success(){
        ResponseModel<T> responseModel = new ResponseModel<T>();
        responseModel.setCode(ResultCode.SUCCESS.getCode());
        responseModel.setMsg(ResultCode.SUCCESS.getMsg());
        return responseModel;
    }

    public static <T> ResponseModel<T> success(T data){
        ResponseModel<T> responseModel = new ResponseModel<T>();
        responseModel.setCode(ResultCode.SUCCESS.getCode());
        responseModel.setMsg(ResultCode.SUCCESS.getMsg());
        responseModel.setData(data);
        return responseModel;
    }

    public static <T> ResponseModel<T> failure(){
        return failure(ResultCode.FAILURE);
    }

    public static <T> ResponseModel<T> failure(ResultCode resultCode){
        ResponseModel<T> responseModel = new ResponseModel<T>();
        responseModel.setCode(resultCode.getCode());
        responseModel.setMsg(resultCode.getMsg());
        return responseModel;
    }

    public static <T> ResponseModel<T> failure(CommonException e) {
        ResponseModel<T> responseModel = new ResponseModel<T>();
        responseModel.setCode(e.getCode());
        responseModel.setMsg(e.getMsg());
        return responseModel;
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
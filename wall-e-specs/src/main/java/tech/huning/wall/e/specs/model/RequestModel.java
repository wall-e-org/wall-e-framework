package tech.huning.wall.e.specs.model;

import io.swagger.annotations.ApiModel;

/**
 * 请求模型
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
@ApiModel("请求模型")
public class RequestModel<T> extends CommonModel<T> {

    private static final long serialVersionUID = 2613382726394084814L;

    public RequestModel(){
        super();
    }

}


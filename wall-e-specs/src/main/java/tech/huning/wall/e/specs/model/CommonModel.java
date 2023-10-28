package tech.huning.wall.e.specs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 通用模型
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
@ApiModel("通用模型")
public class CommonModel<T> implements Serializable {

    private static final long serialVersionUID = 2613382726394084814L;

    @ApiModelProperty("时间戳")
    protected long timestamp;
    @ApiModelProperty("数据")
    protected T data;

    public CommonModel(){
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}


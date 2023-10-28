package tech.huning.wall.e.specs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 列表模型
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
@ApiModel("列表模型")
public class ListModel<T> implements Serializable {

    private static final long serialVersionUID = 3065636072933717414L;

    @ApiModelProperty("数据列表")
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

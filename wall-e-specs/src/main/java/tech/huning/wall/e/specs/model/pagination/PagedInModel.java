package tech.huning.wall.e.specs.model.pagination;

import tech.huning.wall.e.specs.constant.CommonConstant;
import tech.huning.wall.e.specs.model.CoupleModel;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询入参模型
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class PagedInModel<T> implements Serializable {

	private static final long serialVersionUID = 7118609187096456162L;

	// 页码,从1开始
	private Integer pageNo = CommonConstant.PAGE_NO_DEFAULT_VALUE;
	// 每页条数
	private Integer pageSize = CommonConstant.PAGE_SIZE_DEFAULT_VALUE;
    // 排序规则, 左值为排序字段, 右值为排序方式(升序/降序)
	// CommonConstant.ORDER_BY_ASC
	// CommonConstant.ORDER_BY_DESC
	private List<CoupleModel<String, Integer>> orderBys;
    // 查询条件
	private T criteria;

	public Integer getPageNo() {
		return pageNo;
	}

	public PagedInModel<T> setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public PagedInModel<T> setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public List<CoupleModel<String, Integer>> getOrderBys() {
		return orderBys;
	}

	public PagedInModel<T> setOrderBys(List<CoupleModel<String, Integer>> orderBys) {
		this.orderBys = orderBys;
		return this;
	}

	public T getCriteria() {
		return criteria;
	}

	public PagedInModel<T> setCriteria(T criteria) {
		this.criteria = criteria;
		return this;
	}
	
}

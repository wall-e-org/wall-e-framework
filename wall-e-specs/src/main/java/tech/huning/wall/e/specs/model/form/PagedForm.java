package tech.huning.wall.e.specs.model.form;

import tech.huning.wall.e.specs.constant.CommonConstant;

/**
 * 分页表单
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class PagedForm extends CommonForm {

	private static final long serialVersionUID = 628110574875899246L;

	// 页码
	protected Integer pageNo = CommonConstant.PAGE_NO_DEFAULT_VALUE;
	// 每页条数
	protected Integer pageSize = CommonConstant.PAGE_SIZE_DEFAULT_VALUE;
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}

package tech.huning.wall.e.specs.model.pagination;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 * 分页查询出参模型
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class PagedOutModel<T> extends Observable implements Observer, Serializable {

	private static final long serialVersionUID = -3067636071933707414L;

	// 总条数
	private Long totalCount;
	// 总页数
	private Long totalPage;
    // 页码
	private Integer pageNo;
	// 每页条数
	private Integer pageSize;
	// 数据
	private List<T> data;

	public PagedOutModel(){
		addObserver(this);
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public PagedOutModel<T> setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
		setChanged();
		notifyObservers();
		return this;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public PagedOutModel<T> setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
		return this;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public PagedOutModel<T> setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public PagedOutModel<T> setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		setChanged();
		notifyObservers();
		return this;
	}

	public List<T> getData() {
		return data;
	}

	public PagedOutModel<T> setData(List<T> data) {
		this.data = data;
		return this;
	}

	@Override
	public void update(Observable observable, Object arg) {
		if(Objects.isNull(this.getPageSize()) || this.getPageSize() <= 0) {
			return;
		}
		if(Objects.isNull(this.getTotalCount()) || this.getTotalCount() < 0) {
			return;
		}
		this.totalPage = this.getTotalCount() / this.getPageSize();
		if(this.getTotalCount() % this.getPageSize() != 0){
			this.totalPage += 1;
		}
	}

}

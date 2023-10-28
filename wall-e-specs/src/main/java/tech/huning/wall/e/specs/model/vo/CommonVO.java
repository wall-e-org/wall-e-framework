package tech.huning.wall.e.specs.model.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础 Value Object
 * 1.用于定义接口返回数据模型
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class CommonVO implements Serializable {

	private static final long serialVersionUID = 7461054219397961927L;
	// 编码
	protected Long id;
	// 租户编码
	protected Long tenantId;
	// 创建者
	protected String createdBy;
	// 创建时间
	protected Date createdAt;
	// 更新者
	protected String updatedBy;
	// 更新时间
	protected Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}

package tech.huning.wall.e.specs.model.po;

import tech.huning.wall.e.specs.annotation.Autofill;
import tech.huning.wall.e.specs.constant.CommonConstant;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础 Persistent Object
 * 1.用于定义数据库表等持久层数据模型
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class CommonPO implements Serializable {

	private static final long serialVersionUID = -7238608787196456162L;

	// 主键
	@Autofill
	protected Long id;
	// 租户编码
	protected Long tenantId;
	// 删除标记
	protected Byte isDeleted = CommonConstant.IS_DELETED_NO;
	// 优先级
	protected Long priority;
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

	public Byte getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
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

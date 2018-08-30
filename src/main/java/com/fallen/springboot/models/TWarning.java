package com.fallen.springboot.models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_warning database table.
 * 
 */
@Entity
@Table(name="t_warning")
@NamedQuery(name="TWarning.findAll", query="SELECT t FROM TWarning t")
public class TWarning implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="create_time")
	private String createTime;

	@Column(name="deal_content")
	private String dealContent;

	@Column(name="last_update")
	private String lastUpdate;

	@Column(name="last_update_userid")
	private String lastUpdateUserid;

	private String location; 

	private String status;

	@Column(name="warning_content")
	private String warningContent; 

	public TWarning() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDealContent() {
		return this.dealContent;
	}

	public void setDealContent(String dealContent) {
		this.dealContent = dealContent;
	}

	public String getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdateUserid() {
		return this.lastUpdateUserid;
	}

	public void setLastUpdateUserid(String lastUpdateUserid) {
		this.lastUpdateUserid = lastUpdateUserid;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	} 

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWarningContent() {
		return this.warningContent;
	}

	public void setWarningContent(String warningContent) {
		this.warningContent = warningContent;
	} 

}
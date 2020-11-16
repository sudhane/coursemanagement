package com.assignment.courses.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("authors")
public class Author {

	@Id
	@Column("id")
	private Long id;
	
	@Column("full_name")
	private String fullName;

	@CreatedBy
    protected String createdBy = "SYSTEM";
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
    protected Date creationDate;
	
	@LastModifiedBy
    protected String lastModifiedBy = "SYSTEM";

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
    protected Date lastModifiedDate;
	
	@Column("additional_details")
	private String additionalDetails;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}	
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
	}

	public Author(Long id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", fullName=" + fullName + "]";
	}	
	
}

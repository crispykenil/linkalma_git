package com.linkalma.dto;

import java.sql.Date;

public class SchoolUpdateDTO extends BaseDTO {

	
	private long id;
	
	private long schoolID;
	
	private String title;
	
	private String description;
	
	private String schoolName;

	private int updateType;
	
	private long createUserID;
	
	private Date CreateDttm;
	
	private long updateUserID;
	
	private Date updateDttm;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the schoolID
	 */
	public long getSchoolID() {
		return schoolID;
	}

	/**
	 * @param schoolID the schoolID to set
	 */
	public void setSchoolID(long schoolID) {
		this.schoolID = schoolID;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the updateType
	 */
	public int getUpdateType() {
		return updateType;
	}

	/**
	 * @param updateType the updateType to set
	 */
	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	/**
	 * @return the createUserID
	 */
	public long getCreateUserID() {
		return createUserID;
	}

	/**
	 * @param createUserID the createUserID to set
	 */
	public void setCreateUserID(long createUserID) {
		this.createUserID = createUserID;
	}

	/**
	 * @return the createDttm
	 */
	public Date getCreateDttm() {
		return CreateDttm;
	}

	/**
	 * @param createDttm the createDttm to set
	 */
	public void setCreateDttm(Date createDttm) {
		CreateDttm = createDttm;
	}

	/**
	 * @return the updateUserID
	 */
	public long getUpdateUserID() {
		return updateUserID;
	}

	/**
	 * @param updateUserID the updateUserID to set
	 */
	public void setUpdateUserID(long updateUserID) {
		this.updateUserID = updateUserID;
	}

	/**
	 * @return the updateDttm
	 */
	public Date getUpdateDttm() {
		return updateDttm;
	}

	/**
	 * @param updateDttm the updateDttm to set
	 */
	public void setUpdateDttm(Date updateDttm) {
		this.updateDttm = updateDttm;
	}

	/**
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return schoolName;
	}

	/**
	 * @param schoolName the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

}

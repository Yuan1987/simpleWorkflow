package com.dynastech.system.entity;

public class Serial {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column rs_Serial.id
	 * @mbggenerated  Fri Nov 24 16:47:18 CST 2017
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column rs_Serial.name
	 * @mbggenerated  Fri Nov 24 16:47:18 CST 2017
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column rs_Serial.code
	 * @mbggenerated  Fri Nov 24 16:47:18 CST 2017
	 */
	private String code;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column rs_Serial.isdeleted
	 * @mbggenerated  Fri Nov 24 16:47:18 CST 2017
	 */
	private Boolean isdeleted=false;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column rs_Serial.parentid
	 * @mbggenerated  Fri Nov 24 16:47:18 CST 2017
	 */
	private String parentid;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column rs_Serial.id
	 * @return  the value of rs_Serial.id
	 * @mbggenerated  Fri Nov 24 16:47:18 CST 2017
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column rs_Serial.id
	 * @param id  the value for rs_Serial.id
	 * @mbggenerated  Fri Nov 24 16:47:18 CST 2017
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column rs_Serial.name
	 * @return  the value of rs_Serial.name
	 * @mbggenerated  Fri Nov 24 16:47:18 CST 2017
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column rs_Serial.name
	 * @param name  the value for rs_Serial.name
	 * @mbggenerated  Fri Nov 24 16:47:18 CST 2017
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column rs_Serial.code
	 * @return  the value of rs_Serial.code
	 * @mbggenerated  Fri Nov 24 16:47:18 CST 2017
	 */
	public String getCode() {
		return code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column rs_Serial.code
	 * @param code  the value for rs_Serial.code
	 * @mbggenerated  Fri Nov 24 16:47:18 CST 2017
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column rs_Serial.isdeleted
	 * @return  the value of rs_Serial.isdeleted
	 * @mbggenerated  Fri Nov 24 16:47:18 CST 2017
	 */
	public Boolean getIsdeleted() {
		return isdeleted;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column rs_Serial.isdeleted
	 * @param isdeleted  the value for rs_Serial.isdeleted
	 * @mbggenerated  Fri Nov 24 16:47:18 CST 2017
	 */
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column rs_Serial.parentid
	 * @return  the value of rs_Serial.parentid
	 * @mbggenerated  Fri Nov 24 16:47:18 CST 2017
	 */
	public String getParentid() {
		return parentid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column rs_Serial.parentid
	 * @param parentid  the value for rs_Serial.parentid
	 * @mbggenerated  Fri Nov 24 16:47:18 CST 2017
	 */
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
}
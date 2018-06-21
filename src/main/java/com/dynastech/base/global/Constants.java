package com.dynastech.base.global;

/**
 * @author yuan
 */
public class Constants {
	
	/**
	 * 为了重构 角色和组织的关系（取消角色在组织下的模式），且不修改以前的逻辑代码，暂将所有角色都归为根组织。
	 */
	public static final String ORG_ROOT_ID="F24FB3B0-CE54-4E87-B524-CEF637F7C2A9";
	
	public static final String SUPER_USERID="12EEE6EA-7415-4FED-BB0A-A9C07F811976";
	
	public static final String CURRENT_USER = "CURRENT_USER";
	
	public static final String ROLE_NAME_BMLD="bmld";
	
	public static final String ROLE_NAME_SYBLD="sybld";
	
	/**
	 * 人力资源部 部门id
	 */
	public static final String HRDEPTID="006E0ED0-F92A-4DB8-8757-927D7219DEAB";
	
	/**
	 * 一般在岗 角色id
	 */
	public static final String ROLE_NORMAL="AAFF6AAE-AAC8-42F5-892C-47631EB20A8D";
	
	/**
	 * 部门领导角色id
	 */
	public static final String ROLE_BMLD="FD154C7B-B677-4505-93B8-55A6AF881233";
	
	/**
	 * 序列小组的领导
	 */
	public static final String SERIAL_USER_TYPE_0="0";
	
	/**
	 * 序列小组成员
	 */
	public static final String SERIAL_USER_TYPE_1="1";
	
	
	/**
	 * 流程key 测评表
	 */
	public static final String FLOW_CPB = "cpb";
	
	/**
	 * 流程key 申请
	 */
	public static final String FLOW_APPLY = "apply";
	
	/**
	 * 性别字段kind
	 */
	public static final String DIC_KIND_XB="xb";
	
	/**
	 * 婚姻状况kind
	 */
	public static final String DIC_KIND_HYZK="hyzk";
	
	/**
	 * 民族kind
	 */
	public static final String DIC_KIND_MZ="mz";
	
	/**
	 * 申请类别kind
	 */
	public static final String DIC_KIND_SQLX="sqlx";
	
	
	/**
	 * 序列kind
	 */
	public static final String DIC_KIND_SBXL="sbxl";
	
	/**
	 * 申报级别kind
	 */
	public static final String DIC_KIND_SBJB="sbjb";
	
	/**
	 * 常用语
	 */
	public static final String DIC_KIND_CYY="cyy";
	
}

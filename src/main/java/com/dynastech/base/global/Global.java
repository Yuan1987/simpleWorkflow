package com.dynastech.base.global;

/**
 * 全局枚举变量
 * 
 * @author yuan
 *
 */
public enum Global {
	lANG_ZH_CN("zh_CN"),
	lANG_EN_US("en_US"),
	USER_ANONYMITY("anonymity"),
	ERROR_LOGIN_UNKNOWN_ACCOUNT("error.login.unknownAccount"),
	ERROR_LOGIN_INVALID_TOKEN("error.login.invalid.token"), 
	ERROR_LOGIN_INVALID_PASSWORD("error.login.invalid.password"),
	ERROR_LOGIN_NAME_PASSWORD_BLANK("error.login.name.password.blank"),
	ERROR_LOGIN_EXCESSIVE_ATTEMPTS("error.login.excessive.attempts"),
	ERROR_LOGIN_LOCK("error.login.account.lock"),
	ERROR_401("error.401"),
	ERROR_404("error.404"),
	ERROR_503("error.503"),
	ERROR_400("error.400"),
	ERROR_LOGIN_OTHER("error.login.other"),
	UPLOAD_FILE_IO_ERROR("upload.file.io.error"),
	UPLOAD_FILE_OTHER_ERROR("upload.file.other.error"),
	ACTION_POSTFIX(""),
	;

	// 成员变量
	private String name;

	// 构造方法
	private Global(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.name;
	}
}

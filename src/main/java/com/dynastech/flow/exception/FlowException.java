package com.dynastech.flow.exception;

/**
 * 定一个公共异常
 * 
 * @author yuan
 *
 */
public class FlowException extends Exception {
	
	private static final long serialVersionUID = -8539710117456150938L;

	protected int code;

	protected String errorMsg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public FlowException() {
		super();
	}

	public FlowException(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public FlowException(int code, String errorMsg) {
		this.code = code;
		this.errorMsg = errorMsg;
	}

	public FlowException(Throwable cause) {
		super(cause);
	}

}

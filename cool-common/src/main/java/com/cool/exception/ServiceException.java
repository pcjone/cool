package com.cool.exception;

import com.cool.common.HttpCode;

/**
 * Service层公用的Exception.
 * 
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 
 * @author wangj
 * 2013-5-17
 */
public class ServiceException extends BaseException {

	private static final long serialVersionUID = 3583566093089790852L;

	private String message;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		this.message = message;
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(cause);
		this.message = message;
	}

	/**
	 * Getter method for property <tt>message</tt>.
	 * 
	 * @return property value of message
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/** 
	 * @see com.innotek.core.support.exception.BaseException#getHttpCode()
	 */
	@Override
	protected HttpCode getHttpCode() {
		// TODO Auto-generated method stub
		return null;
	}
}
/*
 * @(#)OpenClientAuthenticationException.java Created on 2018年12月17日
 *
 *================================================================
 *
 * (c) Copyright COSCON IT. 2017. All rights reserved.
 * 上海中远资讯科技股份有限公司版权所有版权所有.
 * www.cosconit.com
 */
package com.coscon.oaclient.pure;

/**
 * Openclient
 * 
 * @author <a href="mailto:chenjp2@coscon.com">Chen Jipeng</a>
 */
public class OpenClientSecurityException extends Exception {
	private static final long serialVersionUID = 8231819681445578340L;

	public OpenClientSecurityException() {
	}

	public OpenClientSecurityException(String message) {
		super(message);
	}

	public OpenClientSecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public OpenClientSecurityException(Throwable cause) {
		super(cause);
	}
}

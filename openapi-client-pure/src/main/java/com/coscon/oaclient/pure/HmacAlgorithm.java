/*
 * @(#)HmacAlgorithm.java Created on 2018年10月31日
 *
 *================================================================
 *
 * (c) Copyright COSCON IT. 2017. All rights reserved.
 * 上海中远资讯科技股份有限公司版权所有版权所有.
 * www.cosconit.com
 */
package com.coscon.oaclient.pure;

/**
 * Supported hmac-algorithm.
 * 
 * @author <a href="mailto:chenjp2@coscon.com">Chen Jipeng</a>
 */
public enum HmacAlgorithm {
	/**
	 * Represents HmacSHA1
	 */
	HMAC_SHA1("HmacSHA1"),
	/**
	 * Represents HmacSHA256
	 */
	HMAC_SHA256("HmacSHA256"),
	/**
	 * Represents HmacSHA384
	 */
	HMAC_SHA384("HmacSHA384"),
	/**
	 * Represents HmacSHA512
	 */
	HMAC_SHA512("HmacSHA512");
	/**
	 * Return supported hmac-algorithm.
	 * 
	 * @param algo
	 * @return value of HmacAlgorithm, return <tt>null</null> if the algorithm
	 *         is not supported.
	 * 
	 */
	public static HmacAlgorithm validate(String algo) {
		for (HmacAlgorithm e : values()) {
			if (e.getAlgorithm().equals(algo)) {
				return e;
			}
		}
		return null;
	}

	private String algorithm;

	private HmacAlgorithm(String algo) {
		this.algorithm = algo;
	}

	/**
	 * @return the algorithm
	 */
	public String getAlgorithm() {
		return algorithm;
	}
}

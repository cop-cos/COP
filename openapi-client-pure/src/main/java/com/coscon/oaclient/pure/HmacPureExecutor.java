/*
 * @(#)PureHmacAdvisor.java Created on 2018年12月17日
 *
 *================================================================
 *
 * (c) Copyright COSCON IT. 2017. All rights reserved.
 * 上海中远资讯科技股份有限公司版权所有版权所有.
 * www.cosconit.com
 */
package com.coscon.oaclient.pure;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * Type comment.
 * 
 * @author <a href="mailto:chenjp2@coscon.com">Chen Jipeng</a>
 */
public class HmacPureExecutor {

	public static final String X_DATE = "X-Coscon-Date";
	public static final String CONTENT_MD5 = "X-Coscon-Content-Md5";
	public static final String AUTHORIZATION = "X-Coscon-Authorization";
	public static final String DIGEST = "X-Coscon-Digest";

	public static final String REQUEST_LINE = "request-line";

	public String[] HMAC_ADVISOR_KEYS = { X_DATE, CONTENT_MD5, AUTHORIZATION, DIGEST };

	/**
	 * Available HMAC algorithms are: <b>hmac-sha1</b>, <b>hmac-sha256</b>,
	 * <b>hmac-sha384</b>, <b>hmac-sha512</b>.
	 * <p>
	 * For hashing purpose, we choose the minimum cost algorithm 'hmac-sha1'.
	 */
	private static final String DEFAULT_HMAC_ALGORITHM = "HmacSHA1";
	private static final String COSCON_HMAC_HEADER = "X-Coscon-Hmac";

	/**
	 * The hmac-algorithm in hmac-auth mechanism. Default to hmac-sha1
	 */
	private HmacAlgorithm hmacAlgorithm = HmacAlgorithm.validate(DEFAULT_HMAC_ALGORITHM);

	/**
	 * @return the hmacAlgorithm
	 */
	public HmacAlgorithm getHmacAlgorithm() {
		return hmacAlgorithm;
	}

	private String apiKey = null, secretKey = null;

	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @param apiKey
	 *            the apiKey to set
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @param secretKey
	 *            the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * date formatter follows API Gateway rule.
	 */
	private static final String X_DATE_FORMATTER = "EEE, dd MMM yyyy HH:mm:ss z";

	/**
	 * Internal representation of HmacAlgorithm
	 * 
	 * @param target
	 *            {@link HmacAlgorithm}
	 * @return internal representation which sent to API Gateway.
	 */
	protected String getInternalHmacAlgorithm() {
		switch (getHmacAlgorithm()) {
		case HMAC_SHA1:
			return "hmac-sha1";
		case HMAC_SHA256:
			return "hmac-sha256";
		case HMAC_SHA384:
			return "hmac-sha384";
		case HMAC_SHA512:
			return "hmac-sha512";
		default:
			return "hmac-sha1";
		}

	}

	/**
	 * Builds HMAC authentication key-value pairs.
	 * 
	 * @param requestLine 
	 * @param httpContent
	 *            content before perform content gzip/deflate.
	 * @return hmac k/v pairs.
	 * @throws OpenClientSecurityException
	 */
	public Map<String, String> buildHmacKeys(String requestLine, byte[] httpContent)
			throws OpenClientSecurityException {
		StringBuffer buf = new StringBuffer();
		String guid = UUID.randomUUID().toString();
		String date = "", encodedSignature = "", hmacAuth = "", digest = "", guidMd5 = DigestUtils.md5Hex(guid);

		date = DateFormatUtils.format(new Date(), X_DATE_FORMATTER, TimeZone.getTimeZone("GMT"), Locale.US);

		/**
		 * MUST be 'SHA-256' encryption.
		 */
		digest = "SHA-256=" + Base64.encodeBase64String(DigestUtils.sha256(httpContent));

		buf.setLength(0);
		buf.append(X_DATE).append(": ").append(date);
		buf.append("\n").append(DIGEST).append(": ").append(digest);
		buf.append("\n").append(CONTENT_MD5).append(": ").append(guidMd5);
		buf.append("\n").append(requestLine);
		encodedSignature = Base64.encodeBase64String(
				HmacShaUtil.signature(getHmacAlgorithm().getAlgorithm(), buf.toString(), getSecretKey()));
		buf.setLength(0);
		buf.append("hmac username=\"").append(getApiKey())
				.append("\",algorithm=\"" + getInternalHmacAlgorithm() + "\",headers=\"").append(X_DATE).append(" ")
				.append(DIGEST).append(" ").append(CONTENT_MD5).append(" ").append(REQUEST_LINE)
				.append("\",signature=\"").append(encodedSignature).append("\"");

		hmacAuth = buf.toString();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put(X_DATE, date);
		headers.put(X_DATE, date);
		headers.put(DIGEST, digest);
		headers.put(CONTENT_MD5, guidMd5);
		headers.put(AUTHORIZATION, hmacAuth);
		headers.put(COSCON_HMAC_HEADER, guidMd5);
		return headers;
	}
}

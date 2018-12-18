/*
 * @(#)HmacSha1Util.java Created on 2018年10月23日
 *
 *================================================================
 *
 * @copy.right.declaration@
 *
 *================================================================
 */
package com.coscon.oaclient.pure;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

/**
 * Hmac utility.
 * 
 * @author <a href="mailto:chenjp2@coscon.com">Chen Jipeng</a>
 * @emailto ch_jp@msn.com
 */
public class HmacShaUtil {
	/**
	 * Create a signature for specific data.
	 * 
	 * @param hmacAlgorithm
	 * @param data
	 *            source string data
	 * @param key
	 *            the secretkey
	 * @return bytes represents the hashing signature result.
	 * @throws OpenClientSecurityException
	 */
	public static byte[] signature(String hmacAlgorithm, String data, String key) throws OpenClientSecurityException {
		try {
			HmacAlgorithm algo = HmacAlgorithm.validate(hmacAlgorithm);
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), algo.getAlgorithm());
			Mac mac = Mac.getInstance(algo.getAlgorithm());
			mac.init(signingKey);
			return mac.doFinal(data.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new OpenClientSecurityException(e);
		} catch (InvalidKeyException e) {
			throw new OpenClientSecurityException(e);
		}
	}

	/**
	 * Create a signature for specific data.
	 * 
	 * @param hmacAlgorithm
	 * @param data
	 *            source string data
	 * @param key
	 *            the secret key
	 * @return hexString represents the hashing signature result.
	 * @throws OpenClientSecurityException
	 */
	public static String signatureHex(String hmacAlgorithm, String data, String key)
			throws OpenClientSecurityException, InvalidKeyException {
		return Hex.encodeHexString(signature(hmacAlgorithm, data, key));
	}
}

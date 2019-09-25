/*
 * @(#)AbstractOpenapiTestcase.java Created on 2018年12月24日
 *
 *================================================================
 *
 * (c) Copyright COSCON IT. 2017. All rights reserved.
 * 上海中远资讯科技股份有限公司版权所有版权所有.
 * www.cosconit.com
 */
package com.coscon.openapi.client.httpclient;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;

import com.coscon.oaclient.pure.HmacPureExecutor;
import com.coscon.oaclient.pure.OpenClientSecurityException;

import junit.framework.TestCase;

/**
 * Type comment.
 * 
 * @author <a href="mailto:chenjp2@coscon.com">Chen Jipeng</a>
 */
public class AbstractOpenapiTestcase extends TestCase {
	private HmacPureExecutor hmacPureExecutor = null;

	public static final String OPENAPI_URL_BASE_PROD="https://api.lines.coscoshipping.com/service";
	
	public String getOpenapiBaseUri() {
		return OPENAPI_URL_BASE_PROD;
	}
	/**
	 * @return the hmacPureExecutor
	 */
	public HmacPureExecutor getHmacPureExecutor() {
		return hmacPureExecutor;
	}
	
	private String[] hostPatterns = {"api.lines.coscoshipping.com"};
	private List<CloseableHttpClient> clients = new Vector<CloseableHttpClient>();

	public boolean match(HttpRequest request, String[] patterns) {
		if (patterns == null || patterns.length == 0) {
			return true;
		}
		Header[] hostHeaders = request.getHeaders(HttpHeaders.HOST);
		if (hostHeaders == null || hostHeaders.length == 0) {
			return false;
		}
		String target = hostHeaders[0].getValue();
		if (StringUtils.isBlank(target)) {
			return true;
		}
		if (target.contains(":")) {
			target = target.substring(0, target.indexOf(":"));
		}
		target = target.toUpperCase();
		for (String pattern : patterns) {
			if (StringUtils.isBlank(pattern)) {
				continue;
			}
			pattern = pattern.toUpperCase();
			if ("*".equals(pattern)) {
				return true;
			} else {
				if (pattern.endsWith("*")) {
					if (target.startsWith(pattern.substring(0, pattern.length() - 1))) {
						return true;
					}
				} else if (pattern.startsWith("*")) {
					if (target.endsWith(pattern.substring(1, pattern.length()))) {
						return true;
					}
				} else if (pattern.equals(target)) {
					return true;
				}
			}
		}
		/**
		 * None of patterns match.
		 */
		return false;
	}
	/**
	 * @return the client
	 */
	public CloseableHttpClient createClient() {
		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.addInterceptorLast(new HttpRequestInterceptor() {

			@Override
			public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
				if(!match(request, hostPatterns)) {
					return;
				}
				byte[] httpContent = new byte[0];
				if (request instanceof HttpEntityEnclosingRequest) {
					HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
					if(entity != null) {
						httpContent = IOUtils.toByteArray(entity.getContent());
					}					
				}
				try {
					Map<String, String> headers = getHmacPureExecutor().buildHmacHeaders(request.getRequestLine().toString(), httpContent);
					if(headers!=null) {
						for(Entry<String, String> e:headers.entrySet()) {
							request.addHeader(e.getKey(), e.getValue());
						}
					}
				} catch (OpenClientSecurityException e) {
					e.printStackTrace();
				}
			}
		});
		CloseableHttpClient client = builder.build();
		clients.add(client);
		return client;
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		hmacPureExecutor = new HmacPureExecutor();
		hmacPureExecutor.setApiKey("YOUR_APK_KEY");
		hmacPureExecutor.setSecretKey("YOUR_SECRET_KEY");
		
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		for (CloseableHttpClient client : clients) {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
//	
//	private static final class HmacHttpSecurityRequestLine implements RequestLine {
//		private RequestLine source = null;
//
//		/**
//		 * Default constructor.
//		 * 
//		 * @param source
//		 *            the original {@link RequestLine} instance.
//		 */
//		public HmacHttpSecurityRequestLine(RequestLine source) {
//			this.source = source;
//		}
//
//		@Override
//		public String getMethod() {
//			return source.getMethod();
//		}
//
//		@Override
//		public ProtocolVersion getProtocolVersion() {
//			return source.getProtocolVersion();
//		}
//
//		@Override
//		public String getUri() {
//			URI uri = URI.create(source.getUri());
//			StringBuffer sb = new StringBuffer();
//			if (uri.getPath() != null) {
//				sb.append(uri.getRawPath());
//			} else {
//				sb.append("/");
//			}
//			if (uri.getQuery() != null) {
//				sb.append('?');
//				sb.append(uri.getRawQuery());
//			}
//			return sb.toString();
//		}
//
//		@Override
//		public String toString() {
//			return BasicLineFormatter.INSTANCE.formatRequestLine(null, this).toString();
//		}
//	}
}

/*
 * @(#)CargoTrackingTestcase.java Created on 2018年12月24日
 *
 *================================================================
 *
 * (c) Copyright COSCON IT. 2017. All rights reserved.
 * 上海中远资讯科技股份有限公司版权所有版权所有.
 * www.cosconit.com
 */
package com.coscon.openapi.client.httpclient;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Type comment.
 * @author <a href="mailto:chenjp2@coscon.com">Chen Jipeng</a>
 */
public class CargoTrackingTestcase extends AbstractOpenapiTestcase {

	public void testTrackByBlRef() throws ClientProtocolException, IOException {
		try (CloseableHttpClient client = createClient()) {
			HttpResponse response = client.execute(new HttpGet(getOpenapiBaseUri()+"/info/tracking/6103622780?numberType=bl"));
			String responseText=EntityUtils.toString(response.getEntity());
			assertEquals(200, response.getStatusLine().getStatusCode());
			assertTrue("应包含：内陆转运号V2167699827",responseText.contains("V2167699827"));
			assertTrue("应包含：箱号CBHU4398907",responseText.contains("CBHU4398907"));
		}
	}
	public void testTrackByBkgRef() throws ClientProtocolException, IOException {
		try (CloseableHttpClient client = createClient()) {
			HttpResponse response = client.execute(new HttpGet(getOpenapiBaseUri()+"/info/tracking/6103622780?numberType=bkg"));
			String responseText=EntityUtils.toString(response.getEntity());
			assertEquals(200, response.getStatusLine().getStatusCode());
			assertTrue("应包含：内陆转运号V2167699827",responseText.contains("V2167699827"));
			assertTrue("应包含：箱号CBHU4398907",responseText.contains("CBHU4398907"));
		}
	}
	public void testTrackByCntrRef() throws ClientProtocolException, IOException {
		try (CloseableHttpClient client = createClient()) {
			HttpResponse response = client.execute(new HttpGet(getOpenapiBaseUri()+"/info/tracking/CBHU4398907?numberType=cntr"));
			String responseText=EntityUtils.toString(response.getEntity());
			assertEquals(200, response.getStatusLine().getStatusCode());
			assertTrue("应包含：箱号CBHU4398907",responseText.contains("CBHU4398907"));
		}
	}
}

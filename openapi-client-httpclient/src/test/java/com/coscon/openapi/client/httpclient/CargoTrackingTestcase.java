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

/**
 * Type comment.
 * @author <a href="mailto:chenjp2@coscon.com">Chen Jipeng</a>
 */
public class CargoTrackingTestcase extends AbstractOpenapiTestcase {

	public void testTrackByBlRef() throws ClientProtocolException, IOException {
		HttpClient client = createClient();
		HttpResponse response = client.execute(new HttpGet(getOpenapiBaseUri()+"/info/tracking/6103622780?numberType=bl"));
		assertEquals(200, response.getStatusLine().getStatusCode());
		
	}
}

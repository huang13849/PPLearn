package com.hqb.pplearn.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebUtil {
	private static final String CHARSET_UTF_8 = "utf-8";
	private static final String HTTP_CONTENT_TYPE_JSON = "application/json";
	private static final String HTTP_HEAD_KEY_CONTENT_TYPE = "content-type";
	private static Logger LOG = LoggerFactory.getLogger(WebUtil.class);

	public static HttpRequestResult sendHttpJsonPostRequest(String url, Map<String, Object> parameters) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		try {
			post.setHeader(new BasicHeader(HTTP_HEAD_KEY_CONTENT_TYPE, HTTP_CONTENT_TYPE_JSON));
			String jsonString = JsonUtil.map2json(parameters);
			StringEntity stringEntity = new StringEntity(jsonString, CHARSET_UTF_8);
			post.setEntity(stringEntity);
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			int httpResult = response.getStatusLine().getStatusCode();
			String jsonValue = readContentFromResponse(entity);
			LOG.debug("json_str: ");
			LOG.debug(jsonValue);
			if (httpResult != 200) {
				LOG.error("Nofification message send failed. http return statue code: " + httpResult);
				return new HttpRequestResult(entity, false, jsonValue);
			} else {
				return new HttpRequestResult(entity, true, jsonValue);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return new HttpRequestResult(null, false, null);
		}
	}

	public static HttpRequestResult sendHttpJsonGetRequest(String url, Map<String, Object> parameters) {
		HttpClient client = new DefaultHttpClient();
		StringBuilder sbUrl = new StringBuilder(url);
		if (StringUtils.contains(url, "&")) {
			sbUrl.append("&");
		}
		sbUrl.append("timeStamp=" + System.currentTimeMillis());
		if (MapUtils.isNotEmpty(parameters)) {
			for (String key : parameters.keySet()) {

				Object parameterVal = parameters.get(key);
				String parameterEncodeValue = StringUtils.EMPTY;
				try {
					parameterEncodeValue = parameterVal == null ? StringUtils.EMPTY : URLEncoder.encode(parameterVal.toString(), CHARSET_UTF_8);
				} catch (UnsupportedEncodingException e) {
					LOG.debug(e.getMessage(), e);
					parameterEncodeValue = StringUtils.EMPTY;
				}
				sbUrl.append("&" + key + "=" + parameterEncodeValue);
			}
		}
		LOG.info("Going to call http get request, url:");
		LOG.info(sbUrl.toString());
		HttpGet get = new HttpGet(sbUrl.toString());
		try {
			// post.setHeader(new BasicHeader(HTTP_HEAD_KEY_CONTENT_TYPE,
			// HTTP_CONTENT_TYPE_JSON));
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			int httpResult = response.getStatusLine().getStatusCode();
			String jsonValue = readContentFromResponse(entity);
			LOG.debug("json_str: ");
			LOG.debug(jsonValue);
			if (httpResult != 200) {
				LOG.error("Nofification message send failed. http return statue code: " + httpResult);
				return new HttpRequestResult(entity, false, jsonValue);
			} else {
				return new HttpRequestResult(entity, true, jsonValue);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return new HttpRequestResult(null, false, null);
		}
	}

	private static String readContentFromResponse(HttpEntity entity) throws IOException {
		String jsonValue = null;
		if (entity != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
			StringBuilder builder = new StringBuilder();
			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				builder.append(s);
			}
			jsonValue = builder.toString();
		}
		return jsonValue;
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		String url = "http://sms.bechtech.cn/Api/send/data/json?accesskey=2825&secretkey=27a94899ba8f29ac16816cf68e98a934a0aeec07&timeStamp=1408252526253&content=提醒您，  叶英凯测试  已向你发送通知，详情请查看：  http://222.77.181.175:8080/test-rainbow/system/appDownLoad.do?requestCode=hyxx56wu  【彩虹医生】&mobile=135109021801";
		String finalUrl = URLEncoder.encode(url);
		System.out.println(finalUrl);
	}
}

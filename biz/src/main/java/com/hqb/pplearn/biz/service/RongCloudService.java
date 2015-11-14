package com.hqb.pplearn.biz.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hqb.pplearn.biz.dao.UserRepository;
import com.hqb.pplearn.biz.model.User;
import com.hqb.pplearn.common.util.JsonUtil;

@Service
public class RongCloudService {
	private static final int MAX_RE_CONNECT_TIMES = 30;
	private Logger LOG = LoggerFactory.getLogger(RongCloudService.class);
	@Value("${rong.cloud.auth.url}")
	private String authUrlString;

	@Value("${rong.cloud.app.key}")
	private String appKey;

	@Value("${rong.cloud.app.secret}")
	private String appSecret;

	@Autowired
	private UserRepository userRepository;

	private URL authUrl;

	public URL getAuthUrl() {
		if (authUrl == null) {
			try {
				authUrl = new URL(authUrlString);
			} catch (MalformedURLException e) {
				LOG.error(e.getMessage(), e);
			}
		}
		return authUrl;
	}

	public String auth(String userId, String name, String portraitUri) {
		String id = StringUtils.isNotBlank(userId) ? StringUtils.replace(userId, "-", "") : StringUtils.EMPTY;
		int triedTimes = 0;
		while (triedTimes < MAX_RE_CONNECT_TIMES) {
			String rongCloundResponse = getTokenFromRongClound(id, name, portraitUri);
			if (StringUtils.isNotBlank(rongCloundResponse)) {
				Object objResponse = JsonUtil.json2Object(rongCloundResponse, RongCloundAuthResponse.class);
				if (objResponse instanceof RongCloundAuthResponse) {
					return ((RongCloundAuthResponse) objResponse).getToken();
				}
			} else {
				triedTimes++;
			}
		}

		return StringUtils.EMPTY;
	}

	private String getTokenFromRongClound(String userId, String name, String portraitUri) {
		StringBuilder retSb = null;
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) getAuthUrl().openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			HttpURLConnection.setFollowRedirects(true);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("appKey", appKey);
			conn.setRequestProperty("appSecret", appSecret);
			conn.setRequestProperty("Content-Type", "Application/x-www-form-urlencoded");
			StringBuilder sb = new StringBuilder("userId=");
			sb.append(URLEncoder.encode(userId, "UTF-8"));
			sb.append("&name=").append(URLEncoder.encode(name, "UTF-8"));
			sb.append("&portraitUri=").append(URLEncoder.encode(portraitUri, "UTF-8"));

			OutputStream out = conn.getOutputStream();
			out.write(sb.toString().getBytes("UTF-8"));
			out.flush();
			out.close();

			if (conn.getResponseCode() == 200) {
				LOG.info("RongCloud Get token statuus  200=======================");
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				retSb = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					retSb.append(line);
				}
				reader.close();
				LOG.info(retSb.toString());
			} else {
				LOG.error("RongCloud Cannot Get tokentoken=======================");
				LOG.error(conn.toString());
				LOG.error("ResponseMessage: " + conn.getResponseMessage());
				LOG.error("ResponseCode: " + conn.getResponseCode());
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					retSb = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						retSb.append(line);
					}
					reader.close();
					LOG.error("Response Body: ");
					LOG.error(retSb.toString());
				} catch (Exception ex) {
				}
				LOG.error("=======================RongCloud Cannot Get token");
				return null;
			}
		} catch (Exception ignore) {
			LOG.error("融云获取token出错=======================");
			LOG.error("Exception: " + ignore.getMessage());
			LOG.error(ignore.getMessage(), ignore);
			LOG.error("=======================融云获取token出错");
		} finally {
			try {
				conn.disconnect();
			} catch (Exception ignore) {
				LOG.error(ignore.getMessage(), ignore);
			}
		}
		return retSb == null ? StringUtils.EMPTY : retSb.toString();
	}

	public String getAuthUrlString() {
		return authUrlString;
	}

	public void setAuthUrlString(String authUrlString) {
		this.authUrlString = authUrlString;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public static void main(String[] args) {
		testRongCloudTokenCaseOne();
	}

	//@SuppressWarnings("unused")
	private static void testRongCloudTokenCaseOne() {
		RongCloudService service = new RongCloudService();
		service.setAppKey("82hegw5uh00ax");
		service.setAppSecret("PFRlOBFDam");
		service.setAuthUrlString("http://api.cn.rong.io/user/getToken.json");
		System.out.println(service.auth("xiaoming", "Xiao Ming", "abc.jpg"));
	}

	@SuppressWarnings("unused")
    private static void testRongCloudTokenCaseTwo() {
		RongCloudService service = new RongCloudService();
		service.setAppKey("pwe86ga5ereq6");
		service.setAppSecret("D97fj2PhruDc");
		service.setAuthUrlString("http://api.cn.rong.io/user/getToken.json");
		String token = service.auth("3e3af18d-8d9b-48ba-aff9-097c766067fc", "Huan Huan", "app.url/images/patient/photo/3e3af18d-8d9b-48ba-aff9-097c766067fc.jpg");
		if (StringUtils.isBlank(token)) {
			System.out.println("null");
		} else {
			System.out.println(token);
		}
	}

	public String getRongCloudToken(String userEmail) {
		List<User> user = userRepository.findByEmail(userEmail);
		if (CollectionUtils.isNotEmpty(user)) {
			return getTokenFromRongClound(user.get(0).getId(), user.get(0).getUserAccount(), user.get(0).getHeadImageUrl());
		} else {
			throw new RuntimeException("Cannot find user by email" + userEmail);
		}
	}
}

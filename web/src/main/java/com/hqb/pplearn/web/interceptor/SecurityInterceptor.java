package com.hqb.pplearn.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SecurityInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");

	private List<String> needLogUrls;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		long beginTime = System.currentTimeMillis();
		startTimeThreadLocal.set(beginTime);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		long endTime = System.currentTimeMillis();
		long beginTime = startTimeThreadLocal.get();

		long consumeTime = endTime - beginTime;
		if (consumeTime > 500) {
			logger.info(String.format("%s consume %d millis", request.getRequestURI(), consumeTime));
		}

		if (isNeedLogUri(request.getRequestURI())) {
			
		}
	}


	private boolean isNeedLogUri(String requestUri) {
		if (CollectionUtils.isNotEmpty(needLogUrls)) {
			for (String url : needLogUrls) {
				if (StringUtils.containsIgnoreCase(requestUri, url)) {
					return true;
				}
			}
		}
		return false;
	}

	public List<String> getNeedLogUrls() {
		return needLogUrls;
	}

	public void setNeedLogUrls(List<String> needLogUrls) {
		this.needLogUrls = needLogUrls;
	}
}
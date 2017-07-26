package com.cool.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SysEventService {

	void saveEvent(HttpServletRequest request, HttpServletResponse response, Exception ex, Long startTime,
			Long endTime);
}

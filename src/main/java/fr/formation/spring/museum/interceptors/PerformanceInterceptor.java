package fr.formation.spring.museum.interceptors;

import java.sql.Date;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import fr.formation.spring.museum.models.AccessLog;
import fr.formation.spring.museum.repositories.AccessLogRepository;

// A shitty implementation of performance profiling.
@Component
public class PerformanceInterceptor extends HandlerInterceptorAdapter {
	protected final static Log logger = LogFactory.getLog(PerformanceInterceptor.class);
	
	private long _initialTime;
	private long _handleTime;
	
	@Autowired
	private AccessLogRepository logRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		_initialTime = System.nanoTime() / 1000;
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		_handleTime = System.nanoTime() / 1000;
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, 
			Exception ex) throws Exception {

		long renderTime = System.nanoTime() / 1000;
		float handlerDuration = (float)(_handleTime - _initialTime) / 1000;
		float renderDuration = (float)(renderTime - _handleTime) / 1000;
		
		logger.info("[" + getRemoteAddr(request) + "] '" + request.getRequestURI()
			+ "' " + handlerDuration + "/" + renderDuration + " ms.");
		
//		AccessLog logEntry = new AccessLog();
//		logEntry.setRequestURI(request.getRequestURI());
//		logEntry.setRemoteAddress(getRemoteAddr(request));
//		logEntry.setHandlingTime(handlerDuration);
//		logEntry.setRenderingTime(renderDuration);
//		logEntry.setDate(Date.from(Instant.now()));
		
		// logRepository.save(logEntry);

		super.afterCompletion(request, response, handler, ex);
	}
	
	private static String getRemoteAddr(HttpServletRequest request) {
	    String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
	    if (ipFromHeader != null && ipFromHeader.length() > 0) {
	        return ipFromHeader;
	    }
	    return request.getRemoteAddr();
	}
}

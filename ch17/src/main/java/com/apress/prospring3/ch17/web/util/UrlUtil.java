package com.apress.prospring3.ch17.web.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

/**
 * 리다이렉트에 필요한 URL 인코딩을 하는 유틸리티 클래스
 * @author hjkim
 *
 */
public class UrlUtil {

	public static String encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
		String enc = request.getCharacterEncoding();
		
		if (enc == null) {
			enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
		}
		
		try {
			pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
		} catch (UnsupportedEncodingException e) {
		}
		
		return pathSegment;
	}
	
}

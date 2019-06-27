package com.totvs.template.Security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SecurityFilterController {

	@RequestMapping(value = "/**/{[path:[^\\.]*}")
	public String index(final HttpServletRequest request, HttpServletResponse response) throws IOException {
	    final String url = request.getRequestURI();

	    if (!url.startsWith("/api")) {
			response.sendRedirect("index.html");
		}
	    
	    response.setStatus(404);
	    return null;

	}
}

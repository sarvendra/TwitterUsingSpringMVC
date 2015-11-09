package com.directi.rest.filter;

import com.directi.rest.model.Token;
import com.directi.rest.service.AuthenticationService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;


public final class TokenAuthenticationFilter extends GenericFilterBean {
	private static final String HEADER_TOKEN = "X-Auth-Token";
	private static final String HEADER_USERNAME = "X-Username";
	private static final String HEADER_PASSWORD = "X-Password";

	//	private final String logoutLink;
	private final AuthenticationService authenticationService;

	public TokenAuthenticationFilter(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println(" *** MyAuthenticationFilter.doFilter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		boolean authenticated = checkToken(httpRequest, httpResponse);
		if (!authenticated) {
			// Login works just fine even when we provide token that is valid up to this request, because then we get a new one.
			checkLogin(httpRequest, httpResponse);
		}

		chain.doFilter(request, response);
		System.out.println(" === AUTHENTICATION: " + SecurityContextHolder.getContext().getAuthentication());
	}

	private void checkLogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		String authorization = httpRequest.getHeader("Authorization");
		String username = httpRequest.getHeader(HEADER_USERNAME);
		String password = httpRequest.getHeader(HEADER_PASSWORD);

		if (authorization != null) {
			checkBasicAuthorization(authorization, httpResponse);
		} else if (username != null && password != null) {
			checkUsernameAndPassword(username, password, httpResponse);
		}
	}

	private void checkBasicAuthorization(String authorization, HttpServletResponse httpResponse) throws IOException {
		StringTokenizer tokenizer = new StringTokenizer(authorization);
		if (tokenizer.countTokens() < 2) {
			return;
		}
		if (!tokenizer.nextToken().equalsIgnoreCase("Basic")) {
			return;
		}

		String base64 = tokenizer.nextToken();
		String loginPassword = new String(Base64.decode(base64.getBytes(StandardCharsets.UTF_8)));

		System.out.println("loginPassword = " + loginPassword);
		tokenizer = new StringTokenizer(loginPassword, ":");
		System.out.println("tokenizer = " + tokenizer);
		checkUsernameAndPassword(tokenizer.nextToken(), tokenizer.nextToken(), httpResponse);
	}

	private void checkUsernameAndPassword(String username, String password, HttpServletResponse httpResponse) throws IOException {
		Token tokenInfo = authenticationService.authenticate(username, password);
		if (tokenInfo != null) {
			httpResponse.setHeader(HEADER_TOKEN, tokenInfo.getToken());
			// TODO set other token information possible: IP, ...
		} else {
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	/**
	 * Returns true, if request contains valid authentication token.
	 */
	private boolean checkToken(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		String token = httpRequest.getHeader(HEADER_TOKEN);
		if (token == null) {
			return false;
		}

		if (authenticationService.checkToken(token)) {
			System.out.println(" *** " + HEADER_TOKEN + " valid for: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return true;
		} else {
			System.out.println(" *** Invalid " + HEADER_TOKEN + ' ' + token);
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		return false;
	}
}

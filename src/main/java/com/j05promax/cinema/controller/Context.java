package com.j05promax.cinema.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.interfaces.Claim;

import org.yaml.snakeyaml.util.UriEncoder;


public class Context {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected String UserID;
	protected String UserEmail;
	protected boolean SignedIn = false;

	public Context LoadFromClaim(Map<String, ?> claim) {
		Map<String, ?> details = ((Claim) claim.get(Midleware.TokenDetailsKey)).asMap();
		this.UserID = (String) details.get("user-id");
		this.UserID = (String) details.get("user-email");
		return this;
	}

	public Map<String, Map<String, String>> ToClaim() {
		return Map.of(
				Midleware.TokenDetailsKey,
				Map.of(
					"user-id", this.UserID,
					"user-email", this.UserEmail
				)
			);
	}
	
	public Cookie getCookie(String name) {
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}

		return new Cookie(name, null);
	}

	public Cookie SetUnicodeCookie(String name, String value, String path) {
		Cookie cookie = this.getCookie(name);
		cookie.setValue(UriEncoder.encode(value));
		cookie.setPath(path);
		response.addCookie(cookie);
		return cookie;
	}
}
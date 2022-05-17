package com.j05promax.cinema.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.j05promax.cinema.util.common.Common;
import com.j05promax.cinema.util.log.Log;

class Context {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected String UserID;
	protected boolean SignedIn = false;

	public Context LoadFromClaim(Map<String, ?> claim) {
		Map<String, ?> details = ((Claim) claim.get(Midleware.TokenDetailsKey)).asMap();
		this.UserID = (String) details.get("userID");
		System.out.println(this.UserID);
		return this;
	}

	public Map<String, Map<String, String>> ToClaim() {
		return Map.of(Midleware.TokenDetailsKey, Map.of("userID", this.UserID));
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

}

public class Midleware {
	private static String authKey = "_auth_token";
	protected static String TokenDetailsKey = "tdk";

	public static Context Authenticate(Context ctx) {
		Cookie cookie = ctx.getCookie(authKey);
		if (cookie != null) {
			String token = cookie.getValue();
			Common cm = Common.getInstance();
			Map<String, ?> claim = null;

			try {
				claim = cm.JWT.Decode(token);
			} catch (Exception e) {
				new Log(e).Show();
			}

			if (claim == null) {
				return ctx;
			}

			ctx.LoadFromClaim(claim);
			ctx.SignedIn = true;
			return ctx;
		}

		return ctx;
	}

	public static void SignInToken(Context ctx, String userID) {
		ctx.UserID = userID;
		Common cm = Common.getInstance();
		Cookie cookie = ctx.getCookie(authKey);

		cookie.setMaxAge(Common.TokenExpired);
		String token = "";

		try {
			token = cm.JWT.Encode(ctx.ToClaim());
		} catch (JWTCreationException e) {
			e.printStackTrace();
		}

		cookie.setValue(token);
		cookie.setPath("/");
		ctx.response.addCookie(cookie);
	}
}

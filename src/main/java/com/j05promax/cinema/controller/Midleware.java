package com.j05promax.cinema.controller;

import java.util.Map;

import javax.servlet.http.Cookie;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.j05promax.cinema.entity.Admin;
import com.j05promax.cinema.util.common.Common;
import com.j05promax.cinema.util.log.Log;


public class Midleware {
	private static String authKey = "_auth_token";
	protected static String TokenDetailsKey = "tdk";

	public static boolean IsSignedIn(Context ctx) {
		return ctx.SignedIn;
	}

	public static boolean IsManager(Context ctx) {
		return ctx.UserGroup.contentEquals(Admin.Manager);
	}

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
				return ctx;
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

	public static void SignOutToken(Context ctx) {
		Cookie authC = ctx.getCookie(authKey);
		authC.setValue("");
		authC.setMaxAge(-1);
		ctx.response.addCookie(authC);
	}

	public static void SignInToken(Context ctx, Admin user) {
		ctx.UserID = user.AdminID;
		ctx.UserEmail = user.Email;
		ctx.UserGroup = user.UserGroup;
		Common cm = Common.getInstance();
		Cookie cookie = ctx.getCookie(authKey);

		cookie.setMaxAge(Common.TokenExpired);
		String token = "";

		try {
			token = cm.JWT.Encode(ctx.ToClaim());
		} catch (JWTCreationException e) {
            new Log(e).Show();
		}

		cookie.setValue(token);
		cookie.setPath("/");
		ctx.response.addCookie(cookie);
	}
}

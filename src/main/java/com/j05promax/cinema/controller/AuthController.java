package com.j05promax.cinema.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j05promax.cinema.entity.Admin;
import com.j05promax.cinema.repo.PostgreSQLRepo;
import com.j05promax.cinema.repo.Repository;
import com.j05promax.cinema.service.Service;
import com.j05promax.cinema.util.common.Common;
import com.j05promax.cinema.util.log.Log;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

	public static Context setError(Context ctx, String message) {
		Cookie error = ctx.getCookie("error");
		String mess = "";
		try {
			mess = URLEncoder.encode(message, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			new Log(e).Show();
		}

		error.setValue(mess);
		error.setPath("/");
		ctx.response.addCookie(error);
		return ctx;
	}

	@GetMapping("/")
	public String Default(
			HttpServletRequest request,
			HttpServletResponse response) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		ctx = Midleware.Authenticate(ctx);

		if (!Midleware.IsSignedIn(ctx)) {
			return "redirect:/auth/login";
		}

		// if (!Midleware.IsManager(ctx)) {
		// 	return "permission-denied";
		// }

		return "redirect:/main-page";
	}

	@GetMapping("/auth/login")
	public String Login(
			HttpServletRequest request,
			HttpServletResponse response) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		ctx = Midleware.Authenticate(ctx);
		if (ctx.SignedIn) {
			return "redirect:/main-page";
		}
		return "login";
	}

	@PostMapping("/auth/auth")
	public String Auth(
			HttpServletRequest request,
			HttpServletResponse response,

			@RequestParam(value = "email", defaultValue = "") String adminEmail,
			@RequestParam(value = "password", defaultValue = "") String password) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		Service service = Service.getInstance();
		PostgreSQLRepo repo = PostgreSQLRepo.getInstance();

		Admin foundedAdmin = null;
		try {
			foundedAdmin = repo.Admin.GetByEmail(adminEmail);
		} catch (SQLException e) {
			AuthController.setError(ctx, "Lỗi Hệ Thống");
			return "redirect:/auth/login";
		}

		if (foundedAdmin == null) {
			AuthController.setError(ctx, "Tài Khoản Đăng Nhập Không Hợp Lệ");
			return "redirect:/auth/login";
		}

		if (foundedAdmin.AdminID.contentEquals("")) {
			AuthController.setError(ctx, "Tài Khoản Đăng Nhập Không Hợp Lệ");
			return "redirect:/auth/login";
		}

		if (service.Admin.CheckCorrectPassword(foundedAdmin, password)) {
			Midleware.SignInToken(ctx, foundedAdmin);
			return "redirect:/main-page";
		} else {
			return "redirect:/auth/login";
		}
	}

	@GetMapping("/auth/forgot-password")
	public String forgotPassword() {
		return "forgot-password";
	}


	@PostMapping("/auth/request-code")
	public String forgotPassword(
			HttpServletRequest request,
			HttpServletResponse response,

			@RequestParam(value = "email", defaultValue = "") String email) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		PostgreSQLRepo repo = PostgreSQLRepo.getInstance();
		Service service = Service.getInstance();

		Admin foundedAdmin = null;
		try {
			foundedAdmin = repo.Admin.GetByEmail(email);
		} catch (SQLException e) {
			return "error";
		}

		// user not found
		if (foundedAdmin == null) {
			AuthController.setError(ctx, "Tài khoản không tìm thấy");
			return "redirect:/auth/login";
		}

		if (!service.Admin.RequestCodeEmail(foundedAdmin)) {
			return "error";
		}

		return "redirect:/";
	}

	@GetMapping("/auth/change-password")
	public String recoverPassword(
			HttpServletRequest request,
			HttpServletResponse response,

			@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "code", defaultValue = "") String code) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		Admin admin = AuthController.authAdminWithEmailCode(email, code);
		if (admin == null) {
			AuthController.setError(ctx, "Mã xác thực hết hạn hoặc không tồn tại");
			return "redirect:/auth/login";
		}

		Cookie emailC = ctx.getCookie("email");
		emailC.setValue(email);
		ctx.response.addCookie(emailC);
		Cookie codeC = ctx.getCookie("code");
		codeC.setValue(code);
		ctx.response.addCookie(codeC);

		// change password
		return "change-password";
	}

	public static Admin authAdminWithEmailCode(String email, String code) {
		PostgreSQLRepo repo = PostgreSQLRepo.getInstance();

		Admin admin = null;
		try {
			admin = repo.Admin.GetByEmail(email);
		} catch (SQLException e) {
			return null;
		}

		if (admin == null) {
			return null;
		}

		boolean validCode = (admin.SecCode.contentEquals(code) &&
				(new Date().getTime() - admin.UpdatedAt.getTime()) / 1000 < Common.CodeExpiredTime);
		if (!validCode) {
			return null;
		}

		return admin;
	}

	@PostMapping("/auth/update-password")
	public String updatePassword(
			HttpServletRequest request,
			HttpServletResponse response,

			@RequestParam(value = "password", defaultValue = "") String password) {

        Context ctx = new Context();
        ctx.request = request;
        ctx.response = response;

		Common cm = Common.getInstance();
		PostgreSQLRepo repo = PostgreSQLRepo.getInstance();

		Cookie tmpC;
		String email = "";
		String code = "";

		tmpC = ctx.getCookie("email");
		email = tmpC.getValue();
		tmpC = ctx.getCookie("code");
		code = tmpC.getValue();

		Admin admin = AuthController.authAdminWithEmailCode(email, code);
		if (admin == null) {
			AuthController.setError(ctx, "Mã xác thực hết hạn hoặc không tồn tại");
			return "redirect:/auth/login";
		}

		admin.Password = cm.Bcrypt.HashPassword(password);
		Repository.Error err = repo.Admin.Update(admin);
		if (err != null) {
			AuthController.setError(ctx, err.message);
		}

		Midleware.SignOutToken(ctx);
		return "redirect:/auth/login";
	}
}

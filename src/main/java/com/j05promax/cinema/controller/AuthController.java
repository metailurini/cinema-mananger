package com.j05promax.cinema.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j05promax.cinema.entity.Admin;
import com.j05promax.cinema.repo.PostgreSQLRepo;
import com.j05promax.cinema.service.Service;

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
			e.printStackTrace();
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
		if (!ctx.SignedIn) {
			return "redirect:/auth/login";
		}
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

		if (foundedAdmin.AdminID.contentEquals("")) {
			AuthController.setError(ctx, "Tài Khoản Đăng Nhập Không Hợp Lệ");
			return "redirect:/auth/login";
		}

		// if (username.contentEquals("admin") && password.contentEquals("admin")) {
		if (service.Admin.CheckCorrectPassword(foundedAdmin, password)) {
			Midleware.SignInToken(ctx, foundedAdmin);
			return "redirect:/main-page";
		} else {
			return "redirect:/auth/login";
		}
	}

}

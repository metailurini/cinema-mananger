package com.j05promax.cinema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j05promax.cinema.util.common.Common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckHealthController {

	@GetMapping("/check_health")
	public String health(
		HttpServletRequest request,
		HttpServletResponse response,
		Model model
	) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		ctx = Midleware.Authenticate(ctx);
		model.addAttribute("signedin", ctx.SignedIn);

		Common cm = Common.getInstance();

		System.out.print(cm.GetUID());
		// cm.Gmail.Send(
		// 		new Mail().blank()
		// 				.to("tessst", "shanenoi.org@gmail.com")
		// 				.withSubject("Email Subject")
		// 				.withHTMLText("<b>HHH</b><i>ccc</i>"));

		return "check_health";
	}

}

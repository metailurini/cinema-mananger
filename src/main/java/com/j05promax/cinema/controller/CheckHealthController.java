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
			Model model) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		// PostgreSQLRepo repo = PostgreSQLRepo.getInstance();
		ctx = Midleware.Authenticate(ctx);
		model.addAttribute("signedin", ctx.SignedIn);
		// String datetime = "22-05-2022 11:30";
		// DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		// Date date = new Date();

		// try {
		// date = formatter.parse(datetime);
		// } catch (java.text.ParseException e) {
		// new Log(e).Show();
		// }

		Common cm = Common.getInstance();

		System.out.println(cm.GetUID() + "|" + cm.GetUID() + "|");

		return "tmp";
	}

}

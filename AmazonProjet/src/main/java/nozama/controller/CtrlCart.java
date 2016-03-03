package nozama.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CtrlCart {

	@RequestMapping(value = "/mon-panier")
	public ModelAndView index(HttpServletRequest request) {


		return new ModelAndView("myCart");
	}
	
	
	

}

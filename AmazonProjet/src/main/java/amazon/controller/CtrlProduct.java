package amazon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CtrlProduct {
	
	@RequestMapping(value = "/liste-tous-les-produits")
	public ModelAndView listAllProducts(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");

		return modelAndView;
	}
	
	@RequestMapping(value = "/liste-toutes-les-musiques")
	public ModelAndView listAllMusic(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		
		
		modelAndView.setViewName("listProduct");

		return modelAndView;
	}

}

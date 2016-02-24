package nozama.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nozama.service.ProductService;

@Controller
public class CtrlProduct {
	
	@Autowired
	private ProductService PS;

	@RequestMapping(value = "/liste-toutes-les-musiques")
	public ModelAndView listAllMusic(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("listProductMusic");
		
		Map<String, Object> product = new HashMap<String, Object>();
		product.put("products", PS.getSingle());
		
		return new ModelAndView("listProduct", product);
	}

}

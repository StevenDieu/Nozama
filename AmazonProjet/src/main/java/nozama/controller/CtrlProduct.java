package nozama.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nozama.service.ProductService;
import nozama.util.Util;

@Controller
public class CtrlProduct {

	@Autowired
	private ProductService PS;

	@RequestMapping(value = "/liste-toutes-les-musiques")
	public ModelAndView listAllMusic(HttpServletRequest request) {

		String support = request.getParameter("support");
		String recordType = request.getParameter("recordType");
		String stringYears = request.getParameter("years");
		int years = -1;
		
		if (stringYears != null && Util.convertToInt(stringYears)) {
			years = Integer.parseInt(stringYears);
		}
		if (support == null) {
			support = "CD";
		}
		if (recordType == null) {
			recordType = "single";
		}

		Map<String, Object> product = new HashMap<String, Object>();
		product.put("products", PS.getAllMusicsBySupport(support, recordType, years));
		product.put("recordType", recordType);
		product.put("support", support);
		product.put("years", years);

		return new ModelAndView("listProductMusic", product);
	}

	@RequestMapping(value = "/liste-tous-les-films")
	public ModelAndView listAllMovies(HttpServletRequest request) {
		String support = request.getParameter("support");
		String stringYears = request.getParameter("years");
		int years = -1;
		
		if (stringYears != null && Util.convertToInt(stringYears)) {
			years = Integer.parseInt(stringYears);
		}

		if (support == null) {
			support = "DVD";
		}

		Map<String, Object> product = new HashMap<String, Object>();
		product.put("products", PS.getAllMovieBySupport(support, years));
		product.put("support", support);
		product.put("years", years);

		return new ModelAndView("listProductMovie", product);
	}

}

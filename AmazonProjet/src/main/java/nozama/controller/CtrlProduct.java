package nozama.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import nozama.service.ProductService;
import nozama.util.Util;

@Controller
public class CtrlProduct {

	@Autowired
	private ProductService PS;

	@RequestMapping(value = { "/liste-toutes-les-musiques", "/liste-toutes-les-musiques/{type}", "/liste-toutes-les-musiques/{support}/{recordType}/{years}/{type}" }, method = RequestMethod.GET)
	public ModelAndView listAllMusic(HttpServletRequest request, 
			@PathVariable("support") Optional<String> supportUrl, 
			@PathVariable("recordType") Optional<String> recordTypeUrl, 
			@PathVariable("years") Optional<String> yearsUrl, 
			@PathVariable("type") Optional<String> typeUrl) {

		String support = PS.getParametersString(supportUrl, "CD");
		String recordType = PS.getParametersString(recordTypeUrl, "single");
		String stringYears = PS.getParametersString(yearsUrl, "default");
		String type = PS.getParametersString(typeUrl, "ALL");

		int years = -1;
		if (Util.convertToInt(stringYears)) {
			years = Integer.parseInt(stringYears);
		}

		Map<String, Object> product = new HashMap<String, Object>();
		product.put("products", PS.getAllMusicsBySupport(support, recordType, years, type));
		product.put("recordType", recordType);
		product.put("support", support);
		product.put("years", years);
		product.put("type", type);

		return new ModelAndView("listProductMusic", product);
	}

	@RequestMapping(value = { "/liste-tous-les-films", "/liste-tous-les-films/{type}", "/liste-tous-les-films/{support}/{type}" }, method = RequestMethod.GET)
	public ModelAndView listAllMovies(HttpServletRequest request, @PathVariable("support") Optional<String> supportUrl, @PathVariable("type") Optional<String> typeUrl) {
		String support = PS.getParametersString(supportUrl, "DVD");
		String type = PS.getParametersString(typeUrl, "ALL");

		Map<String, Object> product = new HashMap<String, Object>();
		product.put("products", PS.getAllMovieBySupport(support, type));
		product.put("support", support);
		product.put("type", type);

		return new ModelAndView("listProductMovie", product);
	}

}

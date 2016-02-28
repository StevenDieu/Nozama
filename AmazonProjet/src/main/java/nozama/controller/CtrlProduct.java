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

	@RequestMapping(value = { "/liste-toutes-les-musiques", "/liste-toutes-les-musiques/{type}", "/liste-toutes-les-musiques/{support}/{recordType}/{years}/{type}", "/liste-toutes-les-musiques/{support}/{recordType}/{years}/{type}/{startResult}" }, method = RequestMethod.GET)
	public ModelAndView listAllMusic(HttpServletRequest request,
			@PathVariable("support") Optional<String> supportUrl,
			@PathVariable("recordType") Optional<String> recordTypeUrl,
			@PathVariable("years") Optional<String> yearsUrl,
			@PathVariable("type") Optional<String> typeUrl,
			@PathVariable("startResult") Optional<String> startResultUrl) {

		String support = PS.getParametersString(supportUrl, "CD");
		String recordType = PS.getParametersString(recordTypeUrl, "single");
		String stringYears = PS.getParametersString(yearsUrl, "default");
		String type = PS.getParametersString(typeUrl, "ALL");
		String startResultString = PS.getParametersString(startResultUrl, "1");

		int years = -1;
		if (Util.convertToInt(stringYears)) {
			years = Integer.parseInt(stringYears);
		}

		int startResult = 1;
		if (Util.convertToInt(startResultString)) {
			startResult = Integer.parseInt(startResultString);
		}

		Map<String, Object> product = new HashMap<String, Object>();
		product.put("products", PS.getAllMusicsBySupport(support, recordType, years, type, startResult));
		product.put("recordType", recordType);
		product.put("support", support);
		product.put("years", years);
		product.put("type", type);
		product.put("numberPage", (PS.getCountAllMusicBySupport(support, recordType, years, type) / 12));
		product.put("startPage", startResult);

		return new ModelAndView("listProductMusic", product);
	}

	@RequestMapping(value = { "/liste-tous-les-films", "/liste-tous-les-films/{type}", "/liste-tous-les-films/{support}/{years}/{type}", "/liste-tous-les-films/{support}/{years}/{type}/{startResult}" }, method = RequestMethod.GET)
	public ModelAndView listAllMovies(HttpServletRequest request,
			@PathVariable("support") Optional<String> supportUrl,
			@PathVariable("type") Optional<String> typeUrl,
			@PathVariable("years") Optional<String> yearsUrl,
			@PathVariable("startResult") Optional<String> startResultUrl) {

		String support = PS.getParametersString(supportUrl, "DVD");
		String type = PS.getParametersString(typeUrl, "ALL");
		String startResultString = PS.getParametersString(startResultUrl, "1");
		String stringYears = PS.getParametersString(yearsUrl, "default");

		int startResult = 1;
		if (Util.convertToInt(startResultString)) {
			startResult = Integer.parseInt(startResultString);
		}

		int years = -1;
		if (Util.convertToInt(stringYears)) {
			years = Integer.parseInt(stringYears);
		}

		Map<String, Object> product = new HashMap<String, Object>();
		product.put("products", PS.getAllMovieBySupport(support, type, startResult, years));
		product.put("support", support);
		product.put("years", years);
		product.put("type", type);
		product.put("numberPage", (PS.getCountMovieBySupport(support, type, years) / 12));
		product.put("startPage", startResult);

		return new ModelAndView("listProductMovie", product);
	}
	
	@RequestMapping(value = "/tous-les-produits" , method = RequestMethod.GET)
	public ModelAndView allProduct(HttpServletRequest request,
			@PathVariable("years") Optional<String> yearsUrl,
			@PathVariable("startResult") Optional<String> startResultUrl) {
		String stringYears = PS.getParametersString(yearsUrl, "default");
		int years = -1;
		if (Util.convertToInt(stringYears)) {
			years = Integer.parseInt(stringYears);
		}
		
		PS.getAllProduct(years);
		
		Map<String, Object> product = new HashMap<String, Object>();
		product.put("years", years);

		return new ModelAndView("listAll", product);
	}

}

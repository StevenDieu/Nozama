package nozama.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import nozama.model.Order;
import nozama.model.Product;
import nozama.model.User;
import nozama.service.ProductListServiceImpl;
import nozama.service.ProductPageServiceImpl;
import nozama.service.UserServiceImpl;
import nozama.util.Util;

@Controller
public class CtrlProduct {

	@Autowired
	private UserServiceImpl US;
	
  @Autowired
  private ProductListServiceImpl PLS;

  @Autowired
  private ProductPageServiceImpl PPS;

  @RequestMapping(
      value = {"/liste-toutes-les-musiques", "/liste-toutes-les-musiques/{type}",
          "/liste-toutes-les-musiques/{support}/{recordType}/{years}/{type}",
          "/liste-toutes-les-musiques/{support}/{recordType}/{years}/{type}/{startResult}"},
      method = RequestMethod.GET)
  public ModelAndView listAllMusic(HttpServletRequest request,
      @PathVariable("support") Optional<String> supportUrl,
      @PathVariable("recordType") Optional<String> recordTypeUrl,
      @PathVariable("years") Optional<String> yearsUrl,
      @PathVariable("type") Optional<String> typeUrl,
      @PathVariable("startResult") Optional<String> startResultUrl) {

    String support = PLS.getParametersString(supportUrl, "AllSupport");
    String recordType = PLS.getParametersString(recordTypeUrl, "AllType");
    String stringYears = PLS.getParametersString(yearsUrl, "default");
    String genre = PLS.getParametersString(typeUrl, "ALL");
    String startResultString = PLS.getParametersString(startResultUrl, "1");

    int years = -1;
    if (Util.convertToInt(stringYears)) {
      years = Integer.parseInt(stringYears);
    }

    int startResult = 1;
    if (Util.convertToInt(startResultString)) {
      startResult = Integer.parseInt(startResultString);
    }
    
    List<Map<String, Object>> allProduct = PLS.getAllProductByCondition(support, recordType, years, genre);

    if (((startResult - 1) * 12) > allProduct.size() - 1 || startResult <= 0) {
        startResult = 1;
    }

    Map<String, Object> product = new HashMap<String, Object>();
    int toIndexEndPagination = (startResult * 12) > allProduct.size() ? allProduct.size() : (startResult * 12);

    product.put("products", allProduct.subList(((startResult - 1) * 12), toIndexEndPagination));
    product.put("recordType", recordType);
    product.put("support", support);
    product.put("years", years);
    product.put("type", genre);
    product.put("numberPage", Math.ceil((double) allProduct.size() / 12));
    product.put("startPage", startResult);


    return new ModelAndView("listProduct/listProductMusic", product);
  }

  @RequestMapping(
      value = {"/liste-tous-les-films", "/liste-tous-les-films/{type}",
          "/liste-tous-les-films/{support}/{years}/{type}",
          "/liste-tous-les-films/{support}/{years}/{type}/{startResult}"},
      method = RequestMethod.GET)
  public ModelAndView listAllMovies(HttpServletRequest request,
      @PathVariable("support") Optional<String> supportUrl,
      @PathVariable("type") Optional<String> typeUrl,
      @PathVariable("years") Optional<String> yearsUrl,
      @PathVariable("startResult") Optional<String> startResultUrl) {

    String support = PLS.getParametersString(supportUrl, "AllSupport");
    String genre = PLS.getParametersString(typeUrl, "ALL");
    String startResultString = PLS.getParametersString(startResultUrl, "1");
    String stringYears = PLS.getParametersString(yearsUrl, "default");

    int startResult = 1;
    if (Util.convertToInt(startResultString)) {
      startResult = Integer.parseInt(startResultString);
    }

    int years = -1;
    if (Util.convertToInt(stringYears)) {
      years = Integer.parseInt(stringYears);
    }

    List<Map<String, Object>> allProduct = PLS.getAllProductByCondition(support, "film", years, genre);

    if (((startResult - 1) * 12) > allProduct.size() - 1 || startResult <= 0) {
        startResult = 1;
    }

    Map<String, Object> product = new HashMap<String, Object>();
    int toIndexEndPagination = (startResult * 12) > allProduct.size() ? allProduct.size() : (startResult * 12);

    product.put("products", allProduct.subList(((startResult - 1) * 12), toIndexEndPagination));
    product.put("support", support);
    product.put("years", years);
    product.put("type", genre);
    product.put("numberPage", Math.ceil((double) allProduct.size() / 12));
    product.put("startPage", startResult);
    

    return new ModelAndView("listProduct/listProductMovie", product);
  }

  @RequestMapping(value = {"/liste-tous-les-produits", "/liste-tous-les-produits/{years}",
      "/liste-tous-les-produits/{years}/{startResult}"}, method = RequestMethod.GET)
  public ModelAndView listAllProducts(HttpServletRequest request,
      @PathVariable("years") Optional<String> yearsUrl,
      @PathVariable("startResult") Optional<String> startResultUrl) {
    String stringYears = PLS.getParametersString(yearsUrl, "default");
    String startResultString = PLS.getParametersString(startResultUrl, "1");

    int startResult = 1;
    if (Util.convertToInt(startResultString)) {
      startResult = Integer.parseInt(startResultString);
    }

    int years = -1;
    if (Util.convertToInt(stringYears)) {
      years = Integer.parseInt(stringYears);
    }

    List<Map<String, Object>> allProduct =
        PLS.getAllProductByCondition("AllSupport", "allProduct", years, "ALL");

    if (((startResult - 1) * 12) > allProduct.size() - 1 || startResult <= 0) {
      startResult = 1;
    }

    Map<String, Object> product = new HashMap<String, Object>();
    int toIndexEndPagination =
        (startResult * 12) > allProduct.size() ? allProduct.size() : (startResult * 12);

    product.put("products", allProduct.subList(((startResult - 1) * 12), toIndexEndPagination));
    product.put("years", years);
    product.put("numberPage", Math.ceil((double) allProduct.size() / 12));
    product.put("startPage", startResult);


    return new ModelAndView("listProduct/listAll", product);
  }

  @RequestMapping(value = {"/product/{type}/{nameTagDateReleased}"}, method = RequestMethod.GET)
  public ModelAndView pageProduct(HttpServletRequest request,
      @PathVariable("nameTagDateReleased") Optional<String> nameTagDateReleasedUrl,
      @PathVariable("type") Optional<String> typeUrl) {
    String nameTagDateReleased = PLS.getParametersString(nameTagDateReleasedUrl, "");
    String type = PLS.getParametersString(typeUrl, "");

    if (type.equals("")) {
      return new ModelAndView("redirect:/");
    }

    if (nameTagDateReleased.equals("")) {
      return new ModelAndView("redirect:/");
    }
    Map<String, Object> productItem = PPS.getProduct(nameTagDateReleased);
    if (productItem.size() <= 0) {
      return new ModelAndView("redirect:/");
    }
    Map<String, Object> product = new HashMap<String, Object>();
    product.put("products", productItem);
    product.put("url", request.getRequestURL());



    if (type.equals("film")) {
      return new ModelAndView("pageProduct/movie", product);
    } else {
      return new ModelAndView("pageProduct/music", product);
    }
  }
  
  @RequestMapping(value = "/seeProduct", method = RequestMethod.POST)
  public ModelAndView seeProduct(HttpServletRequest request) {
	  
	String id = request.getParameter("id");
	
	Map<String, Object> variableParam = new HashMap<String, Object>();
	if (request.getSession().getAttribute("User") != null) {
		Order order = new Order();
		order.setIdOrder(Integer.parseInt(id));
	  List<Product> products = US.getProductCmd(order);
	  if(products.size() > 0){
	      variableParam.put("products", products);
	  }
	  return new ModelAndView("modaleProduct", variableParam);
	}
	return new ModelAndView("redirect:/", variableParam);
  }
}

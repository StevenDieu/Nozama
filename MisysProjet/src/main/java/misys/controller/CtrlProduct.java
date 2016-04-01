package misys.controller;

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

import misys.model.Order;
import misys.model.Product;
import misys.service.ProductListServiceImpl;
import misys.service.ProductPageServiceImpl;
import misys.service.UserServiceImpl;
import misys.util.Util;

/**
 * All controller for List product or page Product
 *
 */
@Controller
public class CtrlProduct {

  /**
   * It's a singleton for the service @UserServiceImpl
   */
  @Autowired
  private UserServiceImpl US;

  /**
   * It's a singleton for the service @ProductListServiceImpl
   */
  @Autowired
  private ProductListServiceImpl PLS;

  /**
   * It's a singleton for the service @ProductPageServiceImpl
   */
  @Autowired
  private ProductPageServiceImpl PPS;

  /**
   * 
   * [Ctrl] generate a page with all musics
   * 
   * @param request
   * @param supportUrl
   * @param recordTypeUrl
   * @param yearsUrl
   * @param typeUrl
   * @param startResultUrl
   * @return view listProductMusic
   */
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

    String support = Util.getParametersString(supportUrl, "AllSupport");
    String recordType = Util.getParametersString(recordTypeUrl, "AllType");
    String stringYears = Util.getParametersString(yearsUrl, "default");
    String genre = Util.getParametersString(typeUrl, "ALL");
    String startResultString = Util.getParametersString(startResultUrl, "1");

    int years = -1;
    if (Util.checkConvertToInt(stringYears)) {
      years = Integer.parseInt(stringYears);
    }

    int startResult = 1;
    if (Util.checkConvertToInt(startResultString)) {
      startResult = Integer.parseInt(startResultString);
    }

    List<Map<String, Object>> allProduct =
        PLS.getAllProductByCondition(support, recordType, years, genre);

    if (((startResult - 1) * 12) > allProduct.size() - 1 || startResult <= 0) {
      startResult = 1;
    }

    Map<String, Object> product = new HashMap<String, Object>();
    int toIndexEndPagination =
        (startResult * 12) > allProduct.size() ? allProduct.size() : (startResult * 12);

    product.put("products", allProduct.subList(((startResult - 1) * 12), toIndexEndPagination));
    product.put("recordType", recordType);
    product.put("support", support);
    product.put("years", years);
    product.put("type", genre);
    product.put("numberPage", Math.ceil((double) allProduct.size() / 12));
    product.put("startPage", startResult);


    return new ModelAndView("listProduct/listProductMusic", product);
  }

  /**
   * 
   * [Ctrl] generate a page with all movies
   * 
   * @param request
   * @param supportUrl
   * @param yearsUrl
   * @param startResultUrl
   * @return view listProductMusic
   */
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

    String support = Util.getParametersString(supportUrl, "AllSupport");
    String genre = Util.getParametersString(typeUrl, "ALL");
    String startResultString = Util.getParametersString(startResultUrl, "1");
    String stringYears = Util.getParametersString(yearsUrl, "default");

    int startResult = 1;
    if (Util.checkConvertToInt(startResultString)) {
      startResult = Integer.parseInt(startResultString);
    }

    int years = -1;
    if (Util.checkConvertToInt(stringYears)) {
      years = Integer.parseInt(stringYears);
    }

    List<Map<String, Object>> allProduct =
        PLS.getAllProductByCondition(support, "film", years, genre);

    if (((startResult - 1) * 12) > allProduct.size() - 1 || startResult <= 0) {
      startResult = 1;
    }

    Map<String, Object> product = new HashMap<String, Object>();
    int toIndexEndPagination =
        (startResult * 12) > allProduct.size() ? allProduct.size() : (startResult * 12);

    product.put("products", allProduct.subList(((startResult - 1) * 12), toIndexEndPagination));
    product.put("support", support);
    product.put("years", years);
    product.put("type", genre);
    product.put("numberPage", Math.ceil((double) allProduct.size() / 12));
    product.put("startPage", startResult);


    return new ModelAndView("listProduct/listProductMovie", product);
  }

  /**
   * [Ctrl] generate a page with all liste products
   * 
   * @param request
   * @param yearsUrl
   * @param startResultUrl
   * @return view listAll
   */
  @RequestMapping(value = {"/liste-tous-les-produits", "/liste-tous-les-produits/{years}",
      "/liste-tous-les-produits/{years}/{startResult}"}, method = RequestMethod.GET)
  public ModelAndView listAllProducts(HttpServletRequest request,
      @PathVariable("years") Optional<String> yearsUrl,
      @PathVariable("startResult") Optional<String> startResultUrl) {
    String stringYears = Util.getParametersString(yearsUrl, "default");
    String startResultString = Util.getParametersString(startResultUrl, "1");

    int startResult = 1;
    if (Util.checkConvertToInt(startResultString)) {
      startResult = Integer.parseInt(startResultString);
    }

    int years = -1;
    if (Util.checkConvertToInt(stringYears)) {
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

  /**
   * [Ctrl] generate a page product for music or movie
   * 
   * @param request
   * @param nameTagDateReleasedUrl
   * @param typeUrl
   * @return view movie or music
   */
  @RequestMapping(value = {"/product/{type}/{nameTagDateReleased}"}, method = RequestMethod.GET)
  public ModelAndView pageProduct(HttpServletRequest request,
      @PathVariable("nameTagDateReleased") Optional<String> nameTagDateReleasedUrl,
      @PathVariable("type") Optional<String> typeUrl) {
    String nameTagDateReleased = Util.getParametersString(nameTagDateReleasedUrl, "");
    String type = Util.getParametersString(typeUrl, "");

    if (Util.checkStringIsNull(type)) {
      return new ModelAndView("redirect:/404");
    }

    if (Util.checkStringIsNull(nameTagDateReleased)) {
      return new ModelAndView("redirect:/404");
    }
    Map<String, Object> productItem = PPS.getProduct(nameTagDateReleased);
    if (productItem.size() <= 0) {
      return new ModelAndView("redirect:/404");
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

  /**
   * [Ctrl] generate a modal with all product in account
   * 
   * @param request
   * @return view modaleProduct
   */
  @RequestMapping(value = "/seeProduct", method = RequestMethod.POST)
  public ModelAndView seeProduct(HttpServletRequest request) {

    String id = request.getParameter("id");

    Map<String, Object> variableParam = new HashMap<String, Object>();
    if (request.getSession().getAttribute("User") != null) {
      Order order = new Order();
      order.setIdOrder(Integer.parseInt(id));
      List<Product> products = US.getProductCmd(order);
      if (products.size() > 0) {
        variableParam.put("products", products);
      }
      return new ModelAndView("account/modaleProduct", variableParam);
    }
    return new ModelAndView("redirect:/404", variableParam);
  }
}

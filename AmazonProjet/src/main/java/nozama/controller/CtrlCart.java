package nozama.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import nozama.service.ProductCartServiceImpl;
import nozama.util.Util;

@Controller
public class CtrlCart {

  @Autowired
  private ProductCartServiceImpl PCS;

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/mon-panier")
  public ModelAndView myCart(HttpServletRequest request) {

    Map<String, Object> product = new HashMap<String, Object>();

    if (request.getSession().getAttribute("cart") != null
        && request.getSession().getAttribute("cart") != "") {

      List<Map<String, Object>> allProduct =
          PCS.getAllCart((List<Map<String, Object>>) request.getSession().getAttribute("cart"));
      product.put("products", allProduct);
    }

    return new ModelAndView("myCart", product);
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/ajaxDeleteProductCart", method = RequestMethod.POST)
  @ResponseBody
  public String deleteProductCart(HttpServletRequest request) {
    String idString = request.getParameter("id");
    String type = request.getParameter("type");
    List<Map<String, Object>> listMapCart = new ArrayList<Map<String, Object>>();

    if (idString != "" && Util.convertToInt(idString) && type != "") {
      int id = Integer.parseInt(idString);

      if (request.getSession().getAttribute("cart") != null
          && request.getSession().getAttribute("cart") != "") {
        List<Map<String, Object>> allCart =
            (List<Map<String, Object>>) request.getSession().getAttribute("cart");

        for (Map<String, Object> cart : allCart) {
          int idSession = (int) cart.get("id");

          if (!(idSession == id && cart.get("type").equals(type))) {

            Map<String, Object> listCart = new HashMap<>();
            listCart.put("id", idSession);
            listCart.put("type", cart.get("type"));
            listCart.put("number", (int) cart.get("number"));
            listMapCart.add(listCart);

          }
        }
        request.getSession().setAttribute("nbCart", listMapCart.size());
        request.getSession().setAttribute("cart", listMapCart);
      }
    }
    return Integer.toString(listMapCart.size());
  }

  @RequestMapping(value = "/ajaxDeleteAllProductCart", method = RequestMethod.POST)
  @ResponseBody
  public void deleteAllProductCart(HttpServletRequest request) {
    request.getSession().setAttribute("nbCart", 0);
    request.getSession().setAttribute("cart", null);
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/ajaxChangeNumberProductCart", method = RequestMethod.POST)
  @ResponseBody
  public String changeNumberProductCart(HttpServletRequest request) {
    String idString = request.getParameter("id");
    String numberString = request.getParameter("number");
    String type = request.getParameter("type");

    int number = 1;

    if (idString != "" && Util.convertToInt(idString) && numberString != ""
        && Util.convertToInt(numberString) && type != "") {
      int id = Integer.parseInt(idString);
      number = Integer.parseInt(numberString);
      if (number < 0) {
        number = 1;
      } else if (number > 100) {
        number = 1;
      }
      if (request.getSession().getAttribute("cart") != null
          && request.getSession().getAttribute("cart") != "") {
        List<Map<String, Object>> allCart =
            (List<Map<String, Object>>) request.getSession().getAttribute("cart");
        List<Map<String, Object>> listMapCart = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> cart : allCart) {
          Map<String, Object> listCart = new HashMap<>();
          int idSession = (int) cart.get("id");
          listCart.put("id", idSession);
          listCart.put("type", cart.get("type"));
          if (idSession == id && cart.get("type").equals(type)) {
            listCart.put("number", number);
          } else {
            listCart.put("number", (int) cart.get("number"));
          }
          listMapCart.add(listCart);
        }

        request.getSession().setAttribute("cart", listMapCart);
      }
    }

    return "{\"number\": " + number + "}";
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/ajaxAddCart", method = RequestMethod.POST)
  @ResponseBody
  public String addInMyCart(HttpServletRequest request) {

    String idString = request.getParameter("id");
    String type = request.getParameter("typeData");

    if (idString != "" && type != "" && Util.convertToInt(idString)) {
      int id = Integer.parseInt(idString);
      List<Map<String, Object>> listMapCart;

      if (request.getSession().getAttribute("cart") == null
          || request.getSession().getAttribute("cart") == "") {
        listMapCart = new ArrayList<Map<String, Object>>();
      } else {
        listMapCart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");
        for (Map<String, Object> mapCart : listMapCart) {
          if (((int) mapCart.get("id")) == id && mapCart.get("type").equals(type)) {
            return "{\"statut\": \"error\",\"message\":  \"Ce produit est déjà dans votre panier.\"}";
          }
        }

      }

      Map<String, Object> listCart = new HashMap<>();
      listCart.put("id", id);
      listCart.put("type", type);
      listCart.put("number", 1);
      listMapCart.add(listCart);
      request.getSession().setAttribute("cart", listMapCart);

      request.getSession().setAttribute("nbCart", listMapCart.size());

      return "{\"statut\": \"succes\",\"nbCart\" : " + listMapCart.size()
          + ",\"message\":  \"Votre produit à été ajouté au panier.\"}";
    } else {
      return "{\"statut\": \"error\",\"message\":  \"Une erreur est survenue.\"}";
    }

  }

}

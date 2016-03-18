package nozama.controller;

import java.io.UnsupportedEncodingException;
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

import nozama.model.Adress;
import nozama.model.User;
import nozama.service.ProductCartServiceImpl;
import nozama.service.UserServiceImpl;
import nozama.util.Util;

@Controller
public class CtrlCart {

  @Autowired
  private ProductCartServiceImpl PCS;

  @Autowired
  private UserServiceImpl US;

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

    return new ModelAndView("cart/myCart", product);
  }

  @RequestMapping(value = "/mon-panier-validation-panier")
  public ModelAndView myCartValidation(HttpServletRequest request) {
    request.getSession().setAttribute("cartControlTunnel",
        request.getSession().getAttribute("cart"));

    return new ModelAndView("redirect:/mon-panier-etape-connexion");
  }

  @RequestMapping(value = "/mon-panier-etape-connexion")
  public ModelAndView myCartConnexion(HttpServletRequest request) {

    Map<String, Object> redirect = new HashMap<String, Object>();
    redirect.put("redirect", "/mon-panier-etape-adresse");

    if (request.getSession().getAttribute("cartControlTunnel") == null
        || request.getSession().getAttribute("cartControlTunnel") == "") {

      return new ModelAndView("redirect:/mon-panier");

    }

    if (request.getSession().getAttribute("User") != null) {
      return new ModelAndView("redirect:/mon-panier-etape-adresse");
    }

    return new ModelAndView("cart/myCartConnexion", redirect);
  }

  @RequestMapping(value = "/mon-panier-etape-adresse")
  public ModelAndView myCartAdress(HttpServletRequest request) {

    String message = request.getParameter("message");


    if (request.getSession().getAttribute("cartControlTunnel") == null
        || request.getSession().getAttribute("cartControlTunnel") == "") {

      return new ModelAndView("redirect:/mon-panier");

    }

    if (request.getSession().getAttribute("User") == null) {
      return new ModelAndView("redirect:/mon-panier-etape-connexion");
    }

    User user = (User) request.getSession().getAttribute("User");

    Map<String, Object> adress = new HashMap<String, Object>();
    adress.put("adresss", US.getAllAdressByUser(user));
    if (message != "") {
      adress.put("message", message);
    }

    return new ModelAndView("cart/myCartAdresse", adress);
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/mon-panier-etape-validation-adress", method = RequestMethod.POST)
  public ModelAndView myCartCalidateAdress(HttpServletRequest request)
      throws UnsupportedEncodingException {
    request.setCharacterEncoding("UTF-8");

    String idAdressString = request.getParameter("idAdress");
    String name = request.getParameter("name");
    String nameLastName = request.getParameter("nameLastName");
    String adressPrincipal = request.getParameter("adressPrincipal");
    String adressSecondaire = request.getParameter("adressSecondaire");
    String region = request.getParameter("region");
    String codePostalString = request.getParameter("codePostal");
    String pays = request.getParameter("pays");
    String numberPhone = request.getParameter("numberPhone");

    if (request.getSession().getAttribute("User") == null) {
      return new ModelAndView("redirect:/mon-panier-etape-connexion");
    }
    User user = (User) request.getSession().getAttribute("User");



    if (request.getSession().getAttribute("cartControlTunnel") == null
        || request.getSession().getAttribute("cartControlTunnel") == "") {

      List<Map<String, Object>> allProduct = PCS.getAllCart(
          (List<Map<String, Object>>) request.getSession().getAttribute("cartControlTunnel"));

      if (allProduct.size() == 0) {
        return new ModelAndView("redirect:/mon-panier");
      }

    }


    if (idAdressString != "") {
      if (!Util.convertToInt(idAdressString)) {
        Map<String, Object> redirect = new HashMap<String, Object>();
        redirect.put("message", "Une erreur est survenue.");
        return new ModelAndView("redirect:/mon-panier-etape-adresse", redirect);
      }

      int idAdress = Integer.parseInt(idAdressString);
      Adress adress = US.checkAdressByUser(idAdress, user);
      if (adress == null) {
        Map<String, Object> redirect = new HashMap<String, Object>();
        redirect.put("message", "Une erreur est survenue.");
        return new ModelAndView("redirect:/mon-panier-etape-adresse/", redirect);
      }
      request.getSession().setAttribute("address", adress);
    } else {
      if (name == "" || nameLastName == "" || adressPrincipal == "" || codePostalString == ""
          || pays == "" || numberPhone == "") {
        Map<String, Object> redirect = new HashMap<String, Object>();
        redirect.put("message", "Tout les champs sont obligatoires.");
        return new ModelAndView("redirect:/mon-panier-etape-adresse", redirect);
      } else if (name.length() > 255 || nameLastName.length() > 255
          || adressPrincipal.length() > 1024 || adressSecondaire.length() > 1024
          || region.length() > 255 || codePostalString.length() > 5 || numberPhone.length() > 10) {
        Map<String, Object> redirect = new HashMap<String, Object>();
        redirect.put("message", "Attention à la longueur des champs.");
        return new ModelAndView("redirect:/mon-panier-etape-adresse", redirect);
      }

      int codePostal;
      if (!Util.convertToInt(codePostalString)) {
        Map<String, Object> redirect = new HashMap<String, Object>();
        redirect.put("message", "Le code postal doit être un chiffre");
        return new ModelAndView("redirect:/mon-panier-etape-adresse", redirect);
      }
      codePostal = Integer.parseInt(codePostalString);

      if (!Util.convertToInt(numberPhone)) {
        Map<String, Object> redirect = new HashMap<String, Object>();
        redirect.put("message", "Le numéro de téléphone doit être un chiffre");
        return new ModelAndView("redirect:/mon-panier-etape-adresse", redirect);
      }


      final Adress adress = US.insertAdress(name, nameLastName, adressPrincipal, adressSecondaire,
          region, pays, user, codePostal, numberPhone);

      request.getSession().setAttribute("address", adress);
    }

    return new ModelAndView("redirect:/mon-panier-etape-livraison");
  }


  @RequestMapping(value = "/mon-panier-etape-livraison")
  public ModelAndView myCartDelivery(HttpServletRequest request) {
    if (request.getSession().getAttribute("cartControlTunnel") == null
        || request.getSession().getAttribute("cartControlTunnel") == "") {
      return new ModelAndView("redirect:/mon-panier");
    }

    if (request.getSession().getAttribute("User") == null
        || request.getSession().getAttribute("User") == "") {
      return new ModelAndView("redirect:/mon-panier-etape-connexion");
    }

    if (request.getSession().getAttribute("address") == null
        || request.getSession().getAttribute("address") == "") {
      return new ModelAndView("redirect:/mon-panier-etape-adresse");
    }

    return new ModelAndView("cart/myCartDelivery");
  }



  @RequestMapping(value = "/mon-panier-etape-validation-transport")
  public ModelAndView myCartValidateTransport(HttpServletRequest request) throws UnsupportedEncodingException {
    if (request.getSession().getAttribute("cartControlTunnel") == null
        || request.getSession().getAttribute("cartControlTunnel") == "") {
      return new ModelAndView("redirect:/mon-panier");
    }

    if (request.getSession().getAttribute("User") == null
        || request.getSession().getAttribute("User") == "") {
      return new ModelAndView("redirect:/mon-panier-etape-connexion");
    }

    if (request.getSession().getAttribute("address") == null
        || request.getSession().getAttribute("address") == "") {
      return new ModelAndView("redirect:/mon-panier-etape-adresse");
    }
    request.setCharacterEncoding("utf-8");
    
    String chooseTransport = request.getParameter("chooseTransport");
    String commentaire = request.getParameter("commentaire");

    if (chooseTransport == "") {
      return new ModelAndView("redirect:/mon-panier-etape-livraison");
    }

    Map<String, Object> listTransport = new HashMap<>();
    listTransport.put("id", chooseTransport);
    listTransport.put("commentaire", Util.ConvertStringToNull(commentaire));
    request.getSession().setAttribute("transport", listTransport);

    return new ModelAndView("redirect:/mon-panier-etape-paiement");
  }

  @RequestMapping(value = "/mon-panier-etape-paiement")
  public ModelAndView myCartPayment(HttpServletRequest request) {
    if (request.getSession().getAttribute("cartControlTunnel") == null
        || request.getSession().getAttribute("cartControlTunnel") == "") {
      return new ModelAndView("redirect:/mon-panier");
    }

    if (request.getSession().getAttribute("User") == null
        || request.getSession().getAttribute("User") == "") {
      return new ModelAndView("redirect:/mon-panier-etape-connexion");
    }

    if (request.getSession().getAttribute("address") == null
        || request.getSession().getAttribute("address") == "") {
      return new ModelAndView("redirect:/mon-panier-etape-adresse");
    }

    if (request.getSession().getAttribute("transport") == null
        || request.getSession().getAttribute("transport") == "") {
      return new ModelAndView("redirect:/mon-panier-etape-livraison");
    }

    return new ModelAndView("cart/myCartPayment");
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
    Boolean newProduct = true;

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
            newProduct = false;
            int number = ((int) mapCart.get("number")) + 1;
            if (number <= 100) {
              mapCart.put("number", number);
            }
          }
        }

      }

      if (newProduct) {
        Map<String, Object> listCart = new HashMap<>();
        listCart.put("id", id);
        listCart.put("type", type);
        listCart.put("number", 1);
        listMapCart.add(listCart);
      }

      request.getSession().setAttribute("cart", listMapCart);

      request.getSession().setAttribute("nbCart", listMapCart.size());

      return "{\"statut\": \"succes\",\"nbCart\" : " + listMapCart.size()
          + ",\"message\":  \"Votre produit à été ajouté au panier.\"}";
    } else {
      return "{\"statut\": \"error\",\"message\":  \"Une erreur est survenue.\"}";
    }

  }

}

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
import nozama.model.Order;
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
        || request.getSession().getAttribute("cartControlTunnel") == "[]") {

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
        || request.getSession().getAttribute("cartControlTunnel") == "[]") {

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
    String city = request.getParameter("city");

    if (request.getSession().getAttribute("User") == null) {
      return new ModelAndView("redirect:/mon-panier-etape-connexion");
    }
    User user = (User) request.getSession().getAttribute("User");



    if (request.getSession().getAttribute("cartControlTunnel") == null
        || request.getSession().getAttribute("cartControlTunnel") == "[]") {

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
      if (name.equals("") || nameLastName.equals("") || adressPrincipal.equals("") || codePostalString.equals("")
          || pays.equals("") || numberPhone.equals("") || city.equals("")) {
        Map<String, Object> redirect = new HashMap<String, Object>();
        redirect.put("message", "Tout les champs sont obligatoires.");
        return new ModelAndView("redirect:/mon-panier-etape-adresse", redirect);
      } else if (name.length() > 255 || nameLastName.length() > 255
          || adressPrincipal.length() > 1024 || adressSecondaire.length() > 1024
          || region.length() > 255 || codePostalString.length() > 5 || numberPhone.length() > 10 || city.length() > 255) {
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
          region, pays, user, codePostal, numberPhone,city);

      request.getSession().setAttribute("address", adress);
    }

    return new ModelAndView("redirect:/mon-panier-etape-livraison");
  }


  @RequestMapping(value = "/mon-panier-etape-livraison")
  public ModelAndView myCartDelivery(HttpServletRequest request) {
    if (request.getSession().getAttribute("cartControlTunnel") == null
        || request.getSession().getAttribute("cartControlTunnel") == "[]") {
      return new ModelAndView("redirect:/mon-panier");
    }

    if (request.getSession().getAttribute("User") == null) {
      return new ModelAndView("redirect:/mon-panier-etape-connexion");
    }

    if (request.getSession().getAttribute("address") == null) {
      return new ModelAndView("redirect:/mon-panier-etape-adresse");
    }

    return new ModelAndView("cart/myCartDelivery");
  }



  @RequestMapping(value = "/mon-panier-etape-validation-transport")
  public ModelAndView myCartValidateTransport(HttpServletRequest request)
      throws UnsupportedEncodingException {
    if (request.getSession().getAttribute("cartControlTunnel") == null
        || request.getSession().getAttribute("cartControlTunnel").equals("")) {
      return new ModelAndView("redirect:/mon-panier");
    }

    if (request.getSession().getAttribute("User") == null
        || request.getSession().getAttribute("User").equals("")) {
      return new ModelAndView("redirect:/mon-panier-etape-connexion");
    }

    if (request.getSession().getAttribute("address") == null
        || request.getSession().getAttribute("address").equals("")) {
      return new ModelAndView("redirect:/mon-panier-etape-adresse");
    }
    request.setCharacterEncoding("utf-8");

    String chooseTransport = request.getParameter("chooseTransport");
    String commentaire = request.getParameter("commentaire");


    if (commentaire.length() > 255) {
      return new ModelAndView("redirect:/mon-panier-etape-livraison");
    }

    if (chooseTransport.equals("")) {
      return new ModelAndView("redirect:/mon-panier-etape-livraison");
    }



    Map<String, Object> listTransport = new HashMap<>();
    listTransport.put("id", chooseTransport);
    listTransport.put("commentaire", Util.ConvertStringToNull(commentaire));
    if (chooseTransport.equals("eco")) {
      listTransport.put("prix", new Float(10));
    } else if (chooseTransport.equals("exp")) {
      listTransport.put("prix", new Float(14));
    } else {
      return new ModelAndView("redirect:/mon-panier-etape-livraison");
    }
    request.getSession().setAttribute("transport", listTransport);

    return new ModelAndView("redirect:/mon-panier-etape-paiement");
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/mon-panier-etape-paiement")
  public ModelAndView myCartPayment(HttpServletRequest request) {
    if (request.getSession().getAttribute("cartControlTunnel") == null
        || request.getSession().getAttribute("cartControlTunnel") == "[]") {
      return new ModelAndView("redirect:/mon-panier");
    }

    if (request.getSession().getAttribute("User") == null) {
      return new ModelAndView("redirect:/mon-panier-etape-connexion");
    }

    if (request.getSession().getAttribute("address") == null) {
      return new ModelAndView("redirect:/mon-panier-etape-adresse");
    }

    if (request.getSession().getAttribute("transport") == null) {
      return new ModelAndView("redirect:/mon-panier-etape-livraison");
    }

    float calculTotalProduct = PCS.calculTotalProduct(
        (List<Map<String, Object>>) request.getSession().getAttribute("cartControlTunnel"));
    request.getSession().setAttribute("prixTotalProduct", calculTotalProduct);
    Map<String, Object> transport =
        (Map<String, Object>) request.getSession().getAttribute("transport");
    request.getSession().setAttribute("totalPrice",
        calculTotalProduct + (Float) transport.get("prix"));

    Map<String, Object> product = new HashMap<String, Object>();

    List<Map<String, Object>> allProduct = PCS.getAllCart(
        (List<Map<String, Object>>) request.getSession().getAttribute("cartControlTunnel"));
    product.put("products", allProduct);

    return new ModelAndView("cart/myCartPayment", product);
  }


  @RequestMapping(value = "/ajaxValidatePayment", method = RequestMethod.POST)
  @ResponseBody
  public String validatePayment(HttpServletRequest request) {
    String choosePayment = request.getParameter("choosePayment");

    if (request.getSession().getAttribute("cartControlTunnel") == null
        || request.getSession().getAttribute("cartControlTunnel") == "[]") {
      return "{\"statut\": \"error\",\"message\":  \"Une erreur est survenue.\"}";
    }

    if (request.getSession().getAttribute("User") == null) {
      return "{\"statut\": \"error\",\"message\":  \"Une erreur est survenue.\"}";
    }

    if (request.getSession().getAttribute("address") == null) {
      return "{\"statut\": \"error\",\"message\":  \"Une erreur est survenue.\"}";
    }

    if (request.getSession().getAttribute("transport") == null) {
      return "{\"statut\": \"error\",\"message\":  \"Une erreur est survenue.\"}";
    }

    if (request.getSession().getAttribute("totalPrice") == null) {
      return "{\"statut\": \"error\",\"message\":  \"Une erreur est survenue.\"}";
    }

    User user = (User) request.getSession().getAttribute("User");


    if (choosePayment.equals("PREPAYE") || choosePayment.equals("PAYPAL")
        || choosePayment.equals("CB")) {
      if (choosePayment.equals("PREPAYE")) {
        if (user.getComptePrepaye() < (Float) request.getSession().getAttribute("totalPrice")) {
          return "{\"statut\": \"error\",\"message\":  \"Votre compte prépayé est insuffisant.\"}";
        }
        request.getSession().setAttribute("payment", choosePayment);

        return "{\"statut\": \"ok\",\"redirect\": \"/finalisation-commande\"}";
      }
      request.getSession().setAttribute("payment", choosePayment);

      return "{\"statut\": \"ok\",\"redirect\": \"/page-payment\"}";
    }

    return "{\"statut\": \"error\",\"message\":  \"Une erreur est survenue.\"}";
  }
  
  @RequestMapping(value = "/page-payment")
  public ModelAndView pagePaymentPSP(HttpServletRequest request) {
    if (request.getSession().getAttribute("cartControlTunnel") == null
        || request.getSession().getAttribute("cartControlTunnel") == "[]") {
      return new ModelAndView("redirect:/mon-panier");
    }

    if (request.getSession().getAttribute("User") == null) {
      return new ModelAndView("redirect:/mon-panier-etape-connexion");
    }

    if (request.getSession().getAttribute("address") == null) {
      return new ModelAndView("redirect:/mon-panier-etape-adresse");
    }

    if (request.getSession().getAttribute("transport") == null) {
      return new ModelAndView("redirect:/mon-panier-etape-livraison");
    }

    if (request.getSession().getAttribute("payment") == null) {
      return new ModelAndView("redirect:/mon-panier-etape-paiement");
    }

    
    return new ModelAndView("cart/myPayment");
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/finalisation-commande")
  public ModelAndView finalyzeOrder(HttpServletRequest request) {
    if (request.getSession().getAttribute("cartControlTunnel") == null
        || request.getSession().getAttribute("cartControlTunnel") == "[]") {
      return new ModelAndView("redirect:/mon-panier");
    }

    if (request.getSession().getAttribute("User") == null) {
      return new ModelAndView("redirect:/mon-panier-etape-connexion");
    }

    if (request.getSession().getAttribute("address") == null) {
      return new ModelAndView("redirect:/mon-panier-etape-adresse");
    }

    if (request.getSession().getAttribute("transport") == null) {
      return new ModelAndView("redirect:/mon-panier-etape-livraison");
    }

    if (request.getSession().getAttribute("payment") == null) {
      return new ModelAndView("redirect:/mon-panier-etape-paiement");
    }


    Adress adress = (Adress) request.getSession().getAttribute("address");
    Map<String, Object> listTransport =
        (Map<String, Object>) request.getSession().getAttribute("transport");
    String modePayment = (String) request.getSession().getAttribute("payment");
    User user = (User) request.getSession().getAttribute("User");
    float totalPrice = (float) request.getSession().getAttribute("totalPrice");
    float prixTotalProduct = (float) request.getSession().getAttribute("prixTotalProduct");


    Order order =
        PCS.insertOrder(adress, listTransport, modePayment, user, totalPrice, prixTotalProduct);

    if (modePayment.equals("PREPAYE")) {
      user.setComptePrepaye(user.getComptePrepaye() - totalPrice);
      US.updateUser(user);
      request.getSession().setAttribute("User",user);
    }
    PCS.insertProductOrder((List<Map<String, Object>>) request.getSession().getAttribute("cartControlTunnel"),order);
    request.getSession().setAttribute("cart", null);
    request.getSession().setAttribute("cartControlTunnel", null);
    request.getSession().setAttribute("address", null);
    request.getSession().setAttribute("transport", null);
    request.getSession().setAttribute("payment", null);
    request.getSession().setAttribute("totalPrice", null);
    request.getSession().setAttribute("prixTotalProduct", null);
    request.getSession().setAttribute("nbCart", 0);
    request.getSession().setAttribute("lastOrder", order);

    return new ModelAndView("redirect:/commande-valider");
  }

  @RequestMapping(value = "/commande-valider")
  public ModelAndView orderValidate(HttpServletRequest request) {
    if(request.getSession().getAttribute("lastOrder") == null){
      return new ModelAndView("redirect:/mon-panier");
    }
    
    return new ModelAndView("cart/orderValidate");
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/ajaxDeleteProductCart", method = RequestMethod.POST)
  @ResponseBody
  public String deleteProductCart(HttpServletRequest request) {
    String idString = request.getParameter("id");
    List<Map<String, Object>> listMapCart = new ArrayList<Map<String, Object>>();

    if (idString != "" && Util.convertToInt(idString)) {
      int id = Integer.parseInt(idString);

      if (request.getSession().getAttribute("cart") != null
          && request.getSession().getAttribute("cart") != "") {
        List<Map<String, Object>> allCart =
            (List<Map<String, Object>>) request.getSession().getAttribute("cart");

        for (Map<String, Object> cart : allCart) {
          int idSession = (int) cart.get("id");

          if (!(idSession == id)) {

            Map<String, Object> listCart = new HashMap<>();
            listCart.put("id", idSession);
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

    int number = 1;

    if (idString != "" && Util.convertToInt(idString) && numberString != ""
        && Util.convertToInt(numberString)) {
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
          if (idSession == id) {
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
    Boolean newProduct = true;

    if (idString != "" && Util.convertToInt(idString)) {
      int id = Integer.parseInt(idString);
      List<Map<String, Object>> listMapCart;

      if (request.getSession().getAttribute("cart") == null
          || request.getSession().getAttribute("cart").equals("")) {
        listMapCart = new ArrayList<Map<String, Object>>();
      } else {
        listMapCart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");
        for (Map<String, Object> mapCart : listMapCart) {
          if (((int) mapCart.get("id")) == id) {
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

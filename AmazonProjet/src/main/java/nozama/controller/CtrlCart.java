package nozama.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
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

/**
 * All controller for cart or control tunnel
 *
 */
@Controller
public class CtrlCart {

  /**
   * It's a singleton for the service @ProductCartServiceImpl
   */
  @Autowired
  private ProductCartServiceImpl PCS;

  /**
   * It's a singleton for the service @UserServiceImpl
   */
  @Autowired
  private UserServiceImpl US;

  /**
   * [Ctrl] go to the recapitulatif cart
   * 
   * @param request
   * @return view my cart
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/mon-panier")
  public ModelAndView myCart(HttpServletRequest request) {
    Map<String, Object> products = new HashMap<String, Object>();

    if (request.getSession().getAttribute("cart") != null) {
      products.put("products",
          PCS.getAllCart((List<Map<String, Object>>) request.getSession().getAttribute("cart")));
    }

    return new ModelAndView("cart/myCart", products);
  }

  /**
   * [Ctrl] After the recapitulatif of cart for initialise a new variable session cartControlTunnel
   * 
   * @param request
   * @return redirect in mon-panier-etape-connexion (with us a etape and redirection for using F5
   *         and don't reload action)
   */
  @RequestMapping(value = "/mon-panier-validation-panier")
  public ModelAndView myCartValidation(HttpServletRequest request) {
    request.getSession().setAttribute("cartControlTunnel",
        request.getSession().getAttribute("cart"));

    return new ModelAndView("redirect:/mon-panier-etape-connexion");
  }

  /**
   * [Ctrl] Step 2 of cart for signup or signin
   * 
   * @param request
   * @return
   */
  @RequestMapping(value = "/mon-panier-etape-connexion")
  public ModelAndView myCartSignInOrSignUp(HttpServletRequest request) {

    Map<String, Object> redirect = new HashMap<String, Object>();
    redirect.put("redirect", "/mon-panier-etape-adresse");// redirect after singin or signup

    Map<String, Object> conditionCart = Util.conditionCart(1, request);
    if ((boolean) conditionCart.get("checkCondition")) {
      return new ModelAndView("redirect:/" + (String) conditionCart.get("redirect"));
    }

    return new ModelAndView("cart/myCartConnexion", redirect);
  }

  /**
   * [Ctrl] Step 3 for create or using a adress
   * 
   * @param request
   * @return view myCartAdresse or redirect if we don't have information
   */
  @RequestMapping(value = "/mon-panier-etape-adresse")
  public ModelAndView myCartAdress(HttpServletRequest request) {

    Map<String, Object> conditionCart = Util.conditionCart(2, request);
    if ((boolean) conditionCart.get("checkCondition")) {
      return new ModelAndView("redirect:/" + (String) conditionCart.get("redirect"));
    }

    User user = (User) request.getSession().getAttribute("User");

    Map<String, Object> adress = new HashMap<String, Object>();
    adress.put("adresss", US.getAllAdressByUser(user));
    String message = request.getParameter("message"); // It's a message the register adress have an
                                                      // error
    if (Util.checkStringNotNull(message)) {
      adress.put("message", message);
    }

    return new ModelAndView("cart/myCartAdresse", adress);
  }

  /**
   * [Ctrl] Create or using an adress
   * 
   * @param request
   * @return redirect in mon-panier-etape-livraison or redirect if error
   * @throws UnsupportedEncodingException
   */
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

    Map<String, Object> conditionCart = Util.conditionCart(2, request);
    if ((boolean) conditionCart.get("checkCondition")) {
      return new ModelAndView("redirect:/" + (String) conditionCart.get("redirect"));
    }

    User user = (User) request.getSession().getAttribute("User");

    if (Util.checkStringNotNull(idAdressString)) {
      if (!Util.checkConvertToInt(idAdressString)) {
        return new ModelAndView("redirect:/mon-panier-etape-adresse",
            Util.returnMessage("Une erreur est survenue."));
      }

      int idAdress = Integer.parseInt(idAdressString);
      Adress adress = US.checkAdressByUser(idAdress, user);
      if (adress == null) {
        return new ModelAndView("redirect:/mon-panier-etape-adresse/",
            Util.returnMessage("Une erreur est survenue."));
      }
      request.getSession().setAttribute("address", adress);
    } else {
      if (Util.checkStringIsNull(name) || Util.checkStringIsNull(nameLastName) || Util.checkStringIsNull(adressPrincipal)
          || Util.checkStringIsNull(codePostalString) || Util.checkStringIsNull(pays) || Util.checkStringIsNull(numberPhone)
          || Util.checkStringIsNull(city)) {
        return new ModelAndView("redirect:/mon-panier-etape-adresse",
            Util.returnMessage("Tout les champs sont obligatoires."));
      } else
        if (name.length() > 255 || nameLastName.length() > 255 || adressPrincipal.length() > 1024
            || adressSecondaire.length() > 1024 || region.length() > 255
            || codePostalString.length() > 5 || numberPhone.length() > 10 || city.length() > 255) {
        return new ModelAndView("redirect:/mon-panier-etape-adresse",
            Util.returnMessage("Attention à la longueur des champs."));
      }

      int codePostal;
      if (!Util.checkConvertToInt(codePostalString)) {
        return new ModelAndView("redirect:/mon-panier-etape-adresse",
            Util.returnMessage("Le code postal doit être un chiffre"));
      }
      codePostal = Integer.parseInt(codePostalString);

      if (!Util.checkConvertToInt(numberPhone)) {
        return new ModelAndView("redirect:/mon-panier-etape-adresse",
            Util.returnMessage("Le numéro de téléphone doit être un chiffre"));
      }

      final Adress adress = US.insertAdress(name, nameLastName, adressPrincipal, adressSecondaire,
          region, pays, user, codePostal, numberPhone, city);

      request.getSession().setAttribute("address", adress);
    }

    return new ModelAndView("redirect:/mon-panier-etape-livraison");
  }


  /**
   * [Ctrl] Step 4 for choose delivery
   * 
   * @param request
   * @return view myCartDelivery or redirect if error
   */
  @RequestMapping(value = "/mon-panier-etape-livraison")
  public ModelAndView myCartDelivery(HttpServletRequest request) {
    Map<String, Object> messageError = new HashMap<String, Object>();
    String message = request.getParameter("message"); // It's a message the register adress have an
                                                      // error
    if (Util.checkStringNotNull(message)) {
      messageError.put("message", message);
    }

    Map<String, Object> conditionCart = Util.conditionCart(3, request);
    if ((boolean) conditionCart.get("checkCondition")) {
      return new ModelAndView("redirect:/" + (String) conditionCart.get("redirect"));
    }

    return new ModelAndView("cart/myCartDelivery", messageError);
  }


  /**
   * [Ctrl] validation delivery and redirect to view myCartDelivery
   * 
   * @param request
   * @return mon-panier-etape-paiement or redirect if error
   * @throws UnsupportedEncodingException
   */
  @RequestMapping(value = "/mon-panier-etape-validation-transport")
  public ModelAndView myCartValidateTransport(HttpServletRequest request)
      throws UnsupportedEncodingException {

    Map<String, Object> conditionCart = Util.conditionCart(3, request);
    if ((boolean) conditionCart.get("checkCondition")) {
      return new ModelAndView("redirect:/" + (String) conditionCart.get("redirect"));
    }

    request.setCharacterEncoding("utf-8");
    String chooseTransport = request.getParameter("chooseTransport");
    String commentaire = request.getParameter("commentaire");

    if (commentaire.length() > 255) {
      return new ModelAndView("redirect:/mon-panier-etape-livraison",
          Util.returnMessage("Vérifier la longueur des champs."));
    }

    if (chooseTransport.isEmpty()) {
      return new ModelAndView("redirect:/mon-panier-etape-livraison",
          Util.returnMessage("Le choix d'un transporteur est obligatoire."));
    }

    Map<String, Object> listTransport = new HashMap<>();
    listTransport.put("id", chooseTransport);
    listTransport.put("commentaire", Util.ConvertStringToNull(commentaire));

    if (chooseTransport.equals("eco")) {
      listTransport.put("prix", new Float(10));
    } else if (chooseTransport.equals("exp")) {
      listTransport.put("prix", new Float(14));
    } else {
      return new ModelAndView("redirect:/mon-panier-etape-livraison",
          Util.returnMessage("Une erreur est survenue."));
    }

    request.getSession().setAttribute("transport", listTransport);

    return new ModelAndView("redirect:/mon-panier-etape-paiement");
  }

  /**
   * [Ctrl] Step 5 choose your payment for validate a cart
   * 
   * @param request
   * @return view myCartPayment
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/mon-panier-etape-paiement")
  public ModelAndView myCartPayment(HttpServletRequest request) {

    Map<String, Object> conditionCart = Util.conditionCart(4, request);
    if ((boolean) conditionCart.get("checkCondition")) {
      return new ModelAndView("redirect:/" + (String) conditionCart.get("redirect"));
    }

    List<Map<String, Object>> cartControlTunnel =
        (List<Map<String, Object>>) request.getSession().getAttribute("cartControlTunnel");

    float calculTotalProduct = PCS.calculTotalProduct(cartControlTunnel);
    request.getSession().setAttribute("prixTotalProduct", calculTotalProduct);
    Map<String, Object> transport =
        (Map<String, Object>) request.getSession().getAttribute("transport");
    request.getSession().setAttribute("totalPrice",
        calculTotalProduct + (Float) transport.get("prix"));

    Map<String, Object> products = new HashMap<String, Object>();

    final List<Map<String, Object>> allProduct = PCS.getAllCart(cartControlTunnel);
    products.put("products", allProduct);

    return new ModelAndView("cart/myCartPayment", products);
  }


  /**
   * 
   * [Ctrl] [Ajax] Validate a payment and choose the ctrl suitable
   * 
   * @param request
   * @return a json for javascript
   * @throws JSONException
   */
  @RequestMapping(value = "/ajaxValidatePayment", method = RequestMethod.POST)
  @ResponseBody
  public String validatePayment(HttpServletRequest request) throws JSONException {
    String choosePayment = request.getParameter("choosePayment");

    Map<String, Object> conditionCart = Util.conditionCart(5, request);
    if ((boolean) conditionCart.get("checkCondition")) {
      return Util.getJsonStatutRedirect("success", (String) conditionCart.get("redirect"));
    }

    User user = (User) request.getSession().getAttribute("User");

    if (choosePayment.equals("PREPAYE") || choosePayment.equals("PAYPAL")
        || choosePayment.equals("CB")) {
      if (choosePayment.equals("PREPAYE")) {
        if (user.getComptePrepaye() < (Float) request.getSession().getAttribute("totalPrice")) {
          return Util.getJsonStatutMessage("error", "Votre compte prépayé est insuffisant.");
        }
        request.getSession().setAttribute("payment", choosePayment);
        return Util.getJsonStatutRedirect("success", "finalisation-commande");
      }
      request.getSession().setAttribute("payment", choosePayment);
      return Util.getJsonStatutRedirect("success", "page-payment");
    }
    return Util.getJsonStatutMessage("error", "Une erreur est survenue.");
  }

  /**
   * [Ctrl] page payment for CB or Paypal
   * 
   * @param request
   * @return View myPayment
   */
  @RequestMapping(value = "/page-payment")
  public ModelAndView pagePaymentPSP(HttpServletRequest request) {

    Map<String, Object> conditionCart = Util.conditionCart(5, request);
    if ((boolean) conditionCart.get("checkCondition")) {
      return new ModelAndView("redirect:/" + (String) conditionCart.get("redirect"));
    }

    return new ModelAndView("cart/myPayment");
  }

  /**
   * [Ctrl] finalisation of commande for create a orderId and delete all session
   * 
   * @param request
   * @return redirect to commande-valider
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/finalisation-commande")
  public ModelAndView finalyzeOrder(HttpServletRequest request) {
    Map<String, Object> conditionCart = Util.conditionCart(6, request);
    if ((boolean) conditionCart.get("checkCondition")) {
      return new ModelAndView("redirect:/" + (String) conditionCart.get("redirect"));
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
      request.getSession().setAttribute("User", user);
    }
    PCS.insertProductOrder(
        (List<Map<String, Object>>) request.getSession().getAttribute("cartControlTunnel"), order);
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

  /**
   * [Ctrl] redirection to the page of orderId
   * 
   * @param request
   * @return view orderValidate
   */
  @RequestMapping(value = "/commande-valider")
  public ModelAndView orderValidate(HttpServletRequest request) {
    if (request.getSession().getAttribute("lastOrder") == null) {
      return new ModelAndView("redirect:/mon-panier");
    }

    return new ModelAndView("cart/orderValidate");
  }

  /**
   * [Ctrl] [Ajax] Delete a Product in cart
   * 
   * @param request
   * @return JSON
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/ajaxDeleteProductCart", method = RequestMethod.POST)
  @ResponseBody
  public String deleteProductCart(HttpServletRequest request) {
    String idString = request.getParameter("id");
    List<Map<String, Object>> listMapCart = new ArrayList<Map<String, Object>>();

    if (Util.checkStringNotNull(idString) && Util.checkConvertToInt(idString)) {
      int id = Integer.parseInt(idString);

      if (request.getSession().getAttribute("cart") != null) {
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
        if (listMapCart.size() == 0) {
          request.getSession().setAttribute("cart", null);
        } else {
          request.getSession().setAttribute("cart", listMapCart);
        }
      }
    }

    return Integer.toString(listMapCart.size());
  }

  /**
   * [Ctrl] [Ajax] Delete all Product in cart
   * 
   * @param request
   * @return JSON
   */
  @RequestMapping(value = "/ajaxDeleteAllProductCart", method = RequestMethod.POST)
  @ResponseBody
  public void deleteAllProductCart(HttpServletRequest request) {
    request.getSession().setAttribute("nbCart", 0);
    request.getSession().setAttribute("cart", null);
  }

  /**
   * [Ctrl] [Ajax] Delete change number of product for a cart
   * 
   * @param request
   * @return JSON
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/ajaxChangeNumberProductCart", method = RequestMethod.POST)
  @ResponseBody
  public String changeNumberProductCart(HttpServletRequest request) throws JSONException {
    String idString = request.getParameter("id");
    String numberString = request.getParameter("number");

    int number = 1;

    if (Util.checkStringNotNull(idString) && Util.checkConvertToInt(idString) && Util.checkStringNotNull(numberString)
        && Util.checkConvertToInt(numberString)) {
      int id = Integer.parseInt(idString);
      number = Integer.parseInt(numberString);
      if (number < 0) {
        number = 1;
      } else if (number > 100) {
        number = 1;
      }
      if (request.getSession().getAttribute("cart") != null) {
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


    return Util.getJsonNumber(number);
  }


  /**
   * [Ctrl] [Ajax] Add product in cart
   * 
   * @param request
   * @return JSON
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/ajaxAddCart", method = RequestMethod.POST)
  @ResponseBody
  public String addInMyCart(HttpServletRequest request) throws JSONException {

    String idString = request.getParameter("id");
    Boolean newProduct = true;

    if (Util.checkStringNotNull(idString) && Util.checkConvertToInt(idString)) {
      int id = Integer.parseInt(idString);
      List<Map<String, Object>> listMapCart;

      if (request.getSession().getAttribute("cart") == null) {
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

      return Util.getJsonStatutMessageNbCart("success", "Votre produit à été ajouté au panier.",
          listMapCart.size());
    } else {
      return Util.getJsonStatutMessage("error", "Une erreur est survenue.");
    }
  }

}

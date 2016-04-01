package misys.controller;

import java.io.UnsupportedEncodingException;
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

import misys.model.Adress;
import misys.model.Order;
import misys.model.User;
import misys.service.UserServiceImpl;
import misys.util.Util;

/**
 * All controller for user
 *
 */
@Controller
public class CtrlUser {

  /**
   * It's a singleton for the service @UserServiceImpl
   */
  @Autowired
  private UserServiceImpl US;

  /**
   * [Ctrl] view signUp
   * 
   * @param request
   * @return view signUp
   */
  @RequestMapping(value = "/inscription")
  public String signUp(HttpServletRequest request) {
    if (request.getSession().getAttribute("User") != null) {
      return "redirect:/";
    }
    return "authentication/signUp";
  }

  /**
   * [Ctrl] view signIn
   * 
   * @param request
   * @return view signIn
   */
  @RequestMapping(value = "/connexion")
  public String signIn(HttpServletRequest request) {
    if (request.getSession().getAttribute("User") != null) {
      return "redirect:/";
    }
    return "authentication/signIn";
  }

  /**
   * [Ctrl] [ajax] connect an user
   * 
   * @param request
   * @return JSON
   * @throws UnsupportedEncodingException
   * @throws JSONException
   */
  @RequestMapping(value = "/ajaxConnexion", method = RequestMethod.POST)
  @ResponseBody
  public String ajaxSignIn(HttpServletRequest request)
      throws UnsupportedEncodingException, JSONException {
    request.setCharacterEncoding("UTF-8");

    String email = request.getParameter("email");
    String password = request.getParameter("pwd");

    if (request.getSession().getAttribute("User") != null) {
      return Util.getJsonStatut("success");
    } else if (!US.isEmailAdress(email)) {
      return Util.getJsonStatutMessage("error", "L'adresse email n'est pas valide.");
    } else if (Util.checkStringIsNull(email) || Util.checkStringIsNull(password)) {
      return Util.getJsonStatutMessage("error", "Tout les champs sont obligatoires.");
    }

    User user = US.connexion(email, password);
    if (user == null) {
      return Util.getJsonStatutMessage("error", "une erreur est survenue.");
    }

    request.getSession().setAttribute("User", user);
    return Util.getJsonStatutRedirect("success", "");
  }

  /**
   * [Ctrl] [ajax] register an user
   * 
   * @param request
   * @return
   * @throws UnsupportedEncodingException
   * @throws JSONException
   */
  @RequestMapping(value = "/ajaxInscription", method = RequestMethod.POST)
  @ResponseBody
  public String ajaxSignUp(HttpServletRequest request)
      throws UnsupportedEncodingException, JSONException {
    request.setCharacterEncoding("UTF-8");

    String gender = request.getParameter("gender");
    String name = request.getParameter("name");
    String lastName = request.getParameter("lastName");
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    if (request.getSession().getAttribute("User") != null) {
      return Util.getJsonStatut("success");
    } else if (password.length() < 6) {
      return Util.getJsonStatutMessage("error",
          "Votre mot de passe doit contenir au minimun 6 caratères.");
    } else if (Util.checkStringIsNull(gender) || Util.checkStringIsNull(name) || 
        Util.checkStringIsNull(lastName) || Util.checkStringIsNull(email)
        || Util.checkStringIsNull(password)) {
      return Util.getJsonStatutMessage("error", "Tout les champs sont obligatoires.");
    } else if (!US.isEmailAdress(email)) {
      return Util.getJsonStatutMessage("error", "L'adresse email n'est pas valide.");
    } else if (US.checkEmail(email)) {
      return Util.getJsonStatutMessage("error", "Cette adresse email est déja utilisé.");
    } else if (name.length() > 255 || lastName.length() > 255 || email.length() > 255
        || password.length() > 255) {
      return Util.getJsonStatutMessage("error", "Attention à la longueur des champs.");
    } else if (gender.equals("H") && gender.equals("F")) {
      return Util.getJsonStatutMessage("error", "Une erreur est survenue.");
    }

    User user = US.register(gender, name, lastName, email, password, US.getIpAdresse(request));
    request.getSession().setAttribute("User", user);

    return Util.getJsonStatutRedirect("success", "");
  }

  /**
   * [Ctrl] [Ajax] Disconnect an user
   * 
   * @param request
   * @return view home page
   */
  @RequestMapping(value = "/se-deconnecter")
  public String ajaxDisconnect(HttpServletRequest request) {
    request.getSession().setAttribute("User", null);
    request.getSession().setAttribute("cartControlTunnel", null);
    request.getSession().setAttribute("address", null);
    request.getSession().setAttribute("transport", null);
    request.getSession().setAttribute("payment", null);
    request.getSession().setAttribute("totalPrice", null);
    request.getSession().setAttribute("prixTotalProduct", null);
    request.getSession().setAttribute("lastOrder", null);

    return "redirect:/";
  }

  /**
   * [Ctrl] [Ajax] Add money in current account
   * 
   * @param request
   * @return Json
   * @throws JSONException
   */
  @RequestMapping(value = "/ajaxAddMoneyAccount")
  @ResponseBody
  public String ajaxAddMoneyAccount(HttpServletRequest request) throws JSONException {
    String numberAddAccountString = request.getParameter("numberAddAccount");
    if (request.getSession().getAttribute("User") != null) {
      if (Util.checkConvertToFloat(numberAddAccountString)
          && Util.isPrice(numberAddAccountString)) {
        float numberAddAcount = Float.parseFloat(numberAddAccountString);
        if (numberAddAcount >= 5 && numberAddAcount <= 1000) {
          User user = (User) request.getSession().getAttribute("User");
          user.setComptePrepaye(user.getComptePrepaye() + numberAddAcount);
          US.updateUser(user);
          return Util.getJsonStatutMessageArgent("success", "Compte crédité !",
              user.getComptePrepaye());
        }
      }
    }
    return Util.getJsonStatutMessage("error", "Une erreur est survenue.");
  }

  /**
   * [Ctrl] [Ajax] Delete an adress
   * 
   * @param request
   * @returnJson
   * @throws JSONException
   */
  @RequestMapping(value = "/ajaxDeleteAdress")
  @ResponseBody
  public String ajaxDeleteAdress(HttpServletRequest request) throws JSONException {
    String idAdressString = request.getParameter("idAdress");
    if (request.getSession().getAttribute("User") != null) {
      User user = (User) request.getSession().getAttribute("User");
      if (Util.checkStringNotNull(idAdressString) && Util.checkConvertToInt(idAdressString)) {
        int idAdress = Integer.parseInt(idAdressString);
        Adress adress = US.checkAdressByUser(idAdress, user);
        if (adress != null) {
          US.deleteAdress(adress);
          return Util.getJsonStatut("success");
        }

      }
    }
    return Util.getJsonStatut("error");
  }

  /**
   * [Ctrl] [Ajax] Update an adress by id
   * 
   * @param request
   * @return Json
   * @throws UnsupportedEncodingException
   * @throws JSONException
   */
  @RequestMapping(value = "/ajaxUpdateAdress")
  @ResponseBody
  public String ajaxUpdateAdress(HttpServletRequest request)
      throws UnsupportedEncodingException, JSONException {
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

    if (request.getSession().getAttribute("User") != null) {
      User user = (User) request.getSession().getAttribute("User");
      if (Util.checkConvertToInt(idAdressString)) {
        int idAdress = Integer.parseInt(idAdressString);
        Adress adressCheck = US.checkAdressByUser(idAdress, user);
        if (adressCheck != null) {
          if (Util.checkStringIsNull(name) || Util.checkStringIsNull(nameLastName) || Util.checkStringIsNull(adressPrincipal)
              || Util.checkStringIsNull(codePostalString) || Util.checkStringIsNull(pays) || Util.checkStringIsNull(numberPhone)
              || Util.checkStringIsNull(city)) {
            return Util.getJsonStatutMessage("error", "Tout les champs sont obligatoires.");
          } else if (name.length() > 255 || nameLastName.length() > 255
              || adressPrincipal.length() > 1024 || adressSecondaire.length() > 1024
              || region.length() > 255 || codePostalString.length() > 5 || numberPhone.length() > 10
              || city.length() > 255) {
            return Util.getJsonStatutMessage("error", "Attention à la longueur des champs.");
          }

          int codePostal;
          if (!Util.checkConvertToInt(codePostalString)) {
            return Util.getJsonStatutMessage("error", "Le code postal doit être un chiffre.");
          }
          codePostal = Integer.parseInt(codePostalString);

          if (!Util.checkConvertToInt(numberPhone)) {
            return Util.getJsonStatutMessage("error",
                "Le numéro de téléphone doit être un chiffre.");
          }

          US.updateAdress(idAdress, name, nameLastName, adressPrincipal, adressSecondaire, region,
              pays, user, codePostal, numberPhone, city);
          return Util.getJsonStatut("success");

        }
      }
    }

    return Util.getJsonStatut("error");
  }

  /**
   * [Ctrl] [Ajax] Add an adress
   * 
   * @param request
   * @return
   * @throws UnsupportedEncodingException
   * @throws JSONException
   */
  @RequestMapping(value = "/ajaxAjoutAdresse", method = RequestMethod.POST)
  @ResponseBody
  public String myCartCalidateAdress(HttpServletRequest request)
      throws UnsupportedEncodingException, JSONException {
    request.setCharacterEncoding("UTF-8");

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
      return Util.getJsonStatutMessage("error", "Vous n'êtes plus connecter.");
    }

    User user = (User) request.getSession().getAttribute("User");


    if (Util.checkStringIsNull(name) || Util.checkStringIsNull(nameLastName) || Util.checkStringIsNull(adressPrincipal)
        || Util.checkStringIsNull(codePostalString) || Util.checkStringIsNull(pays) || Util.checkStringIsNull(numberPhone)
        || Util.checkStringIsNull(city)) {
      return Util.getJsonStatutMessage("error", "Tout les champs sont obligatoires.");
    } else if (name.length() > 255 || nameLastName.length() > 255 || adressPrincipal.length() > 1024
        || adressSecondaire.length() > 1024 || region.length() > 255
        || codePostalString.length() > 5 || numberPhone.length() > 10 || city.length() > 255) {
      return Util.getJsonStatutMessage("error", "Attention à la longueur des champs.");
    }

    int codePostal;
    if (!Util.checkConvertToInt(codePostalString)) {
      return Util.getJsonStatutMessage("error", "Le code postal doit être un chiffre.");
    }
    codePostal = Integer.parseInt(codePostalString);

    if (!Util.checkConvertToInt(numberPhone)) {
      return Util.getJsonStatutMessage("error", "Le numéro de téléphone doit être un chiffre.");
    }

    final Adress adress = US.insertAdress(name, nameLastName, adressPrincipal, adressSecondaire,
        region, pays, user, codePostal, numberPhone, city);

    request.getSession().setAttribute("address", adress);

    return Util.getJsonStatutId("success", adress.getIdAdress());
  }

  /**
   * [Ctrl] Page account with more informations
   * 
   * @param request
   * @return view myAccount
   */
  @RequestMapping(value = "/mon-compte")
  @ResponseBody
  public ModelAndView monCompte(HttpServletRequest request) {
    Map<String, Object> variableParam = new HashMap<String, Object>();
    if (request.getSession().getAttribute("User") != null) {
      User user = (User) request.getSession().getAttribute("User");
      variableParam.put("user", user);
      variableParam.put("adresss", US.getAllAdressByUser(user));
      List<Order> orders = US.getOrder(user);
      if (orders.size() > 0) {
        variableParam.put("commandes", orders);
      }
      return new ModelAndView("account/myAccount", variableParam);
    }
    return new ModelAndView("redirect:/", variableParam);
  }

}

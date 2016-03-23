package nozama.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
import nozama.service.UserServiceImpl;
import nozama.util.Util;

@Controller
public class CtrlUser {

  @Autowired
  private UserServiceImpl US;

  @RequestMapping(value = "/inscription")
  public String signUp(HttpServletRequest request) {
    if (request.getSession().getAttribute("User") != null) {
      return "redirect:/";
    }
    return "authentication/signUp";
  }

  @RequestMapping(value = "/connexion")
  public String signIn(HttpServletRequest request) {
    if (request.getSession().getAttribute("User") != null) {
      return "redirect:/";
    }
    return "authentication/signIn";
  }

  @RequestMapping(value = "/ajaxConnexion", method = RequestMethod.POST)
  @ResponseBody
  public String ajaxSignIn(HttpServletRequest request) throws UnsupportedEncodingException {
    request.setCharacterEncoding("UTF-8");

    String email = request.getParameter("email");
    String password = request.getParameter("pwd");

    if (request.getSession().getAttribute("User") != null) {
      return "{\"statut\": \"ok\"}";
    } else if (!US.isEmailAdress(email)) {
      return "{\"statut\": \"error\",\"message\":  \"L'adresse email n'est pas valide.\"}";
    } else if (email == "" || password == "") {
      return "{\"statut\": \"error\",\"message\":  \"Tout les champs sont obligatoires.\"}";
    }

    User user = US.connexion(email, password);
    if (user == null) {
      return "{\"statut\": \"error\",\"message\":  \"une erreur est survenue.\"}";
    }

    request.getSession().setAttribute("User", user);

    return "{\"statut\": \"ok\",\"redirect\": \"/\"}";

  }

  @RequestMapping(value = "/ajaxInscription", method = RequestMethod.POST)
  @ResponseBody
  public String ajaxSignUp(HttpServletRequest request) throws UnsupportedEncodingException {
    request.setCharacterEncoding("UTF-8");

    String gender = request.getParameter("gender");
    String name = request.getParameter("name");
    String lastName = request.getParameter("lastName");
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    if (request.getSession().getAttribute("User") != null) {
      return "{\"statut\": \"ok\"}";
    } else if (password.length() < 6) {
      return "{\"statut\": \"error\",\"message\":  \"Votre mot de passe doit contenir au minimun 6 caratères.\"}";
    } else if (gender == "" || name == "" || lastName == "" || email == "" || password == "") {
      return "{\"statut\": \"error\",\"message\":  \"Tout les champs sont obligatoires.\"}";
    } else if (!US.isEmailAdress(email)) {
      return "{\"statut\": \"error\",\"message\":  \"L'adresse email n'est pas valide.\"}";
    } else if (US.checkEmail(email)) {
      return "{\"statut\": \"error\",\"message\":  \"Cette adresse email est déja utilisé.\"}";
    } else if (name.length() > 255 || lastName.length() > 255 || email.length() > 255
        || password.length() > 255) {
      return "{\"statut\": \"error\",\"message\":  \"Attention à la longueur des champs.\"}";
    } else if (gender.equals("H") && gender.equals("F")){
      return "{\"statut\": \"error\",\"message\":  \"Une erreur est survenue.\"}";
    }

    User user = US.register(gender, name, lastName, email, password, US.getIpAdresse(request));
    request.getSession().setAttribute("User", user);

    return "{\"statut\": \"ok\",\"redirect\": \"/\"}";
  }

  @RequestMapping(value = "/se-deconnecter")
  public String ajaxDisconnect(HttpServletRequest request) {
    request.getSession().setAttribute("User", null);
    return "redirect:/";
  }
  
  
  @RequestMapping(value = "/ajaxAddMoneyAccount")
  @ResponseBody
  public String ajaxAddMoneyAccount(HttpServletRequest request) {
    String numberAddAccountString = request.getParameter("numberAddAccount");
    if (request.getSession().getAttribute("User") != null) {  
      if(Util.convertToFloat(numberAddAccountString) && Util.isPrice(numberAddAccountString)){
        float numberAddAcount = Float.parseFloat(numberAddAccountString);
         if(numberAddAcount >= 5 && numberAddAcount <= 1000){
           User user = (User) request.getSession().getAttribute("User");
           user.setComptePrepaye(user.getComptePrepaye() + numberAddAcount);
           US.updateUser(user);
           return "{\"statut\": \"succes\",\"message\":  \"Compte crédité !\",\"argent\":  " + user.getComptePrepaye() + "}";
         }
      }
    }
    return "{\"statut\": \"error\",\"message\":  \"Une erreur est survenue.\"}";
  }
  
  @RequestMapping(value = "/ajaxDeleteDeleteAdress")
  @ResponseBody
  public String ajaxDeleteAdress(HttpServletRequest request) {
    String idAdressString = request.getParameter("idAdress");
    if (request.getSession().getAttribute("User") != null) {
      User user = (User) request.getSession().getAttribute("User");
      if (idAdressString != "" && Util.convertToInt(idAdressString)) {
        int idAdress = Integer.parseInt(idAdressString);
        Adress adress = US.checkAdressByUser(idAdress, user);
        if (adress != null) {
          US.deleteAdress(adress);
          return "{\"statut\": \"ok\"}";
        }

      }
    }
    return "{\"statut\": \"nok\"}";
  }

  @RequestMapping(value = "/ajaxUpdateAdress")
  @ResponseBody
  public String ajaxUpdateAdress(HttpServletRequest request) throws UnsupportedEncodingException {
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

    if (request.getSession().getAttribute("User") != null) {
      User user = (User) request.getSession().getAttribute("User");
      if (Util.convertToInt(idAdressString)) {
        int idAdress = Integer.parseInt(idAdressString);
        Adress adressCheck = US.checkAdressByUser(idAdress, user);
        if (adressCheck != null) {
          if (name == "" || nameLastName == "" || adressPrincipal == "" || codePostalString == ""
              || pays == "" || numberPhone == "") {
            return "{\"statut\": \"nok\"}";
          } else if (name.length() > 255 || nameLastName.length() > 255
              || adressPrincipal.length() > 1024 || adressSecondaire.length() > 1024
              || region.length() > 255 || codePostalString.length() > 5
              || numberPhone.length() > 10) {
            return "{\"statut\": \"nok\"}";
          }

          int codePostal;
          if (!Util.convertToInt(codePostalString)) {
            return "{\"statut\": \"nok\"}";
          }
          codePostal = Integer.parseInt(codePostalString);

          if (!Util.convertToInt(numberPhone)) {
            return "{\"statut\": \"nok\"}";
          }

          US.updateAdress(idAdress, name, nameLastName, adressPrincipal, adressSecondaire, region,
              pays, user, codePostal, numberPhone);

        }
      }
    }

    return "{\"statut\": \"nok\"}";
  }
  
  
  

  @RequestMapping(value = "/mon-compte")
  public ModelAndView monCompte(HttpServletRequest request) {
    Map<String, Object> variableParam = new HashMap<String, Object>();
    if (request.getSession().getAttribute("User") != null) {
      User user = (User) request.getSession().getAttribute("User");
      variableParam.put("user", user);
      variableParam.put("adresss", US.getAllAdressByUser(user));
      return new ModelAndView("mon-compte", variableParam);
    }
    return new ModelAndView("redirect:/", variableParam);
  }
}

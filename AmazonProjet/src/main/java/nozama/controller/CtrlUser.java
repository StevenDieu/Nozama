package nozama.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nozama.model.User;
import nozama.service.UserServiceImpl;

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
  public String ajaxSignIn(HttpServletRequest request) {

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
  public String ajaxSignUp(HttpServletRequest request) {

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
      return "{\"statut\": \"nok\",\"message\":  \"Cette adresse email est déja utilisé.\"}";
    } else if (name.length() > 255 || lastName.length() > 255 || email.length() > 255
        || password.length() > 255) {
      return "{\"statut\": \"nok\",\"message\":  \"Attention à la longueur des champs.\"}";
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
}

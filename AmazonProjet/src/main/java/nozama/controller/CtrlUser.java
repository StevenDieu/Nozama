package nozama.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import nozama.model.Users;
import nozama.service.UserService;

@Controller
public class CtrlUser {

	@Autowired
	private UserService US;

	@RequestMapping(value = "/inscription")
	public String signUp(HttpServletRequest request) {
		return "authentication/signUp";
	}

	@RequestMapping(value = "/connexion")
	public ModelAndView signIn(HttpServletRequest request) {
		return new ModelAndView("authentication/signIn");
	}

	@RequestMapping(value = "/ajaxConnexion", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxSignIn(HttpServletRequest request) {

		String email = request.getParameter("email");
		String password = request.getParameter("pwd");

		if (request.getSession().getAttribute("User") != null) {
			return "{\"statut\": \"ok\"}";
		} else if (!US.isEmailAdress(email)) {
			return "{\"statut\": \"nok\",\"message\":  \"L'adresse email n'est pas valide.\"}";
		} else if (email == "" || password == "") {
			return "{\"statut\": \"nok\",\"message\":  \"Tout les champs sont obligatoires.\"}";
		} else if (!US.checkEmail(email)) {
			return "{\"statut\": \"nok\",\"message\":  \"Cette adresse email n'existe pas.\"}";
		}
		Users user = US.connexion(email, password);
		if (user == null) {
			return "{\"statut\": \"nok\",\"message\":  \"Le mot de passe est incorrect.\"}";
		}

		request.getSession().setAttribute("User", user);
		

		return "{\"statut\": \"ok\"}";

	}

	@RequestMapping(value = "/ajaxInscription", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxSignUp(HttpServletRequest request) {

		String gender = request.getParameter("gender");
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String cgv = request.getParameter("cgv");

		if (request.getSession().getAttribute("User") != null) {
			return "{\"statut\": \"ok\"}";
		} else if (password.length() < 6 || password.length() > 54) {
			return "{\"statut\": \"nok\",\"message\":  \"Votre mot de passe doit contenir entre 6 et 54 charactères.\"}";
		} else if (gender == "" || name == "" || lastName == "" || email == "" || password == "" || cgv == "") {
			return "{\"statut\": \"nok\",\"message\":  \"Tout les champs sont obligatoires.\"}";
		} else if (!US.isEmailAdress(email)) {
			return "{\"statut\": \"nok\",\"message\":  \"L'adresse email n'est pas valide.\"}";
		} else if (US.checkEmail(email)) {
			return "{\"statut\": \"nok\",\"message\":  \"Cette adresse email est déja utilisé.\"}";
		}

		Users user = US.register(gender, name, lastName, email, password, US.getIpAdresse(request));
		request.getSession().setAttribute("User", user);

		return "{\"statut\": \"ok\"}";
	}
	
	@RequestMapping(value = "/ajaxDisconnect", method = RequestMethod.POST)
	public void ajaxDisconnect(HttpServletRequest request) {
		request.getSession().setAttribute("User", null);
	}
}

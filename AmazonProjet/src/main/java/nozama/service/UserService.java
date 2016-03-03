package nozama.service;

import javax.servlet.http.HttpServletRequest;

import nozama.model.User;

public interface UserService {

	public User connexion(String email, String password);

	public User register(String gender, String name, String lastName, String emailAdress, String password, String ipAdress);

	public boolean checkEmail(String email);

	/**
	 * Check if the mail is compatible
	 * 
	 * @param email
	 * @return
	 */
	public boolean isEmailAdress(String email);

	/**
	 * For encoding the password in sha256
	 * 
	 * @param base
	 * @return
	 */
	public String sha256(String base);

	/**
	 * Get ip address of user
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAdresse(HttpServletRequest request);
}

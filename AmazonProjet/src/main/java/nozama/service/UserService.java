package nozama.service;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nozama.model.Users;
import nozama.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository UR;

	public Users connexion(String email, String password) {
		List<Users> users = UR.getUserByEmailAndPwd(email, sha256(password));
		if (users.size() == 1) {
			return users.get(0);
		} else {
			return null;
		}
	}

	public Users register(String gender, String name, String lastName, String emailAdress, String password, String ipAdress) {
		Users user = new Users();
		user.setGenre(gender);
		user.setName(name);
		user.setLastName(lastName);
		user.setEmailAdress(emailAdress);
		user.setPassword(sha256(password));
		user.setIpAddress(ipAdress);
		UR.insertUser(user);
		
		return user;
	}

	public boolean checkEmail(String email) {
		List<Users> users = UR.getUserByEmail(email);
		if (users.size() == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check if the mail is compatible
	 * 
	 * @param email
	 * @return
	 */
	public boolean isEmailAdress(String email) {
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
		Matcher m = p.matcher(email.toUpperCase());
		return m.matches();
	}

	/**
	 * For encoding the password in sha256
	 * 
	 * @param base
	 * @return
	 */
	public static String sha256(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Get ip address of user
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAdresse(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		return ipAddress;
	}
}

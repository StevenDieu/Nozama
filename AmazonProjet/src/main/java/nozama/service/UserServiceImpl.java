package nozama.service;

import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nozama.model.Adress;
import nozama.model.Order;
import nozama.model.Product;
import nozama.model.User;
import nozama.repository.UserRepository;
import nozama.util.Util;

/**
 * All method for user
 *
 */
@Service
public class UserServiceImpl implements UserService {

  /**
   * It's a singleton for the service @UserRepository
   */
  @Autowired
  public UserRepository UR;

  public static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

  /**
   * Get user if the email and password is correct
   * 
   * @param email
   * @param password
   * @return User
   */
  public User connexion(String email, String password) {
    List<User> users = UR.getUserByEmailAndPwd(email, sha256(password));
    if (users.size() == 1) {
      return users.get(0);
    } else {
      return null;
    }
  }

  /**
   * Get order for an user
   * 
   * @param user
   * @return List<Order>
   */
  public List<Order> getOrder(User user) {
    return UR.getOrdersUser(user);
  }

  /**
   * Get product by order
   * 
   * @param order
   * @return
   */
  public List<Product> getProductCmd(Order order) {
    List<Product> productCmd = UR.getProductCmd(order);
    return productCmd;
  }

  /**
   * register an user
   * 
   * @param gender
   * @param name
   * @param lastName
   * @param emailAdress
   * @param password
   * @param ipAdress
   * @return Current User
   */
  public User register(String gender, String name, String lastName, String emailAdress,
      String password, String ipAdress) {
    User user = new User();
    user.setGenre(gender);
    user.setName(name);
    user.setLastname(lastName);
    user.setEmailAdress(emailAdress);
    user.setPassword(sha256(password));
    user.setIpAddress(ipAdress);
    user.setComptePrepaye(0);
    user.setCreateTime(new Date());
    UR.insertUser(user);

    return user;
  }

  /**
   * Get all address by one user
   * 
   * @param user
   * @return List<Adress>
   */
  public List<Adress> getAllAdressByUser(User user) {
    List<Adress> adresss = UR.getAdressByUser(user);
    return adresss;
  }

  /**
   * Chek if address exist for on user
   * 
   * @param idAdress
   * @param user
   * @return Adress
   */
  public Adress checkAdressByUser(int idAdress, User user) {
    List<Adress> adresss = UR.getAdressByUserAndIdAdress(idAdress, user);
    if (adresss.size() == 1) {
      return adresss.get(0);
    }
    return null;
  }

  /**
   * Insert address in BDD
   * 
   * @param name
   * @param nameLastName
   * @param adressPrincipal
   * @param adressSecondaire
   * @param region
   * @param pays
   * @param user
   * @param codePostal
   * @param numberPhone
   * @param city
   * @return Current Address
   */
  public Adress insertAdress(String name, String nameLastName, String adressPrincipal,
      String adressSecondaire, String region, String pays, User user, int codePostal,
      String numberPhone, String city) {
    Adress adress = new Adress();
    adress.setName(name);
    adress.setAdressPrincipal(adressPrincipal);
    adress.setAdressSecondaire(Util.ConvertStringToNull(adressSecondaire));
    adress.setCodePostal(codePostal);
    adress.setNameLastName(nameLastName);
    adress.setNumberPhone(numberPhone);
    adress.setPays(pays);
    adress.setRegion(Util.ConvertStringToNull(region));
    adress.setUser(user);
    adress.setCity(city);
    UR.insertAdress(adress);

    return adress;
  }

  /**
   * Update address in BDD
   * 
   * @param idAdress
   * @param name
   * @param nameLastName
   * @param adressPrincipal
   * @param adressSecondaire
   * @param region
   * @param pays
   * @param user
   * @param codePostal
   * @param numberPhone
   * @param city
   */
  public void updateAdress(int idAdress, String name, String nameLastName, String adressPrincipal,
      String adressSecondaire, String region, String pays, User user, int codePostal,
      String numberPhone, String city) {
    Adress adress = new Adress();
    adress.setIdAdress(idAdress);
    adress.setName(name);
    adress.setAdressPrincipal(adressPrincipal);
    adress.setAdressSecondaire(Util.ConvertStringToNull(adressSecondaire));
    adress.setCodePostal(codePostal);
    adress.setNameLastName(nameLastName);
    adress.setNumberPhone(numberPhone);
    adress.setPays(pays);
    adress.setRegion(Util.ConvertStringToNull(region));
    adress.setUser(user);
    adress.setCity(city);

    UR.updateAdress(adress);
  }

  /**
   * Delete address in BDD
   * 
   * @param adress
   */
  public void deleteAdress(Adress adress) {
    UR.deleteAdress(adress);
  }

  /**
   * Update user in BDD
   * 
   * @param user
   */
  public void updateUser(User user) {
    UR.updateUser(user);
  }


  /**
   * Check if Email exist in BDD
   * 
   * @param email
   * @return
   */
  public boolean checkEmail(String email) {
    List<User> users = UR.getUserByEmail(email);
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
  public String sha256(String base) {
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
      log.error("[UserServiceImpl] Error in the method sha256 impossible to get sha256");
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

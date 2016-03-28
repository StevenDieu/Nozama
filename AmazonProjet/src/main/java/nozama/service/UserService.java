package nozama.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import nozama.model.Adress;
import nozama.model.Order;
import nozama.model.Product;
import nozama.model.User;

public interface UserService {

  public User connexion(String email, String password);

  public List<Order> getOrder(User user);

  public List<Product> getProductCmd(Order order);

  public User register(String gender, String name, String lastName, String emailAdress,
      String password, String ipAdress);
  
  public List<Adress> getAllAdressByUser(User user);
  
  public Adress checkAdressByUser(int idAdress, User user);
  
  public Adress insertAdress(String name, String nameLastName, String adressPrincipal, String adressSecondaire,
      String region, String pays, User user, int codePostal, String numberPhone, String city);
  
  public void updateAdress(int idAdress, String name, String nameLastName, String adressPrincipal,
      String adressSecondaire, String region, String pays, User user, int codePostal,
      String numberPhone, String city);
  
  public void deleteAdress(Adress adress);
  
  public void updateUser(User user);

  public boolean checkEmail(String email);

  public boolean isEmailAdress(String email);

  public String sha256(String base);

  public String getIpAdresse(HttpServletRequest request);
}

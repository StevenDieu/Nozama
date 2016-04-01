package misys.service;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import misys.model.Adress;
import misys.model.Order;
import misys.model.User;

@Service
public interface ProductCartService {
  public List<Map<String, Object>> getAllArticleInTheCart(List<Map<String, Object>> attribute);

  public float calculTotalProduct(List<Map<String, Object>> list);

  public Order insertOrder(Adress adress, Map<String, Object> listTransport, String modePayment,
      User user, float totalPrice, float prixTotalProduct);

  public void insertProductOrder(List<Map<String, Object>> carts, Order order);
}

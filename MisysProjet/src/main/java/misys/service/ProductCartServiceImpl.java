package misys.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import misys.model.Adress;
import misys.model.Article;
import misys.model.Order;
import misys.model.Product;
import misys.model.ProductOrder;
import misys.model.User;
import misys.repository.ProductRepository;
import misys.util.Util;

/**
 * All method for cart or control tunnel
 *
 */
@Service
public class ProductCartServiceImpl implements ProductCartService {

  /**
   * It's a singleton for the service @ProductRepository
   */
  @Autowired
  private ProductRepository PR;

  private List<Map<String, Object>> allCart;

  /**
   * Get all article reference in the cart
   * 
   * @param allCart
   * @return List<Map<String, Object>>
   */
  public List<Map<String, Object>> getAllArticleInTheCart(List<Map<String, Object>> allCart) {
    this.allCart = allCart;
    List<Map<String, Object>> allProduct = new ArrayList<Map<String, Object>>();

    List<Integer> allId = new ArrayList<Integer>();
    if (allCart != null) {
      for (Map<String, Object> productCart : allCart) {
        allId.add((Integer) productCart.get("id"));
      }
    }

    if (allId.size() > 0) {
      List<Article> articles = PR.getArticleById(allId);
      margeAllResultTypeSupport(articles, allProduct);
    }

    return allProduct;
  }

  /**
   * Marge all articles for get a map
   * 
   * @param articles
   * @param allProduct
   */
  private void margeAllResultTypeSupport(List<Article> articles,
      List<Map<String, Object>> allProduct) {
    for (Article article : articles) {
      insertInProducts(allProduct, article.getProduct().getType(), article.getProduct(), article);
    }
  }

  /**
   * Insert map in list product the last map
   * 
   * @param allProduct
   * @param type
   * @param product
   * @param article
   */
  private void insertInProducts(List<Map<String, Object>> allProduct, String type, Product product,
      Article article) {
    Map<String, Object> newProduct = new HashMap<String, Object>();
    newProduct.put(type, product.getIdProduct());
    newProduct.put("type", type);
    if (type.equals("album")) {
      newProduct.put("urlType", "/liste-toutes-les-musiques/AllSupport/album/AllYears/ALL");
    } else if (type.equals("single")) {
      newProduct.put("urlType", "/liste-toutes-les-musiques/AllSupport/single/AllYears/ALL");
    } else if (type.equals("film")) {
      newProduct.put("urlType", "/liste-tous-les-films");
    }
    newProduct.put("name", product.getName());
    newProduct.put("description", product.getDescription());
    newProduct.put("urlPicture", product.getUrlPicture());
    newProduct.put("dateReleased", product.getDateReleased());
    newProduct.put("nameTagDateReleased", product.getNameTagDateReleased());

    newProduct.put("price", Float.toString(article.getPrice()));
    newProduct.put("support", article.getNameSupport());
    newProduct.put("id", Integer.toString(article.getIdArticle()));

    for (Map<String, Object> productCart : allCart) {
      if (((Integer) productCart.get("id")).equals(article.getIdArticle())) {
        DecimalFormat df = new DecimalFormat("0.00");
        newProduct.put("numberProduct", (Integer) productCart.get("number"));
        newProduct.put("totalProduct",
            df.format(((Integer) productCart.get("number")) * article.getPrice()));
      }
    }

    allProduct.add(newProduct);
  }


  /**
   * Calcul the total price of the cart
   * 
   * @param list
   * @return float
   */
  public float calculTotalProduct(List<Map<String, Object>> list) {
    float priceTotal = 0;
    for (Map<String, Object> product : list) {
      int numberProduct = (Integer) product.get("number");
      Article article = PR.getArticleById((Integer) product.get("id"));
      priceTotal = priceTotal + (numberProduct * article.getPrice());

    }
    return priceTotal;

  }

  /**
   * The method create and insert order in bdd
   * 
   * @param adress
   * @param listTransport
   * @param modePayment
   * @param user
   * @param totalPrice
   * @param prixTotalProduct
   * @return
   */
  public Order insertOrder(Adress adress, Map<String, Object> listTransport, String modePayment,
      User user, float totalPrice, float prixTotalProduct) {
    Order order = new Order();
    order.setAdress(adress);
    order.setCommentDelivery(Util.ConvertStringToNull((String) listTransport.get("commentaire")));
    order.setModeDelivery((String) listTransport.get("id"));
    order.setModePayment(modePayment);
    order.setTotalDelivery((float) listTransport.get("prix"));
    order.setTotalOrder(totalPrice);
    order.setTotalProduct(prixTotalProduct);
    order.setUser(user);
    order.setCreateTime(new Date());
    order.setUpdateTime(new Date());
    PR.inserOrder(order);

    return order;
  }

  /**
   * The method create and insert ProductOrder in bdd
   * 
   * @param carts
   * @param order
   */
  public void insertProductOrder(List<Map<String, Object>> carts, Order order) {
    List<Integer> allId = new ArrayList<>();
    for (Map<String, Object> cart : carts) {
      allId.add((Integer) cart.get("id"));
    }

    if (allId.size() > 0) {
      List<Article> articles = PR.getArticleById(allId);
      for (Article article : articles) {

        ProductOrder productOrder = new ProductOrder();
        productOrder.setArticle(article);
        productOrder.setOrder(order);
        for (Map<String, Object> cart : carts) {
          if (article.getIdArticle() == (Integer) cart.get("id")) {
            productOrder.setQuantity((Integer) cart.get("number"));
          }
        }
        PR.inserOrderProduct(productOrder);
      }
    }
  }
}

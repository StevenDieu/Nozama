package nozama.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import nozama.model.Adress;
import nozama.model.Order;
import nozama.model.Product;
import nozama.model.ProductOrder;
import nozama.model.User;
import nozama.util.HibernateUtil;

/**
 * It's repository for request in BDD for User
 *
 */
@SuppressWarnings("unchecked")
@Repository
public class UserRepository {

  private static Logger log = Logger.getLogger(UserRepository.class.getName());

  /**
   * Get User by conditions :
   * emailAdress
   * password
   * 
   * for singup
   * 
   * @param email
   * @param password
   * @return List<User>
   */
  public List<User> getUserByEmailAndPwd(String email, String password) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(User.class);
    cr.add(Restrictions.eq("emailAdress", email));
    cr.add(Restrictions.eq("password", password));

    List<User> listUser = cr.list();
    openSession.close();
    HibernateUtil.shutdown();
    return listUser;
  }

  /**
   * Get User by conditions :
   * emailAdress
   * 
   * for check is address mail exist
   * 
   * @param email
   * @return
   */
  public List<User> getUserByEmail(String email) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(User.class);
    cr.add(Restrictions.eq("emailAdress", email));
    List<User> listUser = cr.list();
    openSession.close();
    HibernateUtil.shutdown();
    return listUser;
  }

  /**
   * Insert user in BDD
   * 
   * @param user
   */
  public void insertUser(User user) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Transaction tx = openSession.beginTransaction();

    try {
      openSession.save(user);

      tx.commit();
    } catch (RuntimeException e) {
      log.error("[UserRepository] impossible to insert user in bdd");

      tx.rollback();
      throw e;
    }
    openSession.close();
    HibernateUtil.shutdown();
  }

  /**
   * select list adress by conditions :
   * idAdress
   * user
   * 
   * @param idAdress
   * @param user
   * @return List<Adress>
   */
  public List<Adress> getAdressByUserAndIdAdress(int idAdress, User user) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Adress.class);
    cr.createAlias("user", "u");

    cr.add(Restrictions.eq("idAdress", idAdress));
    cr.add(Restrictions.eq("u.idUsers", user.getIdUsers()));
    List<Adress> listAdress = cr.list();
    openSession.close();
    HibernateUtil.shutdown();
    return listAdress;
  }

  /**
   * select list adress by conditions :
   * user
   * 
   * @param user
   * @return List<Adress>
   */
  public List<Adress> getAdressByUser(User user) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Adress.class);
    cr.createAlias("user", "u");

    cr.add(Restrictions.eq("u.idUsers", user.getIdUsers()));

    List<Adress> listAdress = cr.list();
    openSession.close();
    HibernateUtil.shutdown();
    return listAdress;
  }
  
  /**
   * select list order by user
   * 
   * @param user
   * @return List<Order>
   */
  public List<Order> getOrdersUser(User user) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Order.class);
    cr.createAlias("user", "u", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("adress", "ad", JoinType.LEFT_OUTER_JOIN);

    cr.add(Restrictions.eq("u.idUsers", user.getIdUsers()));

    List<Order> listOrders = cr.list();
    openSession.close();
    HibernateUtil.shutdown();
    return listOrders;
  }

  /**
   * Select all product by order
   * 
   * 
   * @param order
   * @return List<Product>
   */
  public List<Product> getProductCmd(Order order){
	  Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(ProductOrder.class);
    cr.createAlias("order", "o", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("article", "ar", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("ar.product", "pr", JoinType.LEFT_OUTER_JOIN);

    cr.add(Restrictions.eq("o.idOrder", order.getIdOrder()));

    List<Product> listProduct = cr.list();
    openSession.close();
    HibernateUtil.shutdown();
    return listProduct;
  }

  /**
   * Insert address in BDD 
   * 
   * @param adress
   */
  public void insertAdress(Adress adress) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Transaction tx = openSession.beginTransaction();

    try {
      openSession.save(adress);

      tx.commit();
    } catch (RuntimeException e) {
      log.error("[UserRepository] impossible to insert adress in bdd");
      tx.rollback();
      throw e;
    }
    openSession.close();
    HibernateUtil.shutdown();
  }

  /**
   * Delete address in BDD
   * 
   * @param adress
   */
  public void deleteAdress(Adress adress) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Transaction tx = openSession.beginTransaction();

    try {
      openSession.delete(adress);

      tx.commit();
    } catch (RuntimeException e) {
      log.error("[UserRepository] impossible to delete adress in bdd");
      tx.rollback();
      throw e;
    }
    openSession.close();
    HibernateUtil.shutdown();
  }

  /**
   * Update address in BDD
   * 
   * @param adress
   */
  public void updateAdress(Adress adress) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Transaction tx = openSession.beginTransaction();

    try {
      openSession.update(adress);
      tx.commit();
      openSession.flush();

    } catch (RuntimeException e) {
      log.error("[UserRepository] impossible to update adress in bdd");
      tx.rollback();
      throw e;
    }
    openSession.close();
    HibernateUtil.shutdown();
  }

  /**
   * Update User by bdd
   * 
   * @param user
   */
  public void updateUser(User user) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Transaction tx = openSession.beginTransaction();

    try {
      openSession.update(user);
      tx.commit();
      openSession.flush();

    } catch (RuntimeException e) {
      log.error("[UserRepository] impossible to update user in bdd");
      tx.rollback();
      throw e;
    }
    openSession.close();
    HibernateUtil.shutdown();
  }

}

package nozama.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import nozama.model.Adress;
import nozama.model.User;
import nozama.util.HibernateUtil;

@SuppressWarnings("unchecked")
@Repository
public class UserRepository {


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

  public List<User> getUserByEmail(String email) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(User.class);
    cr.add(Restrictions.eq("emailAdress", email));
    List<User> listUser = cr.list();
    openSession.close();
    HibernateUtil.shutdown();
    return listUser;
  }

  public void insertUser(User users) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Transaction tx = openSession.beginTransaction();

    try {
      openSession.save(users);

      tx.commit();
    } catch (RuntimeException e) {
      tx.rollback();
      throw e;
    }
    openSession.close();
    HibernateUtil.shutdown();
  }

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


  public void insertAdress(Adress adress) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Transaction tx = openSession.beginTransaction();

    try {
      openSession.save(adress);

      tx.commit();
    } catch (RuntimeException e) {
      tx.rollback();
      throw e;
    }
    openSession.close();
    HibernateUtil.shutdown();
  }

  public void deleteAdress(Adress adress) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Transaction tx = openSession.beginTransaction();

    try {
      openSession.delete(adress);

      tx.commit();
    } catch (RuntimeException e) {
      tx.rollback();
      throw e;
    }
    openSession.close();
    HibernateUtil.shutdown();
  }

  public void updateAdress(Adress adress) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Transaction tx = openSession.beginTransaction();

    try {
      openSession.update(adress);
      tx.commit();
      openSession.flush();

    } catch (RuntimeException e) {
      tx.rollback();
      throw e;
    }
    openSession.close();
    HibernateUtil.shutdown();
  }

  public void updateUser(User user) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Transaction tx = openSession.beginTransaction();

    try {
      openSession.update(user);
      tx.commit();
      openSession.flush();

    } catch (RuntimeException e) {
      tx.rollback();
      throw e;
    }
    openSession.close();
    HibernateUtil.shutdown();
  }

}

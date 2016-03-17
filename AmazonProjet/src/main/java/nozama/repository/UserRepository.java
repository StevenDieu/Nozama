package nozama.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import nozama.model.Adress;
import nozama.model.User;
import nozama.util.HibernateUtil;

@SuppressWarnings("unchecked")
@Repository
public class UserRepository {

  Session openSession = HibernateUtil.getSessionFactory().openSession();

  public List<User> getUserByEmailAndPwd(String email, String password) {
    Criteria cr = openSession.createCriteria(User.class);
    cr.add(Restrictions.eq("emailAdress", email));
    cr.add(Restrictions.eq("password", password));

    return cr.list();
  }

  public List<User> getUserByEmail(String email) {
    Criteria cr = openSession.createCriteria(User.class);
    cr.add(Restrictions.eq("emailAdress", email));

    return cr.list();
  }

  public void insertUser(User users) {
    openSession.beginTransaction();

    openSession.save(users);

    openSession.getTransaction().commit();
  }

  public List<Adress> getAdressByUserAndIdAdress(int idAdress, User user) {
    Criteria cr = openSession.createCriteria(Adress.class);
    cr.createAlias("user", "u");

    cr.add(Restrictions.eq("idAdress", idAdress));
    cr.add(Restrictions.eq("u.idUsers", user.getIdUsers()));

    return cr.list();
  }


  public List<Adress> getAdressByUser(User user) {
    Criteria cr = openSession.createCriteria(Adress.class);
    cr.createAlias("user", "u");

    cr.add(Restrictions.eq("u.idUsers", user.getIdUsers()));

    return cr.list();
  }


  public void insertAdress(Adress adress) {
    openSession.beginTransaction();

    openSession.save(adress);

    openSession.getTransaction().commit();
  }

}

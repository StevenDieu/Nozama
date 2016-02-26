package nozama.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

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
		HibernateUtil.shutdown();

		return cr.list();
	}

	public List<User> getUserByEmail(String email) {
		Criteria cr = openSession.createCriteria(User.class);
		cr.add(Restrictions.eq("emailAdress", email));
		HibernateUtil.shutdown();

		return cr.list();
	}

	public void insertUser(User users) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.save(users);

		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}

}

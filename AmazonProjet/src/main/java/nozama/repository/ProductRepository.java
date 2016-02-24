package nozama.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import nozama.model.Product;
import nozama.model.Single;
import nozama.util.HibernateUtil;

@SuppressWarnings("unchecked")
@Repository
public class ProductRepository {
	
	Session openSession = HibernateUtil.getSessionFactory().openSession();

	public List<Product> getSingle() {
		Criteria cr = openSession.createCriteria(Single.class);
		cr.createAlias("product", "prod");
		cr.createAlias("typeSupportSingles", "tss");

		return cr.list();
	}

}

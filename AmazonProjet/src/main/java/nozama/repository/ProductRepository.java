package nozama.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import nozama.model.Product;
import nozama.util.HibernateUtil;

@SuppressWarnings("unchecked")
@Repository
public class ProductRepository {
	
	Session openSession = HibernateUtil.getSessionFactory().openSession();

	public List<Product> getProductByTypeProduct(String typeProduct) {
		Criteria cr = openSession.createCriteria(Product.class);
		cr.add(Restrictions.eq("typeProduct", typeProduct));
		return cr.list();
	}

}

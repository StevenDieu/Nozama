package nozama.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import nozama.model.Product;
import nozama.model.TypeSupportAlbm;
import nozama.model.TypeSupportMovie;
import nozama.model.TypeSupportSingle;
import nozama.util.HibernateUtil;

@SuppressWarnings("unchecked")
@Repository
public class ProductRepository {
	
	Session openSession = HibernateUtil.getSessionFactory().openSession();

	public List<Product> getAllSingleBySupport(String support) {
		Criteria cr = openSession.createCriteria(TypeSupportSingle.class);
		cr.createAlias("single", "s");
		cr.createAlias("s.product", "prod");
		cr.add(Restrictions.eq("nameSupport", support));

		return cr.list();
	}

	public List<Product> getAllMovieBySupport(String support) {
		Criteria cr = openSession.createCriteria(TypeSupportMovie.class);
		cr.createAlias("movie", "m");
		cr.createAlias("m.product", "prod");
		cr.add(Restrictions.eq("nameSupport", support));

		return cr.list();
	}

	public List<Product> getAllAlbumBySupport(String support) {
		Criteria cr = openSession.createCriteria(TypeSupportAlbm.class);
		cr.createAlias("album", "a");
		cr.createAlias("a.product", "prod");
		cr.add(Restrictions.eq("nameSupport", support));

		return cr.list();
	}

	public List<Product> getAllSingleBySupportMiniYears(String support, Date dateYears) {
		Criteria cr = openSession.createCriteria(TypeSupportSingle.class);
		cr.createAlias("single", "s");
		cr.createAlias("s.product", "prod");
		cr.add(Restrictions.eq("nameSupport", support));
		cr.add(Restrictions.gt("s.dateReleased", dateYears));

		return cr.list();
	}

	public List<Product> getAllAlbumBySupportMiniYears(String support, Date years) {
		Criteria cr = openSession.createCriteria(TypeSupportAlbm.class);
		cr.createAlias("album", "a");
		cr.createAlias("a.product", "prod");
		cr.add(Restrictions.eq("nameSupport", support));
		cr.add(Restrictions.gt("a.dateReleased", years));

		return cr.list();
	}

	public List<Product> getAllSingleBySupportBetweenYears(String support, Date years, Date yearsMoreTen) {
		Criteria cr = openSession.createCriteria(TypeSupportSingle.class);
		cr.createAlias("single", "s");
		cr.createAlias("s.product", "prod");
		cr.add(Restrictions.eq("nameSupport", support));
		cr.add(Restrictions.between("s.dateReleased", years, yearsMoreTen));

		return cr.list();
	}

	public List<Product> getAllAlbumBySupportBetweenYears(String support, Date years, Date yearsMoreTen) {
		Criteria cr = openSession.createCriteria(TypeSupportAlbm.class);
		cr.createAlias("album", "a");
		cr.createAlias("a.product", "prod");
		cr.add(Restrictions.eq("nameSupport", support));
		cr.add(Restrictions.between("a.dateReleased", years, yearsMoreTen));

		return cr.list();
	}

}

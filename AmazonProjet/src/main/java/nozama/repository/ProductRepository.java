package nozama.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import nozama.model.Product;
import nozama.model.TypeSupportAlbum;
import nozama.model.TypeSupportMovie;
import nozama.model.TypeSupportSingle;
import nozama.util.HibernateUtil;

@SuppressWarnings("unchecked")
@Repository
public class ProductRepository {

	Session openSession = HibernateUtil.getSessionFactory().openSession();

	public List<Product> getAllMovieBySupport(String support, boolean useDate, boolean useType, Date dateYears, Date dateYearsAfter, String type, int startResult) {
		Criteria cr = openSession.createCriteria(TypeSupportMovie.class);
		cr.createAlias("movie", "m");
		cr.createAlias("m.product", "prod");
		cr.add(Restrictions.eq("nameSupport", support));
		int startResultNumber = (startResult - 1) * 12;
		cr.setFirstResult(startResultNumber);
		cr.setMaxResults(startResultNumber + 12);
		if (useDate) {
			cr.add(Restrictions.between("m.dateReleased", dateYears, dateYearsAfter));
		}
		if (useType) {
			cr.add(Restrictions.eq("m.type", type));
		}

		return cr.list();
	}

	public List<Product> getAllSingleBySupportAndTypeBetweenYears(String support, boolean useDate, boolean useType, Date dateYears, Date dateYearsAfter, String type, int startResult) {
		Criteria cr = openSession.createCriteria(TypeSupportSingle.class);
		cr.createAlias("single", "s");
		cr.createAlias("s.product", "prod");
		cr.add(Restrictions.eq("nameSupport", support));
		int startResultNumber = (startResult - 1) * 12;
		cr.setFirstResult(startResultNumber);
		cr.setMaxResults(startResultNumber + 12);
		if (useDate) {
			cr.add(Restrictions.between("s.dateReleased", dateYears, dateYearsAfter));
		}
		if (useType) {
			cr.add(Restrictions.eq("s.type", type));
		}

		return cr.list();
	}

	public List<Product> getAllAlbumBySupportAndTypBetweenYears(String support, boolean useDate, boolean useType, Date dateYears, Date dateYearsAfter, String type, int startResult) {
		Criteria cr = openSession.createCriteria(TypeSupportAlbum.class);
		cr.createAlias("album", "a");
		cr.createAlias("a.product", "prod");
		cr.add(Restrictions.eq("nameSupport", support));
		int startResultNumber = (startResult - 1) * 12;
		cr.setFirstResult(startResultNumber);
		cr.setMaxResults(startResultNumber + 12);
		if (useDate) {
			cr.add(Restrictions.between("a.dateReleased", dateYears, dateYearsAfter));
		}
		if (useType) {
			cr.add(Restrictions.eq("a.type", type));
		}

		return cr.list();
	}

	public int getCountAllLovieBySupport(String support, boolean useDate, boolean useType, Date dateYears, Date dateYearsAfter, String type) {
		Criteria cr = openSession.createCriteria(TypeSupportMovie.class);
		cr.createAlias("movie", "m");
		cr.createAlias("m.product", "prod");
		cr.add(Restrictions.eq("nameSupport", support));
		if (useDate) {
			cr.add(Restrictions.between("m.dateReleased", dateYears, dateYearsAfter));
		}
		if (useType) {
			cr.add(Restrictions.eq("m.type", type));
		}

		return ((Long) cr.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	public int getCountAllAlbumBySupport(String support, boolean useDate, boolean useType, Date dateYears, Date dateYearsAfter, String type) {
		Criteria cr = openSession.createCriteria(TypeSupportAlbum.class);
		cr.createAlias("album", "a");
		cr.createAlias("a.product", "prod");
		cr.add(Restrictions.eq("nameSupport", support));
		if (useDate) {
			cr.add(Restrictions.between("a.dateReleased", dateYears, dateYearsAfter));
		}
		if (useType) {
			cr.add(Restrictions.eq("a.type", type));
		}

		return ((Long) cr.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	public int getCountAllMusicBySupport(String support, boolean useDate, boolean useType, Date dateYears, Date dateYearsAfter, String type) {
		Criteria cr = openSession.createCriteria(TypeSupportSingle.class);
		cr.createAlias("single", "s");
		cr.createAlias("s.product", "prod");
		cr.add(Restrictions.eq("nameSupport", support));
		if (useDate) {
			cr.add(Restrictions.between("s.dateReleased", dateYears, dateYearsAfter));
		}
		if (useType) {
			cr.add(Restrictions.eq("s.type", type));
		}

		return ((Long) cr.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	public List<Product> getAllMovieByDate(boolean useDate,Date dateYears,Date dateYearsAfter) {
		Criteria cr = openSession.createCriteria(TypeSupportMovie.class);
		cr.createAlias("movie", "m");
		cr.createAlias("m.product", "prod");
		if (useDate) {
			cr.add(Restrictions.between("m.dateReleased", dateYears, dateYearsAfter));
		}

		return cr.list();
	}
	
	public List<Product> getAllAlbumByDate(boolean useDate,Date dateYears,Date dateYearsAfter) {
		Criteria cr = openSession.createCriteria(TypeSupportAlbum.class);
		cr.createAlias("album", "a");
		cr.createAlias("a.product", "prod");
		if (useDate) {
			cr.add(Restrictions.between("a.dateReleased", dateYears, dateYearsAfter));
		}

		return cr.list();
	}
	
	public List<Product> getAllSingleByDate(boolean useDate,Date dateYears,Date dateYearsAfter) {
		Criteria cr = openSession.createCriteria(TypeSupportSingle.class);
		cr.createAlias("single", "s");
		cr.createAlias("s.product", "prod");
		if (useDate) {
			cr.add(Restrictions.between("s.dateReleased", dateYears, dateYearsAfter));
		}

		return cr.list();
	}

}

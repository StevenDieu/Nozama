package nozama.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
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

	public List<TypeSupportMovie> getAllMovieBySupport(String support, boolean useSupport,boolean useDate, boolean useType, Date dateYears, Date dateYearsAfter, String type, int startResult) {
		Criteria cr = openSession.createCriteria(TypeSupportMovie.class);
		cr.createAlias("movie", "m");
		cr.createAlias("m.product", "prod");
		int startResultNumber = (startResult - 1) * 12;
		cr.setFirstResult(startResultNumber);
		cr.setMaxResults(startResultNumber + 12);
		if (useSupport) {
			cr.add(Restrictions.eq("nameSupport", support));
		}
		if (useDate) {
			cr.add(Restrictions.between("m.dateReleased", dateYears, dateYearsAfter));
		}
		if (useType) {
			cr.add(Restrictions.eq("m.type", type));
		}
		HibernateUtil.shutdown();

		return cr.list();
	}

	public List<TypeSupportSingle> getAllSingleBySupportAndTypeBetweenYears(String support, boolean useSupport, boolean useDate, boolean useType, Date dateYears, Date dateYearsAfter, String type, int startResult) {
		Criteria cr = openSession.createCriteria(TypeSupportSingle.class);
		cr.createAlias("single", "s");
		cr.createAlias("s.product", "prod");
		int startResultNumber = (startResult - 1) * 12;
		cr.setFirstResult(startResultNumber);
		cr.setMaxResults(startResultNumber + 12);
		if (useSupport) {
			cr.add(Restrictions.eq("nameSupport", support));
		}
		if (useDate) {
			cr.add(Restrictions.between("s.dateReleased", dateYears, dateYearsAfter));
		}
		if (useType) {
			cr.add(Restrictions.eq("s.type", type));
		}
		HibernateUtil.shutdown();

		return cr.list();
	}

	public List<TypeSupportAlbum> getAllAlbumBySupportAndTypBetweenYears(String support, boolean useSupport, boolean useDate, boolean useType, Date dateYears, Date dateYearsAfter, String type, int startResult) {
		Criteria cr = openSession.createCriteria(TypeSupportAlbum.class);
		cr.createAlias("album", "a");
		cr.createAlias("a.product", "prod");

		int startResultNumber = (startResult - 1) * 12;
		cr.setFirstResult(startResultNumber);
		cr.setMaxResults(startResultNumber + 12);
		if (useSupport) {
			cr.add(Restrictions.eq("nameSupport", support));
		}
		if (useDate) {
			cr.add(Restrictions.between("a.dateReleased", dateYears, dateYearsAfter));
		}
		if (useType) {
			cr.add(Restrictions.eq("a.type", type));
		}
		HibernateUtil.shutdown();

		return cr.list();
	}

	public int getCountAllLovieBySupport(String support, boolean useSupport, boolean useDate, boolean useType, Date dateYears, Date dateYearsAfter, String type) {
		Criteria cr = openSession.createCriteria(TypeSupportMovie.class);
		cr.createAlias("movie", "m");
		cr.createAlias("m.product", "prod");
		if (useSupport) {
			cr.add(Restrictions.eq("nameSupport", support));
		}
		if (useDate) {
			cr.add(Restrictions.between("m.dateReleased", dateYears, dateYearsAfter));
		}
		if (useType) {
			cr.add(Restrictions.eq("m.type", type));
		}
		HibernateUtil.shutdown();

		return ((Long) cr.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	public int getCountAllAlbumBySupport(String support, boolean useSupport, boolean useDate, boolean useType, Date dateYears, Date dateYearsAfter, String type) {
		Criteria cr = openSession.createCriteria(TypeSupportAlbum.class);
		cr.createAlias("album", "a");
		cr.createAlias("a.product", "prod");
		if (useSupport) {
			cr.add(Restrictions.eq("nameSupport", support));
		}
		if (useDate) {
			cr.add(Restrictions.between("a.dateReleased", dateYears, dateYearsAfter));
		}
		if (useType) {
			cr.add(Restrictions.eq("a.type", type));
		}
		HibernateUtil.shutdown();

		return ((Long) cr.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	public int getCountAllMusicBySupport(String support, boolean useSupport, boolean useDate, boolean useType, Date dateYears, Date dateYearsAfter, String type) {
		Criteria cr = openSession.createCriteria(TypeSupportSingle.class);
		cr.createAlias("single", "s");
		cr.createAlias("s.product", "prod");
		if (useSupport) {
			cr.add(Restrictions.eq("nameSupport", support));
		}
		if (useDate) {
			cr.add(Restrictions.between("s.dateReleased", dateYears, dateYearsAfter));
		}
		if (useType) {
			cr.add(Restrictions.eq("s.type", type));
		}
		HibernateUtil.shutdown();

		return ((Long) cr.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	public List<TypeSupportMovie> getAllMovieByDate(boolean useDate, Date dateYears, Date dateYearsAfter) {
		Criteria cr = openSession.createCriteria(TypeSupportMovie.class);
		cr.createAlias("movie", "m");
		cr.createAlias("m.product", "prod");
		cr.addOrder(Property.forName("nameSupport").asc());
		if (useDate) {
			cr.add(Restrictions.between("m.dateReleased", dateYears, dateYearsAfter));
		}
		HibernateUtil.shutdown();

		return cr.list();
	}

	public List<TypeSupportAlbum> getAllAlbumByDate(boolean useDate, Date dateYears, Date dateYearsAfter) {
		Criteria cr = openSession.createCriteria(TypeSupportAlbum.class);
		cr.createAlias("album", "a");
		cr.createAlias("a.product", "prod");
		cr.addOrder(Property.forName("nameSupport").asc());

		if (useDate) {
			cr.add(Restrictions.between("a.dateReleased", dateYears, dateYearsAfter));
		}
		HibernateUtil.shutdown();

		return cr.list();
	}

	public List<TypeSupportSingle> getAllSingleByDate(boolean useDate, Date dateYears, Date dateYearsAfter) {
		Criteria cr = openSession.createCriteria(TypeSupportSingle.class);
		cr.createAlias("single", "s");
		cr.createAlias("s.product", "prod");
		cr.addOrder(Property.forName("nameSupport").asc());

		if (useDate) {
			cr.add(Restrictions.between("s.dateReleased", dateYears, dateYearsAfter));
		}
		HibernateUtil.shutdown();

		return cr.list();
	}

}

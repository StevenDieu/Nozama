package nozama.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import nozama.model.Article;
import nozama.model.Product;
import nozama.util.HibernateUtil;

@SuppressWarnings("unchecked")
@Repository
public class ProductRepository {


  public List<Product> getAllProductByTypeAndSupportAndAttribut(boolean useSupport, String support,
      boolean useDate, Date dateYears, Date dateYearsAfter, boolean useType,
      List<String> type, boolean useGenre, String genre) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    
    Criteria cr = openSession.createCriteria(Product.class);


    cr.createAlias("articles", "a", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("attrProducts", "ap", JoinType.LEFT_OUTER_JOIN);
    
    cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    
    if (useType) {
      cr.add(Restrictions.in("type", type));
    }
    if (useSupport) {
      cr.add(Restrictions.eq("a.nameSupport", support));
    }
    if (useDate) {
      cr.add(Restrictions.between("dateReleased", dateYears, dateYearsAfter));
    }
    if (useGenre) {
      cr.add(Restrictions.eq("ap.attribut", "genre"));
      cr.add(Restrictions.eq("ap.valeur", genre));
    }
    List<Product> listProduct = cr.list();

    openSession.close();

    return listProduct;
  }

  public int getCountAllProductBySupport(boolean useSupport, String support,
      boolean useDate, Date dateYears, Date dateYearsAfter, boolean useType,
      List<String> type, boolean useGenre, String genre) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Product.class);
    cr.createAlias("articles", "a", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("attrProducts", "ap", JoinType.LEFT_OUTER_JOIN);
    if (useType) {
      cr.add(Restrictions.in("type", type));
    }
    if (useSupport) {
      cr.add(Restrictions.eq("a.nameSupport", support));
    }
    if (useDate) {
      cr.add(Restrictions.between("dateReleased", dateYears, dateYearsAfter));
    }
    if (useGenre) {
      cr.add(Restrictions.eq("ap.attribut", "genre"));
      cr.add(Restrictions.eq("ap.valeur", genre));
    }
    int countProducts = ((Long) cr.setProjection(Projections.rowCount()).uniqueResult()).intValue();

    openSession.close();

    return countProducts;
  }

  public List<Product> getAllProductByDate(boolean useType, List<String> type, boolean useDate,
      Date dateYears, Date dateYearsAfter) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Product.class);
    cr.createAlias("articles", "a", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("attrProducts", "ap", JoinType.LEFT_OUTER_JOIN);
    cr.addOrder(Property.forName("a.nameSupport").asc());
    if (useType) {
      cr.add(Restrictions.in("type", type));
    }
    if (useDate) {
      cr.add(Restrictions.between("dateReleased", dateYears, dateYearsAfter));
    }

    List<Product> listProduct = cr.list();
    openSession.close();
    return listProduct;
  }


  public List<Product> getProducts(boolean UserArtiste, List<String> type,
      String nameTagDateReleased) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Product.class);
    cr.createAlias("articles", "a", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("attrProducts", "ap", JoinType.LEFT_OUTER_JOIN);
    if (UserArtiste) {
      cr.createAlias("artistes", "art");
    }
    cr.add(Restrictions.in("type", type));

    cr.add(Restrictions.eq("nameTagDateReleased", nameTagDateReleased));

    List<Product> listProduct = cr.list();
    openSession.close();
    return listProduct;
  }

  public List<Product> getProductById(List<Integer> idProduct) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Product.class);
    cr.createAlias("articles", "a", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("attrProducts", "ap", JoinType.LEFT_OUTER_JOIN);

    cr.add(Restrictions.in("idProduct", idProduct));

    List<Product> listProduct = cr.list();
    openSession.close();
    return listProduct;
  }

  public Product getProductByNameTagDate(String nameTagDateReleased) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Product.class);
    cr.createAlias("articles", "a", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("attrProducts", "ap", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("artistes", "art", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("products", "prod", JoinType.LEFT_OUTER_JOIN);

    cr.add(Restrictions.eq("nameTagDateReleased", nameTagDateReleased));

    List<Product> listProduct = cr.list();
    openSession.close();
    
    return listProduct.get(0);
  }

  public Product getProductParent(int idProduct) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Product.class);
    cr.createAlias("articles", "a", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("attrProducts", "ap", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("artistes", "art", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("products", "prod", JoinType.LEFT_OUTER_JOIN);
    cr.add(Restrictions.eq("prod.idProduct", idProduct));

    

    List<Product> listProduct = cr.list();
    openSession.close();
    
    return listProduct.get(0);
  }

  public Product getProductById(Integer idProduct) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Product.class);
    cr.createAlias("articles", "a", JoinType.LEFT_OUTER_JOIN);
    cr.createAlias("attrProducts", "ap", JoinType.LEFT_OUTER_JOIN);

    cr.add(Restrictions.eq("idProduct", idProduct));

    List<Product> listProduct = cr.list();
    openSession.close();
    return listProduct.get(0);
  }

  public List<Article> getArticleById(List<Integer> allId) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Article.class);
    cr.createAlias("product", "p", JoinType.LEFT_OUTER_JOIN);

    cr.add(Restrictions.in("idArticle", allId));

    List<Article> listProduct = cr.list();
    
    openSession.close();
    return listProduct;
  }

  public Article getArticleById(Integer id) {
    Session openSession = HibernateUtil.getSessionFactory().openSession();

    Criteria cr = openSession.createCriteria(Article.class);
    cr.createAlias("product", "p", JoinType.LEFT_OUTER_JOIN);

    cr.add(Restrictions.eq("idArticle", id));

    List<Article> listProduct = cr.list();
    
    openSession.close();
    return listProduct.get(0);
  }
  
  
}

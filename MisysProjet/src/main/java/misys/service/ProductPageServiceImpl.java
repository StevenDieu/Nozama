package misys.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import misys.model.Article;
import misys.model.Artiste;
import misys.model.AttrProduct;
import misys.model.Product;
import misys.repository.ProductRepository;

/**
 * All method for page product
 *
 */
@Service
public class ProductPageServiceImpl implements ProductPageService {

  /**
   * It's a singleton for the service @ProductRepository
   */
  @Autowired
  private ProductRepository PR;
  
  private static Logger log = Logger.getLogger(ProductPageServiceImpl.class.getName());

  /**
   * Get product for the page of product
   * 
   * @param nameTagDateReleased
   * @return Map<String, Object>
   */
  public Map<String, Object> getProduct(String nameTagDateReleased) {
    Map<String, Object> product = new HashMap<String, Object>();

    try {
      Product thisProduct = PR.getProductByNameTagDate(nameTagDateReleased);
      margeAllResult(thisProduct, product);
    } catch (Exception e) {
      log.error(
          "[ProductPageServiceImpl] Error in the method getProduct impossible to get Product by NameTagDate");
    }

    return product;
  }

  /**
   * Marge all result of product in the List<Map>
   * 
   * @param allProduct
   * @param products
   */
  private void margeAllResult(Product thisProduct, Map<String, Object> product) {
    if (thisProduct != null) {
      margeAllResultSupport(product, thisProduct);
      insertInProducts(thisProduct.getArticles(), product);
    }
  }

  /**
   * Insert Product in the list<Map>
   * 
   * @param allProduct
   * @param type
   * @param articles
   * @param product
   */
  private void insertInProducts(Set<Article> articles, Map<String, Object> product) {
    List<Map<String, String>> listType = new ArrayList<Map<String, String>>();
    for (Article article : articles) {
      Map<String, String> insertTypeSupportAlbum = new HashMap<String, String>();
      insertTypeSupportAlbum.put("price", Float.toString(article.getPrice()));
      insertTypeSupportAlbum.put("support", article.getNameSupport());
      insertTypeSupportAlbum.put("id", Integer.toString(article.getIdArticle()));
      listType.add(insertTypeSupportAlbum);
    }
    Collections.sort(listType, mapComparatorSupport);

    product.put("listType", listType);
  }

  /**
   * Marge all result of product in the List<Map>
   * 
   * @param allProduct
   * @param products
   */
  private void margeAllResultSupport(Map<String, Object> product, Product thisProduct) {
    product.put(thisProduct.getType(), thisProduct);
    product.put("type", thisProduct.getType());
    product.put("name", thisProduct.getName());
    product.put("description", thisProduct.getDescription());
    product.put("urlPicture", thisProduct.getUrlPicture());
    product.put("dateReleased", thisProduct.getDateReleased());
    product.put("nameTagDateReleased", thisProduct.getNameTagDateReleased());
    if (thisProduct.getType().equals("single")) {
      elementForJustSinglePage(product, thisProduct);
    } else if (thisProduct.getType().equals("album")) {
      elementForJustAlbumPage(product, thisProduct);
    } else if (thisProduct.getType().equals("film")) {
      elementForJustMoviePage(product, thisProduct);
    }
    List<Map<String, String>> listType = new ArrayList<Map<String, String>>();
    product.put("listType", listType);
  }

  /**
   * Element of page just for Movie
   * 
   * @param product
   * @param thisProduct
   */
  private void elementForJustMoviePage(Map<String, Object> product, Product thisProduct) {
    List<String> genreList = new ArrayList<String>();

    for (AttrProduct attrProduct : thisProduct.getAttrProducts()) {
      if (attrProduct.getAttribut().equals("genre")) {
        genreList.add(attrProduct.getValue());
      }
    }

    if (genreList.size() > 0) {
      Collections.sort(genreList);
      product.put("genre", genreList);
    }
  }

  /**
   * Element of page just for Single
   * 
   * @param product
   * @param thisProduct
   */
  private void elementForJustSinglePage(Map<String, Object> product, Product thisProduct) {
    for (Artiste artiste : thisProduct.getArtistes()) {
      product.put("artisteName", artiste.getName());
    }
    List<String> genreList = new ArrayList<String>();

    for (AttrProduct attrProduct : thisProduct.getAttrProducts()) {
      if (attrProduct.getAttribut().equals("genre")) {
        genreList.add(attrProduct.getValue());
      } else if (attrProduct.getAttribut().equals("label")) {
        product.put("label", attrProduct.getValue());
      } else if (attrProduct.getAttribut().equals("totalTime")) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        try {
          product.put("totalTime", sdf.parse(attrProduct.getValue()));
        } catch (ParseException e) {
          log.error(
              "[ProductPageServiceImpl] Error in the method elementForJustSinglePage impossible to convert date");
          e.printStackTrace();
        }
      }
    }
    if (genreList.size() > 0) {
      Collections.sort(genreList);
      product.put("genre", genreList);
    }
    Product productParent = PR.getProductParent(thisProduct.getIdProduct());
    if (productParent != null) {
      product.put("albumName", productParent);
    }
  }

  /**
   * Element of page just for Album
   * 
   * @param product
   * @param thisProduct
   */
  private void elementForJustAlbumPage(Map<String, Object> product, Product thisProduct) {
    for (Artiste artiste : thisProduct.getArtistes()) {
      product.put("artisteName", artiste.getName());
    }
    List<String> genreList = new ArrayList<String>();
    for (AttrProduct attrProduct : thisProduct.getAttrProducts()) {
      if (attrProduct.getAttribut().equals("genre")) {
        genreList.add(attrProduct.getValue());
      } else if (attrProduct.getAttribut().equals("label")) {
        product.put("label", attrProduct.getValue());
      }
    }
    if (genreList.size() > 0) {
      Collections.sort(genreList);
      product.put("genre", genreList);
    }

    List<Product> listeProductChild = getProductsChild(thisProduct.getProducts());
    if (listeProductChild.size() > 0) {
      product.put("totalTime", getTimeAlbum(listeProductChild));
      product.put("allSingle", createListMapSingleChild(listeProductChild));
    }
  }

  /**
   * get time album with additional all time of single
   * 
   * @param listeProductChild
   * @return
   */
  private Date getTimeAlbum(List<Product> listeProductChild) {
    Calendar cal = Calendar.getInstance();

    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);

    for (Product product : listeProductChild) {
      Calendar calSingle = Calendar.getInstance();
      for (AttrProduct attrProduct : product.getAttrProducts()) {
        if (attrProduct.getAttribut().equals("totalTime")) {
          SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
          try {
            calSingle.setTime(sdf.parse(attrProduct.getValue()));
          } catch (ParseException e) {
            log.error(
                "[ProductPageServiceImpl] Error in the method getTimeAlbum impossible to convert date");
            e.printStackTrace();
          }
          cal.add(Calendar.HOUR_OF_DAY, calSingle.get(Calendar.HOUR_OF_DAY));
          cal.add(Calendar.MINUTE, calSingle.get(Calendar.MINUTE));
          cal.add(Calendar.SECOND, calSingle.get(Calendar.SECOND));
        }
      }
    }
    return cal.getTime();
  }

  /**
   * Create a list with all child (example an album have multi child)
   * 
   * @param listeProductChild
   * @return List<Map<String, Object>>
   */
  private List<Map<String, Object>> createListMapSingleChild(List<Product> listeProductChild) {

    List<Map<String, Object>> listMapSingleChild = new ArrayList<Map<String, Object>>();
    for (Product product : listeProductChild) {
      Map<String, Object> mapSingleChild = new HashMap<String, Object>();
      mapSingleChild.put("nameTagDateReleased", product.getNameTagDateReleased());
      mapSingleChild.put("name", product.getName());
      mapSingleChild.put("dateReleased", product.getDateReleased());
      for (AttrProduct attrProduct : product.getAttrProducts()) {
        if (attrProduct.getAttribut().equals("totalTime")) {
          mapSingleChild.put("totalTime", attrProduct.getValue());
        }
      }
      listMapSingleChild.add(mapSingleChild);
    }

    return listMapSingleChild;

  }

  /**
   * Get list of id child product
   * 
   * @param productChilds
   * @return
   */
  private List<Product> getProductsChild(Set<Product> productChilds) {
    List<Product> listProductChild = new ArrayList<>();
    for (Product product : productChilds) {
      listProductChild.add(PR.getProductById(product.getIdProduct()));
    }
    return listProductChild;
  }

  /**
   * Sort by support
   */
  private Comparator<Map<String, String>> mapComparatorSupport =
      new Comparator<Map<String, String>>() {
        public int compare(Map<String, String> m1, Map<String, String> m2) {
          return m1.get("support").compareTo(m2.get("support"));
        }
      };

}

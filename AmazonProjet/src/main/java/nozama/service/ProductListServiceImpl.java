package nozama.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nozama.model.Article;
import nozama.model.Product;
import nozama.repository.ProductRepository;

/**
 * All method for List product
 *
 */
@Service
public class ProductListServiceImpl implements ProductListService {

  /**
   * It's a singleton for the service @ProductRepository
   */
  @Autowired
  private ProductRepository PR;

  /**
   * get all product for the list page
   * 
   * @param support
   * @param recordType
   * @param years
   * @param genre
   * @return List<Map<String, Object>>
   */
  public List<Map<String, Object>> getAllProductByCondition(String support, String recordType,
      int years, String genre) {
    List<Map<String, Object>> allProduct = new ArrayList<Map<String, Object>>();
    List<String> listType = new ArrayList<String>();

    final boolean useSupport = checkUseSupport(support);
    final boolean useGenre = checkUseGenre(genre);
    final boolean useType = setListType(recordType, listType);

    final Map<String, Object> mapForDate = setMapForDate(years);

    List<Product> products = PR.getAllProductByTypeAndSupportAndDateAndGenre(useSupport, support,
        (boolean) mapForDate.get("useDate"), (Date) mapForDate.get("dateYears"),
        (Date) mapForDate.get("dateYearsAfter"), useType, listType, useGenre, genre);
    margeAllResultProduct(allProduct, products);
    Collections.sort(allProduct, mapComparator);
    return allProduct;

  }

  /**
   * Marge all result of product in the List<Map>
   * 
   * @param allProduct
   * @param products
   */
  private void margeAllResultProduct(List<Map<String, Object>> allProduct, List<Product> products) {
    for (Product product : products) {

      boolean flagExisteAlbumInList = true;
      for (Map<String, Object> productInAll : allProduct) {
        if (productInAll.get("type").equals(product.getType())) {
          if (product.getIdProduct() == (int) productInAll.get(product.getType())) {
            flagExisteAlbumInList = false;
          }
        }
      }
      if (flagExisteAlbumInList) {
        insertInProducts(allProduct, product.getType(), product.getArticles(), product);
      }
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
  private void insertInProducts(List<Map<String, Object>> allProduct, String type,
      Set<Article> articles, Product product) {
    Map<String, Object> newProduct = new HashMap<String, Object>();
    newProduct.put(type, product.getIdProduct());
    newProduct.put("type", type);
    if (type.equals("album")) {
      newProduct.put("urlType", "/liste-toutes-les-musiques/AllSupport/album/AllYears/ALL");
    } else if (type.equals("single")) {
      newProduct.put("urlType", "/liste-toutes-les-musiques/AllSupport/single/AllYears/ALL");
    } else if (type.equals("film")) {
      newProduct.put("urlType", "/liste-tous-les-films");
    }
    newProduct.put("name", product.getName());
    newProduct.put("description", product.getDescription());
    newProduct.put("urlPicture", product.getUrlPicture());
    newProduct.put("dateReleased", product.getDateReleased());
    newProduct.put("nameTagDateReleased", product.getNameTagDateReleased());
    List<Map<String, String>> listType = new ArrayList<Map<String, String>>();

    for (Article article : articles) {
      Map<String, String> insertTypeSupportAlbum = new HashMap<String, String>();
      insertTypeSupportAlbum.put("price", Float.toString(article.getPrice()));
      insertTypeSupportAlbum.put("support", article.getNameSupport());
      insertTypeSupportAlbum.put("id", Integer.toString(article.getIdArticle()));
      listType.add(insertTypeSupportAlbum);
    }
    Collections.sort(listType, mapComparatorSupport);

    newProduct.put("listType", listType);
    allProduct.add(newProduct);
  }

  /**
   * Cehck if we have condition with date
   * 
   * @param years
   * @return Map<String, Object>
   */
  public Map<String, Object> setMapForDate(int years) {
    Map<String, Object> mapForDate = new HashMap<String, Object>();
    boolean useDate = true;

    Date dateYearsAfter = null;
    Date dateYears = null;

    if (years == -1) {
      useDate = false;
    } else {
      dateYears = getDate(years);
      if (years == 1939) {
        dateYears = getDate(0);
        dateYearsAfter = dateYears;
      } else {
        dateYearsAfter = getDate(years + 10);
      }
    }

    mapForDate.put("useDate", useDate);
    mapForDate.put("dateYearsAfter", dateYearsAfter);
    mapForDate.put("dateYears", dateYears);
    return mapForDate;
  }

  /**
   * get date by year (int)
   * 
   * @param years
   * @return Date
   */
  private Date getDate(int years) {
    Calendar cal = Calendar.getInstance();

    cal.set(Calendar.YEAR, years);
    cal.set(Calendar.MONTH, 0);
    cal.set(Calendar.DAY_OF_MONTH, 1);
    cal.set(Calendar.HOUR, 0);
    cal.set(Calendar.MINUTE, 0);

    return cal.getTime();
  }

  /**
   * Sort by dateReleased
   */
  private Comparator<Map<String, Object>> mapComparator = new Comparator<Map<String, Object>>() {
    public int compare(Map<String, Object> m1, Map<String, Object> m2) {
      Date firstCompare = (Date) m1.get("dateReleased");
      Date secondeCompare = (Date) m2.get("dateReleased");
      return secondeCompare.compareTo(firstCompare);
    }
  };

  /**
   * Sort by support
   */
  private Comparator<Map<String, String>> mapComparatorSupport =
      new Comparator<Map<String, String>>() {
        public int compare(Map<String, String> m1, Map<String, String> m2) {
          return m1.get("support").compareTo(m2.get("support"));
        }
      };


  /**
   * Check if we use genre and add type in list
   * 
   * @param recordType
   * @param listType
   * @return boolean
   */
  private boolean setListType(String recordType, List<String> listType) {
    if (recordType.equals("AllType")) {
      listType.add("single");
      listType.add("album");
    } else if (recordType.equals("allProduct")) {
      return false;
    } else {
      listType.add(recordType);
    }
    return true;
  }

  /**
   * Check if we use genre
   * 
   * @param genre
   * @return boolean
   */
  private boolean checkUseGenre(String genre) {
    if (genre.equals("ALL")) {
      return false;
    }
    return true;
  }

  /**
   * Check if we use support
   * 
   * @param support
   * @return boolean
   */
  private boolean checkUseSupport(String support) {
    if (support.equals("AllSupport")) {
      return false;
    }
    return true;
  }

  /**
   * Get product for the home page
   * 
   * @param type
   * @return List<Map<String, Object>>
   */
  public List<Map<String, Object>> getProductHomeByCondition(String type) {
    List<String> listType = new ArrayList<String>();
    listType.add(type);

    List<Map<String, Object>> allProduct = new ArrayList<Map<String, Object>>();

    List<Product> products = PR.getAllProductByTypeAndAttribut(listType, "home");

    margeAllResultProduct(allProduct, products);

    Collections.sort(allProduct, mapComparator);

    return allProduct;
  }



}

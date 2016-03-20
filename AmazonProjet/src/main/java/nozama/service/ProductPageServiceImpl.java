package nozama.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nozama.model.Article;
import nozama.model.Artiste;
import nozama.model.AttrProduct;
import nozama.model.Product;
import nozama.repository.ProductRepository;

@Service
public class ProductPageServiceImpl implements ProductPageService {

  @Autowired
  private ProductRepository PR;

  @Override
  public Map<String, Object> getProduct(String nameTagDateReleased) {
    Map<String, Object> product = new HashMap<String, Object>();

    Product thisProduct = PR.getProductByNameTagDate(nameTagDateReleased);
    margeAllResult(thisProduct, product);

    return product;
  }

  private void margeAllResult(Product thisProduct, Map<String, Object> product) {
    if (thisProduct != null) {

      margeAllResultSupport(product, thisProduct);

      insertTypeInProducts(thisProduct.getArticles(), product);


    }
  }

  private void insertTypeInProducts(Set<Article> articles, Map<String, Object> product) {
    List<Map<String, String>> listType = new ArrayList<Map<String, String>>();
    for (Article article : articles) {
      Map<String, String> insertTypeSupportAlbum = new HashMap<String, String>();
      insertTypeSupportAlbum.put("price", Float.toString(article.getPrice()));
      insertTypeSupportAlbum.put("support", article.getNameSupport());
      insertTypeSupportAlbum.put("id", Integer.toString(article.getIdArticle()));
      listType.add(insertTypeSupportAlbum);
    }

    product.put("listType", listType);
  }

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

    }
    List<Map<String, String>> listType = new ArrayList<Map<String, String>>();
    product.put("listType", listType);
  }

  private void elementForJustSinglePage(Map<String, Object> product, Product thisProduct) {
    for (Artiste artiste : thisProduct.getArtistes()) {
      product.put("artisteName", artiste.getName());
    }
    for (AttrProduct attrProduct : thisProduct.getAttrProducts()) {
      if (attrProduct.getAttribut().equals("label")) {
        product.put("label", attrProduct.getValeur());
      } else if (attrProduct.getAttribut().equals("totalTime")) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        try {
          product.put("totalTime", sdf.parse(attrProduct.getValeur()));
        } catch (ParseException e) {
          e.printStackTrace();
        }
      }
    }
    Product productParent = PR.getProductParent(thisProduct.getIdProduct());
    if (productParent != null) {
      product.put("albumName", productParent);
    }
  }

  private void elementForJustAlbumPage(Map<String, Object> product, Product thisProduct) {
    for (Artiste artiste : thisProduct.getArtistes()) {
      product.put("artisteName", artiste.getName());
    }
    for (AttrProduct attrProduct : thisProduct.getAttrProducts()) {
      if (attrProduct.getAttribut().equals("label")) {
        product.put("label", attrProduct.getValeur());
      }
    }

    List<Product> listeProductChild = getProductsChild(thisProduct.getProducts());
    if (listeProductChild.size() > 0) {
      product.put("totalTime", getTimeAlbum(listeProductChild));
      product.put("allSingle", createListMapSingleChild(listeProductChild));
    }
  }

  private List<Map<String, Object>> createListMapSingleChild(List<Product> listeProductChild) {

    List<Map<String, Object>> listMapSingleChild = new ArrayList<Map<String, Object>>();
    for (Product product : listeProductChild) {
      Map<String, Object> mapSingleChild = new HashMap<String, Object>();
      mapSingleChild.put("nameTagDateReleased", product.getNameTagDateReleased());
      mapSingleChild.put("name", product.getName());
      mapSingleChild.put("dateReleased", product.getDateReleased());
      for (AttrProduct attrProduct : product.getAttrProducts()) {
        if (attrProduct.getAttribut().equals("totalTime")) {
            mapSingleChild.put("totalTime",attrProduct.getValeur());
        }
      }
      listMapSingleChild.add(mapSingleChild);
    }

    return listMapSingleChild;

  }


  private List<Product> getProductsChild(Set<Product> productChilds) {
    List<Product> listProductChild = new ArrayList<>();
    for (Product product : productChilds) {
      listProductChild.add(PR.getProductById(product.getIdProduct()));
    }
    return listProductChild;
  }

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
            calSingle.setTime(sdf.parse(attrProduct.getValeur()));
          } catch (ParseException e) {
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

}

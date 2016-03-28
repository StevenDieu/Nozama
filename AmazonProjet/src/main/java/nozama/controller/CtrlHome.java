package nozama.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nozama.service.ProductListServiceImpl;

/**
 * All controller for home or error page
 *
 */
@Controller
public class CtrlHome {

  /**
   * It's a singleton for the service @ProductListServiceImpl
   */
  @Autowired
  private ProductListServiceImpl PLS;

  /**
   * [Ctrl] redirect to home page
   * 
   * @param request
   * @return view index
   */
  @RequestMapping(value = "/")
  public ModelAndView index(HttpServletRequest request) {
    List<Map<String, Object>> singles = PLS.getProductHomeByCondition("single");
    List<Map<String, Object>> albums = PLS.getProductHomeByCondition("album");
    List<Map<String, Object>> movies = PLS.getProductHomeByCondition("film");

    Map<String, Object> product = new HashMap<String, Object>();

    int sizeSingles = 3;
    if (singles.size() < 3) {
      sizeSingles = singles.size();
    }
    int sizeAlbums = 3;
    if (albums.size() < 3) {
      sizeAlbums = albums.size();
    }
    int sizeMovies = 3;
    if (movies.size() < 3) {
      sizeMovies = movies.size();
    }

    if (sizeSingles != 0) {
      product.put("singles", singles.subList(0, sizeSingles));
    }
    if (sizeAlbums != 0) {
      product.put("albums", albums.subList(0, sizeAlbums));
    }
    if (sizeMovies != 0) {
      product.put("movies", movies.subList(0, sizeMovies));
    }
    return new ModelAndView("index", product);
  }

  @RequestMapping(value = "/404")
  public String error404() {
    return "404";
  }

}

package nozama.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nozama.service.ProductListServiceImpl;

@Controller
public class CtrlHome {

  @Autowired
  private ProductListServiceImpl PLS;

  @RequestMapping(value = "/")
  public ModelAndView index(HttpServletRequest request) {
//    List<Map<String, Object>> allProduct =
//        PLS.getAllProductByCondition(support, recordType, years, genre);
//
//    product.put("products", allProduct.subList(((startResult - 1) * 12), toIndexEndPagination));

    return new ModelAndView("index");
  }



}

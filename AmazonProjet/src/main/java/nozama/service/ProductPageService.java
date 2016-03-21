package nozama.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ProductPageService {

  Map<String, Object> getProduct(String nameTagDateReleased);
}

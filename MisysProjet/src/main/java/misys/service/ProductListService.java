package misys.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ProductListService {

  public List<Map<String, Object>> getAllProductByCondition(String support, String recordType,
      int years, String genre);

  public Map<String, Object> setMapForDate(int years);

  public List<Map<String, Object>> getProductHomeByCondition(String type);
}

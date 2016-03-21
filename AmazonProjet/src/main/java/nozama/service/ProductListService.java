package nozama.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface ProductListService {

  public String getParametersString(Optional<String> supportUrl, String stringDefault);


  List<Map<String, Object>> getAllProductByCondition(String support, String recordType, int years,
      String genre);


}

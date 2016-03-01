package nozama.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ProductPageService {
	public Map<String, Object> getProduct(String nameTagDateReleased, String type);
}

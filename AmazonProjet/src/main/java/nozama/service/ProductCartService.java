package nozama.service;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ProductCartService {
	public Object getAllCart(List<Map<String, Object>> attribute);
}

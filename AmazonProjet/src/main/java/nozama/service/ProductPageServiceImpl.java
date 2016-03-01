package nozama.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nozama.repository.ProductRepository;

@Service
public class ProductPageServiceImpl implements ProductPageService {

	@Autowired
	private ProductRepository PR;

	@Override
	public Map<String, Object> getProduct(String nameTagDateReleased, String type) {
		if(type == "single"){
			PR.getProductSingle(nameTagDateReleased);
		}else if(type == "album"){
			PR.getProductAlbum(nameTagDateReleased);
		}else{
			PR.getProductMovie(nameTagDateReleased);
		}
		return null;
	}

}

package nozama.service;

import java.util.List;

import org.springframework.stereotype.Service;

import nozama.model.Product;

@Service
public interface ProductService {
	public List<Product> getAllProductByTypeProduct(String typeProduct);
}

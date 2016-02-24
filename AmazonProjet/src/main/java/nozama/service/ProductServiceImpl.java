package nozama.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nozama.model.Product;
import nozama.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository PR;

	@Override
	public List<Product> getAllSingleBySupport(String support) {
		return PR.getAllSingleBySupport(support);
	}
	
	@Override
	public List<Product> getAllMovieBySupport(String support) {
		return PR.getAllMovieBySupport(support);
	}

}

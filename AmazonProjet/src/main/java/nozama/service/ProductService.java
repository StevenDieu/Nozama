package nozama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import nozama.model.Product;

@Service
public interface ProductService {
	public List<Product> getAllMusicsBySupport(String support, String recordType, int years, String type);
	public List<Product> getAllMovieBySupport(String support, String type);
	public String getParametersString(Optional<String> supportUrl, String stringDefault);
}

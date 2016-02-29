package nozama.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface ProductService {
	public List<Map<String, Object>> getAllMusicsBySupport(String support, String recordType, int years, String type, int startResult);
	public List<Map<String, Object>> getAllMovieBySupport(String support, String type, int startResult, int years);
	public String getParametersString(Optional<String> supportUrl, String stringDefault);
	public int getCountAllMusicBySupport(String support, String recordType, int years, String type);
	public int getCountMovieBySupport(String support, String type, int years);
	public List<Map<String, Object>> getAllProduct(int years, int startResult);
}

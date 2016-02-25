package nozama.service;

import java.util.Calendar;
import java.util.Date;
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
	public List<Product> getAllMusicsBySupport(String support, String recordType, int years) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, years);
		Date dateYears = cal.getTime();
		
		if(years == -1){
			if (recordType.equals("single")) {
				return PR.getAllSingleBySupport(support);
			} else {
				return PR.getAllAlbumBySupport(support);
			}
		}else if(years == 2000){
			if (recordType.equals("single")) {
				return PR.getAllSingleBySupportMiniYears(support,dateYears);
			} else {
				return PR.getAllAlbumBySupportMiniYears(support,dateYears);
			}
		}else{

			
			cal.set(Calendar.YEAR, years + 10);
			Date dateYearsMoreTen = cal.getTime();
			
			if (recordType.equals("single")) {
				return PR.getAllSingleBySupportBetweenYears(support,dateYears,dateYearsMoreTen);
			} else {
				return PR.getAllAlbumBySupportBetweenYears(support,dateYears,dateYearsMoreTen);
			}
		}
		
		
	}

	@Override
	public List<Product> getAllMovieBySupport(String support, int years) {
		return PR.getAllMovieBySupport(support);
	}

}

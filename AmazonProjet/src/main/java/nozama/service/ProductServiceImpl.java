package nozama.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nozama.model.Product;
import nozama.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository PR;

	@Override
	public List<Product> getAllMusicsBySupport(String support, String recordType, int years, String type, int startResult) {
		boolean useType = true;
		Map<String, Object> mapForDate = new HashMap<String, Object>();
		setMapForDate(years, mapForDate);

		if (type.equals("ALL")) {
			useType = false;
		}

		if (recordType.equals("single")) {
			return PR.getAllSingleBySupportAndTypeBetweenYears(support, (boolean) mapForDate.get("useDate"), useType, (Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"), type, startResult);
		} else {
			return PR.getAllAlbumBySupportAndTypBetweenYears(support, (boolean) mapForDate.get("useDate"), useType, (Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"), type, startResult);
		}

	}

	@Override
	public List<Product> getAllMovieBySupport(String support, String type, int startResult, int years) {
		boolean useType = true;
		Map<String, Object> mapForDate = new HashMap<String, Object>();
		setMapForDate(years, mapForDate);

		if (type.equals("ALL")) {
			useType = false;
		}

		return PR.getAllMovieBySupport(support, (boolean) mapForDate.get("useDate"), useType, (Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"), type, startResult);
	}

	@Override
	public int getCountAllMusicBySupport(String support, String recordType, int years, String type) {
		boolean useType = true;

		Map<String, Object> mapForDate = new HashMap<String, Object>();
		setMapForDate(years, mapForDate);

		if (type.equals("ALL")) {
			useType = false;
		}

		if (recordType.equals("single")) {
			return PR.getCountAllMusicBySupport(support, (boolean) mapForDate.get("useDate"), useType, (Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"), type);
		} else {
			return PR.getCountAllAlbumBySupport(support, (boolean) mapForDate.get("useDate"), useType, (Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"), type);
		}
	}

	@Override
	public int getCountMovieBySupport(String support, String type, int years) {
		boolean useType = true;

		Map<String, Object> mapForDate = new HashMap<String, Object>();
		setMapForDate(years, mapForDate);

		return PR.getCountAllLovieBySupport(support, (boolean) mapForDate.get("useDate"), useType, (Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"), type);
	}

	@Override
	public String getParametersString(Optional<String> supportUrl, String stringDefault) {
		String stringParameters;
		if (supportUrl.isPresent()) {
			stringParameters = supportUrl.get();
		} else {
			stringParameters = stringDefault;
		}
		return stringParameters;
	}

	@Override
	public void getAllProduct(int years) {
		Map<String, Object> mapForDate = new HashMap<String, Object>();
		setMapForDate(years, mapForDate);
		
		PR.getAllAlbumByDate((boolean) mapForDate.get("useDate"), (Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"));
		PR.getAllMovieByDate((boolean) mapForDate.get("useDate"), (Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"));
		PR.getAllSingleByDate((boolean) mapForDate.get("useDate"), (Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"));
	}

	public void setMapForDate(int years, Map<String, Object> mapForDate) {
		boolean useDate = true;

		Date dateYearsAfter = null;
		Date dateYears = null;

		if (years == -1) {
			useDate = false;
		} else {
			dateYears = getDate(years);
			if (years == 1939) {
				dateYears = getDate(0);
				dateYearsAfter = dateYears;
			} else {
				dateYearsAfter = getDate(years + 10);
			}
		}

		mapForDate.put("useDate", useDate);
		mapForDate.put("dateYearsAfter", dateYearsAfter);
		mapForDate.put("dateYears", dateYears);
	}

	private Date getDate(int years) {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, years);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);

		return cal.getTime();
	}

}

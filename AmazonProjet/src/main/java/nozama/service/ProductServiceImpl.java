package nozama.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
		boolean useDate = true;
		boolean useType = true;

		Calendar cal = Calendar.getInstance();
		Date dateYearsAfter = null;
		Date dateYears = null;

		if (years == -1) {
			useDate = false;
		} else {
			cal.set(Calendar.YEAR, years);
			dateYears = cal.getTime();
			if (years == 1939) {
				Calendar calNow = Calendar.getInstance();
				cal.set(Calendar.YEAR, 0);
				dateYears = calNow.getTime();
				cal.set(Calendar.YEAR, years);
				dateYearsAfter = cal.getTime();
			} else {
				cal.set(Calendar.YEAR, years + 10);
				dateYearsAfter = cal.getTime();
			}
		}

		if (type.equals("ALL")) {
			useType = false;
		}

		if (recordType.equals("single")) {
			return PR.getAllSingleBySupportAndTypeBetweenYears(support, useDate, useType, dateYears, dateYearsAfter, type, startResult);
		} else {
			return PR.getAllAlbumBySupportAndTypBetweenYears(support, useDate, useType, dateYears, dateYearsAfter, type, startResult);
		}

	}

	@Override
	public List<Product> getAllMovieBySupport(String support, String type, int startResult) {
		boolean useType = true;

		if (type.equals("ALL")) {
			useType = false;
		}

		return PR.getAllMovieBySupport(support, useType, type, startResult);
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
	public int getCountAllMusicBySupport(String support, String recordType, int years, String type) {
		boolean useDate = true;
		boolean useType = true;

		Calendar cal = Calendar.getInstance();
		Date dateYearsAfter = null;
		Date dateYears = null;

		if (years == -1) {
			useDate = false;
		} else {
			cal.set(Calendar.YEAR, years);
			dateYears = cal.getTime();
			if (years == 2000) {
				Calendar calNow = Calendar.getInstance();
				dateYearsAfter = calNow.getTime();
			} else {
				cal.set(Calendar.YEAR, years + 10);
				dateYearsAfter = cal.getTime();
			}
		}

		if (type.equals("ALL")) {
			useType = false;
		}

		if (recordType.equals("single")) {
			return PR.getCountAllMusicBySupport(support, useDate, useType, dateYears, dateYearsAfter, type);
		} else {
			return PR.getCountAllAlbumBySupport(support, useDate, useType, dateYears, dateYearsAfter, type);
		}
	}

	@Override
	public int getCountMovieBySupport(String support, String type) {
		boolean useType = true;

		if (type.equals("ALL")) {
			useType = false;
		}

		return PR.getCountAllLovieBySupport(support, useType, type);
	}

}

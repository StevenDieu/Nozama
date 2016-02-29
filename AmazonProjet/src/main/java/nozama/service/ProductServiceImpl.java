package nozama.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nozama.model.Categorie;
import nozama.model.Product;
import nozama.model.TypeSupport;
import nozama.model.TypeSupportAlbum;
import nozama.model.TypeSupportMovie;
import nozama.model.TypeSupportSingle;
import nozama.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository PR;

	@Override
	public List<Product> getAllMusicsBySupport(String support, String recordType, int years, String type,
			int startResult) {
		boolean useType = true;
		Map<String, Object> mapForDate = new HashMap<String, Object>();
		setMapForDate(years, mapForDate);

		if (type.equals("ALL")) {
			useType = false;
		}

		if (recordType.equals("single")) {
			return PR.getAllSingleBySupportAndTypeBetweenYears(support, (boolean) mapForDate.get("useDate"), useType,
					(Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"), type, startResult);
		} else {
			return PR.getAllAlbumBySupportAndTypBetweenYears(support, (boolean) mapForDate.get("useDate"), useType,
					(Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"), type, startResult);
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

		return PR.getAllMovieBySupport(support, (boolean) mapForDate.get("useDate"), useType,
				(Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"), type, startResult);
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
			return PR.getCountAllMusicBySupport(support, (boolean) mapForDate.get("useDate"), useType,
					(Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"), type);
		} else {
			return PR.getCountAllAlbumBySupport(support, (boolean) mapForDate.get("useDate"), useType,
					(Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"), type);
		}
	}

	@Override
	public int getCountMovieBySupport(String support, String type, int years) {
		boolean useType = true;

		Map<String, Object> mapForDate = new HashMap<String, Object>();
		setMapForDate(years, mapForDate);

		return PR.getCountAllLovieBySupport(support, (boolean) mapForDate.get("useDate"), useType,
				(Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"), type);
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
	public List<Map<String, Object>> getAllProduct(int years, int startResult) {
		List<Map<String, Object>> allProduct = new ArrayList<Map<String, Object>>();

		Map<String, Object> mapForDate = new HashMap<String, Object>();
		setMapForDate(years, mapForDate);

		List<TypeSupportAlbum> typeSupportAlbums = PR.getAllAlbumByDate((boolean) mapForDate.get("useDate"),
				(Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"));
		List<TypeSupportMovie> typeSupportMovie = PR.getAllMovieByDate((boolean) mapForDate.get("useDate"),
				(Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"));
		List<TypeSupportSingle> typeSupportSingle = PR.getAllSingleByDate((boolean) mapForDate.get("useDate"),
				(Date) mapForDate.get("dateYears"), (Date) mapForDate.get("dateYearsAfter"));

		margeAllResultTypeSupportAlbum(typeSupportAlbums, allProduct);
		margeAllResultTypeSupportMovie(typeSupportMovie, allProduct);
		margeAllResultTypeSupportSignle(typeSupportSingle, allProduct);
		


		Collections.sort(allProduct, mapComparator);
		return allProduct;
	}
	
	public Comparator<Map<String, String>> mapComparator = new Comparator<Map<String, String>>() {
	    public int compare(Map<String, String> m1, Map<String, String> m2) {
	        return m1.get("name").compareTo(m2.get("name"));
	    }
	};
	

	private void margeAllResultTypeSupportAlbum(List<TypeSupportAlbum> typeSupportAlbums,
			List<Map<String, Object>> allProduct) {

		for (TypeSupportAlbum typeSupportAlbum : typeSupportAlbums) {
			margeAllResultSupport(allProduct, "album", typeSupportAlbum, typeSupportAlbum.getAlbum());
		}

	}

	private void margeAllResultTypeSupportMovie(List<TypeSupportMovie> typeSupportMovies,
			List<Map<String, Object>> allProduct) {

		for (TypeSupportMovie typeSupportMovie : typeSupportMovies) {
			margeAllResultSupport(allProduct, "movie", typeSupportMovie, typeSupportMovie.getMovie());
		}

	}

	private void margeAllResultTypeSupportSignle(List<TypeSupportSingle> typeSupportSingles,
			List<Map<String, Object>> allProduct) {

		for (TypeSupportSingle typeSupportSingle : typeSupportSingles) {
			margeAllResultSupport(allProduct, "single", typeSupportSingle, typeSupportSingle.getSingle());
		}

	}

	@SuppressWarnings("unchecked")
	private void margeAllResultSupport(List<Map<String, Object>> allProduct, String Type, TypeSupport typeSupport,
			Categorie typeSupportCategorie) {
		boolean flagExisteAlbumInList = false;
		for (Map<String, Object> product : allProduct) {
			if (typeSupportCategorie == product.get(Type)) {
				flagExisteAlbumInList = true;
				insertTypeInProducts(typeSupport, product, (List<Map<String, String>>) product.get("listType"));
			}
		}

		if (!flagExisteAlbumInList) {
			insertInProducts(allProduct, Type, typeSupport, typeSupportCategorie);
		}
	}

	private void insertInProducts(List<Map<String, Object>> allProduct, String Type, TypeSupport typeSupport,
			Categorie typeSupportCategorie) {
		Map<String, Object> newProduct = new HashMap<String, Object>();
		newProduct.put(Type, typeSupportCategorie);
		newProduct.put("name", typeSupportCategorie.getProduct().getName());
		newProduct.put("description", typeSupportCategorie.getProduct().getDescription());
		newProduct.put("urlPicture", typeSupportCategorie.getProduct().getUrlPicture());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(typeSupportCategorie.getDateReleased());
		newProduct.put("dateReleased", Integer.toString(calendar.get(Calendar.YEAR)));
		List<Map<String, String>> listType = new ArrayList<Map<String, String>>();

		insertTypeInProducts(typeSupport, newProduct,listType);
		allProduct.add(newProduct);
	}

	private void insertTypeInProducts(TypeSupport typeSupport, Map<String, Object> product, List<Map<String, String>> listType) {
		Map<String, String> insertTypeSupportAlbum = new HashMap<String, String>();
		insertTypeSupportAlbum.put("price", Integer.toString(typeSupport.getPrice()));
		insertTypeSupportAlbum.put("support", typeSupport.getNameSupport());
		insertTypeSupportAlbum.put("id", Integer.toString(typeSupport.getIdTypeSupport()));
		listType.add(insertTypeSupportAlbum);
		product.put("listType", listType);
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

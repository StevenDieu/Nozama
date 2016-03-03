package nozama.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nozama.model.AlbumHasSingle;
import nozama.model.Categorie;
import nozama.model.TypeSupport;
import nozama.model.TypeSupportAlbum;
import nozama.model.TypeSupportMovie;
import nozama.model.TypeSupportSingle;
import nozama.repository.ProductRepository;

@Service
public class ProductPageServiceImpl implements ProductPageService {

	@Autowired
	private ProductRepository PR;

	@Override
	public Map<String, Object> getProduct(String nameTagDateReleased, String type) {
		Map<String, Object> product = new HashMap<String, Object>();
		if (type.equals("single")) {
			List<TypeSupportSingle> typeSupportSingles = PR.getProductSingle(nameTagDateReleased);
			margeAllResultTypeSupportSingle(typeSupportSingles, product);
		} else if (type.equals("album")) {
			List<TypeSupportAlbum> typeSupportAlbums = PR.getProductAlbum(nameTagDateReleased);
			margeAllResultTypeSupportAlbum(typeSupportAlbums, product);
		} else {
			List<TypeSupportMovie> typeSupportMovies = PR.getProductMovie(nameTagDateReleased);
			margeAllResultTypeSupportMovie(typeSupportMovies, product);
		}
		return product;
	}

	@SuppressWarnings("unchecked")
	private void margeAllResultTypeSupportAlbum(List<TypeSupportAlbum> typeSupportAlbums, Map<String, Object> product) {
		if (typeSupportAlbums.size() >= 1) {
			TypeSupportAlbum typeSupport = typeSupportAlbums.get(0);
			margeAllResultSupport(product, "album", typeSupport, typeSupport.getAlbum());

			for (TypeSupportAlbum typeSupportAlbum : typeSupportAlbums) {
				insertTypeInProducts(typeSupportAlbum, product, (List<Map<String, String>>) product.get("listType"));
			}
			List<AlbumHasSingle> allSingle = PR.getAllSingle(typeSupport.getAlbum().getIdAlbum());

			product.put("artisteName", typeSupport.getAlbum().getArtiste().getName());
			product.put("label", typeSupport.getAlbum().getLabel());
			product.put("allSingle", allSingle);
			product.put("totalTime", getTimeAlbum(allSingle));
		}
	}

	@SuppressWarnings("unchecked")
	private void margeAllResultTypeSupportMovie(List<TypeSupportMovie> typeSupportMovies, Map<String, Object> product) {
		if (typeSupportMovies.size() >= 1) {

			TypeSupportMovie typeSupport = typeSupportMovies.get(0);
			margeAllResultSupport(product, "film", typeSupport, typeSupport.getMovie());

			for (TypeSupportMovie typeSupportMovie : typeSupportMovies) {
				insertTypeInProducts(typeSupportMovie, product, (List<Map<String, String>>) product.get("listType"));
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void margeAllResultTypeSupportSingle(List<TypeSupportSingle> typeSupportSingles, Map<String, Object> product) {
		if (typeSupportSingles.size() >= 1) {

			TypeSupportSingle typeSupport = typeSupportSingles.get(0);
			margeAllResultSupport(product, "single", typeSupport, typeSupport.getSingle());

			for (TypeSupportSingle typeSupportSingle : typeSupportSingles) {
				insertTypeInProducts(typeSupportSingle, product, (List<Map<String, String>>) product.get("listType"));
			}
			List<AlbumHasSingle> allAlbum = PR.getNameAlbumBySingle(typeSupport.getSingle().getIdSingle());

			product.put("artisteName", typeSupport.getSingle().getArtiste().getName());
			product.put("label", typeSupport.getSingle().getLabel());
			product.put("totalTime", typeSupport.getSingle().getTotalTime());
			product.put("albumName", allAlbum);
		}
	}

	private void margeAllResultSupport(Map<String, Object> product, String Type, TypeSupport typeSupport, Categorie typeSupportCategorie) {
		product.put(Type, typeSupportCategorie);
		product.put("type", Type);
		product.put("name", typeSupportCategorie.getProduct().getName());
		product.put("description", typeSupportCategorie.getProduct().getDescription());
		product.put("urlPicture", typeSupportCategorie.getProduct().getUrlPicture());
		product.put("dateReleased", typeSupportCategorie.getProduct().getDateReleased());
		product.put("nameTagDateReleased", typeSupportCategorie.getProduct().getNameTagDateReleased());
		List<Map<String, String>> listType = new ArrayList<Map<String, String>>();
		product.put("listType", listType);
	}
	
	private Date getTimeAlbum(List<AlbumHasSingle> allSingle) {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		for(AlbumHasSingle albumHasSingle : allSingle){
			Calendar calSingle = Calendar.getInstance();
			calSingle.setTime(albumHasSingle.getSingle().getTotalTime());
			cal.add(Calendar.HOUR_OF_DAY, calSingle.get(Calendar.HOUR_OF_DAY));
			cal.add(Calendar.MINUTE, calSingle.get(Calendar.MINUTE));
			cal.add(Calendar.SECOND, calSingle.get(Calendar.SECOND));
		}
		
		return cal.getTime();
	}

	private void insertTypeInProducts(TypeSupport typeSupport, Map<String, Object> product, List<Map<String, String>> listType) {
		Map<String, String> insertTypeSupportAlbum = new HashMap<String, String>();
		insertTypeSupportAlbum.put("price", Float.toString(typeSupport.getPrice()));
		insertTypeSupportAlbum.put("support", typeSupport.getNameSupport());
		insertTypeSupportAlbum.put("id", Integer.toString(typeSupport.getIdTypeSupport()));
		listType.add(insertTypeSupportAlbum);
		product.put("listType", listType);
	}

}

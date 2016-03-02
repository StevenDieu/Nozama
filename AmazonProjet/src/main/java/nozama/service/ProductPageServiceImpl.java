package nozama.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		TypeSupportAlbum typeSupport = typeSupportAlbums.get(0);
		margeAllResultSupport(product, "single", typeSupport, typeSupport.getAlbum());

		for (TypeSupportAlbum typeSupportAlbum : typeSupportAlbums) {
			insertTypeInProducts(typeSupportAlbum, product,  (List<Map<String, String>>) product.get("listType"));
		}
		product.put("artisteName", typeSupport.getAlbum().getArtiste().getName());
		product.put("allSingle", PR.getAllSingle(typeSupport.getAlbum().getIdAlbum()));
	}

	@SuppressWarnings("unchecked")
	private void margeAllResultTypeSupportMovie(List<TypeSupportMovie> typeSupportMovies, Map<String, Object> product) {
		 
		
		TypeSupportMovie typeSupport = typeSupportMovies.get(0);
		margeAllResultSupport(product, "movie", typeSupport, typeSupport.getMovie());
		
		for (TypeSupportMovie typeSupportMovie : typeSupportMovies) {
			insertTypeInProducts(typeSupportMovie, product,  (List<Map<String, String>>) product.get("listType"));
		}
		
	}

	@SuppressWarnings("unchecked")
	private void margeAllResultTypeSupportSingle(List<TypeSupportSingle> typeSupportSingles, Map<String, Object> product) {

		TypeSupportSingle typeSupport = typeSupportSingles.get(0);
		margeAllResultSupport(product, "single", typeSupport, typeSupport.getSingle());

		for (TypeSupportSingle typeSupportSingle : typeSupportSingles) {
			insertTypeInProducts(typeSupportSingle, product,  (List<Map<String, String>>) product.get("listType"));
		}
		product.put("artisteName", typeSupport.getSingle().getArtiste().getName());
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
	
	private void insertTypeInProducts(TypeSupport typeSupport, Map<String, Object> product, List<Map<String, String>> listType) {
		Map<String, String> insertTypeSupportAlbum = new HashMap<String, String>();
		insertTypeSupportAlbum.put("price", Integer.toString(typeSupport.getPrice()));
		insertTypeSupportAlbum.put("support", typeSupport.getNameSupport());
		insertTypeSupportAlbum.put("id", Integer.toString(typeSupport.getIdTypeSupport()));
		listType.add(insertTypeSupportAlbum);
		product.put("listType", listType);
	}

}

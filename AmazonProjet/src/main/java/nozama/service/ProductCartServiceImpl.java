package nozama.service;

import java.text.DecimalFormat;
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
import nozama.model.User;
import nozama.repository.ProductRepository;

@Service
public class ProductCartServiceImpl implements ProductCartService {

	@Autowired
	private ProductRepository PR;
	
	private List<Map<String, Object>> allCart;

	public List<Map<String, Object>> getAllCart(List<Map<String, Object>> allCart) {
		this.allCart = allCart;
		List<Map<String, Object>> allProduct = new ArrayList<Map<String, Object>>();

		List<Integer> allIdSingle = new ArrayList<Integer>();
		List<Integer> allIdAlbum = new ArrayList<Integer>();
		List<Integer> allIdMovie = new ArrayList<Integer>();
		for (Map<String, Object> productCart : allCart) {
			if (((String) productCart.get("type")).equals("single")) {
				allIdSingle.add((Integer) productCart.get("id"));
			} else if (((String) productCart.get("type")).equals("album")) {
				allIdAlbum.add((Integer) productCart.get("id"));
			} else if (((String) productCart.get("type")).equals("movie")) {
				allIdMovie.add((Integer) productCart.get("id"));
			}
		}

		if (allIdSingle.size() > 0) {
			List<TypeSupportSingle> typeSupportSingle = PR.getAllSingle(allIdSingle);
			margeAllResultTypeSupportSignle(typeSupportSingle, allProduct);
		}
		if (allIdAlbum.size() > 0) {
			List<TypeSupportAlbum> typeSupportAlbums = PR.getAllAlbum(allIdAlbum);
			margeAllResultTypeSupportAlbum(typeSupportAlbums, allProduct);
		}
		if (allIdMovie.size() > 0) {
			List<TypeSupportMovie> typeSupportMovie = PR.getAllMovie(allIdMovie);
			margeAllResultTypeSupportMovie(typeSupportMovie, allProduct);
		}

		return allProduct;
	}

	public void margeAllResultTypeSupportAlbum(List<TypeSupportAlbum> typeSupportAlbums, List<Map<String, Object>> allProduct) {
		for (TypeSupportAlbum typeSupportAlbum : typeSupportAlbums) {
			insertInProducts(allProduct, "album", "album", (TypeSupport) typeSupportAlbum, (Categorie) typeSupportAlbum.getAlbum(), "/liste-toutes-les-musiques/AllSupport/album/AllYears/ALL");
		}
	}

	public void margeAllResultTypeSupportMovie(List<TypeSupportMovie> typeSupportMovies, List<Map<String, Object>> allProduct) {

		for (TypeSupportMovie typeSupportMovie : typeSupportMovies) {
			insertInProducts(allProduct, "movie", "film", (TypeSupport) typeSupportMovie, (Categorie) typeSupportMovie.getMovie(), "/liste-tous-les-films");
		}
	}

	public void margeAllResultTypeSupportSignle(List<TypeSupportSingle> typeSupportSingles, List<Map<String, Object>> allProduct) {
		for (TypeSupportSingle typeSupportSingle : typeSupportSingles) {
			insertInProducts(allProduct, "single", "single", (TypeSupport) typeSupportSingle, (Categorie) typeSupportSingle.getSingle(), "/liste-toutes-les-musiques/AllSupport/single/AllYears/ALL");
		}
	}

	private void insertInProducts(List<Map<String, Object>> allProduct, String type, String typeHtml, TypeSupport typeSupport, Categorie typeSupportCategorie, String urlType) {
		Map<String, Object> newProduct = new HashMap<String, Object>();
		newProduct.put(type, typeSupportCategorie);
		newProduct.put("type", type);
		newProduct.put("typeHtml", typeHtml);
		newProduct.put("urlType", urlType);
		newProduct.put("name", typeSupportCategorie.getProduct().getName());
		newProduct.put("description", typeSupportCategorie.getProduct().getDescription());
		newProduct.put("urlPicture", typeSupportCategorie.getProduct().getUrlPicture());
		newProduct.put("dateReleased", typeSupportCategorie.getProduct().getDateReleased());
		newProduct.put("nameTagDateReleased", typeSupportCategorie.getProduct().getNameTagDateReleased());
		newProduct.put("price", Float.toString(typeSupport.getPrice()));
		newProduct.put("support", typeSupport.getNameSupport());
		newProduct.put("id", Integer.toString(typeSupport.getIdTypeSupport()));
		for (Map<String, Object> productCart : allCart) {
			if (((Integer) productCart.get("id")).equals(typeSupport.getIdTypeSupport()) && productCart.get("type").equals(type)) {
				DecimalFormat df = new DecimalFormat("0.00");
				newProduct.put("numberProduct", (Integer) productCart.get("number"));
				newProduct.put("totalProduct", df.format(((Integer) productCart.get("number")) * typeSupport.getPrice()));
			}
		}

		allProduct.add(newProduct);
	}

}

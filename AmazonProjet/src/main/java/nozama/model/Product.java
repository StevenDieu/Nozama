package nozama.model;
// Generated 26 f�vr. 2016 19:41:07 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Product generated by hbm2java
 */
@Entity
@Table(name = "product", catalog = "nozama")
public class Product implements java.io.Serializable {

	private Integer idProduct;
	private String name;
	private String description;
	private String urlPicture;
	private Set<Movie> movies = new HashSet<Movie>(0);
	private Set<Album> albums = new HashSet<Album>(0);
	private Set<Single> singles = new HashSet<Single>(0);

	public Product() {
	}

	public Product(String name, String urlPicture) {
		this.name = name;
		this.urlPicture = urlPicture;
	}

	public Product(String name, String description, String urlPicture, Set<Movie> movies, Set<Album> albums, Set<Single> singles) {
		this.name = name;
		this.description = description;
		this.urlPicture = urlPicture;
		this.movies = movies;
		this.albums = albums;
		this.singles = singles;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_product", unique = true, nullable = false)
	public Integer getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "url_picture", nullable = false, length = 1024)
	public String getUrlPicture() {
		return this.urlPicture;
	}

	public void setUrlPicture(String urlPicture) {
		this.urlPicture = urlPicture;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Movie> getMovies() {
		return this.movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Single> getSingles() {
		return this.singles;
	}

	public void setSingles(Set<Single> singles) {
		this.singles = singles;
	}

}

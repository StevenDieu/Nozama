package nozama.model;
// Generated 25 f�vr. 2016 15:17:06 by Hibernate Tools 4.3.1.Final

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
 * Artiste generated by hbm2java
 */
@Entity
@Table(name = "artiste", catalog = "nozama")
public class Artiste implements java.io.Serializable {

	private Integer idArtiste;
	private String name;
	private Set<Album> albums = new HashSet<Album>(0);
	private Set<Single> singles = new HashSet<Single>(0);

	public Artiste() {
	}

	public Artiste(String name) {
		this.name = name;
	}

	public Artiste(String name, Set<Album> albums, Set<Single> singles) {
		this.name = name;
		this.albums = albums;
		this.singles = singles;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_artiste", unique = true, nullable = false)
	public Integer getIdArtiste() {
		return this.idArtiste;
	}

	public void setIdArtiste(Integer idArtiste) {
		this.idArtiste = idArtiste;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "artiste")
	public Set<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "artiste")
	public Set<Single> getSingles() {
		return this.singles;
	}

	public void setSingles(Set<Single> singles) {
		this.singles = singles;
	}

}

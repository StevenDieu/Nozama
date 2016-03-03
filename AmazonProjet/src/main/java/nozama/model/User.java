package nozama.model;
// Generated 2 mars 2016 20:06:10 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "nozama", uniqueConstraints = @UniqueConstraint(columnNames = "emailAdress") )
public class User implements java.io.Serializable {

	private Integer idUsers;
	private String name;
	private String lastname;
	private String emailAdress;
	private String password;
	private String ipAddress;
	private String genre;

	public User() {
	}

	public User(String name, String lastname, String emailAdress, String password, String ipAddress, String genre) {
		this.name = name;
		this.lastname = lastname;
		this.emailAdress = emailAdress;
		this.password = password;
		this.ipAddress = ipAddress;
		this.genre = genre;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idUsers", unique = true, nullable = false)
	public Integer getIdUsers() {
		return this.idUsers;
	}

	public void setIdUsers(Integer idUsers) {
		this.idUsers = idUsers;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "lastname", nullable = false)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "emailAdress", unique = true, nullable = false)
	public String getEmailAdress() {
		return this.emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "ipAddress", nullable = false, length = 45)
	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(name = "genre", nullable = false, length = 3)
	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}

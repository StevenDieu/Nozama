package nozama.model;
// Generated 21 mars 2016 08:24:02 by Hibernate Tools 4.3.1.Final


import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "nozama",
    uniqueConstraints = @UniqueConstraint(columnNames = "emailAdress") )
public class User implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Integer idUsers;
  private String name;
  private String lastname;
  private String emailAdress;
  private String password;
  private String ipAddress;
  private String genre;
  private float comptePrepaye;
  private Date createTime;
  private Set<Adress> adresses = new HashSet<Adress>(0);
  private Set<Order> orders = new HashSet<Order>(0);

  public User() {}


  public User(String name, String lastname, String emailAdress, String password, String ipAddress,
      String genre, float comptePrepaye, Date createTime) {
    this.name = name;
    this.lastname = lastname;
    this.emailAdress = emailAdress;
    this.password = password;
    this.ipAddress = ipAddress;
    this.genre = genre;
    this.comptePrepaye = comptePrepaye;
    this.createTime = createTime;
  }

  public User(String name, String lastname, String emailAdress, String password, String ipAddress,
      String genre, float comptePrepaye, Date createTime, Set<Adress> adresses, Set<Order> orders) {
    this.name = name;
    this.lastname = lastname;
    this.emailAdress = emailAdress;
    this.password = password;
    this.ipAddress = ipAddress;
    this.genre = genre;
    this.comptePrepaye = comptePrepaye;
    this.createTime = createTime;
    this.adresses = adresses;
    this.orders = orders;
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


  @Column(name = "compte_prepaye", nullable = false, precision = 12, scale = 0)
  public float getComptePrepaye() {
    return this.comptePrepaye;
  }

  public void setComptePrepaye(float comptePrepaye) {
    this.comptePrepaye = comptePrepaye;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_time", nullable = false, length = 19)
  public Date getCreateTime() {
    return this.createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  public Set<Adress> getAdresses() {
    return this.adresses;
  }

  public void setAdresses(Set<Adress> adresses) {
    this.adresses = adresses;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  public Set<Order> getOrders() {
    return this.orders;
  }

  public void setOrders(Set<Order> orders) {
    this.orders = orders;
  }



}



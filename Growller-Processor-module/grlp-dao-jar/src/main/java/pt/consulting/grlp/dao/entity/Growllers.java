package pt.consulting.grlp.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity(name = "growllers")
@NamedQueries({
	@NamedQuery(name = "Growllers.getAllGrowllers", query = "SELECT g FROM growllers g"),
	@NamedQuery(name = "Growllers.getByToken", query = "SELECT g FROM growllers g WHERE g.token = ?1"), })
public class Growllers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "token")
    private String token;

    @NotNull
    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "version")
    private String version;

    @Column(name = "last_message")
    private String lastMessage;

    @Column(name = "transaction_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "growllers")
    private List<Users> users;

    @OneToMany(mappedBy = "growller", cascade = CascadeType.ALL)
    private List<Sensors> sensors;

    @OneToMany(mappedBy = "growller", cascade = CascadeType.ALL)
    private List<Tasks> tasks;

    public Growllers() {
	long time = System.currentTimeMillis();
	createdAt = new Date(time);
	updatedAt = new Date(time);
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public String getIpAddress() {
	return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
	this.ipAddress = ipAddress;
    }

    public String getVersion() {
	return version;
    }

    public void setVersion(String version) {
	this.version = version;
    }

    public String getLastMessage() {
	return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
	this.lastMessage = lastMessage;
    }

    public Date getTransactionDate() {
	return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
	this.transactionDate = transactionDate;
    }

    public Date getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
	return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
    }

    public List<Users> getUsers() {
	if (null == users) {
	    users = new ArrayList<Users>();
	}
	return users;
    }

    public void setUsers(List<Users> users) {
	this.users = users;
    }

    public List<Sensors> getSensors() {
	if (null == sensors) {
	    sensors = new ArrayList<Sensors>();
	}
	return sensors;
    }

    public void setSensors(List<Sensors> sensors) {
	this.sensors = sensors;
    }

    public List<Tasks> getTasks() {
	if (null == tasks) {
	    tasks = new ArrayList<Tasks>();
	}
	return tasks;
    }

    public void setTasks(List<Tasks> tasks) {
	this.tasks = tasks;
    }

}
package pt.consulting.grlp.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity(name = "sensors")
public class Sensors implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temperature")
    private String temperature;

    @Column(name = "humidity")
    private String humidity;

    @Column(name = "light")
    private String light;

    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // bi-directional many-to-one association to Growller
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "growller_id")
    private Growllers growller;

    /**
     * Default constructor.
     */
    public Sensors() {
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

    public String getTemperature() {
	return temperature;
    }

    public void setTemperature(String temperature) {
	this.temperature = temperature;
    }

    public String getHumidity() {
	return humidity;
    }

    public void setHumidity(String humidity) {
	this.humidity = humidity;
    }

    public String getLight() {
	return light;
    }

    public void setLight(String light) {
	this.light = light;
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

    public Growllers getGrowller() {
	return growller;
    }

    public void setGrowller(Growllers growller) {
	this.growller = growller;
    }

}

package pt.consulting.grlp.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity(name = "tasks")
@NamedQueries({ @NamedQuery(name = "Tasks.allWaitingByToken", query = "SELECT t FROM tasks t WHERE t.status = 'waiting' and t.growller.token = ?1 ORDER BY t.createdAt") })
public class Tasks implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "command")
    private String command;

    @NotNull
    @Column(name = "schedule")
    @Temporal(TemporalType.TIMESTAMP)
    private Date schedule;

    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // bi-directional many-to-one association to Growller
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "growller_id")
    private Growllers growller;

    public Tasks() {
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

    public String getCommand() {
	return command;
    }

    public void setCommand(String command) {
	this.command = command;
    }

    public Date getSchedule() {
	return schedule;
    }

    public void setSchedule(Date schedule) {
	this.schedule = schedule;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
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

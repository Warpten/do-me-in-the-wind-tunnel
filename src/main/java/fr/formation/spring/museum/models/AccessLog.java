package fr.formation.spring.museum.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "access_logs")
public class AccessLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date date;
	private String requestUri;
	private String remoteAddress;

	@Column(name = "handlingTime")
	private double handlingTime;
	@Column(name = "renderingTime")
	private double renderingTime;
	
	public int getId() { return this.id; }
	public String getRequestURI() { return this.requestUri; }
	public String getRemoteAddress() { return this.remoteAddress; }
	public double getHandlingTime() { return this.handlingTime; }
	public double getRenderingTime() { return this.renderingTime; }
	public Date getDate() { return this.date; }

	public void setId(int id) { this.id = id; }
	public void setRequestURI(String requestUri) { this.requestUri = requestUri; }
	public void setRemoteAddress(String remoteAddress) { this.remoteAddress = remoteAddress; }
	public void setHandlingTime(double handlingTime) { this.handlingTime = handlingTime; }
	public void setRenderingTime(double renderingTime) { this.renderingTime = renderingTime; }
	public void setDate(Date date) { this.date = date; }
}

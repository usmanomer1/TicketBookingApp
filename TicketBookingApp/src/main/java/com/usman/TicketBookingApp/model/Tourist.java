package com.usman.TicketBookingApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tourist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tid; // Booking ID

	private String customerName; // Customer's Name
	private String destination; // Destination (City or Place)
	private String packageType; // Package Type (e.g., Economy, Premium)
	private Double price; // Booking price
	private String status; // Booking status (e.g., "Confirmed", "Pending", "Canceled")

	public Tourist() {
		super();
	}

	// Getters and Setters
	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

package com.qa.ims.persistence.domain;

public class Item {
	private Long id;
	private String name;
	private double value;

	// ==============================
	// Constructors
	// ==============================
	
	public Item() {
		this.id = 0L;
		this.name = "???";
		this.value = 0.00;
	}
	
	public Item(Long id) {
		this.id = id;
		this.name = "???";
		this.value = 0.00;
	}
	
	public Item(String name, double value) {
		this.name = name;
		this.value = value;
	}

	public Item(Long id, String name, double value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	// ==============================
	// Methods
	// ==============================
	
	// getters & setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name; 
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	// Override methods
	
	@Override
	public String toString() {
		return "id:" + id + " name:" + name + " value:" + value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		if (value == 0) { 
			if (other.value != 0)
				return false;
		} else if (value != other.value)
			return false;
		return true;
	}
}

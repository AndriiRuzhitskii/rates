package com.example.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;

//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Currency {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	private short r030;
	private String txt;
	private String cc;
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="currency")
	@JsonIgnore
	private List<Rate> rates;

	public Currency() {
	}

	public Currency(short r030, String txt, String cc, List<Rate> rates) {
		this.r030 = r030;
		this.txt = txt;
		this.cc = cc;
		this.rates = rates;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public short getR030() {
		return r030;
	}

	public void setR030(short r030) {
		this.r030 = r030;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cc == null) ? 0 : cc.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + r030;
		result = prime * result + ((rates == null) ? 0 : rates.hashCode());
		result = prime * result + ((txt == null) ? 0 : txt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Currency other = (Currency) obj;
		if (cc == null) {
			if (other.cc != null)
				return false;
		} else if (!cc.equals(other.cc))
			return false;
		if (id != other.id)
			return false;
		if (r030 != other.r030)
			return false;
		if (rates == null) {
			if (other.rates != null)
				return false;
		} else if (!rates.equals(other.rates))
			return false;
		if (txt == null) {
			if (other.txt != null)
				return false;
		} else if (!txt.equals(other.txt))
			return false;
		return true;
	} 
	
}

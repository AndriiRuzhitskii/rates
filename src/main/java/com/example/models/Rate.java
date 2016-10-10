package com.example.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Rate {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
//	private short r030;
//	private String txt;
	private float rate;
//	private String cc;
	private String exchangedate;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="currency_id")
//    @JsonIgnore
	private Currency currency;

	public Rate() {
	}

	public Rate(float rate, String exchangedate, Currency currency) {
		this.rate = rate;
		this.exchangedate = exchangedate;
		this.currency = currency;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getExchangedate() {
		return exchangedate;
	}

	public void setExchangedate(String exchangedate) {
		this.exchangedate = exchangedate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((currency == null) ? 0 : currency.hashCode());
		result = prime * result
				+ ((exchangedate == null) ? 0 : exchangedate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + Float.floatToIntBits(rate);
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
		Rate other = (Rate) obj;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (exchangedate == null) {
			if (other.exchangedate != null)
				return false;
		} else if (!exchangedate.equals(other.exchangedate))
			return false;
		if (id != other.id)
			return false;
		if (Float.floatToIntBits(rate) != Float.floatToIntBits(other.rate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rate [id=" + id + ", rate=" + rate + ", exchangedate="
				+ exchangedate + ", currency=" + currency + "]";
	} 

}

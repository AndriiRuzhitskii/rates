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
public class RateNbu {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	private short r030;
	private String txt;
	private float rate;
	private String cc;
	private String exchangedate;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="currency_id")
//    @JsonIgnore
	private Currency currency;

	public RateNbu() {
	} 
	
	public RateNbu(short r030, String txt, float rate, String cc,
			String exchangedate) {
		super();
		this.r030 = r030;
		this.txt = txt;
		this.rate = rate;
		this.cc = cc;
		this.exchangedate = exchangedate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public RateNbu(String cc) {
		this.setCc(cc);
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
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getExchangedate() {
		return exchangedate;
	}
	public void setExchangedate(String exchangedate) {
		this.exchangedate = exchangedate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cc == null) ? 0 : cc.hashCode());
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
		RateNbu other = (RateNbu) obj;
		if (cc == null) {
			if (other.cc != null)
				return false;
		} else if (!cc.equals(other.cc))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Rate [id=" + id + ", r030=" + r030 + ", txt=" + txt + ", rate=" + rate
				+ ", cc=" + cc + ", exchangedate=" + exchangedate + "]";
	}
}

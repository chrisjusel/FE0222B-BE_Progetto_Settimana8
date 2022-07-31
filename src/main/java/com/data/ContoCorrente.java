/*
 * Relazione uno a molti tra Conto Corrente e Movimento
 */
package com.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ContoCorrente {
	private String dataCreazione;
	private String iban;
	private double saldo;
	private String intestatario;
	private List<Movimento> movimenti = new ArrayList<Movimento>();

	/**
	 * Quando viene creato un nuovo Conto la sua data di creazione viene automaticamente 
	 * istanziata con la data e ora attuale
	 * @param tipo
	 * @param importo
	 */
	public void aggiungiMovimento(String tipo, double importo) {
		movimenti.add(new Movimento(new Date().toLocaleString(), tipo, importo, iban));
	}

	public String getDataCreazione() {
		return dataCreazione;
	}

	public String getIban() {
		return iban;
	}

	public double getSaldo() {
		return saldo;
	}

	public String getIntestatario() {
		return intestatario;
	}

	public List<Movimento> getMovimenti() {
		return movimenti;
	}

	public void setMovimenti(List<Movimento> movimenti) {
		this.movimenti = movimenti;
	}

	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(iban);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContoCorrente other = (ContoCorrente) obj;
		return Objects.equals(iban, other.iban);
	}

}

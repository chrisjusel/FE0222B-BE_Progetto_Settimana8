/**
 * Relazione molti a uno tra Movimento e Conto Corrente
 * l'iban potrebbe essere inteso come chiave esterna di Conto Corrente in Movimento
 */
package com.data;

import java.util.Objects;

public class Movimento {
	private String data;
	private String tipo;
	private double importo;
	private String iban;

	public Movimento() {
	}

	public Movimento(String data, String tipo, double importo, String iban) {
		this.data = data;
		this.tipo = tipo;
		this.importo = importo;
		this.iban = iban;
	}

	public String getData() {
		return data;
	}

	public String getTipo() {
		return tipo;
	}

	public double getImporto() {
		return importo;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	private void setData(String data) {
		this.data = data;
	}

	private void setTipo(String tipo) {
		this.tipo = tipo;
	}

	private void setImporto(double importo) {
		this.importo = importo;
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
		Movimento other = (Movimento) obj;
		return Objects.equals(iban, other.iban);
	}

}

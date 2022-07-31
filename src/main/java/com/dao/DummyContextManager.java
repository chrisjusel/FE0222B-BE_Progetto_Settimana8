/**
 * Questa classe ha il compito di simulare il livello di persistenza sul database
 * ed i relativi metodi CRUD
 */
package com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.data.ContoCorrente;
import com.data.Movimento;

public class DummyContextManager {
	private List<ContoCorrente> contiCorrente;

	public DummyContextManager() {
		contiCorrente = new ArrayList<ContoCorrente>();
	}

	/**
	 * Attraverso la logica descritta in questo metodo, l'iban è reso "chiave primaria".
	 * Se esiste già un conto corrente con l'iban che si sta cercando di aggiungere
	 * il metodo ritorna false e non verrà aggiunto alcun conto.
	 * @param cc
	 * @return
	 */
	public boolean persist(ContoCorrente cc) {
		if (getContoByIban(cc.getIban()) == null) {
			cc.setDataCreazione(new Date().toLocaleString());
			contiCorrente.add(cc);
			return true;
		}
		return false;
	}

	/**
	 * Se è presente il conto corrente che si sta cercando di rimuovere esso verrà rimosso
	 * @param cc
	 * @return
	 */
	public boolean remove(ContoCorrente cc) {
		if (cc != null && getContoByIban(cc.getIban()) != null) {
			contiCorrente.remove(cc);
			return true;
		}
		return false;
	}

	/**
	 * Date le proprietà di un conto corrente, questo metodo permette di modificare
	 * solamente l'intestatario di un conto corrente in quanto
	 * 	- il saldo è modificabile solamente attraverso un versamento o un prelievo
	 * 	- l'iban non credo possa essere modificato
	 *  - la data di creazione di un conto rimane sempre la stessa di quando si è creato il conto
	 *  - i movimenti seguono la stessa logica del saldo, possono essere aggiunti solo attraverso movimenti
	 * @param cc
	 * @return
	 */
	public ContoCorrente merge(ContoCorrente cc) {
		ContoCorrente ccToModify = null;
		int index = -1;
		if (cc != null && getContoByIban(cc.getIban()) != null) {
			ccToModify = getContoByIban(cc.getIban());
			index = contiCorrente.indexOf(ccToModify);
			cc.setSaldo(ccToModify.getSaldo());
			cc.setDataCreazione(ccToModify.getDataCreazione());
			cc.setMovimenti(ccToModify.getMovimenti());
			contiCorrente.set(index, cc);
		}
		if (index >= 0)
			return contiCorrente.get(index);
		return null;
	}

	public List<ContoCorrente> getAllConti() {
		return contiCorrente;
	}

	/**
	 * Se il conto corrente con l'iban richiesto è presente viene restituito altrimenti
	 * restituisce null
	 * @param iban
	 * @return
	 */
	public ContoCorrente getContoByIban(String iban) {
		ContoCorrente cc = null;
		for (ContoCorrente conto : contiCorrente) {
			if (conto.getIban() != null && conto.getIban().equals(iban)) {
				cc = conto;
			}
		}
		return cc;
	}

	/**
	 * Se esiste il conto corrente richiesto nel movimento ed ha abbastanza soldi disponibili
	 * viene effettuato il prelievo e restituito il conto altrimenti restituisce null
	 * @param movimento
	 * @return
	 */
	public ContoCorrente preleva(Movimento movimento) {
		String iban = movimento.getIban();
		double importo = movimento.getImporto();
		ContoCorrente cc = getContoByIban(iban);
		int indexCC = -1;
		if (cc != null && cc.getSaldo() >= importo) {
			cc.setSaldo(cc.getSaldo() - importo);
			cc.aggiungiMovimento("Prelievo", importo);
			indexCC = contiCorrente.indexOf(cc);
			contiCorrente.set(indexCC, cc);
		}
		if (indexCC >= 0)
			return contiCorrente.get(indexCC);
		return null;
	}

	/**
	 * Se esiste il conto corrente richiesto esiste viene versato l'importo richiesto e 
	 * restituito il conto corrente altrimenti restituisce null
	 * @param movimento
	 * @return
	 */
	public ContoCorrente versa(Movimento movimento) {
		String iban = movimento.getIban();
		double importo = movimento.getImporto();
		ContoCorrente cc = getContoByIban(iban);
		int indexCC = -1;
		if (cc != null) {
			cc.setSaldo(cc.getSaldo() + importo);
			cc.aggiungiMovimento("Versamento", importo);
			indexCC = contiCorrente.indexOf(cc);
			contiCorrente.set(indexCC, cc);
		}
		if (indexCC >= 0)
			return contiCorrente.get(indexCC);
		return null;
	}

	/**
	 * Se esiste il conto corrente richiesto viene restituita la sua lista movimenti altrimenti
	 * restituisce null
	 * @param iban
	 * @return
	 */
	public List<Movimento> listaMovimenti(String iban) {
		ContoCorrente cc = getContoByIban(iban);
		if (cc != null) {
			return cc.getMovimenti();
		}
		return null;
	}
}

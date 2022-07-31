/**
 * Pattern DAO, utilizzato per separare la logica di persistenza (che in questo caso è affidata
 * ad una classe che simula il comportamento dell'entity manager delle JPA) dai metodi che potranno 
 * essere usati a prescindere da come si decide di salvare i dati;
 */
package com.dao;

import java.util.List;

import com.data.ContoCorrente;
import com.data.Movimento;

public class ContoCorrenteDAOImpl implements ContoCorrenteDAO {
	private DummyContextManager dummyContextManager;

	public ContoCorrenteDAOImpl() {
		dummyContextManager = new DummyContextManager();
	}

	@Override
	public boolean inserisciConto(ContoCorrente cc) {
		return dummyContextManager.persist(cc);
	}

	@Override
	public boolean rimuoviConto(String iban) {
		return dummyContextManager.remove(dummyContextManager.getContoByIban(iban));
	}

	@Override
	public ContoCorrente modificaConto(ContoCorrente cc) {
		return dummyContextManager.merge(cc);
	}

	@Override
	public List<ContoCorrente> getAllConti() {
		return dummyContextManager.getAllConti();
	}

	@Override
	public ContoCorrente getContoByIban(String iban) {
		return dummyContextManager.getContoByIban(iban);
	}

	@Override
	public ContoCorrente preleva(Movimento movimento) {
		return dummyContextManager.preleva(movimento);
	}

	@Override
	public ContoCorrente versa(Movimento movimento) {
		return dummyContextManager.versa(movimento);
	}

	@Override
	public List<Movimento> listaMovimenti(String iban) {
		return dummyContextManager.listaMovimenti(iban);
	}

}

package com.dao;

import java.util.List;

import com.data.ContoCorrente;
import com.data.Movimento;

public interface ContoCorrenteDAO {
	public boolean inserisciConto(ContoCorrente cc);

	public boolean rimuoviConto(String iban);

	public ContoCorrente modificaConto(ContoCorrente cc);

	public List<ContoCorrente> getAllConti();

	public ContoCorrente getContoByIban(String iban);

	public ContoCorrente preleva(Movimento movimento);

	public ContoCorrente versa(Movimento movimento);

	public List<Movimento> listaMovimenti(String iban);
}

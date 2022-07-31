package tests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dao.DummyContextManager;
import com.data.ContoCorrente;
import com.data.Movimento;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DummyContextManagerTest {

	private static ContoCorrente conto = new ContoCorrente();
	private static DummyContextManager context = new DummyContextManager();

	/**
	 * Prima di tutto setto gli attributi del conto che sono andato a creare con
	 * alcuni dati
	 */
	@BeforeAll
	public static void beforeAll() {
		conto.setDataCreazione(new Date().toLocaleString());
		conto.setIban("IT000111");
		conto.setIntestatario("Christian Vitale");
		conto.setSaldo(500);
	}

	/**
	 * Questo metodo testa se l'inserimento di un nuovo conto è andato a buon fine
	 */
	@Test
	public void testPersist() {
		Assertions.assertTrue(context.persist(conto));
	}

	/**
	 * Questo metodo testa se il conto restituito dalla ricerca per iban è proprio
	 * il conto che volevo ricercare
	 */
	@Test
	public void testGetContoByIban() {
		Assertions.assertEquals(conto, context.getContoByIban("IT000111"));
	}

	/**
	 * Questo metodo testa se la modifica del conto è andata a buon fine
	 */
	@Test
	public void testModificaConto() {
		ContoCorrente cc = conto;
		cc.setIntestatario("Mario Rossi");
		Assertions.assertNotNull(context.merge(cc));
	}

	/**
	 * Questo metodo testa se la getAllConti restituisce l'istanza di una lista
	 */
	@Test
	public void testListaMovimenti() {
		List<ContoCorrente> array = new ArrayList<ContoCorrente>();
		Assertions.assertInstanceOf(array.getClass(), context.getAllConti());
	}

	/**
	 * Questo metodo testa se, dato un movimento in input al metodo preleva, il prelievo è andato a buon
	 * fine
	 */
	@Test
	public void testPreleva() {
		Movimento movimento = new Movimento(new Date().toLocaleString(), "Prelievo", 10, "IT000111");
		Assertions.assertEquals(conto, context.preleva(movimento));
	}

}

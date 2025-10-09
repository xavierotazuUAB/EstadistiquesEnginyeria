import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MockDB extends DB
{
	public boolean connect()
	{
		return true;
	}
	
	public String [][] query(String q)
	{
		String[][] sResultatQuery;
		return sResultatQuery;		
	}
	
	public boolean close()
	{
		return true;
	}
	
}

class EstadistiquesEnginyeriaTest
{

	@BeforeEach
	void setUp() throws Exception
	{
	}

	@Test
	void testPerCentAprovats()
	{
		EstadistiquesEnginyeria estad = new EstadistiquesEnginyeria();

		// Cas simple, amb un 50% d'aprovats.
		
		// Decidim que per aquest cas de prova la DB haurà de tornar una taula amb les notes dels diferents estudiants,
		// on la meitat seran aprovats i l'altra meitat seran suspesos.
		
		assertEquals(estad.PerCentAprovats("TQS","NFinal"),0.5);
	}

		// Test Constructor
	@Test
	void testEstadistiquesEnginyeria()
	{
			// Constructor per defecte
		EstadistiquesEnginyeria estad = new EstadistiquesEnginyeria();

		assertEquals(estad.getMyDB(),null);

		// Constructor amb parametre DB
		DB myDB = new mockDB();	// Hem de posar mockDB doncs no podem instanciar un objecte de tipus DB ja que és una classe abstracta (un interface) 

		EstadistiquesEnginyeria estad = new EstadistiquesEnginyeria(myDB);

		assertEquals(estad.getMyDB(), myDB);
	}
}

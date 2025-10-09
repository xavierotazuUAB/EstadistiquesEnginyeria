import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MockDB implements DB
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

class MockCreadorQueriesSQL extends CreadorQuerySQL
{
	public String CrearQuery(String Assignatura, String Nota)
	{
		String sQuery = "";
		
		return sQuery;
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
		DB myDB = new MockDB();	// Hem de posar mockDB doncs no podem instanciar un objecte de tipus DB ja que és una classe abstracta (un interface) 

		EstadistiquesEnginyeria estad2 = new EstadistiquesEnginyeria(myDB);

		assertEquals(estad2.getMyDB(), myDB);
	}


		// Test setDB
	@Test
	void testsetDB()
	{
		EstadistiquesEnginyeria estad = new EstadistiquesEnginyeria();
	
		DB myDB = new MockDB();	// Hem de posar mockDB doncs no podem instanciar un objecte de tipus DB ja que és una classe abstracta (un interface) 
	
		estad.setDB(myDB);
	
		assertEquals(estad.getMyDB(), myDB);
	}

}

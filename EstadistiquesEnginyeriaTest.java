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
		String[][] sResultatQuery =	{{""}};
		
		if(q.equals("TQSNFinal"))
		{
			String[][] sTmp = {{"6.0"},{"7.0"},{"9.0"},{"10.0"},{"5.5"},{"3.5"},{"0.0"},{"1.0"},{"1.0"},{"4.5"}};
			sResultatQuery = sTmp;			
		}
		if(q.equals("LPNPract"))
		{
			String[][] sTmp = {{"4.0"},{"7.0"},{"3.0"},{"10.0"},{"6.5"},{"3.5"},{"0.0"},{"9.0"},{"1.0"},{"4.5"}};
			sResultatQuery = sTmp;			
		}
		if(q.equals("LPNTeo"))
		{
			String[][] sTmp = {{"4.0"},{"2.0"},{"3.0"},{"1.0"},{"2.5"},{"3.5"},{"0.0"},{"1.0"},{"1.0"},{"4.5"}};
			sResultatQuery = sTmp;			
		}
		if(q.equals("LPNFinal"))
		{
			String[][] sTmp = {{"9.0"},{"6.0"},{"5.0"},{"6.0"},{"8.5"},{"7.5"},{"10.0"},{"9.0"},{"7.0"},{"5.5"}};
			sResultatQuery = sTmp;			
		}
		
		return sResultatQuery;		
	}
	
	public boolean close()
	{
		return true;
	}
	
}

class MockCreadorQuerySQL extends CreadorQuerySQL
{
	public String CrearQuery(String Assignatura, String Nota)
	{
		String sQuery = Assignatura + Nota;
		
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
		
		// Mock de CreadorQuerySQL
		CreadorQuerySQL creadorQerySQL = new MockCreadorQuerySQL();
		// Mock de DB
		DB myDB = new MockDB();
		
		estad.setCreadorQuerySQL(creadorQerySQL); // Li passem a estad el creador de queries SQL
		estad.setDB(myDB);

		
		// Cas simple, amb un 50% d'aprovats.
		
		// Decidim que per aquest cas de prova la DB haurà de tornar una taula amb les notes dels diferents estudiants,
		// on la meitat seran aprovats i l'altra meitat seran suspesos.
		
		assertEquals(estad.PerCentAprovats("TQS","NFinal"),0.5);
		assertEquals(estad.PerCentAprovats("LP","NPract"),0.4);
		assertEquals(estad.PerCentAprovats("LP","NTeo"),0.0);
		assertEquals(estad.PerCentAprovats("LP","NFinal"),1.0);
}

		// Test Constructor
	@Test
	void testEstadistiquesEnginyeria()
	{
			// Constructor per defecte
		EstadistiquesEnginyeria estad = new EstadistiquesEnginyeria();

		assertEquals(estad.getMyDB(),null);
		assertEquals(estad.getCreadorQuerySQL(),null);

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

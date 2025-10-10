import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MockDB implements DB
{
	public boolean connect()
	{
		// Com que realment no hem de fer cap connexio podem retornar el valor que vulguem
		return true;
	}
	
	public String [][] query(String q)
	{
		String[][] sResultatQuery =	{};
		
		// Cadascuna de les queries que ens arriben a traves del parametre 'q' ens determina quins valors hem de tornar

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
		if(q.equals("RSNFinal"))
		{
			String[][] sTmp = {{"9.0"},{"NP"},{"5.0"},{"6.0"},{"8.5"},{"3.5"},{"10.0"},{"1.0"},{"7.0"},{"NP"}};
			sResultatQuery = sTmp;
		}

		// Taula buida
		if(q.equals("TQSNFinal"))
		{
			// El seguent codi seria equivalent a no fer res, doncs sResultatQuery ja ha estat inicialitzada a sobre amb el mateix valor.
//			String[][] sTmp = {{}};
//			sResultatQuery = sTmp;			
		}

		return sResultatQuery;		
	}
	
	public boolean close()
	{
		// Com que realment no hem de tancar cap connexio podem retornar el valor que vulguem
		return true;
	}
	
}

class MockCreadorQuerySQL extends CreadorQuerySQL
{
	public String CrearQuery(String Assignatura, String Nota)
	{
		// Com que no és necessari crear una query SQL correcta, decidim crear-la simplement amb la combinacio dels dos parametres.
		// Aquesta query és la que haura d'utilitzar el mockDB per saber quins valors ha de tornar
		
		String sQuery = Assignatura + Nota;
		
		return sQuery;
	}
}


class EstadistiquesEnginyeriaTest
{
	EstadistiquesEnginyeria estad;
	CreadorQuerySQL creadorQuerySQL;
	DB myDB;

	@BeforeEach
	void setUp() throws Exception
	{
		estad = new EstadistiquesEnginyeria();
		
		// Mock de CreadorQuerySQL
		creadorQuerySQL = new MockCreadorQuerySQL();
		// Mock de DB
		myDB = new MockDB();
		
		estad.setCreadorQuerySQL(creadorQuerySQL); // Li passem a estad el creador de queries SQL
		estad.setDB(myDB);
	}

	@Test
	void testPerCentAprovats()
	{
		
		// Cas simple, amb un 50% d'aprovats.
		
		// Decidim que per aquest cas de prova la DB haurà de tornar una taula amb les notes dels diferents estudiants,
		// on la meitat seran aprovats i l'altra meitat seran suspesos.
		
		assertEquals(estad.PerCentAprovats("TQS","NFinal"),0.5);
		assertEquals(estad.PerCentAprovats("LP","NPract"),0.4);
		assertEquals(estad.PerCentAprovats("LP","NTeo"),0.0);
		assertEquals(estad.PerCentAprovats("LP","NFinal"),1.0);
		assertEquals(estad.PerCentAprovats("RS","NFinal"),0.6);
		
		// Taula de resultats buida.
		assertEquals(estad.PerCentAprovats("TQS","NPract"),0.0);
	}

	@Test
	void testPerCentSuspesos()
	{

			// Aprofitem les mateixes matrius i calculem el percentatge de suspesos (1-aprovats)
		assertEquals(estad.PerCentSuspesos("TQS","NFinal"),0.5);
		assertEquals(estad.PerCentSuspesos("LP","NPract"),0.6);
		assertEquals(estad.PerCentSuspesos("LP","NTeo"),1.0);
		assertEquals(estad.PerCentSuspesos("LP","NFinal"),0.0);
		
		assertEquals(estad.PerCentSuspesos("RS","NFinal"),0.2); // No es 0.4 (1-0.6) perque en la taula de resultats hi ha dos "NP"

		// Taula de resultats buida.
		assertEquals(estad.PerCentSuspesos("TQS","NPract"),0.0);

	}

	@Test
	void testPerCentNoPresentats()
	{
		assertEquals(estad.PerCentNoPresentats("RS","NFinal"),0.2);

		// Taula de resultats buida.
		assertEquals(estad.PerCentNoPresentats("TQS","NPract"),0.0);
	}

		// Test Constructor
	@Test
	void testEstadistiquesEnginyeria()
	{
			// Constructor per defecte
		EstadistiquesEnginyeria estadbuit = new EstadistiquesEnginyeria();

		assertEquals(estadbuit.getMyDB(),null);
		assertEquals(estadbuit.getCreadorQuerySQL(),null);

			// Constructor amb parametre DB
		DB myDB = new MockDB();	// Hem de posar mockDB doncs no podem instanciar un objecte de tipus DB ja que és una classe abstracta (un interface) 

		EstadistiquesEnginyeria estad2 = new EstadistiquesEnginyeria(myDB);

		assertEquals(estad2.getMyDB(), myDB);
	}


		// Test setDB
	@Test
	void testsetDB()
	{
		EstadistiquesEnginyeria estad2 = new EstadistiquesEnginyeria();
	
		DB myDB = new MockDB();	// Hem de posar mockDB doncs no podem instanciar un objecte de tipus DB ja que és una classe abstracta (un interface) 
	
		estad2.setDB(myDB);
	
		assertEquals(estad2.getMyDB(), myDB);
	}

}

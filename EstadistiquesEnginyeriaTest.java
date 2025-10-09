import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}

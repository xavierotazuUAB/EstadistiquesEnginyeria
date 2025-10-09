
public class EstadistiquesEnginyeria
{
	DB myDB;
	
	public EstadistiquesEnginyeria()
	{
		myDB = null;
	}
	
	public EstadistiquesEnginyeria(DB pDB)
	{
		myDB = pDB;
	}
	
	public void setDB(DB pDB)
	{
		myDB = pDB;
	}

	public double PerCentAprovats(String Assignatura, String Nota)
	{
		double dPerCent = 0.0;
		String sQuery ="";
		
		// Creacio una query SQL a partir dels parametres d'entrada
		
		// ...
		
		myDB.connect();		
		myDB.query(sQuery);		
		myDB.close();

		// Calcul del percentatge
		
		return dPerCent;
	};
	
	
	public double PerCentsuspesos(String Assignatura, String Nota);
	
	public double PerCentNoPresentats(String Assignatura, String Nota);
	
	
	// Metodes per fer test
	
	DB getMyDB()
	{
		return myDB;
	}
}

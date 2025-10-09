
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
		return 0.0;
	};
	
	
	public double PerCentsuspesos(String Assignatura, String Nota);
	
	public double PerCentNoPresentats(String Assignatura, String Nota);
	
	
	// Metodes per fer test
	
	DB getMyDB()
	{
		return myDB;
	}
}

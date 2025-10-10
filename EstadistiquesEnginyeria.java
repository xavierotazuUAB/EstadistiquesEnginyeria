
public class EstadistiquesEnginyeria
{
	DB myDB;
	CreadorQuerySQL myCreador;
	
	public EstadistiquesEnginyeria()
	{
		myDB = null;
		myCreador = null;
	}
	
	public EstadistiquesEnginyeria(DB pDB)
	{
		myDB = pDB;
	}
	
	public void setDB(DB pDB)
	{
		myDB = pDB;
	}

	public void setCreadorQuerySQL(CreadorQuerySQL pCreador)
	{
		myCreador = pCreador;
	}

	public double PerCentAprovats(String Assignatura, String Nota)
	{
		double dPerCent = 0.0;
		String sQuery ="";
		
		// Creacio una query SQL a partir dels parametres d'entrada
		
		sQuery = myCreador.CrearQuery(Assignatura, Nota);
		
		myDB.connect();
		String[][] sResultat = myDB.query(sQuery);		
		myDB.close();

		// Calcul del percentatge

		int nFiles = sResultat.length;
		int nAprovats = 0;
		int nAlumnes = 0;
		
		for(int i=0; i<nFiles;++i)
		{
			++nAlumnes;
			if(!sResultat[i][0].equals("NP") && Double.parseDouble(sResultat[i][0])>=5.0)
			{
				++nAprovats;
			}
				
		}
		
		if(nAlumnes>0)
			dPerCent = nAprovats / (double) nAlumnes;
		
		return dPerCent;
	};
	
	public double PerCentSuspesos(String Assignatura, String Nota)
	{
		double dPerCent = 0.0;
		String sQuery ="";
		
		// Creacio una query SQL a partir dels parametres d'entrada
		
		sQuery = myCreador.CrearQuery(Assignatura, Nota);
		
		myDB.connect();
		String[][] sResultat = myDB.query(sQuery);		
		myDB.close();

		// Calcul del percentatge

		int nFiles = sResultat.length;
		int nSuspesos = 0;
		int nAlumnes = 0;
		
		for(int i=0; i<nFiles;++i)
		{
			++nAlumnes;
			if(!sResultat[i][0].equals("NP") && Double.parseDouble(sResultat[i][0])<5.0)
			{
				++nSuspesos;
			}
				
		}
		
		dPerCent = nSuspesos / (double) nAlumnes;
		
		return dPerCent;
	};
	
	public double PerCentNoPresentats(String Assignatura, String Nota)
	{
		double dPerCent = 0.0;
		String sQuery ="";
		
		// Creacio una query SQL a partir dels parametres d'entrada
		
		sQuery = myCreador.CrearQuery(Assignatura, Nota);
		
		myDB.connect();
		String[][] sResultat = myDB.query(sQuery);		
		myDB.close();

		// Calcul del percentatge

		int nFiles = sResultat.length;
		int nNoPresentats = 0;
		int nAlumnes = 0;
		
		for(int i=0; i<nFiles;++i)
		{
			++nAlumnes;
			if(sResultat[i][0].equals("NP"))
			{
				++nNoPresentats;
			}
				
		}
		
		dPerCent = nNoPresentats / (double) nAlumnes;
		
		return dPerCent;
	};

	
	// Metodes per fer test
	
	DB getMyDB()
	{
		return myDB;
	}

	CreadorQuerySQL getCreadorQuerySQL()
	{
		return myCreador;
	}
}

// DRC: Interfaz db
public interface DB
{
	public boolean connect();
	public String [][] query(String q);
	public boolean close();
}

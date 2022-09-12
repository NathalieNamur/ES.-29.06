package it.polito.tdp.itunes.db;

public class TestItunesDAO {

	public static void main(String[] args) {

		ItunesDAO dao = new ItunesDAO();
		
		System.out.println("Numero vertici (18): "+dao.getVertici(18).size());
		
		System.out.println("Numero canzoni album 23: "+dao.getNumCanzoni(23, 18));
		
	}

}

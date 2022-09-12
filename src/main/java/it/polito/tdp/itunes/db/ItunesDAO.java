package it.polito.tdp.itunes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.itunes.model.Album;
import it.polito.tdp.itunes.model.Artist;
import it.polito.tdp.itunes.model.Genre;
import it.polito.tdp.itunes.model.MediaType;
import it.polito.tdp.itunes.model.Playlist;
import it.polito.tdp.itunes.model.Track;

public class ItunesDAO {
	
	//METODO CHE RESTITUISCE LA LISTA DEI VERTICI
	//(ALBUM CHE RISPETTANO IL VINCOLO):
	public List<Album> getVertici(int n){
		
		final String sql = "SELECT DISTINCT a.* "
						 + "FROM track t, album a "
						 + "WHERE t.AlbumId = a.AlbumId "
						 + "GROUP BY t.AlbumId "
						 + "HAVING COUNT(DISTINCT t.TrackId)>?";
		
		List<Album> result = new LinkedList<>();
		
		try {
			
			Connection conn = DBConnect.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, n);

			ResultSet res = st.executeQuery();
			while (res.next()) {
				
				result.add(new Album(res.getInt("AlbumId"), 
									 res.getString("Title"),
									 res.getInt("ArtistId")));
			}
			
			conn.close();
			
			return result;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore nel metodo getVertici().");
			throw new RuntimeException("SQL Error");
		}
		
	}
	
	
	//METODO CHE RESTITUISCE IL NUMERO DI CANZONI DELL'ALBUM DATO:
	public int getNumCanzoni (int a_id, int n) {
		
		final String sql = "SELECT COUNT(DISTINCT t.TrackId) AS num "
						 + "FROM track t, album a "
						 + "WHERE t.AlbumId = a.AlbumId AND a.AlbumId = ? "
						 + "GROUP BY t.AlbumId "
						 + "HAVING COUNT(DISTINCT t.TrackId)>?";
		
		Integer result = null;
		
		try {
			
			Connection conn = DBConnect.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, a_id);
			st.setInt(2, n);

			ResultSet res = st.executeQuery();
			while (res.next()) {
				
				result = res.getInt("num"); 
			
			}
			
			conn.close();
			
			return result;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore nel metodo getNumCanzoni().");
			throw new RuntimeException("SQL Error");
		}
	}
	
	
}

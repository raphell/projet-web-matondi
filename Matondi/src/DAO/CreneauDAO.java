package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CreneauDAO extends DAOContext {
	
	
	public static boolean isCreneauAvailable(int idJour, int hdeb, int mindeb, int hend, int minend) {
		System.out.println("isCreneauAvailable :");
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "SELECT idcreneau FROM creneau WHERE jourcreneau=? AND"
													+ " (((heuredebut<? OR heuredebut=? AND minutedebut<?) AND (heurefin>? OR (heurefin=? AND minutefin>?))) OR "
													+ "  ((heurefin>? OR heurefin=? AND minutefin>?) AND (heuredebut<? OR (heuredebut=? AND minutedebut<?))))";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				stmt.setInt(1, idJour);
				
				stmt.setInt(2, hdeb);
				stmt.setInt(3, hdeb);
				stmt.setInt(4, mindeb);
				stmt.setInt(5, hdeb);
				stmt.setInt(6, hdeb);
				stmt.setInt(7, mindeb);
				
				stmt.setInt(8, hend);
				stmt.setInt(9, hend);
				stmt.setInt(10, minend);
				stmt.setInt(11, hend);
				stmt.setInt(12, hend);
				stmt.setInt(13, minend);
				try(ResultSet resultSet = stmt.executeQuery()){
					if(resultSet.next()) {
						return false ;
					}
					else {
						return true;
					}
				}
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static int addCreneau(int idJour, int hdeb, int mindeb, int hend, int minend, int idband, String style) {
		System.out.println(" add creneau");
		int nbrLine;
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "INSERT INTO creneau (jourcreneau, idgroupecreneau, stylemusiquecreneau, heuredebut , minutedebut, heurefin, minutefin) VALUES (?,?,?,?,?,?,?) ON CONFLICT DO NOTHING;";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				stmt.setInt(1, idJour);
				stmt.setInt(2, idband);
				stmt.setString(3, style);
				stmt.setInt(4, hdeb);
				stmt.setInt(5, mindeb);
				stmt.setInt(6, hend);
				stmt.setInt(7, minend);
				
				nbrLine = stmt.executeUpdate();
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		return nbrLine;
	}
	
	
	
	
	
}

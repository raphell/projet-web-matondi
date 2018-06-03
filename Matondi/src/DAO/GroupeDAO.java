package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import type.Groupe;

public class GroupeDAO extends DAOContext{

	
	public static int addBand(String name, int nbrMenber, String style, String mail, String phone) {
		System.out.println(" add event URL :"+dbURL);
		int nbrLine;
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "INSERT INTO groupe (nomgroupe, nbrmusicien, stylegroupe, mailgroupe, telephonegroupe) VALUES (?,?,?,?,?)ON CONFLICT DO NOTHING;";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				stmt.setString(1, name);
				stmt.setInt(2, nbrMenber);
				stmt.setString(3, style);
				stmt.setString(4, mail);
				stmt.setString(5, phone);
				nbrLine = stmt.executeUpdate();
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		return nbrLine;
	}
	
	public static int modifyBand(String name, int nbrMenber, String style, String mail, String phone, int idBand) {
		System.out.println(" add event URL :"+dbURL);
		int nbrLine;
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "UPDATE groupe SET (nomgroupe, nbrmusicien, stylegroupe, mailgroupe, telephonegroupe) = (?,?,?,?,?) WHERE idgroupe =? ON CONFLICT DO NOTHING;";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				stmt.setString(1, name);
				stmt.setInt(2, nbrMenber);
				stmt.setString(3, style);
				stmt.setString(4, mail);
				stmt.setString(5, phone);
				stmt.setInt(6, idBand);
				nbrLine = stmt.executeUpdate();
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		return nbrLine;
	}
	
	
	public static int addGroupeToDay(int idJour, int idGroup, int cost) {
		System.out.println(" add event URL :"+dbURL);
		int nbrLine;
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "INSERT INTO presence (jourpresence, idgroupepresence, deffraiementpresence) VALUES (?,?,?) ON CONFLICT DO NOTHING;";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				stmt.setInt(1, idJour);
				stmt.setInt(2, idGroup);
				stmt.setInt(3, cost);
				nbrLine = stmt.executeUpdate();
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		return nbrLine;
	}

	
	public static ArrayList<Groupe> getAllBands(){
		ArrayList<Groupe> allBands = new ArrayList<Groupe>();
		System.out.println(" get event URL :"+dbURL);
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "SELECT * FROM groupe;";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				try(ResultSet resultSet = stmt.executeQuery()){
					while(resultSet.next()) {
						int id = resultSet.getInt("idgroupe");
						String nameBand = resultSet.getString("nomgroupe");
						int nbrPeopleBand = resultSet.getInt("nbrmusicien");
						String styleBand = resultSet.getString("stylegroupe");
						String mailBand = resultSet.getString("mailgroupe");
						String phoneBand = resultSet.getString("telephonegroupe");
						Groupe band = new Groupe(id, nameBand, nbrPeopleBand, styleBand, mailBand, phoneBand) ;
						allBands.add(band);
					}
				}
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		return allBands;
	}
	
	
	
	public static Groupe getBandByName(String nameBand) {
		System.out.println(" get event URL :"+dbURL);
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "SELECT * FROM groupe WHERE nomgroupe=?;";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				stmt.setString(1, nameBand);
				try(ResultSet resultSet = stmt.executeQuery()){
					if(resultSet.next()) {
						int id = resultSet.getInt("idgroupe");
						int nbrPeopleBand = resultSet.getInt("nbrmusicien");
						String styleBand = resultSet.getString("stylegroupe");
						String mailBand = resultSet.getString("mailgroupe");
						String phoneBand = resultSet.getString("telephonegroupe");
						return new Groupe(id, nameBand, nbrPeopleBand, styleBand, mailBand, phoneBand) ;
					}
					else {
						return null;
					}
				}
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Groupe getBandById(int idBand) {
		System.out.println(" get event URL :"+dbURL);
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "SELECT * FROM groupe WHERE idgroupe=?;";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				stmt.setInt(1, idBand);
				try(ResultSet resultSet = stmt.executeQuery()){
					if(resultSet.next()) {
						String nameBand = resultSet.getString("nomgroupe");
						int nbrPeopleBand = resultSet.getInt("nbrmusicien");
						String styleBand = resultSet.getString("stylegroupe");
						String mailBand = resultSet.getString("mailgroupe");
						String phoneBand = resultSet.getString("telephonegroupe");
						return new Groupe(idBand, nameBand, nbrPeopleBand, styleBand, mailBand, phoneBand) ;
					}
					else {
						return null;
					}
				}
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}

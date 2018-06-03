package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import type.Evenement;
import type.Jour;

public class EvenementDAO extends DAOContext{

	//return -1 if the organizer doesn't exist, the id if he exists
	public static int addEvenement( String name, String city, String street, int streetNbr, Date dateDebut, int nbrJour) {
		System.out.println(" add event URL :"+dbURL);
		int nbrLine;
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "INSERT INTO Evenement (nomEvent, dateEvent, villeEvent, rueEvent, numRueEvent, nbrDeJourEvent) VALUES (?,?,?,?,?,?)ON CONFLICT DO NOTHING;";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				stmt.setString(1, name);
				stmt.setDate(2, dateDebut);
				stmt.setString(3, city);
				stmt.setString(4, street);
				stmt.setInt(5, streetNbr);
				stmt.setInt(6, nbrJour);
				nbrLine = stmt.executeUpdate();
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		return nbrLine;
	}
	
	
	public static Evenement getEventByName(String nom) {
		System.out.println(" get event by name");
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "SELECT * FROM evenement WHERE nomevent=?;";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				stmt.setString(1, nom);
				try(ResultSet resultSet = stmt.executeQuery()){
					if(resultSet.next()) {
						System.out.println("event recuperé");
						int idEvent = resultSet.getInt("idevent");
						String cityEvent = resultSet.getString("villeevent");
						String streetEvent = resultSet.getString("rueevent");
						int streetNbrEvent = resultSet.getInt("numrueevent");
						int nbrDayEvent = resultSet.getInt("nbrdejourevent");
						Date dateEvent = resultSet.getDate("dateEvent");
						return new Evenement(idEvent, nom, dateEvent, cityEvent, streetEvent, streetNbrEvent, nbrDayEvent) ;
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
	
	public static Evenement getEventById(int i) {
		System.out.println(" get event by id");
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "SELECT * FROM evenement WHERE idevent=?;";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				stmt.setInt(1, i);
				try(ResultSet resultSet = stmt.executeQuery()){
					if(resultSet.next()) {
						System.out.println("FOUND ONE EVENT WITH THIS ID");
						String nameEvent = resultSet.getString("nomevent");
						String cityEvent = resultSet.getString("villeevent");
						String streetEvent = resultSet.getString("rueevent");
						int streetNbrEvent = resultSet.getInt("numrueevent");
						int nbrDayEvent = resultSet.getInt("nbrdejourevent");
						Date dateEvent = resultSet.getDate("dateEvent");
						return new Evenement(i, nameEvent, dateEvent, cityEvent, streetEvent, streetNbrEvent, nbrDayEvent) ;
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
	
	
	
	public static ArrayList<Jour> getEventDays(int idEvent){
		ArrayList<Jour> eventDays = new ArrayList<Jour>();
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "SELECT * FROM jour WHERE ideventjour=?;";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				stmt.setInt(1, idEvent);
				try(ResultSet resultSet = stmt.executeQuery()){
					while(resultSet.next()) {
						int idDay = resultSet.getInt("idjour");
						int idEventDay = resultSet.getInt("ideventjour");
						Date dateDay = resultSet.getDate("datejour");
						Jour day = new Jour(idDay, idEventDay, dateDay);
						eventDays.add(day);
					}
				}
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		return eventDays;
	}
	
	
	public static ArrayList<Evenement> getIncomingEvents() {
		System.out.println(" get incoming events ");
		ArrayList<Evenement> incomingEvents = new ArrayList<Evenement>();
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "SELECT * FROM evenement WHERE dateEvent>=? ORDER BY dateEvent ASC;";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				stmt.setDate(1, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
				try(ResultSet resultSet = stmt.executeQuery()){
					Evenement event;
					while(resultSet.next()) {
						int idEvent = resultSet.getInt("idevent");
						String nameEvent = resultSet.getString("nomevent");
						String cityEvent = resultSet.getString("villeevent");
						String streetEvent = resultSet.getString("rueevent");
						int streetNbrEvent = resultSet.getInt("numrueevent");
						int nbrDayEvent = resultSet.getInt("nbrdejourevent");
						Date dateEvent = resultSet.getDate("dateEvent");
						event = new Evenement(idEvent, nameEvent, dateEvent, cityEvent, streetEvent, streetNbrEvent, nbrDayEvent);
						incomingEvents.add(event);
					}
				}
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		return incomingEvents;
	}
	
	
	public static ArrayList<Evenement> getAllEvents() {
		System.out.println(" get incoming events ");
		ArrayList<Evenement> allEvents = new ArrayList<Evenement>();
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "SELECT * FROM evenement ORDER BY dateEvent DESC;";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				try(ResultSet resultSet = stmt.executeQuery()){
					Evenement event;
					while(resultSet.next()) {
						int idEvent = resultSet.getInt("idevent");
						String nameEvent = resultSet.getString("nomevent");
						String cityEvent = resultSet.getString("villeevent");
						String streetEvent = resultSet.getString("rueevent");
						int streetNbrEvent = resultSet.getInt("numrueevent");
						int nbrDayEvent = resultSet.getInt("nbrdejourevent");
						Date dateEvent = resultSet.getDate("dateEvent");
						event = new Evenement(idEvent, nameEvent, dateEvent, cityEvent, streetEvent, streetNbrEvent, nbrDayEvent);
						allEvents.add(event);
					}
				}
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		return allEvents;
	}


	public static int modifyEvent(String nameEvent, Date dateEvent, String cityEvent, String streetEvent, int streetNbrEvent, int nbrDayEvent, int idEvent) {
		System.out.println(" add event URL :"+dbURL);
		int nbrLine;
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "UPDATE Evenement SET (nomEvent, dateEvent, villeEvent, rueEvent, numRueEvent, nbrDeJourEvent) = (?,?,?,?,?,?) WHERE idevent=? ;";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				stmt.setString(1, nameEvent);
				stmt.setDate(2, dateEvent);
				stmt.setString(3, cityEvent);
				stmt.setString(4, streetEvent);
				stmt.setInt(5, streetNbrEvent);
				stmt.setInt(6, nbrDayEvent);
				stmt.setInt(7, idEvent);
				nbrLine = stmt.executeUpdate();
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		return nbrLine;
	}
}

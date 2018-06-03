package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import type.Creneau;
import type.Groupe;

public class JourDAO extends DAOContext {
	
	public static ArrayList<Groupe> getParticipatingBand(int idJour) {
		System.out.println(" get Participating Band");
		ArrayList<Groupe> participatingGroup = new ArrayList<Groupe>();
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "SELECT * FROM groupe WHERE EXISTS (SELECT * FROM presence WHERE jourpresence=? AND idgroupepresence=groupe.idGroupe) AND NOT EXISTS(SELECT * FROM creneau WHERE jourcreneau=? AND idgroupecreneau=groupe.idGroupe);";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				stmt.setInt(1, idJour);
				stmt.setInt(2, idJour);
				try(ResultSet resultSet = stmt.executeQuery()){
					Groupe group;
					while(resultSet.next()) {
						int idGroupe = resultSet.getInt("idgroupe");
						String nameGroup = resultSet.getString("nomgroupe");
						String styleGroup = resultSet.getString("stylegroupe");
						int nbrMenberGroup = resultSet.getInt("nbrmusicien");
						String mailGroup = resultSet.getString("mailgroupe");
						String phoneGroup = resultSet.getString("telephonegroupe");
						group = new Groupe(idGroupe, nameGroup, nbrMenberGroup, styleGroup, mailGroup, phoneGroup);
						participatingGroup.add(group);
					}
				}
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		return participatingGroup;
	}
	
	public static ArrayList<Groupe> getUnparticipatingBand(int idJour){
		ArrayList<Groupe> unparticipatingBands = new ArrayList<Groupe>();
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "SELECT * FROM groupe c1 WHERE NOT EXISTS (SELECT * FROM presence WHERE jourpresence=? AND idgroupepresence=c1.idGroupe);";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				stmt.setInt(1, idJour);
				try(ResultSet resultSet = stmt.executeQuery()){
					while(resultSet.next()) {
						int id = resultSet.getInt("idgroupe");
						String nameBand = resultSet.getString("nomgroupe");
						int nbrPeopleBand = resultSet.getInt("nbrmusicien");
						String styleBand = resultSet.getString("stylegroupe");
						String mailBand = resultSet.getString("mailgroupe");
						String phoneBand = resultSet.getString("telephonegroupe");
						Groupe band = new Groupe(id, nameBand, nbrPeopleBand, styleBand, mailBand, phoneBand) ;
						unparticipatingBands.add(band);
					}
				}
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		return unparticipatingBands;
	}
	
	
	public static ArrayList<Creneau> getCreneaux(int idJour){
		ArrayList<Creneau> creneaux = new ArrayList<Creneau>();
		System.out.println(" get event URL :"+dbURL);
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "SELECT * FROM creneau WHERE jourcreneau=? ORDER BY heuredebut ASC;";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				stmt.setInt(1, idJour);
				try(ResultSet resultSet = stmt.executeQuery()){
					while(resultSet.next()) {
						int id = resultSet.getInt("idcreneau");
						int idDay = resultSet.getInt("jourcreneau");
						int idBand = resultSet.getInt("idgroupecreneau");
						String style = resultSet.getString("stylemusiquecreneau");
						int hDeb = resultSet.getInt("heuredebut");
						int mDeb = resultSet.getInt("minutedebut");
						int hEnd = resultSet.getInt("heurefin");
						int mEnd = resultSet.getInt("minutefin");
						
						Creneau creneau = new Creneau(id, style, idDay, idBand, hDeb, mDeb, hEnd, mEnd) ;
						creneaux.add(creneau);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return creneaux;
	}
}

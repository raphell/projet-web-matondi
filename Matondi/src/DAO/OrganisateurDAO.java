package DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrganisateurDAO extends DAOContext{

	//return -1 if the organizer doesn't exist, the id if he exists
	public static int isOrgaValid( String login, String password) {
		System.out.println("VALEUR URL :"+dbURL);
		int result;
		try(Connection connexion = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String query = "SELECT * FROM organisateur WHERE loginOrga=? and password=? ;";
			try(PreparedStatement stmt = connexion.prepareStatement(query)){
				String cryptedPassword = OrganisateurDAO.hash(password);
				stmt.setString(1, login);
				stmt.setString(2, cryptedPassword);System.out.println(cryptedPassword);
				try(ResultSet resultSet = stmt.executeQuery()){
					if(resultSet.next()) {
						result = resultSet.getInt("idOrga");
					}
					else {
						result = -1;
					}
				}
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		//System.out.println("VALEUR result :"+result);
		//HttpSession session = request.getSession(true);
		//System.out.println("orgaDAO :"+${identifiant});
		return result;
	}
	
	
	private static String hash(String passwordToHash)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
		return generatedPassword;
    }
	
}

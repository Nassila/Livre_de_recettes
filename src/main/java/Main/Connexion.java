package Main;


import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
	public static Connection connexionBD() {

		//creer une connexion avec la base de données
		
		java.sql.Connection cn = null;
		

		try {

			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BD","root","");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}
}

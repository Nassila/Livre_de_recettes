package environnement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Outils {
	static Statement state = null;
	static ResultSet result = null;
	static PreparedStatement prepare = null;
	static String id = null;

	public static String authetification(String login, String motdepasse) {
		// creer un requ�te SQL
		try {
			// creer la connexion avec la base de donn�es
			state = Connexion.connexionBD().createStatement();
			// requ�te SQL
			String sql = "SELECT `user_name`, `pass_word` FROM `utilisateurs`";
			// executer la requ�te
			result = state.executeQuery(sql);
			// si le bouton "sign in" est press�

			// et si les champs sont vides
			if (login.equals("") || motdepasse.equals("")) {
				// alors afficher un message d'erreur
				return "Veuillez remplir les champs vides";
				// sinon
			} else {
				while (result.next()) {
					// recuperer des donn�es de la base de donn�es
					String loginBD = result.getString("user_name");
					String motdepasseBD = result.getString("pass_word");
					// si la valeur des les champs est egale � la valeur des la base de donn�es
					if (loginBD.equals(login) && motdepasseBD.equals(motdepasse)) {
						// alors la connexion est accept�e et il ouvre la page d'accueil
						return "ok";

					} else {
						return "Erreur dans le login ou mot de passe";
					}
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return "Erreur dans le login ou mot de passe";

	}

	public static void ajoutRecette(String name, String ing, String prepa) {
		try {
			// creer la connexion avec la base de donn�es
			state = Connexion.connexionBD().createStatement();

			// requ�te SQL
			String sql = "INSERT INTO `recettes` (`nom`, `ingredients`, `preparation`) VALUES ('" + name + "', '" + ing
					+ "', '" + prepa + "');";
			// executer la requ�te
			state.executeUpdate(sql);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	public static ResultSet afficherListeRecette() {
		try {

			// requ�te SQL
			String sql = "SELECT * FROM `recettes`;";

			// creer la connexion avec la base de donn�es
			prepare = Connexion.connexionBD().prepareStatement(sql);

			// executer la requ�te
			result = prepare.executeQuery();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return result;
	}

	public static void supprimerRecette(String name) {
		try {
			// creer la connexion avec la base de donn�es
			state = Connexion.connexionBD().createStatement();

			// requ�te SQL
			String sql = "DELETE FROM `recettes` WHERE `nom` = ?;";

			// executer la requ�te
			prepare = Connexion.connexionBD().prepareStatement(sql);
			prepare.setString(1, name);
			prepare.executeUpdate();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	public static void modifierRectte(String nom, String ingr, String prepa) {
		try {

			// requ�te SQL
			String sql = "UPDATE `recettes` SET `nom`= ?,`ingredients`= ?,`preparation`= ? WHERE `id`= ?;";

			prepare = Connexion.connexionBD().prepareStatement(sql);

			prepare.setString(1, nom);
			prepare.setString(2, ingr);
			prepare.setString(3, prepa);
			prepare.setString(4, id);
			// executer la requ�te
			prepare.executeUpdate();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	public static String rechercherRecette(String name) {

		try {

			// requ�te SQL
			String sql = "SELECT `id`, `nom`, `ingredients`, `preparation` FROM `recettes` WHERE `nom` = ? ;";

			// creer la connexion avec la base de donn�es
			prepare = Connexion.connexionBD().prepareStatement(sql);
			prepare.setString(1, name);

			// executer la requ�te
			result = prepare.executeQuery();

			if (result.next()) {

				id = result.getString("id");
				String nom = result.getString("nom");
				String ingredients = result.getString("ingredients");
				String preparation = result.getString("preparation");

				return "ok-/-" + nom + "-/-" + ingredients + "-/-" + preparation;

			} else {
				return "ko-/-Cette recette n'existe pas";

			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return "Cette recette n'existe pas";

	}
}

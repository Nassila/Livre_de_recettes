package environnementTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import environnement.Outils;

public class OutilsTest {

	@Test
	public void testAuthetification() {
		assertEquals("ok", Outils.authetification("user1", "pass1"));
	}

	@Test
	public void testAjoutRecette() {

	}

	@Test
	public void testAfficherListeRecette() {

	}

	@Test
	public void testSupprimerRecette() {
	}

	@Test
	public void testModifierRectte() {

	}

	@Test
	public void testRechercherRecette() {
		String[] reponseTrue = Outils.rechercherRecette("Soupe").split("-/-");
		assertEquals("ok", reponseTrue[0]);

		String[] reponseFalse = Outils.rechercherRecette("Gratin").split("-/-");
		assertEquals("ko", reponseFalse[0]);
	}

}

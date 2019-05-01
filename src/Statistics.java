
/**************************************************************
 * @CLASS_TITLE:	Statistics
 * 
 * @Description: 	Classe tr�s simpliste et non optimis�e.
 * 					sert seulement � afficher le nombre de
 * 					tentatives de chaque technique utilis�e
 * 					pour trouver la combinaison secr�te.
 * 
 * @author:			Alexandre Laroche
 * 
 **************************************************************/
public class Statistics {

	/*****************************
	 * Variables (public)
	 *****************************/
	public int 
	bruteForce = 0,
	optimizedBruteForce = 0,
	optimizedBruteForceIntervals = 0,
	bruteForceReverseEngineering = 0;

	/******************************************************
	 * Constructeur - Afficher Stats
	 * 
	 * @Resumer:	Affiche la performance de chaque technique
	 * 				utilis�e pour trouver la combinaison
	 * 				secr�te. Le nombre de tentatives et la
	 * 				moyenne de tentatives par caract�re �
	 * 				trouver est affich�.
	 * 
	 ******************************************************/
	public void afficherStats(int combinationLength) {

		System.out.println(
				"\n******************************************" +
				"\n*        COMPARAISON PERFORMANCE !       *" +
				"\n*                                        *" +
				"\n*  Voici un aper�u de la performance     *" +
				"\n*  des quatres techniques utilis�es pour *" + 
				"\n*  trouver la combinaison secr�te        *" + 
				"\n*  compos�e de " + combinationLength + " caract�res.		 *" + 
				"\n*                                        *" +
				"\n******************************************");

		System.out.println("\nBrute Force:				" + (bruteForce * 2) + " tentatives --> Moyenne de " + 
				((bruteForce * 2) / combinationLength) + " tentatives par caract�re � trouver.");
		System.out.println("\nOptimized Brute Force:			" + (optimizedBruteForce) + " tentatives --> Moyenne de " + 
				(optimizedBruteForce / combinationLength) + " tentatives par caract�re � trouver.");
		System.out.println("\nOptimized Brute Force Intervals:	" + (optimizedBruteForceIntervals) + " tentatives --> Moyenne de " + 
				(optimizedBruteForceIntervals / combinationLength) + " tentatives par caract�re � trouver.");
		System.out.println("\nBrute Force Reverse Engineering:	" + (bruteForceReverseEngineering) + " tentatives --> Moyenne de " + 
				(bruteForceReverseEngineering/combinationLength) + " tentatives par caract�re � trouver.");
	}
}

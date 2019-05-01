
/**************************************************************
 * @CLASS_TITLE:	Statistics
 * 
 * @Description: 	Classe très simpliste et non optimisée.
 * 					sert seulement à afficher le nombre de
 * 					tentatives de chaque technique utilisée
 * 					pour trouver la combinaison secrète.
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
	 * 				utilisée pour trouver la combinaison
	 * 				secrète. Le nombre de tentatives et la
	 * 				moyenne de tentatives par caractère à
	 * 				trouver est affiché.
	 * 
	 ******************************************************/
	public void afficherStats(int combinationLength) {

		System.out.println(
				"\n******************************************" +
				"\n*        COMPARAISON PERFORMANCE !       *" +
				"\n*                                        *" +
				"\n*  Voici un aperçu de la performance     *" +
				"\n*  des quatres techniques utilisées pour *" + 
				"\n*  trouver la combinaison secrète        *" + 
				"\n*  composée de " + combinationLength + " caractères.		 *" + 
				"\n*                                        *" +
				"\n******************************************");

		System.out.println("\nBrute Force:				" + (bruteForce * 2) + " tentatives --> Moyenne de " + 
				((bruteForce * 2) / combinationLength) + " tentatives par caractère à trouver.");
		System.out.println("\nOptimized Brute Force:			" + (optimizedBruteForce) + " tentatives --> Moyenne de " + 
				(optimizedBruteForce / combinationLength) + " tentatives par caractère à trouver.");
		System.out.println("\nOptimized Brute Force Intervals:	" + (optimizedBruteForceIntervals) + " tentatives --> Moyenne de " + 
				(optimizedBruteForceIntervals / combinationLength) + " tentatives par caractère à trouver.");
		System.out.println("\nBrute Force Reverse Engineering:	" + (bruteForceReverseEngineering) + " tentatives --> Moyenne de " + 
				(bruteForceReverseEngineering/combinationLength) + " tentatives par caractère à trouver.");
	}
}

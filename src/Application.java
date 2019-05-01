/**************************************************************
 * @CLASS_TITLE:	Application
 * 
 * @Description: 	Démarrage de l'application.
 * 					Programme appel la classe Cadenas et distribue 
 * 					ses paramètres dans quatre techniques 
 * 					différente pour trouver la combinaison cachée 
 * 					de la classe cadenas.
 * 
 * 					1- Brute Force
 * 					2- Optimized Brute Force
 * 					3- Optimized Brute Force Intervals
 * 					4- Brute Force Reverse Engineering
 * 
 * @author:			Alexandre Laroche
 * 
 **************************************************************/
public class Application {

	/******************************
	 * Constantes - Message
	 ******************************/
	private static final String
	SEPARATOR = 	"\n******************************************\n",
	MSG_BIENVENU = 	"\n******************************************" +
					"\n*                BIENVENU !              *" +
					"\n*                                        *" +
					"\n*  Voici une application qui utilise 4   *" +
					"\n*  techniques différentes pour trouver   *" + 
					"\n*  une combinaison hexadécimale secrète  *" + 
					"\n*  générée au hasard.                    *" +
					"\n*                                        *" +
					"\n******************************************";

	/******************************************************
	 * Main 
	 * 
	 * @Resumer:	Démarrage de l'application avec un
	 * 				message de bienvenue.
	 * 
	 ******************************************************/
	public static void main(String[] args) { 
		System.out.println(MSG_BIENVENU);
		new Application(); 
	}

	/******************************************************
	 * Constructeur - Application 
	 * 
	 * @Resumer:	Envoie les paramètres Cadenas et Stats 
	 * 				à quatre fonctions pour décoder la 
	 * 				combinaison et évaluer l'efficacité.
	 * 
	 * 				On ferme le cadenas entre chaque fonction 
	 * 				pour permettre aux autres techniques de 
	 * 				trouver la combinaison.
	 * 
	 ******************************************************/
	public Application() {
		
		//Initialisation de la combinaison secrète
		PadLock padlock = new PadLock();
		Statistics stats = new Statistics();	
			
		// 1- Trouve la combinaison en testant toutes les valeurs alphanumériques possibles.
		System.out.println(SEPARATOR + "* 		Brute Force 		 *" + SEPARATOR);
		SearchFunctions.bruteForce(padlock, stats);
		padlock.close();

		// 2- Trouve la combinaison en séparant les lettres des nombres.
		System.out.println(SEPARATOR + "* 	Optimized Brute Force 		 *" + SEPARATOR);
		SearchFunctions.optimizedBruteForce(padlock, stats);
		padlock.close();

		// 3- Trouve la combinaison avec des intervalles pour les lettres ainsi que les nombres.
		System.out.println(SEPARATOR + "*    Optimized Brute Force Intervals     *" + SEPARATOR);
		SearchFunctions.optimizedBruteForceIntervals(padlock, stats);
		padlock.close();

		// 4- Trouve la combinaison en utilisant des opérations arithmétiques.
		System.out.println(SEPARATOR + "*    Brute Force Reverse Engineering     *" + SEPARATOR);
		SearchFunctions.optimizedBruteForceReverseEngineering(padlock, stats);
		padlock.close();

		// Affiche l'efficacité des quatre techniques à décoder la combinaison. 
		stats.afficherStats(padlock.getCombination().length());
	}
}

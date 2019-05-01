/**************************************************************
 * @CLASS_TITLE:	Application
 * 
 * @Description: 	D�marrage de l'application.
 * 					Programme appel la classe Cadenas et distribue 
 * 					ses param�tres dans quatre techniques 
 * 					diff�rente pour trouver la combinaison cach�e 
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
					"\n*  techniques diff�rentes pour trouver   *" + 
					"\n*  une combinaison hexad�cimale secr�te  *" + 
					"\n*  g�n�r�e au hasard.                    *" +
					"\n*                                        *" +
					"\n******************************************";

	/******************************************************
	 * Main 
	 * 
	 * @Resumer:	D�marrage de l'application avec un
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
	 * @Resumer:	Envoie les param�tres Cadenas et Stats 
	 * 				� quatre fonctions pour d�coder la 
	 * 				combinaison et �valuer l'efficacit�.
	 * 
	 * 				On ferme le cadenas entre chaque fonction 
	 * 				pour permettre aux autres techniques de 
	 * 				trouver la combinaison.
	 * 
	 ******************************************************/
	public Application() {
		
		//Initialisation de la combinaison secr�te
		PadLock padlock = new PadLock();
		Statistics stats = new Statistics();	
			
		// 1- Trouve la combinaison en testant toutes les valeurs alphanum�riques possibles.
		System.out.println(SEPARATOR + "* 		Brute Force 		 *" + SEPARATOR);
		SearchFunctions.bruteForce(padlock, stats);
		padlock.close();

		// 2- Trouve la combinaison en s�parant les lettres des nombres.
		System.out.println(SEPARATOR + "* 	Optimized Brute Force 		 *" + SEPARATOR);
		SearchFunctions.optimizedBruteForce(padlock, stats);
		padlock.close();

		// 3- Trouve la combinaison avec des intervalles pour les lettres ainsi que les nombres.
		System.out.println(SEPARATOR + "*    Optimized Brute Force Intervals     *" + SEPARATOR);
		SearchFunctions.optimizedBruteForceIntervals(padlock, stats);
		padlock.close();

		// 4- Trouve la combinaison en utilisant des op�rations arithm�tiques.
		System.out.println(SEPARATOR + "*    Brute Force Reverse Engineering     *" + SEPARATOR);
		SearchFunctions.optimizedBruteForceReverseEngineering(padlock, stats);
		padlock.close();

		// Affiche l'efficacit� des quatre techniques � d�coder la combinaison. 
		stats.afficherStats(padlock.getCombination().length());
	}
}

import java.util.ArrayList;

/**************************************************************
 * @CLASS_TITLE:	Finder Technics
 * 
 * @Description: 	La classe contient 4 techniques pour trouver
 * 					la combinaison cach�e qui a �t� cr�e par 
 * 					la classe Cadenas.
 * 
 * 					Chaque technique renvoie le nombre de tentatives 
 * 					effectu�es pour trouver la combinaison cach�e 
 * 					afin d'�valuer l'efficacit� de la technique.
 * 
 * 					1- Force Brute
 * 					2- Force Brute Opmimis�e
 * 					3- Force Brute Opmimis�e Intervalles
 * 					4- Force Brute Opmimis�e Retro Ingenierie
 * 
 * @author:			Alexandre Laroche
 * 
 **************************************************************/
public class SearchFunctions {

	/*****************************
	 * Constantes
	 *****************************/
	private static final int 
	CHAR_BORNE_SUPERIEUR_ASCII = 90, 	//Lettres majuscules (A-Z).
	CHAR_BORNE_INFERIEUR_ASCII = 65,
	NUM_BORNE_SUPERIEUR_ASCII = 57, 	// Nombres (0 � 9)
	NUM_BORNE_INFERIEUR_ASCII = 48;

	private static final String ATTEMPTS = "Attempts : ";

	/******************************************************
	 * Force Brute
	 * 
	 * @Resumer:	Trouve la combinaison en testant toutes 
	 * 				les valeurs alphanum�riques possibles.
	 * 
	 ******************************************************/
	public static void bruteForce(PadLock padlock, Statistics stats) {
		int actualIndex = 0;
		System.out.print(ATTEMPTS);

		while (!padlock.padlockIsOpen()) {

			char letter = SearchTools.randomCapitalLetter();
			char number = SearchTools.randomNumber();
			System.err.print(letter);
			System.err.print(number);

			// On teste les caract�res alphanum�riques de la classe SearchTools avec � la classe placerCharAlaPosition.
			if (padlock.placeCharAtIndex(letter, actualIndex) == 0 || 
					padlock.placeCharAtIndex(number, actualIndex) == 0) {

				// Incr�mente jusqu'� ce qu'on trouve tous les caract�res de la combinaison
				actualIndex++;
				if(!padlock.padlockIsOpen()) 
					System.out.print(ATTEMPTS);	
			}
			//Permet d'obtenir de nombre de tentatives pour trouver la combinaison cach�e.
			stats.bruteForce++;
		}
	}

	/******************************************************
	 * Force Brute Opmimis�e
	 * 
	 * @Resumer:	Trouve la combinaison en s�parant les 
	 * 				lettres des nombres.
	 * 
	 ******************************************************/
	public static void optimizedBruteForce(PadLock padlock, Statistics stats) {
		int actualIndex = 0;
		System.out.print(ATTEMPTS);

		while (!padlock.padlockIsOpen()) {

			char letter = SearchTools.randomCapitalLetter();
			char number = SearchTools.randomNumber();
			System.err.print(letter);
			System.err.print(number);

			// Si la position �quivaut � 0 ou 1
			if (actualIndex < 2) {

				// On teste les lettres majuscules de la classe SearchTools avec la classe placerCarAlaPosition.
				if (padlock.placeCharAtIndex(letter, actualIndex) == 0) { 
					actualIndex++;

					if(!padlock.padlockIsOpen()) 
						System.out.print(ATTEMPTS);
				}
				stats.optimizedBruteForce++;

			} // Sinon, excluent les positions 0 et 1, on retrouve seulement des nombres.
			else {

				// On teste les nombres de la classe SearchTools avec la classe placerCarAlaPosition.
				if (padlock.placeCharAtIndex(number, actualIndex) == 0) {
					actualIndex++;

					if(!padlock.padlockIsOpen()) 
						System.out.print(ATTEMPTS);
				}
				stats.optimizedBruteForce++;
			}
		}
	}

	/******************************************************
	 * Force Brute Opmimis�e Intervalles
	 * 
	 * @Resumer:	Trouve la combinaison avec des intervalles 
	 * 				pour les lettres ainsi que les nombres.
	 * 
	 ******************************************************/
	public static void optimizedBruteForceIntervals(PadLock padlock, Statistics stats) {
		int actualIndex = 0;
		System.out.print(ATTEMPTS);

		// D�claration et initialisation des variables associ�es � des intervalles choisi de la table ASCII.
		int higherBoundLetter = CHAR_BORNE_SUPERIEUR_ASCII;
		int lowerBoundLetter = CHAR_BORNE_INFERIEUR_ASCII;
		int higherBoundNumber = NUM_BORNE_SUPERIEUR_ASCII;
		int lowerBoundNumber = NUM_BORNE_INFERIEUR_ASCII;

		while (!padlock.padlockIsOpen()) {

			// D�claration et initialisation de la variable qui garde en m�moire le caract�re re�ue de la table ASCII sous l'intervalle impos�.
			char letter = SearchTools.charBetweenTwoCodeAscii(lowerBoundLetter, higherBoundLetter);
			char number = SearchTools.charBetweenTwoCodeAscii(lowerBoundNumber, higherBoundNumber);
			System.err.print(letter);
			System.err.print(number);

			// Pour la position 0 et 1 (lettres seulement).
			if (actualIndex < 2) {

				// D�claration et initialisation de la variable qui garde en m�moire la valeur re�ue de placerCarAlaPosition.
				int valueReceived = padlock.placeCharAtIndex(letter, actualIndex);

				if (valueReceived == 0) {

					actualIndex++;
					if(!padlock.padlockIsOpen()) 
						System.out.print(ATTEMPTS);

					// On appel les variables associ�es aux intervalles choisi de la table ASCII.
					lowerBoundLetter = CHAR_BORNE_INFERIEUR_ASCII;
					higherBoundLetter = CHAR_BORNE_SUPERIEUR_ASCII;

				} else if (valueReceived == 1) {
					lowerBoundLetter = letter;	// Borne inf�rieur prend la valeur du caract�re test� (monte) et r�duit la marge de s�lection.
					stats.optimizedBruteForceIntervals++;

				} else if (valueReceived == -1) {
					higherBoundLetter = letter;	// Borne sup�rieur prend la valeur du caract�re test� (descend) et r�duit la marge de s�lection.
					stats.optimizedBruteForceIntervals++;
				}
				stats.optimizedBruteForceIntervals++;	
			} 
			// Pour les autres valeurs de type num�rique sur une position > 2.
			else {

				// D�claration et initialisation de la variable qui garde en m�moire la valeur re�ue de placerCarAlaPosition.
				int valueReceived = padlock.placeCharAtIndex(number, actualIndex);

				if (valueReceived == 0) {

					actualIndex++;
					if(!padlock.padlockIsOpen()) 
						System.out.print(ATTEMPTS);

					// On appel les variables associ�es aux intervalles choisi de la table ASCII.
					lowerBoundNumber = NUM_BORNE_INFERIEUR_ASCII;
					higherBoundNumber = NUM_BORNE_SUPERIEUR_ASCII;

				} else if (valueReceived == 1) {
					lowerBoundNumber = number;	// Borne inf�rieur prend la valeur du nombre test� (monte) et r�duit la marge de s�lection.
					stats.optimizedBruteForceIntervals++;

				} else if (valueReceived == -1) {
					higherBoundNumber = number;	// Borne sup�rieur prend la valeur nombre test� (descend) et r�duite la marge de s�lection.
					stats.optimizedBruteForceIntervals++;
				}
				stats.optimizedBruteForceIntervals++;
			}
		}
	}

	/******************************************************
	 * Force Brute Opmimis�e Retro Ingenierie
	 * 
	 * @Resumer:	Trouve la combinaison en utilisant des 
	 * 				op�rations arithm�tiques.
	 * 
	 ******************************************************/
	public static void optimizedBruteForceReverseEngineering(PadLock padlock, Statistics stats) {
		int actualIndex = 0;
		ArrayList<Character> numberTab = new ArrayList<>();
		ArrayList<Character> letterTab = new ArrayList<>();
		System.out.print(ATTEMPTS);

		// D�claration et initialisation des variables associ�es aux intervalles choisi de la table ASCII.
		int higherBoundLetter = CHAR_BORNE_SUPERIEUR_ASCII;
		int lowerBoundLetter = CHAR_BORNE_INFERIEUR_ASCII;

		// D�claration et initialisation d'un tableau qui garde en m�moire les valeurs de tabIndices.
		int[] indexTab = padlock.getClueTab();

		while (!padlock.padlockIsOpen()) {

			// Pour la position 0 et 1 (lettres seulement).
			if (actualIndex < 2) {

				// D�claration et initialisation de la variable qui garde en m�moire le caract�re re�ue de la table ASCII sous l'intervalle impos�.
				char letter = SearchTools.charBetweenTwoCodeAscii(lowerBoundLetter, higherBoundLetter);

				//Pour �viter de tester plusieurs fois la m�me lettre qui n'est pas la bonne
				if(!letterTab.contains(letter)) {
					letterTab.add(letter);
					System.err.print(letter);

					// D�claration et initialisation de la variable qui garde en m�moire la valeur re�ue de placerCarAlaPosition.
					int receivedValue = padlock.placeCharAtIndex(letter, actualIndex);

					if (receivedValue == 0) {

						actualIndex++;
						if(!padlock.padlockIsOpen()) 
							System.out.print(ATTEMPTS);

						// On appel les variables associ�es aux intervalles choisi de la table ASCII.
						lowerBoundLetter = CHAR_BORNE_INFERIEUR_ASCII;
						higherBoundLetter = CHAR_BORNE_SUPERIEUR_ASCII;

					} else if (receivedValue == 1) {
						lowerBoundLetter = letter;	// Borne inf�rieur prend la valeur du caract�re test� (monte) et r�duit la marge de s�lection.
						stats.bruteForceReverseEngineering++;

					} else if (receivedValue == -1) {
						higherBoundLetter = letter;	// Borne sup�rieur prend la valeur du caract�re test� (descend) et r�duit la marge de s�lection.
						stats.bruteForceReverseEngineering++;
					}
				}
				letterTab.clear();
			}
			// Pour les autres valeurs de type num�rique sur une position > 2. 
			else { 

				// Pour chaque couple de chiffres dans tabIndices.
				for (int i = 0; i < indexTab.length; i++) {
					for (int j = 0; j < indexTab.length; j++) {

						// Pour chaque op�ration arithm�tique
						for (int k = 0; k < SearchTools.OPERATIONS.length; k++) {							

							// Si SearchTools.OPERATIONS et tabIndices n'�quivaut pas 0 (valeur envoy� par de la classe placerCarAlaPosition).
							if (!(SearchTools.OPERATIONS[k].equals("/") && indexTab[j] == 0)) {

								// D�claration et initialisation de la variable qui garde en m�moire la valeur absolue re�ue.
								// La valeur test�e passe par toutes les op�rations arithm�tiques et tous les nombres un � la fois. (La quantit� de test est similaire � un factoriel)
								char number = SearchTools.firstCharFromInt(Math.abs(
										SearchTools.operationResult(indexTab[i], indexTab[j], SearchTools.OPERATIONS[k])));

								//Pour �viter de tester plusieurs fois le m�me nombre qui n'est pas la bonne
								if(!numberTab.contains(number)) {
									numberTab.add(number);

									System.err.print(number);
									stats.bruteForceReverseEngineering++;

									// Si le r�sultat obtenu de l'op�ration arithm�tique returne 0, on passe � l'autre ligne.
									if (padlock.placeCharAtIndex(number, actualIndex) == 0) { 
										actualIndex++;
										if(!padlock.padlockIsOpen()) 
											System.out.print(ATTEMPTS);
									}
									if (padlock.padlockIsOpen()) 
										return;
								}
							}
						}
					}
				}
				numberTab.clear();
			}
		}
	}
}

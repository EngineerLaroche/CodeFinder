import java.util.ArrayList;

/**************************************************************
 * @CLASS_TITLE:	Finder Technics
 * 
 * @Description: 	La classe contient 4 techniques pour trouver
 * 					la combinaison cachée qui a été crée par 
 * 					la classe Cadenas.
 * 
 * 					Chaque technique renvoie le nombre de tentatives 
 * 					effectuées pour trouver la combinaison cachée 
 * 					afin d'évaluer l'efficacité de la technique.
 * 
 * 					1- Force Brute
 * 					2- Force Brute Opmimisée
 * 					3- Force Brute Opmimisée Intervalles
 * 					4- Force Brute Opmimisée Retro Ingenierie
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
	NUM_BORNE_SUPERIEUR_ASCII = 57, 	// Nombres (0 à 9)
	NUM_BORNE_INFERIEUR_ASCII = 48;

	private static final String ATTEMPTS = "Attempts : ";

	/******************************************************
	 * Force Brute
	 * 
	 * @Resumer:	Trouve la combinaison en testant toutes 
	 * 				les valeurs alphanumériques possibles.
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

			// On teste les caractères alphanumériques de la classe SearchTools avec à la classe placerCharAlaPosition.
			if (padlock.placeCharAtIndex(letter, actualIndex) == 0 || 
					padlock.placeCharAtIndex(number, actualIndex) == 0) {

				// Incrémente jusqu'à ce qu'on trouve tous les caractères de la combinaison
				actualIndex++;
				if(!padlock.padlockIsOpen()) 
					System.out.print(ATTEMPTS);	
			}
			//Permet d'obtenir de nombre de tentatives pour trouver la combinaison cachée.
			stats.bruteForce++;
		}
	}

	/******************************************************
	 * Force Brute Opmimisée
	 * 
	 * @Resumer:	Trouve la combinaison en séparant les 
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

			// Si la position équivaut à 0 ou 1
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
	 * Force Brute Opmimisée Intervalles
	 * 
	 * @Resumer:	Trouve la combinaison avec des intervalles 
	 * 				pour les lettres ainsi que les nombres.
	 * 
	 ******************************************************/
	public static void optimizedBruteForceIntervals(PadLock padlock, Statistics stats) {
		int actualIndex = 0;
		System.out.print(ATTEMPTS);

		// Déclaration et initialisation des variables associées à des intervalles choisi de la table ASCII.
		int higherBoundLetter = CHAR_BORNE_SUPERIEUR_ASCII;
		int lowerBoundLetter = CHAR_BORNE_INFERIEUR_ASCII;
		int higherBoundNumber = NUM_BORNE_SUPERIEUR_ASCII;
		int lowerBoundNumber = NUM_BORNE_INFERIEUR_ASCII;

		while (!padlock.padlockIsOpen()) {

			// Déclaration et initialisation de la variable qui garde en mémoire le caractère reçue de la table ASCII sous l'intervalle imposé.
			char letter = SearchTools.charBetweenTwoCodeAscii(lowerBoundLetter, higherBoundLetter);
			char number = SearchTools.charBetweenTwoCodeAscii(lowerBoundNumber, higherBoundNumber);
			System.err.print(letter);
			System.err.print(number);

			// Pour la position 0 et 1 (lettres seulement).
			if (actualIndex < 2) {

				// Déclaration et initialisation de la variable qui garde en mémoire la valeur reçue de placerCarAlaPosition.
				int valueReceived = padlock.placeCharAtIndex(letter, actualIndex);

				if (valueReceived == 0) {

					actualIndex++;
					if(!padlock.padlockIsOpen()) 
						System.out.print(ATTEMPTS);

					// On appel les variables associées aux intervalles choisi de la table ASCII.
					lowerBoundLetter = CHAR_BORNE_INFERIEUR_ASCII;
					higherBoundLetter = CHAR_BORNE_SUPERIEUR_ASCII;

				} else if (valueReceived == 1) {
					lowerBoundLetter = letter;	// Borne inférieur prend la valeur du caractère testé (monte) et réduit la marge de sélection.
					stats.optimizedBruteForceIntervals++;

				} else if (valueReceived == -1) {
					higherBoundLetter = letter;	// Borne supérieur prend la valeur du caractère testé (descend) et réduit la marge de sélection.
					stats.optimizedBruteForceIntervals++;
				}
				stats.optimizedBruteForceIntervals++;	
			} 
			// Pour les autres valeurs de type numérique sur une position > 2.
			else {

				// Déclaration et initialisation de la variable qui garde en mémoire la valeur reçue de placerCarAlaPosition.
				int valueReceived = padlock.placeCharAtIndex(number, actualIndex);

				if (valueReceived == 0) {

					actualIndex++;
					if(!padlock.padlockIsOpen()) 
						System.out.print(ATTEMPTS);

					// On appel les variables associées aux intervalles choisi de la table ASCII.
					lowerBoundNumber = NUM_BORNE_INFERIEUR_ASCII;
					higherBoundNumber = NUM_BORNE_SUPERIEUR_ASCII;

				} else if (valueReceived == 1) {
					lowerBoundNumber = number;	// Borne inférieur prend la valeur du nombre testé (monte) et réduit la marge de sélection.
					stats.optimizedBruteForceIntervals++;

				} else if (valueReceived == -1) {
					higherBoundNumber = number;	// Borne supérieur prend la valeur nombre testé (descend) et réduite la marge de sélection.
					stats.optimizedBruteForceIntervals++;
				}
				stats.optimizedBruteForceIntervals++;
			}
		}
	}

	/******************************************************
	 * Force Brute Opmimisée Retro Ingenierie
	 * 
	 * @Resumer:	Trouve la combinaison en utilisant des 
	 * 				opérations arithmétiques.
	 * 
	 ******************************************************/
	public static void optimizedBruteForceReverseEngineering(PadLock padlock, Statistics stats) {
		int actualIndex = 0;
		ArrayList<Character> numberTab = new ArrayList<>();
		ArrayList<Character> letterTab = new ArrayList<>();
		System.out.print(ATTEMPTS);

		// Déclaration et initialisation des variables associées aux intervalles choisi de la table ASCII.
		int higherBoundLetter = CHAR_BORNE_SUPERIEUR_ASCII;
		int lowerBoundLetter = CHAR_BORNE_INFERIEUR_ASCII;

		// Déclaration et initialisation d'un tableau qui garde en mémoire les valeurs de tabIndices.
		int[] indexTab = padlock.getClueTab();

		while (!padlock.padlockIsOpen()) {

			// Pour la position 0 et 1 (lettres seulement).
			if (actualIndex < 2) {

				// Déclaration et initialisation de la variable qui garde en mémoire le caractère reçue de la table ASCII sous l'intervalle imposé.
				char letter = SearchTools.charBetweenTwoCodeAscii(lowerBoundLetter, higherBoundLetter);

				//Pour éviter de tester plusieurs fois la même lettre qui n'est pas la bonne
				if(!letterTab.contains(letter)) {
					letterTab.add(letter);
					System.err.print(letter);

					// Déclaration et initialisation de la variable qui garde en mémoire la valeur reçue de placerCarAlaPosition.
					int receivedValue = padlock.placeCharAtIndex(letter, actualIndex);

					if (receivedValue == 0) {

						actualIndex++;
						if(!padlock.padlockIsOpen()) 
							System.out.print(ATTEMPTS);

						// On appel les variables associées aux intervalles choisi de la table ASCII.
						lowerBoundLetter = CHAR_BORNE_INFERIEUR_ASCII;
						higherBoundLetter = CHAR_BORNE_SUPERIEUR_ASCII;

					} else if (receivedValue == 1) {
						lowerBoundLetter = letter;	// Borne inférieur prend la valeur du caractère testé (monte) et réduit la marge de sélection.
						stats.bruteForceReverseEngineering++;

					} else if (receivedValue == -1) {
						higherBoundLetter = letter;	// Borne supérieur prend la valeur du caractère testé (descend) et réduit la marge de sélection.
						stats.bruteForceReverseEngineering++;
					}
				}
				letterTab.clear();
			}
			// Pour les autres valeurs de type numérique sur une position > 2. 
			else { 

				// Pour chaque couple de chiffres dans tabIndices.
				for (int i = 0; i < indexTab.length; i++) {
					for (int j = 0; j < indexTab.length; j++) {

						// Pour chaque opération arithmétique
						for (int k = 0; k < SearchTools.OPERATIONS.length; k++) {							

							// Si SearchTools.OPERATIONS et tabIndices n'équivaut pas 0 (valeur envoyé par de la classe placerCarAlaPosition).
							if (!(SearchTools.OPERATIONS[k].equals("/") && indexTab[j] == 0)) {

								// Déclaration et initialisation de la variable qui garde en mémoire la valeur absolue reçue.
								// La valeur testée passe par toutes les opérations arithmétiques et tous les nombres un à la fois. (La quantité de test est similaire à un factoriel)
								char number = SearchTools.firstCharFromInt(Math.abs(
										SearchTools.operationResult(indexTab[i], indexTab[j], SearchTools.OPERATIONS[k])));

								//Pour éviter de tester plusieurs fois le même nombre qui n'est pas la bonne
								if(!numberTab.contains(number)) {
									numberTab.add(number);

									System.err.print(number);
									stats.bruteForceReverseEngineering++;

									// Si le résultat obtenu de l'opération arithmétique returne 0, on passe à l'autre ligne.
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

import java.util.Random;

/**************************************************************
 * @CLASS_TITLE:	PadLock
 * 
 * @Description: 	Cette classe repr�sente un cadenas virtuel. 
 * 					C'est ici qu'on g�n�re au hasard une 
 * 					combinaison secr�te. Selon l'�tat de la 
 * 					combinaison, on affiche un cadenas ouvert 
 * 					ou ferm� dans la console. 
 * 
 * @author:			Alexandre Laroche
 * 
 **************************************************************/
public class PadLock {

	/*****************************
	 * Constantes
	 *****************************/
	private static final String TOP_PADLOCK_CLOSED = 
					"\r\n\n     _________			"+
					"\r\n    /#########.			"+
					"\r\n   /#/       |#|			"+
					"\r\n   |#|       |#|			"+
					"\r\n   |#|       |#|			"+
					"\r\n /###############.	   		"+
					"\r\n |#             #| 		"+
					"\r\n |#             #| 		";
	
	private static final String TOP_PADLOCK_OPENED = 
					"\r\n\n              _________	"+
					"\r\n             /#########.	"+
					"\r\n            /#/       |#|	"+
					"\r\n            |#|       |#|	"+
					"\r\n            |#|       |#|	"+
					"\r\n /###############.	   		"+
					"\r\n |#             #| 		"+
					"\r\n |#             #| 		";
		
	private static final String BOTTOM_PADLOCK = 
					"\r\n |#             #| 		"+
					"\r\n .###############/ 	\n\n";

	private static final int 
	MAX_COMBINATION_SIZE = 10,			//Grosseur maximal de la combinaison � g�n�rer
	LETTERS_QTY = 2;					// Nombre de lettres majuscules au d�part de la combinaison.

	/*****************************
	 * Variables
	 *****************************/
	private String combination = "";	//La combinaison � g�n�rer
	private int lettersFoundQty = 0;	//retient le nombre de lettre trouv�e.

	/*****************************
	 * Arrays
	 *****************************/
	private int[] clueTab = null; 		// Aide � g�n�rer la combinaison
	private boolean[] charFound = null;	//Sert � retenir les caract�re d�voil�s.
	
	/*****************************
	 * Primitive
	 *****************************/
	private boolean isOpen = false;		//Donne l'�tat du cadenas virtuel
	

	/******************************************************
	 * Constructeur - PadLock
	 * 
	 * @Resumer:	Cr�e un cadenas qui peut �tre ouvert ou non.   
	 * 				Il s'ouvre si tous les caract�res de la 
	 * 				combinaison ont �t� trouv�s.  La v�rification 
	 * 				se fait � l'aide de la fonction bool�enne 
	 * 				placerCarALaPosition.
	 * 
	 * 				L'�tat du cadenas est affich� en mode console 
	 * 				ainsi que la combinaison lorsqu'elle est trouv�e.
	 * 
	 ******************************************************/
	public PadLock() {
		
		generateCombination();
		clueTab = new int[MAX_COMBINATION_SIZE]; 
		charFound = new boolean[combination.length()];
		displayPadlockCombination();
	}

	/******************************************************
	 * Generate Clue Tab
	 * 
	 * @Resumer:	Remplit le tableau d'indices avec des 
	 * 				chiffres al�atoires.
	 * 
	 ******************************************************/
	private void generateClueTab() {
		for (int i = 0; i < clueTab.length; i++) 
			clueTab[i] = new Random().nextInt(SearchTools.MAX_NUMBERS);
	}

	/******************************************************
	 * Generate Clue Tab
	 * 
	 * @Resumer:	Regarde sur un couple lettre/position 
	 * 				est valide pour la combinaison du
	 * 				cadenas.
	 * 
	 * 				STRATEGIE: On r�cup�re le caract�re de 
	 * 				la combinaison pour la position re�ue 
	 * 				puis on le compare avec le caract�re 
	 * 				propos�.
	 *
	 * 				Si les caract�res sont �gaux, on incr�mente 
	 * 				lettersFoundQty.Finalement, si tous les 
	 * 				caract�res ont �t� trouv�s, alors on affiche
	 * 				le cadenas ouvert.
	 * 
	 * @param 		index La position � regarder.
	 * @param 		character La lettre propos�e pour la position.
	 * @return 		-1 Si la lettre propos�e est avant la lettre dans la table ascii.
	 * @return 		1 Si la lettre propos�e est apr�s la lettre dans la table ascii.
	 * @return 		0 Si la lettre propos�e est la bonne lettre.
	 * 
	 ******************************************************/
	public int placeCharAtIndex(char character, int index) {

		char actualChar = combination.charAt(index);
		int searchState = 0;

		if (actualChar == character) {
			System.out.print(character);

			// Retenir que ce caract�re vient d'�tre trouv�.
			charFound[lettersFoundQty] = true;
			lettersFoundQty++;

			// S'ils sont tous d�voil�e, on l'ouvre.
			if (lettersFoundQty == combination.length()) 
				isOpen = true;

			displayPadlock();
		}
		// Retourne l'�tat selon la comparaison entre le caract�re courant et celui re�u.
		else searchState = (actualChar > character) ? 1 : -1;
		return searchState;
	}

	/******************************************************
	 * Generate First Letter
	 * 
	 * @Resumer:	G�n�re les NB_LETTRES majuscules au d�but 
	 * 				de la combinaison.
	 * 
	 ******************************************************/
	private void generateFirtsLetter(){

		// Choisit les deux premi�res lettres au hasard et les concat�ne � la combinaison.
		for (int i = 0; i < LETTERS_QTY; i++) {
			int value = (int) Math.floor(Math.random() * SearchTools.CAPITAL_LETTERS.length());
			combination += SearchTools.CAPITAL_LETTERS.charAt(value);
		}
	}

	/******************************************************
	 * Generate Numbers
	 * 
	 * @Resumer:	G�n�re les chiffres de la combinaison 
	 * 				en se servant d'un tableau d'indices
	 * 				g�n�rer au hasard.
	 * 
	 ******************************************************/
	private void generateNumbers(){

		// G�n�rer le tableau d'indices.
		generateClueTab();

		// Chaque caract�re suivant est le r�sultat de l'op�ration choisit au hasard.
		for (int i = 0; i < clueTab.length; i = i + 2) {

			String operation;

			// �viter la division par 0.
			do{
				int value = (int) Math.floor(Math.random() * SearchTools.OPERATIONS.length);
				operation = SearchTools.OPERATIONS[value];
			}while( clueTab[i + 1] == 0 && operation == "/");
			combination += SearchTools.operationResult(clueTab[i], clueTab[i + 1], operation);
		}
	}

	/******************************************************
	 * Generate Combination
	 * 
	 * @Resumer:	G�n�re une combinaison pour le cadenas
	 * 
	 * 				STRATEGIE: On g�n�re une  combinaison 
	 * 				compos�e de 2 lettres + un nombre. 
	 * 				Le nombre est g�n�r� al�atoirement 
	 * 				en utilisant les op�rations math�matiques 
	 * 				standards entre chaque nombre du tableau
	 * 				tabIndices et en concatenant les r�sultats. 
	 * 		
	 * 				Par ex:
	 *
	 * 				numbers : 2 7 9 5 7 3 
	 *                        2+7 = 9 (MDP : AA9) 
	 *                        9-5 = 4 (MDP : AA94) 
	 *                        7*3 = 21 (MDP : AA9421)
	 * 
	 ******************************************************/
	private void generateCombination() {
		generateFirtsLetter();
		generateNumbers();
	}
	
	/******************************************************
	 * Display PadLock
	 * 
	 * @Resumer:	Affiche l'�tat du cadenas (lock/unlock).
	 * 				Affiche les caract�res trouv�s dans la
	 * 				cadenas virtuel affich� dans la console. 
	 * 				Les caract�res non trouv�s sont remplac�s
	 * 				par des *. 
	 * 
	 ******************************************************/
	private void displayPadlock() {

		if (isOpen) {
			System.out.print(TOP_PADLOCK_OPENED);
			System.out.print("\r\n |#  UNLOCKED   #| 		");
			System.out.print("\r\n |#  "); 
		} else {
			System.err.print(TOP_PADLOCK_CLOSED);
			System.err.print("\r\n |#   LOCKED    #| 		");
			System.err.print("\r\n |#  "); 
		}

		//Identifie les caract�res trouv�s, sinon on affiche *
		for (int i = 0; i < charFound.length; i++) {
			if(charFound[i]) System.out.print(combination.charAt(i));
			else			 System.err.print("*");				
		}
		
		if (isOpen) {
			System.out.print("	#| 		");
			System.out.print(BOTTOM_PADLOCK);
		} else {
			System.err.print("	#| 		");
			System.err.print(BOTTOM_PADLOCK);
		}
	}
	
	/******************************************************
	 * Display PadLock Combination
	 * 
	 * @Resumer:	Sert seulement au d�but de l'application
	 * 				pour afficher la combinaison secr�te
	 * 				dans le cadenas virtuel.
	 * 
	 ******************************************************/
	private void displayPadlockCombination() {
		System.out.print(TOP_PADLOCK_OPENED);
		System.out.print("\r\n |#  UNLOCKED   #| 		");
		System.out.print("\r\n |#  " + combination + "	#| 		");
		System.out.print(BOTTOM_PADLOCK);
	}
	
	/******************************************************
	 * Close
	 * 
	 * @Resumer:	R�initialise les valeurs pour se 
	 * 				retrouver dans l'�tat "pas ouvert". 
	 * 
	 ******************************************************/
	public void close(){
		isOpen = false;
		lettersFoundQty = 0;

		for(int i = 0; i < charFound.length;i++)
			charFound[i] = false;
	}

	/******************************************************
	 * Get Combination
	 * 
	 * @Resumer:	Retourne la combinaison secr�te.
	 * 
	 ******************************************************/
	public String getCombination() {return combination;}

	/******************************************************
	 * Get Clue Tab
	 * 
	 * @Resumer:	Retourne les nombres al�atoires utilis�s.
	 * 
	 ******************************************************/
	public int[] getClueTab() {return clueTab;}

	/******************************************************
	 * PadLock is Open
	 * 
	 * @Resumer:	Retourne l'�tat du cadenas.
	 * 
	 ******************************************************/
	public boolean padlockIsOpen() {return isOpen;}
}
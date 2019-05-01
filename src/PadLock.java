import java.util.Random;

/**************************************************************
 * @CLASS_TITLE:	PadLock
 * 
 * @Description: 	Cette classe représente un cadenas virtuel. 
 * 					C'est ici qu'on génère au hasard une 
 * 					combinaison secrète. Selon l'état de la 
 * 					combinaison, on affiche un cadenas ouvert 
 * 					ou fermé dans la console. 
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
	MAX_COMBINATION_SIZE = 10,			//Grosseur maximal de la combinaison à générer
	LETTERS_QTY = 2;					// Nombre de lettres majuscules au départ de la combinaison.

	/*****************************
	 * Variables
	 *****************************/
	private String combination = "";	//La combinaison à générer
	private int lettersFoundQty = 0;	//retient le nombre de lettre trouvée.

	/*****************************
	 * Arrays
	 *****************************/
	private int[] clueTab = null; 		// Aide à générer la combinaison
	private boolean[] charFound = null;	//Sert à retenir les caractère dévoilés.
	
	/*****************************
	 * Primitive
	 *****************************/
	private boolean isOpen = false;		//Donne l'état du cadenas virtuel
	

	/******************************************************
	 * Constructeur - PadLock
	 * 
	 * @Resumer:	Crée un cadenas qui peut être ouvert ou non.   
	 * 				Il s'ouvre si tous les caractères de la 
	 * 				combinaison ont été trouvés.  La vérification 
	 * 				se fait à l'aide de la fonction booléenne 
	 * 				placerCarALaPosition.
	 * 
	 * 				L'état du cadenas est affiché en mode console 
	 * 				ainsi que la combinaison lorsqu'elle est trouvée.
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
	 * 				chiffres aléatoires.
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
	 * 				STRATEGIE: On récupère le caractère de 
	 * 				la combinaison pour la position reçue 
	 * 				puis on le compare avec le caractère 
	 * 				proposé.
	 *
	 * 				Si les caractères sont égaux, on incrémente 
	 * 				lettersFoundQty.Finalement, si tous les 
	 * 				caractères ont été trouvés, alors on affiche
	 * 				le cadenas ouvert.
	 * 
	 * @param 		index La position à regarder.
	 * @param 		character La lettre proposée pour la position.
	 * @return 		-1 Si la lettre proposée est avant la lettre dans la table ascii.
	 * @return 		1 Si la lettre proposée est après la lettre dans la table ascii.
	 * @return 		0 Si la lettre proposée est la bonne lettre.
	 * 
	 ******************************************************/
	public int placeCharAtIndex(char character, int index) {

		char actualChar = combination.charAt(index);
		int searchState = 0;

		if (actualChar == character) {
			System.out.print(character);

			// Retenir que ce caractère vient d'être trouvé.
			charFound[lettersFoundQty] = true;
			lettersFoundQty++;

			// S'ils sont tous dévoilée, on l'ouvre.
			if (lettersFoundQty == combination.length()) 
				isOpen = true;

			displayPadlock();
		}
		// Retourne l'état selon la comparaison entre le caractère courant et celui reçu.
		else searchState = (actualChar > character) ? 1 : -1;
		return searchState;
	}

	/******************************************************
	 * Generate First Letter
	 * 
	 * @Resumer:	Génère les NB_LETTRES majuscules au début 
	 * 				de la combinaison.
	 * 
	 ******************************************************/
	private void generateFirtsLetter(){

		// Choisit les deux premières lettres au hasard et les concatène à la combinaison.
		for (int i = 0; i < LETTERS_QTY; i++) {
			int value = (int) Math.floor(Math.random() * SearchTools.CAPITAL_LETTERS.length());
			combination += SearchTools.CAPITAL_LETTERS.charAt(value);
		}
	}

	/******************************************************
	 * Generate Numbers
	 * 
	 * @Resumer:	Génère les chiffres de la combinaison 
	 * 				en se servant d'un tableau d'indices
	 * 				générer au hasard.
	 * 
	 ******************************************************/
	private void generateNumbers(){

		// Générer le tableau d'indices.
		generateClueTab();

		// Chaque caractère suivant est le résultat de l'opération choisit au hasard.
		for (int i = 0; i < clueTab.length; i = i + 2) {

			String operation;

			// Éviter la division par 0.
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
	 * @Resumer:	Génère une combinaison pour le cadenas
	 * 
	 * 				STRATEGIE: On génère une  combinaison 
	 * 				composée de 2 lettres + un nombre. 
	 * 				Le nombre est généré aléatoirement 
	 * 				en utilisant les opérations mathématiques 
	 * 				standards entre chaque nombre du tableau
	 * 				tabIndices et en concatenant les résultats. 
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
	 * @Resumer:	Affiche l'état du cadenas (lock/unlock).
	 * 				Affiche les caractères trouvés dans la
	 * 				cadenas virtuel affiché dans la console. 
	 * 				Les caractères non trouvés sont remplacés
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

		//Identifie les caractères trouvés, sinon on affiche *
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
	 * @Resumer:	Sert seulement au début de l'application
	 * 				pour afficher la combinaison secrète
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
	 * @Resumer:	Réinitialise les valeurs pour se 
	 * 				retrouver dans l'état "pas ouvert". 
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
	 * @Resumer:	Retourne la combinaison secrète.
	 * 
	 ******************************************************/
	public String getCombination() {return combination;}

	/******************************************************
	 * Get Clue Tab
	 * 
	 * @Resumer:	Retourne les nombres aléatoires utilisés.
	 * 
	 ******************************************************/
	public int[] getClueTab() {return clueTab;}

	/******************************************************
	 * PadLock is Open
	 * 
	 * @Resumer:	Retourne l'état du cadenas.
	 * 
	 ******************************************************/
	public boolean padlockIsOpen() {return isOpen;}
}
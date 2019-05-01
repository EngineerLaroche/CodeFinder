import java.util.Random;

/**************************************************************
 * @CLASS_TITLE:	Search Tools
 * 
 * @Description: 	Cette classe regroupe les outils pour la
 * 					recherche des caract�res de la combinaison
 * 					secr�te.
 * 
 **************************************************************/
public class SearchTools {

	/*****************************
	 * Constantes
	 *****************************/
	public static String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String[] OPERATIONS = new String[] { "+", "-", "/", "*" };
	public static int MAX_NUMBERS = 10;

	
	/******************************************************
	 * Force Brute
	 * 
	 * @Resumer:	Applique l'operation arithm�tique entre 
	 * 				nombre1 et nombre2. Le r�sultat ne peut 
	 * 				pas �tre n�gatif.
	 *
	 * @param 		num1
	 * @param 		num2
	 * @param 		operation "_", "-" , "*" ou "/".
	 * @return 		entier positif
	 * 
	 ******************************************************/
	public static int operationResult(int num1, int num2, String operation) {
		int result = 0;
		switch (operation) {
		
		case "/":
			result = num1 / num2;
			break;

		case "*":
			result = num1 * num2;
			break;
			
		case "+":
			result = num1 + num2;
			break;
			
		case "-":
			result = num1 - num2;
			break;
			
		default:
			break;
		}
		// Que des nombres positifs.
		return Math.abs(result);
	}
	
	/******************************************************
	 * First Character From Int
	 * 
	 * @Resumer:	Retourne  le premier caract�re d un int.
	 * 				Par ex 1231 -> '1'
	 * 
	 * @param 		num
	 * @return 		Le premier chiffre d'un entier en char.
	 * 
	 ******************************************************/
	public static char firstCharFromInt(int num){
		return firstCharFromString(intToString(num));
	}

	/******************************************************
	 * First Character From String
	 * 
	 * @Resumer:	Retourne le 1er caract�re d'une cha�ne.
	 * 
	 * @param 		string La cha�ne � consid�rer.
	 * @return 		Le 1er caract�re de string
	 * 
	 ******************************************************/
	public static char firstCharFromString(String chain){
		return chain.charAt(0);
	}

	/******************************************************
	 * Int to String
	 * 
	 * @Resumer:	Transforme un int en String.
	 * 
	 * @param 		Le nombreAconvertir
	 * @return 		Le m�me nombre version String.
	 * 
	 ******************************************************/
	public static String intToString(int numberToConvert){
		return "" + numberToConvert;
	}

	/******************************************************
	 * Character Between Two Code Ascii
	 * 
	 * @Resumer:	G�n�re une lettre entre deux bornes de 
	 * 				la table ascii.
	 * 
	 * @param 		min
	 * @param 		max
	 * @return 		une  lettre entre deux bornes de la table ascii.
	 * 
	 ******************************************************/
	public static char charBetweenTwoCodeAscii(int min, int max){
		return (char) randomBetweenMinMax(min, max);
	}

	/******************************************************
	 * Random Between Min and Max
	 * 
	 * @Resumer:	G�n�re un entier entre min et max.
	 * 
	 * @param 		min
	 * @param 		max
	 * @return 		un entier entre min et max.
	 * 
	 ******************************************************/
	public static int randomBetweenMinMax(int min, int max){
		return new Random().nextInt((max - min) + 1) + min;
	}

	/******************************************************
	 * Random Capital Letter
	 * 
	 * @Resumer:	Une lettre en majuscule.
	 * 
	 ******************************************************/
	public static char randomCapitalLetter(){
		return CAPITAL_LETTERS.charAt(
				(int) Math.floor(Math.random() * CAPITAL_LETTERS.length()));
	}

	/******************************************************
	 * Random Number
	 * 
	 * @Resumer:	Un chiffre entre '0' et '9'.
	 * 
	 ******************************************************/
	public static char randomNumber(){
		return firstCharFromInt(new Random().nextInt(MAX_NUMBERS));
	}
}
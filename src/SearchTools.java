import java.util.Random;

/**************************************************************
 * @CLASS_TITLE:	Search Tools
 * 
 * @Description: 	Cette classe regroupe les outils pour la
 * 					recherche des caractères de la combinaison
 * 					secrète.
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
	 * @Resumer:	Applique l'operation arithmétique entre 
	 * 				nombre1 et nombre2. Le résultat ne peut 
	 * 				pas être négatif.
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
	 * @Resumer:	Retourne  le premier caractère d un int.
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
	 * @Resumer:	Retourne le 1er caractère d'une chaîne.
	 * 
	 * @param 		string La chaîne à considérer.
	 * @return 		Le 1er caractère de string
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
	 * @return 		Le même nombre version String.
	 * 
	 ******************************************************/
	public static String intToString(int numberToConvert){
		return "" + numberToConvert;
	}

	/******************************************************
	 * Character Between Two Code Ascii
	 * 
	 * @Resumer:	Génère une lettre entre deux bornes de 
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
	 * @Resumer:	Génère un entier entre min et max.
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
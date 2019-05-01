# Combination Finder

Le projet consiste à explorer différentes techniques de création de combinaison encodée (alphanumérique).
L'application Java permet de décoder une combinaison générée au hasard pour l’ouverture d’un cadenas virtuel.
L'idée est d'étudier et implémenter quatre techniques de décodage afin d'évaluer leur performance.

## Techniques de décodage

Quatre techniques de décodage ont été implémentées:

	  1- Brute Force
  	2- Optimized Brute Force
  	3- Optimized Brute Force with Intervals
  	4- Optimized Brute Force with Intervals Reverse Engineering 

Le démarrage de l'application se fait à partir de la classe Application.java.
L'évolution de l'identification de la combinaison secrète est affichée dans la console de votre IDE Java.

Voici un simple exemple de ce que vous allez retrouver dans la cvonsole après avoir démarrée l'application.


******************************************
*                BIENVENU !              *
*                                        *
*  Voici une application qui utilise 4   *
*  techniques différentes pour trouver   *
*  une combinaison hexadécimale secrète  *
*  générée au hasard.                    *
*                                        *
******************************************
              _________	
             /#########.	
            /#/       |#|	
            |#|       |#|	
            |#|       |#|	
 /###############.	   		
 |#             #| 		
 |#             #| 		
 |#  UNLOCKED   #| 		
 |#  UW6436313	#| 		
 |#             #| 		
 .###############/ 		


******************************************
* 				Brute Force 			 *
******************************************

Attempts : Y1Z8D7W1W2K9M1M4P8X6F9B6Y5Q6R3D4H5L6S8Y8B9B4Q2P2Y6Z7M6V5W5P9B0K5P6Z9S9U5 U

     _________			
    /#########.			
   /#/       |#|			
   |#|       |#|			
   |#|       |#|			
 /###############.	   		
 |#             #| 		
 |#             #| 		
 |#   LOCKED    #| 		
 |#  U********	#| 		
 |#             #| 		
 .###############/ 		

Attempts : V2K9P6J8B5Z9P1L1L5B1T5P1K2N1K7W3 W

     _________			
    /#########.			
   /#/       |#|			
   |#|       |#|			
   |#|       |#|			
 /###############.	   		
 |#             #| 		
 |#             #| 		
 |#   LOCKED    #| 		
 |#  UW*******	#| 		
 |#             #| 		
 .###############/ 		

(...)	

Attempts : R6D8M9D2Z5F5R6G1V8X1C9W5P5Z7A6O0H 3

              _________	
             /#########.	
            /#/       |#|	
            |#|       |#|	
            |#|       |#|	
 /###############.	   		
 |#             #| 		
 |#             #| 		
 |#  UNLOCKED   #| 		
 |#  UW6436313	#| 		
 |#             #| 		
 .###############/  

## Author
- Alexandre Laroche
# Word Blocks Design Document 

### High Concept Statement 
The perfect balance between 2 gameplays : a word game mixed with a tower game.


### Features 
* The bottom of the screen will display 6 randomly selected letters. The goal is to make a word with the letters given. In this random selection, there will always be at least one vowel to give the player a chance to create a valid word. 
* When a valid word is created, a “block” is constructed. Valid words are considered to be words of 3 letters or longer. This block will be the size of the word, so a 5 letter word would make a block of size 5.  
* The main goal of the game is to build the tallest tower possible in the given time limit by stacking the word blocks the player has created on top of one another. 
* The timer countdown will appear in the top right hand corner of the screen..
* One level will equal one point. For example, if a player has two blocks, one stacked on top of the other, then the player would have a tower of two levels. Additionally, a certain number of bonus points will be awarded when blocks of size 5 or 6 are created to encourage the creation of longer words.
* The score will appear in the top left corner of the screen
* A player can only stack blocks of the same size or smaller on top of each other. For example, a block of size 4 cannot be stacked on top of a block of size 3. 
* To encourage the player to stack blocks vertically, the width of the tower cannot exceed a size of 12. This width constraint will be outlined with vertical black lines, so players can only place blocks in between these black lines.
* When the block is outlined green, this will indicate that the player can place the block in the spot they specified. When the block is outlined red, this will indicate that the player cannot play the block in the spot they have specified. 
* When a valid word has been created, the letters used will disappear and then new letters will replace them. 
* If the player attempts to create an invalid word, then the timer will decrease by 5 seconds instead of 1 second.
* Controls are easy to understand: select letters with the mouse to create a word and place and stack the block using the arrow keys.
* The player will have a 2D view of the tower, with a point of view following the top of the tower.

### Player motivation 
The player needs to create the tallest tower in 1 minute. The score depends on the height of the tower and partially on the length of the words that are created. 

### Genre 
Score game; mixing word gameplay and tower build gameplay.
Target Customer
Individuals who enjoy puzzle games, as players are tasked with solving both a word and building puzzle

### Competition 
Anagrams

### Unique selling points 
2 mixed gameplays

### Target hardware 
Every supporting Java platform

### Design goals 
- *Understandable interface*: by displaying some short instructions at the beginning of the game, the player will be able to grasp how to play and game and quickly adjust to how the game should be played 
- *Engaging*: players will want to keep playing the game as they will want to beat their previous score and try different methods of building the tower. Since the letters are different every time, the gameplay will differ every round. 
- *Desire to improve*: rewarding the players for creating longer words with points will encourage players to more carefully consider how to build longer words. Likewise, penalizing them for creating incorrect words will have the same effect.

	



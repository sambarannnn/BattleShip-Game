# BattleShip Game
 Java program for a 2 Player game of BattleShip

The game is played on two fields, each 10 X 10 squares. The columns are labeled A-J, and the rows are labeled 1-10. Each player's fleet of ships consists of one aircraft carrier, one battleship, one destroyer, one submarine and one cruiser. The size and shape of each ship is as follows:

Destroyer (2 squares): 
Submarine (3 squares): 
Cruiser (3 squares): 
Battleship (4 squares): 
Aircraft Carrier (5 squares): 

Before the game starts, each player secretly places their ships anywhere on their own playing field. Ships cannot overlap one another, but may be placed either vertically or horizontally.

Players take turns to try to guess the location of the other's ships by naming a square (e.g. F7). The opponent declares the square to be a hit or a miss, depending on whether there is a ship occupying that square. When all the squares occupied by a particular ship have been guessed, the player must announce that that particular ship is sunk. A player keeps track of the hits and misses on a copy of the opponent's field.

During each player's turn, only their board is completely visible whereas the opponent's board only shows attacked squares, i.e. HITS (X) and MISSES (M).

The first player to sink all the other's ships is the winner.

Project UML : ![Project UML](https://user-images.githubusercontent.com/63100608/119877457-b037e080-bf46-11eb-8918-874f34590c80.png)

# BattleshipGame
System Design 
--------------

Please refer to design image attached - BALLESHIP_DESIGN.jpg

Building source : 
------------------
1. You will need Maven to be available at path.

Run > mvn clean install

Run> mvn javadoc:javadoc for generating JAVA API usage docs. Refer javadoc for already generated docs.

2. Verify that in target folder you get jar file generated. 

Executing Program :
-------------------

run/target > java -jar BattleShipGame-1.0-SNAPSHOT.jar


Sample Input
---------------
Program will launch the game , and expect you to provide input in given format as console IN.  
5 E
2
Q 1 1 A1 B2
P 2 1 D4 C3
A1 B2 B2 B3
A1 B2 B3 A1 D1 E1 D4 D4 D5 D5

Result
--------
Program will print battleArea and result in given format. 

############ BATTLE AREA IS PLOTTED BELOW   ######
[Q,  ,  ,  ,  ]
[ ,  ,  ,  ,  ]
[ ,  ,  ,  ,  ]
[ ,  ,  , P, P]
[ ,  ,  ,  ,  ]
###############################################
[ ,  ,  ,  ,  ]
[ , Q,  ,  ,  ]
[ ,  , P, P,  ]
[ ,  ,  ,  ,  ]
[ ,  ,  ,  ,  ]
###############################################
PLAYER_1 fires a missile with target A1 which got miss
PLAYER_2 fires a missile with target A1 which got hit
PLAYER_2 fires a missile with target B2 which got miss
PLAYER_1 fires a missile with target B2 which got hit
PLAYER_1 fires a missile with target B2 which got hit
PLAYER_1 fires a missile with target B3 which got miss
PLAYER_2 fires a missile with target B3 which got miss
PLAYER_1 has no more missiles left to launch
PLAYER_2 fires a missile with target A1 which got hit
PLAYER_2 fires a missile with target D1 which got miss
PLAYER_1 has no more missiles left to launch
PLAYER_2 fires a missile with target E1 which got miss
PLAYER_1 has no more missiles left to launch
PLAYER_2 fires a missile with target D4 which got hit
PLAYER_2 fires a missile with target D4 which got miss
PLAYER_1 has no more missiles left to launch
PLAYER_2 fires a missile with target D5 which got hit
PLAYER_2 has won the battle.

Additional Info
----------------
Refer resources \ SampleResults.txt to data coverage as part of testing.


# Design doc

## Classes and public methods

### class GameLogic{
discription:


This class describes the logic of the chess game. 


objects:


- pieces 
- board -> (x,y) coords -> empty or piece, at start of the game fill board with pieces or empty
- game history, array with moves mode throughout the game
- time
 

 methods:
 
 
- check_move(piece,begin(x,y),end(x,y),game history) returns boolean
- per piece: piece_movement(piece,begin(x,y),end(x,y)) returns boolean
- check_victory(board) returns boolean
- check_draw(board, game history) returns boolean
- check_check(board) returns boolean
- doMove(board, begin(x,y),end(x,y),game history) returns string move
- updateGameHistory(gameHistory, move) returns boolean
- run_game()

### }

### class BuggHouseGameLogic extends GameLogic{
discription:


This class specifies the logic of the bugghouse chess game. 


objects


- collection


methods:


- check_place(board) returns boolean
- place_piece(collection,board), places to piece and uses check-place()
- override check_victory(board) returns boolean

### }

### class API_Bluetooth{
discription:


This class enables the player to use bluetooth. Sending challenges,moves and pieces for bugghouse. 


methods:

- sendChallenge()
- acceptChallenge()
- sendMove()
- acceptMove()
- sendPiece()
- acceptPiece()
 

### }

### class API_Touch{

discription:

This class gets in the input for the gamelogic, translating the touch of the screen into (x,y) coordinates.


 - get_input() returns start(x,y),end(x,y)
 - get_coords(start(x,y),end(x,y)) returns board coordinates


### }
### class GameLobby{
discription:


This class will define some methods used in the gamelobby for challenging and building a bugghouse team.


methods:

- startChallenge()
- addBuggHouseTeam()
- startBugghouse()
- updateList()
 
### }
### class Rating{
discription:


This class will use a the elo rating algorithm to compute the player's elo rating.


methods:


- elo_algorithm(myRating,opponentRating,result) return int newRating
- update_rating() ,send to database

### }

### activity local_game_handler{
  handles the local game, makes use of classes: 
  - GameLogic

### }

### activity bluetooth_game_handler{
  handles the bluetooth connected game, makes use of classes
  - GameLogic
  - Rating
  - GameLobby
  - API_Touch
  - API_Bluetooth

### }

### activity bugghouse_game_handler{


  handles the bluetooth connected bugghouse game, makes use of classes 
  - BuggHouseameLogic
  - Rating
  - GameLobby
  - API_Touch
  - API_Bluetooth

### }
### activity account_handler{


  handles the changes in account settings, stores it in sharedpreferences
  

### }
### activity preferences_handler{


  handles the changes in in-game settings, stores it in sharedpreferences

### }
## Sketches 
The local game will look like this, with activity: local_game_handler


![Image localgame](https://github.com/FreddyG/app_studio_10287302/blob/master/doc/Screens/Game_local.png)


The bluetooth normal game will look like this, with activity: local_game_handler


![Image bluetoothgame](https://github.com/FreddyG/app_studio_10287302/blob/master/doc/Screens/Game_normal.png)


The bluetooth bugghouse game will look like this, with activity: local_game_handler


![Image bugghousegame](https://github.com/FreddyG/app_studio_10287302/blob/master/doc/Screens/Game_bugghouse.png)


Screens are liked like this: 


![Image bluetoothgame](https://github.com/FreddyG/app_studio_10287302/blob/master/doc/Screen_links.png)
## API's
### Bluetooth
This app will use Bluetooth to play games. It should be able to send a game information (move,challenge and time) and player information(name,rating) via Bluetooth

### Touch


This app will use Touch to get input for playing the game. You should be able to drag a piece to its destination.

## Databases

Use sharedpreferences to store name,rating (maybe history of games)

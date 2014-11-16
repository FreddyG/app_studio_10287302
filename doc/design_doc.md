# Design doc

## Classes and public methods

### class GameLogic{


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
objects


- collection


methods:


- check_place(board) returns boolean
- place_piece(collection,board), places to piece and uses check-place()
- override check_victory(board) returns boolean

### }

### class API_Bluetooth{
methods:

- sendChallenge()
- acceptChallenge()
- sendMove()
- acceptMove()
- sendPiece()
- acceptPiece()
 

### }

### class API_Touch{
 - get_input() returns start(x,y),end(x,y)
 - get_coords(start(x,y),end(x,y)) returns board coordinates


### }
### class GameLobby{

methods:

- startChallenge()
- addBuggHouseTeam()
- startBugghouse()
- updateList()
 
### }
### class Rating{


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

## API's
### Bluetooth
This app will use Bluetooth to play games. It should be able to send a game information (move,challenge and time) and player information(name,rating) via Bluetooth

### Touch


This app will use Touch to get input for playing the game. You should be able to drag a piece to its destination.

## Databases

Use sharedpreferences to store name,rating (maybe history of games)

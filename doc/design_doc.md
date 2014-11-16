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

### class local_game_handler{
  

### }

### class bluetooth_game_handler{
  

### }

### class bugghouse_game_handler{
  

### }
### class account_handler{
  

### }
### class preferences_handler{
  

### }
## Sketches 

## API's
### Bluetooth


### Touch

## Databases

Use sharedpreferences to store name,rating (maybe history of games)

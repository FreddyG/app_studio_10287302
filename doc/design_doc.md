# Design doc

## Classes and public methods

###class GameLogic{


objects:


-pieces 
-board -> (x,y) coords -> empty or piece, at start of the game fill board with pieces or empty
-game history, array with moves mode throughout the game
 methods:
 
 
-check_move(piece,begin(x,y),end(x,y),game history) returns boolean
-per piece: piece_movement(piece,begin(x,y),end(x,y)) returns boolean
-check_victory(board) returns boolean
-check_draw(board, game history) returns boolean
-check_check(board) returns boolean
-
}

class BuggHouseGameLogic extends GameLogic{
objects
-collection
methods:
-check_place(board) returns boolean
-place_piece(collection,board), places to piece and uses check-place()
-override check_victory(board) returns boolean
}

class Bluetooth{

}

class Rating{

}

### Sketches 

### API's

### Databases

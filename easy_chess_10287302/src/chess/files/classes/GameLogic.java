package chess.files.classes;

import android.util.Log;

public class GameLogic {
	
	/**
	
	Pieces are defined as:
	
	0  = white field
    1  = black field 
    2  = pawn_white
    3  = pawn_black
    4  = knight_white
    5  = knight_black
    6  = bishop_white
    7  = bishop_black
    8  = rock_white
    9  = rock_black
    10 = queen_white
    11 = queen_black
    12 = king_white
    13 = king_black
      
    */
	public int [][] board = new int[8][8];
	//return board filled with pieces in initial state
	public int[][] init_board(){
		
		
		
		for (int row = 0; row < 8; row ++)
		    for (int col = 0; col < 8; col++)
		        board[row][col] = 0;
		
		//pieces black
		board[0][0]=9;
		board[0][1]=5;
		board[0][2]=7;
		board[0][3]=11;
		board[0][4]=13;
		board[0][5]=7;
		board[0][6]=5;
		board[0][7]=9;
		
		//pieces white
		board[7][0]=8;
		board[7][1]=4;
		board[7][2]=6;
		board[7][3]=10;
		board[7][4]=12;
		board[7][5]=6;
		board[7][6]=4;
		board[7][7]=8;
		
		//pawns black
		for (int row = 1; row < 2; row ++)
		    for (int col = 0; col < 8; col++)
		        board[row][col] = 3;
		
		//pawns white
		for (int row = 6; row < 7; row ++)
		    for (int col = 0; col < 8; col++)
		        board[row][col] = 2;
		
		
		return board;
		
	}
	
	//look if a move is valid by trying to prove it is not valid
	public boolean isValidMove(int piece,int previous_x,int previous_y,int x,int y,int isWhite){
		
		//does the user play with his own pieces
		if(!isSameColor(piece,isWhite)){
			return false;
		}
		
		//update y, because the board was 
		y = 7 - y;
		previous_y= 7 - previous_y;
		
		Log.d("Easy chess","try to prove " + piece + " " + previous_x + " " + previous_y+ " " + x+ " " +y + " " + isWhite);
		//look if the move is conform the piece's rules
		switch (piece) {
		 case 2:
			 if(!whitePawnMove(previous_x,previous_y,x,y))
				 return false;
         break;
		 case 3:
			 if(!blackPawnMove(previous_x,previous_y,x,y))
				 return false;
         break;
		 case 4:
			 if(!knightMove(previous_x,previous_y,x,y))
				 return false;
         break;
		 case 5:
			 if(!knightMove(previous_x,previous_y,x,y))
				 return false;
         break;
		 case 6:
			 if(!bishopMove(previous_x,previous_y,x,y))
				 return false;
         break;
		 case 7:
			 if(!bishopMove(previous_x,previous_y,x,y))
				 return false;
         break;
		 case 8:
			 if(!rockMove(previous_x,previous_y,x,y))
				 return false;
         break;
		 case 9:
			 if(!rockMove(previous_x,previous_y,x,y))
				 return false;
         break;
		 case 10:
			 if(!queenMove(previous_x,previous_y,x,y))
				 return false;
         break;
		 case 11:
			 if(!queenMove(previous_x,previous_y,x,y))
				 return false;
         break;
		 case 12:
			 if(!kingMove(previous_x,previous_y,x,y))
				 return false;
         break;
		 case 13:
			 if(!kingMove(previous_x,previous_y,x,y))
				 return false;
         break;
		}
		return true;
	}
	private boolean isSameColor(int piece,int isWhite){
		if(piece==0||piece==1){
			return false;
		}
		if(isWhite==1){
			if (piece%2==0){
				return true;
			}
			return false;
		}
		else{
			if (piece%2==1){
				return true;
			}
			return false;
		}
	}
	private boolean whitePawnMove(int previous_x,int previous_y,int x,int y){
		//pawn takes a piece
		if(previous_x!=x){
			if((previous_x-x)*(previous_x-x)==1){
				if(y-previous_y==1&&board[x][y]>1){
					return true;
				}
			}
		}
		//pawn moves forward
		else{
			if(y-previous_y==1){
				return true;
			}
			//initiale special move
			if(previous_y==1&&y==3){
				return true;
			}
		}
		
		return false;
		
	}
	private boolean blackPawnMove(int previous_x,int previous_y,int x,int y){
		//pawn takes a piece
		if(previous_x!=x){
			if((previous_x-x)*(previous_x-x)==1){
				if(previous_y-y==1&&board[x][y]>1){
					return true;
				}
			}
		}
		//pawn moves forward
		else{
			if(previous_y-y==1){
				return true;
			}
			//initiale special move
			if(previous_y==6&&y==4){
				return true;
				
			}
		}
		
		return false;
		
	}
	private boolean knightMove(int previous_x,int previous_y,int x,int y){
		if((previous_x-x)*(previous_x-x)*(previous_y-y)*(previous_y-y)==4){
			return true;
		}
		
		return false;
		
	}
	private boolean bishopMove(int previous_x,int previous_y,int x,int y){
		if((previous_x-x)*(previous_x-x)-(previous_y-y)*(previous_y-y)==0){
			return true;
		}
		
		return false;
		
	}
	private boolean rockMove(int previous_x,int previous_y,int x,int y){
		if((previous_x-x)==0||(previous_y-y)==0){
			return true;
		}
		return false;
		
	}
	private boolean queenMove(int previous_x,int previous_y,int x,int y){
		if((previous_x-x)==0||(previous_y-y)==0){
			return true;
		}
		if((previous_x-x)*(previous_x-x)-(previous_y-y)*(previous_y-y)==0){
			return true;
		}
		
		return false;
		
	}
	private boolean kingMove(int previous_x,int previous_y,int x,int y){		
		if((previous_x-x)*(previous_x-x)==1){
			if((previous_y-y)*(previous_y-y)==1||(previous_y-y)*(previous_y-y)==0){
				return true;
			}
		}
		else if((previous_x-x)*(previous_x-x)==0){
			if((previous_y-y)*(previous_y-y)==1||(previous_y-y)*(previous_y-y)==0){
				return true;
			}
			
		}
		return false;
	}

}

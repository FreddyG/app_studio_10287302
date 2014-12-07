package chess.files;

import chess.files.classes.*;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
//import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


public class Local_Game_Activity extends Activity {
	//arrays
	private String [] Y_Coords = new String[] { "8", "7",
			"6", "5","4","3","2","1"};
	private String [] X_Coords = new String[] { "a", "b",
			"c", "d","e","f","g","h"};
	private int [][] board = new int[8][8];
	private int [][] listBoard = new int[8][8];
	
	//objects
	GameLogic game = new GameLogic();
	TextView t;	
	ListView list;
	ListView x_coordinates;
	
	//variables
	private int previous_column = -1;
	private int previous_row = -1;
	private int column = -1;
	private int row = -1;
	private int isWhite = 0;  //starts at 0 but gets updated to 1

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		//set view
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_local__game_);
		
		//set textview
		t=new TextView(this); 
	    t=(TextView)findViewById(R.id.move);
	    
	    //set listview
	    board = game.init_board();
	    listBoard= getData(board);
	    updateBoard();	    
		
	}
	
	
	
	public void yes_button(View view){
		//check move with game logic
		if(previous_row!=-1){
			int piece = board[previous_row][previous_column];
			int restoreField = board[row][column];
			game.board = board;
			if(game.isValidMove(piece,previous_column,previous_row,column,row,isWhite)){
				
				//simulate move
				if((previous_row+previous_column)%2==0){
					game.board[previous_row][previous_column] = 0;
				}
				else{
					game.board[previous_row][previous_column] = 1;
				}
				game.board[row][column] = piece;
				
				
				if(game.check(isWhite)){
					Log.d("EasyChess","Player is NOT check");
				}
				else{
					Log.d("EasyChess","Player is check");
				}
					if(game.check(isWhite)){
					if((previous_row+previous_column)%2==0){
						board[previous_row][previous_column] = 0;
					}
					else{
						board[previous_row][previous_column] = 1;
					}
					
					board[row][column] = piece;
					updateBoard();
					onRelease();
				}
				else{			
					game.board[row][column] = restoreField;
					game.board[previous_row][previous_column] = piece;
					row = -1;
					column = -1;
					previous_row = -1;
					previous_column = -1;
					t.setText("You're king is in check! ");		
				}
			}
			else{
				row = -1;
				column = -1;
				previous_row = -1;
				previous_column = -1;
				t.setText("Not a valid move.");			
			}
		}
		
	}
	
	public void no_button(View view){
		onRelease();
	}
	
	private void onPressed(){
		if(previous_row!=-1){
			t.setText("Move from "+ X_Coords[previous_column] + (8 -previous_row)+ " to " + X_Coords[column] + (8 - row) + "?");
		}
		else{
		t.setText("Selected "+ X_Coords[column]+(8- row));
		}
	}
	
	private void onRelease(){
		row = -1;
		column = -1;
		previous_row = -1;
		previous_column = -1;
		if(isWhite==1){
			t.setText("White can select a piece");
		}
		else{
			t.setText("Black can select a piece");
		}
	}
	
	private void updateBoard(){
		//update game board
		game.board = board;
		
		
		//update the board
		listBoard= getData(board);
		ChessList adapter = new
				ChessList(Local_Game_Activity.this, Y_Coords,listBoard);
		list=(ListView)findViewById(R.id.list);
		list.setAdapter(adapter);
		
		//swap turns after move
		if(isWhite==1){
			isWhite = 0;
		}
		else{
			isWhite = 1;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.local__game_, menu);
		return true;
	}
	
	@SuppressLint("Recycle")
	private int[][] getData(int [][] pieces) {
		
		int [][] imageItems = new int[8][8];
		int getItem = 0;
		
		Resources a = getResources();
		TypedArray imgs = a.obtainTypedArray(R.array.image_ids);
		
		for (int j = 0;j<8;j++){
			for (int i = 0; i < 8; i++) {
				getItem = pieces[j][i];		
				imageItems[j][i]= imgs.getResourceId(getItem, -1);
			}
		}

		return imageItems;

	}
	
	public void veld1(View view) {
		if(row!=list.getPositionForView(view)||column!=0){
		 previous_row = row;
		 previous_column = column;
		 row = list.getPositionForView(view);
	     column = 0;
	     onPressed();
		}
		else{
			onRelease();
		}
	}
	
	public void veld2(View view) {
		if(row!=list.getPositionForView(view)||column!=1){
			 previous_row = row;
			 previous_column = column;
			 row = list.getPositionForView(view);
		     column = 1;
		     onPressed();
			}
			else{
				onRelease();
			}
	}
	
	public void veld3(View view) {
		if(row!=list.getPositionForView(view)||column!=2){
			previous_row = row;
			 previous_column = column;
			 row = list.getPositionForView(view);
		     column = 2;
		     onPressed();
			}
			else{
				onRelease();
			}
	}
	
	public void veld4(View view) {
		if(row!=list.getPositionForView(view)||column!=3){
			previous_row = row;
			 previous_column = column;
			 row = list.getPositionForView(view);
		     column = 3;
		     onPressed();
			}
			else{
				onRelease();
			}
	}
	
	public void veld5(View view) {
		if(row!=list.getPositionForView(view)||column!=4){
			previous_row = row;
			 previous_column = column;
			 row = list.getPositionForView(view);
		     column = 4;
		     onPressed();
			}
			else{
				onRelease();
			}
	}
	
	public void veld6(View view) {
		if(row!=list.getPositionForView(view)||column!=5){
			previous_row = row;
			 previous_column = column;
			 row = list.getPositionForView(view);
		     column = 5;
		     onPressed();
			}
			else{
				onRelease();
			}
	}
	
	public void veld7(View view) {
		if(row!=list.getPositionForView(view)||column!=6){
			previous_row = row;
			 previous_column = column;
			 row = list.getPositionForView(view);
		     column = 6;
		     onPressed();
			}
			else{
				onRelease();
			}
	}
	
	public void veld8(View view) {
		if(row!=list.getPositionForView(view)||column!=7){
			previous_row = row;
			 previous_column = column;
			 row = list.getPositionForView(view);
		     column = 7;
		     onPressed();
			}
			else{
				onRelease();
			}
	}
	
	public void back(View view) {
	     finish();
	}

}

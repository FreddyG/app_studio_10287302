package chess.files;

import chess.files.classes.*;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
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
	ImageView indicator;
	
	//variables
	private int previous_column = -1;
	private int previous_row = -1;
	private int column = -1;
	private int row = -1;
	private int isWhite = 0;  //starts at 0 but gets updated to 1
	
	SharedPreferences sharedpreferences;
	//sharedPreferences keys
	public static final String MyPREFERENCES = "MyPrefs" ;
	public static final String Pieces = "piecesKey"; 
	public static final String SaveBoard = "boardKey";
	public static final String SaveTurn = "turnKey";
	public static final String SaveHistory = "historyKey";
	public static final String SaveCount = "countKey";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		//set view
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_local__game_);
		
		//set textview
		t=new TextView(this); 
	    t=(TextView)findViewById(R.id.move);
	    
	    //set imageview
	    indicator = new ImageView(this);
	    indicator = (ImageView)findViewById(R.id.indicator);
	    
	    //set listview
	    board = game.init_board();
	    listBoard= getData(board);
	    updateBoard();	    
		
	}
	
	
	
	public void yes_button(View view){
		//check move with game logic
		if(previous_row!=-1){
			
			//get pieces and set board
			int piece = board[previous_row][previous_column];
			int restoreField = board[row][column];
			game.board = board;
			
			if(game.isValidMove(piece,previous_column,previous_row,column,row,isWhite)){
				
				//simulate move
				game.simulateMove(previous_column, previous_row, column, row, piece,0);

				//check if the simulated move, does not cause check
				if(game.check(isWhite)){
					game.move(previous_column,previous_row,column,row,piece);
					if((previous_row+previous_column)%2==0){
						board[previous_row][previous_column] = 0;
					}
					else{
						board[previous_row][previous_column] = 1;
					}
					
					//hardcoded castling white
					if(previous_column==4){
						if(piece==12&&column==2){
							board[row][3]=8;
							board[row][0]=1;
						}
						if(piece==12&&column==6){
							board[row][5]=8;
							board[row][7]=0;
						}
					}
					
					//hardcoded castling black
					if(previous_column==4){
						if(piece==13&&column==2){
							board[row][3]=9;
							board[row][0]=0;
						}
						if(piece==13&&column==6){
							board[row][5]=9;
							board[row][7]=1;
						}
					}
					
					//hardcoded en passant rule
					if(game.flag_en_passant==1){
						if(isWhite==1){
							previous_row = 3;
						}
						else{
							previous_row = 4;
						}
						if((previous_row+column)%2==0){
							board[previous_row][column] = 0;
						}
						else{
							board[previous_row][column] = 1;
						}
					}
					game.flag_en_passant = 0;
					
					
					board[row][column] = piece;
					updateBoard();
					if(game.isCheckMate(isWhite)){
						t.setText("CheckMate!");
					}
					else{
						onRelease();
					}
					
				}
				else{
					
					//move causes check, so reset
					game.board[row][column] = restoreField;
					game.board[previous_row][previous_column] = piece;
					
					resetValues();
					
					t.setText("Your king is in check! ");		
				}
			}
			else{
				resetValues();
				t.setText("Not a valid move.");			
			}
		}
		
	}
	
	public void no_button(View view){
		onRelease();
	}
	
	public void resetValues(){
		
		row = -1;
		column = -1;
		previous_row = -1;
		previous_column = -1;
		
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
		resetValues();
		if(isWhite==1){
			t.setText("White can select a piece");
		}
		else{
			t.setText("Black can select a piece");
		}
	}
	
	private void updateBoard(){
		//update game board
		for (int row = 0; row < 8; row ++){
		    for (int col = 0; col < 8; col++){
		    	game.board[row][col] = board[row][col];
		    }
		}
		
		
		//update the board
		listBoard= getData(board);
		ChessList adapter = new
				ChessList(Local_Game_Activity.this, Y_Coords,listBoard);
		list=(ListView)findViewById(R.id.list);
		list.setAdapter(adapter);
			
		
		BitmapDrawable black = (BitmapDrawable)getResources().getDrawable(R.drawable.black);
		BitmapDrawable white = (BitmapDrawable)getResources().getDrawable(R.drawable.white);
		
		//swap turns after move
		if(isWhite==1){
			isWhite = 0;
			indicator.setBackground(black);
			
		}
		else{
			isWhite = 1;
			indicator.setBackground(white);
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
		String chosenPieces;
		Resources a = getResources();
		sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		if (sharedpreferences.contains(Pieces)){
	         chosenPieces = sharedpreferences.getString(Pieces, "");
	     }
		else{
			chosenPieces = "Classic";
		}
		TypedArray imgs;
		if(chosenPieces.equals("Classic")){
			imgs = a.obtainTypedArray(R.array.image_ids);
		}
		else if(chosenPieces.equals("Modern")){
			imgs = a.obtainTypedArray(R.array.image_ids2);
		}
		else{
			imgs = a.obtainTypedArray(R.array.image_ids);
		}
		
		
		
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
		
		//make dialog
		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.popup_pause);
		dialog.show();
		
		Button ContinueButton = (Button) dialog.findViewById(R.id.Continue);	
		ContinueButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				dialog.dismiss();
				
			}
		});
		Button newGameButton = (Button) dialog.findViewById(R.id.NewGame);
		
		//start a new game
		newGameButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				board = game.init_board();
			    listBoard= getData(board);
			    isWhite = 0;
			    updateBoard();		    
				dialog.dismiss();
				
			}
		});
		
		//save current game as a string
		Button SaveButton = (Button) dialog.findViewById(R.id.Save);
		SaveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				
				StringBuilder sb = new StringBuilder();
				
				for (int j = 0;j<8;j++){
					for (int i = 0; i < 8; i++) {
						sb.append(board[j][i]);
						if(i<7)
							sb.append(",");
					}
					if(j<7)
						sb.append("-");
				}
				
				//use sharedpreference to save
				Editor editor = sharedpreferences.edit();
				editor.putString(SaveBoard, sb.toString());
				
				int saveTurn = 0;
				if(isWhite==1){
					saveTurn = 0;
				}
				else{
					saveTurn = 1;
				}
				//also save who's turn it is
				editor.putString(SaveTurn, ("" + saveTurn));
				
				//editor.putString(SaveHistory, game.gamehistory);
				//editor.putString(SaveCount, sb.toString());
				editor.commit();
				dialog.dismiss();
				
			}
		});
		
		//load game from shared preferences
		Button loadButton = (Button) dialog.findViewById(R.id.Load);

		loadButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (sharedpreferences.contains(SaveBoard)){
					
					//build board from string
					
					String LoadBoard = sharedpreferences.getString(SaveBoard, "");
					String[] parts = LoadBoard.split("-");
					
					//"-" splits rows, "," splits elements in rows
					for( int j = 0;j<8;j++){
						
						String []current = parts[j].split(",");
						for( int i = 0;i<8;i++){
							
							board[j][i] = Integer.parseInt(current[i]);
						}
					}
				}
				if( sharedpreferences.contains(SaveTurn)){				
					isWhite = Integer.parseInt(sharedpreferences.getString(SaveTurn, ""));
				}
				
				updateBoard();
				dialog.dismiss();
				
			}
		});
		
		
		Button ExitButton = (Button) dialog.findViewById(R.id.Exit);
		ExitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			    finish();
				dialog.dismiss();
				
			}
		});
	}
}

package chess.files.classes;

public class GameHistory {
	public int start_x;
	public int start_y;
	public int end_x;
	public int end_y;
	public int piece;
	
	public GameHistory(int input_start_x,int input_start_y,int input_end_x,int input_end_y,int input_piece){
		start_x = input_start_x;
		start_y = input_start_y;
		end_x   = input_end_x;
		end_y   = input_end_y;
		piece = input_piece;
	}
	
	
}

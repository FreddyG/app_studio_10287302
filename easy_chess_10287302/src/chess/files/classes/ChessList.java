package chess.files.classes;

import chess.files.*;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ChessList extends ArrayAdapter<String>{
	
	
	private final Activity context;
	private final String[] coordinates;
	private final int[][] data;
	
	public ChessList(Activity context,
	String[] coordinates, int [][] data) {
		
		super(context,R.layout.chessboard_list , coordinates);
		this.context = context;
		this.coordinates = coordinates;
		this.data = data;

	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.chessboard_list, null, true);
		
		//set coordinate
		TextView t=(TextView)rowView.findViewById(R.id.y_coordinate);
		t.setText(""+ coordinates[position]);
		
		//set chess images
		ImageView imageView1 = (ImageView) rowView.findViewById(R.id.field1);
		int resourcePosition = data[position][0];
		imageView1.setImageResource(resourcePosition);
		
		ImageView imageView2 = (ImageView) rowView.findViewById(R.id.field2);
		resourcePosition = data[position][1];
		imageView2.setImageResource(resourcePosition);
		
		ImageView imageView3 = (ImageView) rowView.findViewById(R.id.field3);
		resourcePosition = data[position][2];
		imageView3.setImageResource(resourcePosition);
		
		ImageView imageView4 = (ImageView) rowView.findViewById(R.id.field4);
		resourcePosition = data[position][3];
		imageView4.setImageResource(resourcePosition);
		
		ImageView imageView5 = (ImageView) rowView.findViewById(R.id.field5);
		resourcePosition = data[position][4];
		imageView5.setImageResource(resourcePosition);
		
		ImageView imageView6 = (ImageView) rowView.findViewById(R.id.field6);
		resourcePosition = data[position][5];
		imageView6.setImageResource(resourcePosition);
		
		ImageView imageView7 = (ImageView) rowView.findViewById(R.id.field7);
		resourcePosition = data[position][6];
		imageView7.setImageResource(resourcePosition);
		
		ImageView imageView8 = (ImageView) rowView.findViewById(R.id.field8);
		resourcePosition = data[position][7];
		imageView8.setImageResource(resourcePosition);
		
		return rowView;
	}
	

}

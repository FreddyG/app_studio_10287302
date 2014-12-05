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
	private final String[] web;
	private final int[][] data;
	
	public ChessList(Activity context,
	String[] web, int [][] data) {
		
		super(context,R.layout.chessboard_list , web);
		this.context = context;
		this.web = web;
		this.data = data;

	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.chessboard_list, null, true);
		
		TextView t=(TextView)rowView.findViewById(R.id.y_coordinate);
		t.setText(""+ web[position]);
		
		ImageView imageView1 = (ImageView) rowView.findViewById(R.id.field1);
		int d = data[position][0];
		imageView1.setImageResource(d);
		
		ImageView imageView2 = (ImageView) rowView.findViewById(R.id.field2);
		d = data[position][1];
		imageView2.setImageResource(d);
		
		ImageView imageView3 = (ImageView) rowView.findViewById(R.id.field3);
		d = data[position][2];
		imageView3.setImageResource(d);
		
		ImageView imageView4 = (ImageView) rowView.findViewById(R.id.field4);
		d = data[position][3];
		imageView4.setImageResource(d);
		
		ImageView imageView5 = (ImageView) rowView.findViewById(R.id.field5);
		d = data[position][4];
		imageView5.setImageResource(d);
		
		ImageView imageView6 = (ImageView) rowView.findViewById(R.id.field6);
		d = data[position][5];
		imageView6.setImageResource(d);
		
		ImageView imageView7 = (ImageView) rowView.findViewById(R.id.field7);
		d = data[position][6];
		imageView7.setImageResource(d);
		
		ImageView imageView8 = (ImageView) rowView.findViewById(R.id.field8);
		d = data[position][7];
		imageView8.setImageResource(d);
		
		
		return rowView;
	}
	

}

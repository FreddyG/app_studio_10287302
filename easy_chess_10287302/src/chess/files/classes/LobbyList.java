package chess.files.classes;

import chess.files.*;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class LobbyList extends ArrayAdapter<String>{
	
	
	private final Activity context;
	private final String[] users;
	
	public LobbyList(Activity context,String[] users) {
		
		super(context,R.layout.lobby_list , users);
		this.context = context;
		this.users = users;
		
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.lobby_list, null, true);
		
		TextView txtTitle = (TextView) rowView.findViewById(R.id.Naam);		
		txtTitle.setText(users[position]);

		return rowView;
	}
}
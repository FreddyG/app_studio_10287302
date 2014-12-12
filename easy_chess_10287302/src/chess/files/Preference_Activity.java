package chess.files;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Preference_Activity extends Activity {
	private RadioGroup chessPieces;
	private RadioButton chessPiecesChoise;
	TextView quote;
	SharedPreferences sharedpreferences;
	
	public static final String MyPREFERENCES = "MyPrefs" ;
	public static final String Pieces = "piecesKey"; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preference_);
		chessPieces = (RadioGroup) findViewById(R.id.radio_pieces);
		quote = (TextView) findViewById(R.id.quote);
		quote.setTypeface(null, Typeface.ITALIC);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.preference_, menu);
		
		return true;
	}
	public void back(View view) {
		int selectedId = chessPieces.getCheckedRadioButtonId();
		 
		// find the radiobutton by returned id
		sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		chessPiecesChoise = (RadioButton) findViewById(selectedId);
		Editor editor = sharedpreferences.edit();
		String choise = chessPiecesChoise.getText().toString();
		editor.putString(Pieces, choise);
		editor.commit(); 

		
		Preference_Activity.this.finish();
		
	}

}

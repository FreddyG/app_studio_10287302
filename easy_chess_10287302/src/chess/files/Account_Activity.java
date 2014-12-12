package chess.files;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


public class Account_Activity extends Activity {
	
	TextView name;
	TextView age;
	TextView rating;
	
	public static final String MyPREFERENCES = "MyPrefs" ;
	public static final String Name = "nameKey"; 
	public static final String Age = "ageKey"; 
	
	SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_);
		//set textviews
		name = (TextView) findViewById(R.id.name);
		age = (TextView) findViewById(R.id.age);
		rating = (TextView) findViewById(R.id.rating);
		
		sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		//if there were values added before, load them
		if( sharedpreferences.contains(Name)){
	       name.setText(sharedpreferences.getString(Name, ""));
	    }
		if( sharedpreferences.contains(Age)){
	       age.setText(sharedpreferences.getString(Age, ""));
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account_, menu);
		return true;
	}
	public void back(View view) {
		//get input
		String name_string  = name.getText().toString();
		String age_string  = age.getText().toString();
	    //save input   
	    Editor editor = sharedpreferences.edit();
	    editor.putString(Name, name_string);
	    editor.putString(Age, age_string);  

	    editor.commit(); 
		//finish activity
		Account_Activity.this.finish();
		
	}

}

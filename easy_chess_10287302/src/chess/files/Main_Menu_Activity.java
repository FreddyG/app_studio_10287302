package chess.files;







import android.os.Bundle;
import android.app.Activity;

import android.content.Intent;
//import android.content.SharedPreferences;

import android.view.Menu;
import android.view.View;



public class Main_Menu_Activity extends Activity {

    
	// Handle to a SharedPreferences editor
	//private SharedPreferences mPrefs;
    //private SharedPreferences.Editor mEditor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main__menu_);
		//mPrefs = getSharedPreferences(LocationUtils.SHARED_PREFERENCES, Context.MODE_PRIVATE);

	}
	public void connect(View view) {
		
		Intent Connect_Intent = new Intent(Main_Menu_Activity.this,Connect_Activity.class);
        Main_Menu_Activity.this.startActivity(Connect_Intent);
	 }
	public void preferences(View view) {
		Intent Preferences_Intent = new Intent(Main_Menu_Activity.this,Preference_Activity.class);
        Main_Menu_Activity.this.startActivity(Preferences_Intent);
	 }
	public void my_account(View view) {
		Intent Account_Intent = new Intent(Main_Menu_Activity.this,Account_Activity.class);
        Main_Menu_Activity.this.startActivity(Account_Intent);
	 }
	public void quit(View view) {
	     finish();
	 }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main__menu_, menu);
		return true;
	}
	

}

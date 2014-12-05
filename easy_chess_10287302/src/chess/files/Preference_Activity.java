package chess.files;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class Preference_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preference_);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.preference_, menu);
		return true;
	}
	public void back(View view) {
		
		Preference_Activity.this.finish();
		
	}

}

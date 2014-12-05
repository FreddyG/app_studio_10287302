package chess.files;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Bluetooth_Game_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluetooth__game_);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bluetooth__game_, menu);
		return true;
	}

}

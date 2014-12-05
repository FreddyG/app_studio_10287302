package chess.files;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Connect_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connect_);
	}
	public void local(View view) {
		
		Intent Local_Intent = new Intent(Connect_Activity.this,Local_Game_Activity.class);
		Connect_Activity.this.startActivity(Local_Intent);
	 }
	public void bluetooth(View view) {
		Intent Bluetooth_Intent = new Intent(Connect_Activity.this,Bluetooth_Activity.class);
		Connect_Activity.this.startActivity(Bluetooth_Intent);
	 }
	public void back(View view) {
		
		Connect_Activity.this.finish();
	 }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.connect_, menu);
		return true;
	}

}

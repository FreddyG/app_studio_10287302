package chess.files;

import chess.files.classes.*;



import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;



public class Bluetooth_Activity extends Activity {
    int started_Bluetooth = 0;
    String [] Users = null;   
    Bluetooth_thread BluetoothObject = null;
    Thread Bluetooth_Thread = null;
    ListView list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluetooth_);
		Log.d("StayC", "Is " + this);
		BluetoothObject = new Bluetooth_thread(this);
		Log.d("StayC", "wordt " + this);
		Bluetooth_Thread = new Thread(BluetoothObject);
		Bluetooth_Thread.start();
		started_Bluetooth = 1;
		
		
		  
    	
	}
	public void refresh(View view) {
		Users = BluetoothObject.getValue();
		
		if(Users!=null){
			CustomList adapter = new
					CustomList(Bluetooth_Activity.this, Users);
					list=(ListView)findViewById(R.id.list);
					list.setAdapter(adapter);
					
					list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
							boolean didMakeConnection = false;
							String user = BluetoothObject.Users.get(position);
							Toast.makeText(Bluetooth_Activity.this, "Making connection with " +user, Toast.LENGTH_SHORT).show();
							try{
								didMakeConnection = BluetoothObject.makeSocket(position);
								
							
							}finally {
								if(!didMakeConnection){
								Toast.makeText(Bluetooth_Activity.this, "Could not make connection with " +user, Toast.LENGTH_SHORT).show();
								}
								else{
									Toast.makeText(Bluetooth_Activity.this,"Connection made!",Toast.LENGTH_SHORT).show();
								}
							}
					    }
					});
			Log.d("StayC", "" + Users);
		}
	}
	public void back(View view) {
		if(started_Bluetooth==1){
			BluetoothObject.clear();	
			
		}
		//Bluetooth_Thread.stop();
		Bluetooth_Activity.this.finish();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bluetooth_, menu);
		return true;
	}
	

}

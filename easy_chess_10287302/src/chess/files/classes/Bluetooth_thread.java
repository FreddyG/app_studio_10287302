package chess.files.classes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Looper;
import android.os.ParcelUuid;
import android.util.Log;

public class Bluetooth_thread implements Runnable{
	Context mContext = null;
	public int done = 0;
	static final int SEND_REQUEST = 0;
	public Bluetooth_thread(Context context){
      this.mContext = context;
	}
	public String myOwnMac;
	private BluetoothAdapter btAdapter;
	public int counter = 0;
	public int MAX_DISCOVERY = 3;	
	public ArrayList<BluetoothDevice> foundDevices = new ArrayList<BluetoothDevice>();
	public ArrayList<String> Users = new ArrayList<String>();
	public BroadcastReceiver mReceiver = null;
	public OutputStream outputStream;
	public InputStream inStream;

@Override
public void run() {
	//super.onCreate(savedInstanceState);
	Looper.prepare();
	btAdapter = BluetoothAdapter.getDefaultAdapter();
	if (btAdapter == null) {
		Log.d("StayC","Geen bluetoothAdapter!\n");
	}       
	
	if (!btAdapter.isEnabled()) {
		btAdapter.enable();
	}

	if(btAdapter.getName()==null){
		String newName = "EasyChess!";
		btAdapter.setName(newName);
	}
	
	

	myOwnMac = btAdapter.getAddress();
	
	Users.add(btAdapter.getName());

	
	Set<BluetoothDevice> devices = btAdapter.getBondedDevices();
	if(devices.size()>0){
		for (BluetoothDevice device : devices) {
			 String addedString = device.getAddress();
			  Log.d("StayC", "Paired device found " + device);
			  if(addedString!=myOwnMac){
				  foundDevices.add(device);
				  Users.add(device.getName());
			  }
			  
		}
	}	
	//eigen mac
	Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
	discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
	mContext.startActivity(discoverableIntent);
	//startActivityForResult(discoverableIntent, REQUEST_DISCOVERABLE);
	final IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
	filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
	mContext.registerReceiver(mReceiver, filter);		
	//btService.start();		
	Log.d("StayC", "Start discovering");
	btAdapter.startDiscovery();
	mReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			final String action = intent.getAction();
			btAdapter = BluetoothAdapter.getDefaultAdapter();
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
			     // Get the BluetoothDevice object from the Intent
				Log.d("StayC","ACTION FOUND");
			     final BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			     final String name = device.getName();
			     Log.d("Bluetooth", "Found: " + name);
			     if(foundDevices!=null&&name!=null){
			       if(!foundDevices.contains(device)){
			    	  String test = device.getName();
			     	  String addedString = device.getAddress();
			     	  
			     	 String myOwnMac = btAdapter.getAddress();
			     	 if(addedString!=myOwnMac){
			     		 foundDevices.add(device);
			     	     Users.add(device.getName());
			     		 Log.d("StayC","Slaat naam device op. " +test+" "+addedString +" \n");
			     	 }
			       }
			     }
			     		            
		   }	        
		   else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {	        	
			   Log.d("Bluetooth", "Discovery finished");
			   counter++;
			   if (foundDevices.isEmpty()) {	
				   if(counter<MAX_DISCOVERY){
					   Log.d("StayC","Start discovery!.\n");
					   btAdapter.cancelDiscovery();
					   btAdapter.startDiscovery();
				   }
			   else{
 			
 			   done = 1;
 			   btAdapter.cancelDiscovery();
 		       }
 	          }
 	       else {        	
     	   //stuur foundDevices.
 	    	  Log.d("Bluetooth", "Komt hier");
               if(counter<MAX_DISCOVERY){
            	   btAdapter.cancelDiscovery();
            	   btAdapter.startDiscovery();
     	       }
     	       else{
     	    	   done = 1;
     	    	   btAdapter.cancelDiscovery();
     	       }
     	  }
        }
	  }
   };
	while(done==0){}
	clear();

}
	public void clear(){
		foundDevices.clear();
	
		Log.d("StayC","stop thread");
		//try {
		
			//mContext.unregisterReceiver(mReceiver);
			if(btAdapter.isDiscovering()){
				btAdapter.cancelDiscovery();
			}
			if(btAdapter.isEnabled()){
				btAdapter.disable();
			}
			//Thread.currentThread().join();
		/**} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		
		
	}
	public String [] getValue() {
		Set<BluetoothDevice> devices = btAdapter.getBondedDevices();
		if(devices.size()>0){
			for (BluetoothDevice device : devices) {
				  String addedString = device.getAddress();
				  Log.d("StayC", "Paired device found " + device);
				  if(addedString!=myOwnMac&&!foundDevices.contains(device)){
					  foundDevices.add(device);
					  Users.add(device.getName());
					  
				  }
				  
			}
		}
		String[] stringArray = Users.toArray(new String[Users.size()]);
		return stringArray;
	}
	public void makeSocket(int deviceNr){
		BluetoothDevice device= foundDevices.get(deviceNr-1);
		if(device.fetchUuidsWithSdp()){
			ParcelUuid[] uuids = device.getUuids();
	        BluetoothSocket socket;
			try {
				socket = device.createRfcommSocketToServiceRecord(uuids[0].getUuid());
				socket.connect();
				outputStream = socket.getOutputStream();
		        inStream = socket.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else{
			Log.d("StayC","can't find uuid");
		}
	}
	
}

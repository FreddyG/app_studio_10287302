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


public class Bluetooth_thread implements Runnable{
	
	//initialize variables
	
	public int done = 0;
	static final int SEND_REQUEST = 0;
	public String myOwnMac;
	public int MAX_DISCOVERY = 3; //try max 3 times to find other devices
	
	//initialize objects
	private BluetoothAdapter btAdapter;
	Context mContext = null;
	public int counter = 0;
	public ArrayList<BluetoothDevice> foundDevices = new ArrayList<BluetoothDevice>();
	public ArrayList<String> Users = new ArrayList<String>();
	public BroadcastReceiver mReceiver = null;
	public OutputStream outputStream;
	public InputStream inStream;
	
	public Bluetooth_thread(Context context){
      this.mContext = context;
	}
	
@Override
public void run() {
	
	Looper.prepare();
	btAdapter = BluetoothAdapter.getDefaultAdapter();
	
	//check bluetooth of device
	if (btAdapter == null) {
		return ;
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

	//get paired devices
	Set<BluetoothDevice> devices = btAdapter.getBondedDevices();
	if(devices.size()>0){
		for (BluetoothDevice device : devices) {
			 String addedString = device.getAddress();
			  if(addedString!=myOwnMac){
				  foundDevices.add(device);
				  Users.add(device.getName());
			  }
		}
	}	
	
	//make myself discoverable for other devices
	Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
	discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
	mContext.startActivity(discoverableIntent);
	
	final IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
	filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
	mContext.registerReceiver(mReceiver, filter);		

	//start searching for nearby bluetooth devices
	btAdapter.startDiscovery();
	mReceiver = new BroadcastReceiver() {
		
		
		public void onReceive(Context context, Intent intent) {
			
			final String action = intent.getAction();
			btAdapter = BluetoothAdapter.getDefaultAdapter();
			
			//when i receive a device
			if( BluetoothDevice.ACTION_FOUND.equals(action)) {
				
			     final BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			     final String name = device.getName();
			     
			     //don't want to add the same name twice
			     if( foundDevices!=null&&name!=null){
			       if( !foundDevices.contains(device)){
		
			     	 String addedString = device.getAddress();
			     	  
			     	 String myOwnMac = btAdapter.getAddress();
			     	 if(addedString!=myOwnMac){
			     		 foundDevices.add(device);
			     	     Users.add(device.getName());
			     		
			     	 }
			       }
			     }
			     		            
		   }
		   //if we are done searching, decide to try again
		   else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){	        	
			   counter++;
			   if (foundDevices.isEmpty()) {	
				   if(counter<MAX_DISCOVERY){
					   
					   btAdapter.cancelDiscovery();
					   btAdapter.startDiscovery();
					   
				   }
				   else{
	 			
		 			   done = 1;
		 			   btAdapter.cancelDiscovery();
		 			   
	 		       }
 	          }
 	       else {        	
 	    	 
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
   //wait till we are done
   while(done==0){}
   clear();

}
	public void clear(){
		
		//clean up
		foundDevices.clear();
	    if(btAdapter.isDiscovering()){
		  btAdapter.cancelDiscovery();
	    }
	    if(btAdapter.isEnabled()){
		  btAdapter.disable();
	    }		
		
	}
	
	public String [] getValue() {
		
		Set<BluetoothDevice> devices = btAdapter.getBondedDevices();
		
		if(devices.size()>0){
			
			for (BluetoothDevice device : devices) {
				
				  String addedString = device.getAddress();
				  
				  if(addedString!=myOwnMac&&!foundDevices.contains(device)){
					  
					  foundDevices.add(device);
					  Users.add(device.getName());
					  
				  }
				  
			}
		}
		
		String[] stringArray = Users.toArray(new String[Users.size()]);
		return stringArray;
		
	}
	public boolean makeSocket(int deviceNr){
		
		BluetoothDevice device= foundDevices.get(deviceNr-1);
		
		if(device.fetchUuidsWithSdp()){
			
			ParcelUuid[] uuids = device.getUuids();
	        BluetoothSocket socket;
	        
			try {
				
				socket = device.createRfcommSocketToServiceRecord(uuids[0].getUuid());
				socket.connect();
				outputStream = socket.getOutputStream();
		        inStream = socket.getInputStream();
		        return true;
		        
			} catch (IOException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
				
			}

		}
		else{

			return false;
			
		}
	}
	
}

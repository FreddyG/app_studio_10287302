package chess.files.classes;


import chess.files.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;


public class Pause{
	Context ExitContext = null;
	public int choise = 0;
	
	public Pause(Context context){
		
		this.ExitContext = context;
		final Activity PauseActivity = (context instanceof Activity)? (Activity) context:null;

		final Dialog dialog = new Dialog(PauseActivity);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.popup_pause);
		
		
	    
		// set the custom dialog components - text, image and button

		dialog.show();
		Button ContinueButton = (Button) dialog.findViewById(R.id.Continue);
		// if button is clicked, close the custom dialog
		ContinueButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				
			}
		});
		Button newGameButton = (Button) dialog.findViewById(R.id.NewGame);
		// if button is clicked, close the custom dialog
		newGameButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				choise = 1;
				dialog.dismiss();
				
			}
		});
		Button SaveButton = (Button) dialog.findViewById(R.id.Save);
		// if button is clicked, close the custom dialog
		SaveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				choise = 2;
				dialog.dismiss();
				
				
			}
		});
		Button ExitButton = (Button) dialog.findViewById(R.id.Exit);
		// if button is clicked, close the custom dialog
		ExitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				PauseActivity.finish();
				dialog.dismiss();
				
			}
		});
		

	}

}
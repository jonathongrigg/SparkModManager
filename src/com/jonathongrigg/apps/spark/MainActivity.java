/*
 * Copyright (C) 2011 Jonathon Grigg <jonathongrigg@gmail.com>
 *
 * Licensed under the Open Software License version 3.0
 * A copy of the license may be obtained from http://www.opensource.org/licenses/osl-3.0
 */

package com.jonathongrigg.apps.spark;

import com.jonathongrigg.apps.spark.backend.Commands;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends FragmentActivity {

    private static final String TAG = "Spark Mod Manager, MainActivity";
    public Commands commands = new Commands(this);
    private Button applyButton;
    private CheckBox touchkeyDimmerCheckBox;
    private CheckBox touchWakeCheckBox;
    private CheckBox screenDimmerCheckBox;
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_items, menu);
        return true;
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		applyButton = (Button) findViewById(R.id.button1);
		touchkeyDimmerCheckBox = (CheckBox) findViewById(R.id.checkBox1);
		touchWakeCheckBox = (CheckBox) findViewById(R.id.checkBox2);
		screenDimmerCheckBox = (CheckBox) findViewById(R.id.checkBox3);
		
		applyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "Applying settings");
                
                commands.toggleTouchkeyDimmer(touchkeyDimmerCheckBox.isChecked());
                commands.toggleTouchWake(touchWakeCheckBox.isChecked());
                commands.toggleScreenDimmer(screenDimmerCheckBox.isChecked());
            }
        });	
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle Actionbar menu item selection
	    switch (item.getItemId()) {
	    case R.id.actionbar_about:
	        showAboutDialog();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	public void showAboutDialog() {
	    final AlertDialog aboutDialog = new AlertDialog.Builder(this).create();
        aboutDialog.setTitle(this.getText(R.string.dialog_title));
        aboutDialog.setMessage(this.getText(R.string.dialog_text));
        aboutDialog.setIcon(R.drawable.ic_menu_info_details);
        
        aboutDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                aboutDialog.dismiss();                       
          } }); 
        
        aboutDialog.show();
	}
}
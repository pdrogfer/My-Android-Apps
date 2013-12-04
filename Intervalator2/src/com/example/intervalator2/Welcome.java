package com.example.intervalator2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Welcome extends Activity implements OnClickListener{

	private Button playBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		playBtn = (Button) findViewById(R.id.btnStart);
		
		playBtn.setOnClickListener(this);
		
		// TODO add button Start
	}
	@Override
	public void onClick(View v) {
		// start the machine
		if (v.getId() == R.id.btnStart) {
			startMachine();
		}
		
	}
	private void startMachine() {
		// open the calculator activity
		Intent machineIntent = new Intent(this, MainActivity.class);
		this.startActivity(machineIntent);
	}
}

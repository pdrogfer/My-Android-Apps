package com.example.youdothemath;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

// TODO A–adir sonidos a los clicks y respuestas corectas e incorrectas
public class MainActivity extends Activity implements OnClickListener {

	private TextView intro;
	private Button playBtn, helpBtn, highBtn;
	private Typeface myFont;
	private String[] levelNames = {"Easy", "Medium","Hard"};
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// all this to change "intro" font
		intro = (TextView) findViewById(R.id.intro);
		playBtn = (Button) findViewById(R.id.play_btn);
		helpBtn = (Button) findViewById(R.id.help_btn);
		highBtn = (Button) findViewById(R.id.high_btn);
		Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
		intro.setTypeface(myFont);
		playBtn.setTypeface(myFont);
		helpBtn.setTypeface(myFont);
		highBtn.setTypeface(myFont);

		// listen to clicks
		playBtn.setOnClickListener(this);
		helpBtn.setOnClickListener(this);
		highBtn.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	@Override
	public void onClick(View v) {
		// respond to clicks
		if (v.getId() == R.id.play_btn) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this); 

		}
		if (v.getId() == R.id.help_btn) {

		}
		if (v.getId() == R.id.high_btn) {
		}

	}

}

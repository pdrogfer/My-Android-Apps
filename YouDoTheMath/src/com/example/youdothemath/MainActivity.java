package com.example.youdothemath;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView intro;
	Button play, help;
	Typeface myFont;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// all this to change "intro" font 
		intro = (TextView) findViewById(R.id.intro);
		play = (Button) findViewById(R.id.play_btn);
		help = (Button) findViewById(R.id.help_btn);
		Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
		intro.setTypeface(myFont);
		play.setTypeface(myFont);
		help.setTypeface(myFont);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	
		
	}

}

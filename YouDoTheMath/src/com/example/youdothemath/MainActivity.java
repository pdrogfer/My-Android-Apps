package com.example.youdothemath;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

// TODO A–adir sonidos a los clicks y respuestas correctas e incorrectas
public class MainActivity extends Activity implements OnClickListener {

	private TextView intro;
	private Button playBtn, helpBtn, highBtn;
	private Typeface myFont;
	private String[] levelNames = { "Easy", "Medium", "Hard" };

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
			builder.setTitle("Choose a level").setSingleChoiceItems(levelNames, 0,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							// start gameplay
							startPlay(which);

						}
					});
			AlertDialog ad = builder.create();
			ad.show();
		} else if (v.getId() == R.id.help_btn) {
			// the Intent launch is delegated to startHelp() function
			startHelp();

		} else if (v.getId() == R.id.high_btn) {
			// as above
			startHigh();
		}

	}

	private void startPlay(int chosenLevel) {
		// start gameplay
		Intent playIntent = new Intent(this, PlayGame.class);
		playIntent.putExtra("level", chosenLevel);
		this.startActivity(playIntent);
	}

	private void startHelp() {
		// show the help screen
		Intent helpIntent = new Intent(this, HowToPlay.class);
		this.startActivity(helpIntent);
	}
	
	private void startHigh() {
		// show the high scores view
		Intent highIntent = new Intent(this, HighScores.class);
		this.startActivity(highIntent);
	}

}

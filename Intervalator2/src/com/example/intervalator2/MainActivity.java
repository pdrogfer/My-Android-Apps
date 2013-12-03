package com.example.intervalator2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private Button noteStart, noteEnd, calculate, reset;
	private String[] notes = { "C/Do", "D/Re", "E/Mi", "F/Fa", "G/Sol", "A/La", "B/Si" };
	private int indNoteStart, indNoteEnd;
	private String interval;
	private String[] intervals = { "Unison", "Second", "Third", "Fourth", "Fifth", "Sixth",
			"Seventh" };
	private TextView result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// link variables to widgets
		noteStart = (Button) findViewById(R.id.btnNote1);
		noteEnd = (Button) findViewById(R.id.btnNote2);
		calculate = (Button) findViewById(R.id.btnGo);
		reset = (Button) findViewById(R.id.btnReset);
		result = (TextView) findViewById(R.id.output);

		// listen to clicks
		noteStart.setOnClickListener(this);
		noteEnd.setOnClickListener(this);
		calculate.setOnClickListener(this);
		reset.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		final int btnID = v.getId();
		if ((v.getId() == R.id.btnNote1) || (v.getId() == R.id.btnNote2)) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.notesTitle).setSingleChoiceItems(notes, 0,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// close and store note name TODO
							dialog.dismiss();
							if (btnID == R.id.btnNote1) {
								noteStart.setText(notes[which]);
								indNoteStart = which;
							} else {
								noteEnd.setText(notes[which]);
								indNoteEnd = which;
							}
							
						}
					});
			AlertDialog ad = builder.create();
			ad.show();
		}
		else if (v.getId() == R.id.btnGo) {
			calculate();
		}
		else if (v.getId() == R.id.btnReset) {
			noteStart.setText(R.string.selNoteBtn);
			noteEnd.setText(R.string.selNoteBtn);
			result.setText(R.string.hintOutput);
		}

	}

	protected void calculate() {
		// TODO Calculate interval
		interval = intervals[indNoteEnd - indNoteStart];
		result.setText("Your interval is a " + interval);
	}
}

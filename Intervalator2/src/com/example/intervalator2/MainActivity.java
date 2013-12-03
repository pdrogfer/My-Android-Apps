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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener, OnCheckedChangeListener {

	private Button noteStart, noteEnd, calculate, reset;
	private String[] notes = { "C/Do", "D/Re", "E/Mi", "F/Fa", "G/Sol", "A/La", "B/Si" };
	private int indNoteStart, indNoteEnd, intervalNum;
	private int dir = 0;
	private String interval;
	private String[] intervals = { "Unison", "Second", "Third", "Fourth", "Fifth", "Sixth",
			"Seventh" };
	private int[] halfSteps = { 0, 2, 4, 5, 7, 9, 11, 12 };
	private int hsDist = 0, accDev = 0; // distance in half-steps, deviation caused by accidentals
	private TextView result;
	private RadioGroup direction, acc1, acc2;
	private RadioButton up, down;

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
		direction = (RadioGroup) findViewById(R.id.rGrDirection);
		acc1 = (RadioGroup) findViewById(R.id.rGrAltNote1);
		acc2 = (RadioGroup) findViewById(R.id.rGrAltNote2);
		// listen to clicks
		noteStart.setOnClickListener(this);
		noteEnd.setOnClickListener(this);
		calculate.setOnClickListener(this);
		reset.setOnClickListener(this);
		direction.setOnCheckedChangeListener(this);
		acc1.setOnCheckedChangeListener(this);
		acc2.setOnCheckedChangeListener(this);
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
		} else if (v.getId() == R.id.btnGo) {
			calculate();
		} else if (v.getId() == R.id.btnReset) {
			noteStart.setText(R.string.selNoteBtn);
			noteEnd.setText(R.string.selNoteBtn);
			result.setText(R.string.hintOutput);
			interval = null;
			hsDist = 0; 
			accDev = 0;
		}
	}

	protected void calculate() {
		// Calculate interval
		intervalNum = indNoteEnd - indNoteStart;
		hsDist = (halfSteps[indNoteEnd] - halfSteps[indNoteStart]) + accDev;
		// Check direction and invert in case

		if (dir == 1) {
			intervalNum = 7 - intervalNum;
			hsDist = 12 - hsDist;
		}
		interval = intervals[intervalNum];
		result.setText("Your interval is a " + interval + hsDist);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// Manage clicks in all radio buttons
		if (group.getId() == R.id.rGrAltNote1) {
			switch (checkedId) {
			case R.id.note1Sharp:
				accDev -= 1;
				break;
			case R.id.note1Natural:
				break;
			case R.id.note1Flat:
				accDev += 1;
				break;
			}
		} else if (group.getId() == R.id.rGrAltNote2) {
			switch (checkedId) {
			case R.id.note2Sharp:
				accDev += 1;
				break;
			case R.id.note2Natural:
				break;
			case R.id.note2Flat:
				accDev -= 1;
				break;
			}
		} else if (group.getId() == R.id.rGrDirection) {
			switch (checkedId) {
			case R.id.btnDirUp:
				dir = 0;
				break;
			case R.id.btnDirDown:
				dir = 1;
				break;
			}
		}

	}
}

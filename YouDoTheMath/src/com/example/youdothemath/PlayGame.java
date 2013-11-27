package com.example.youdothemath;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayGame extends Activity implements OnClickListener {

	private int level = 0, answer = 0, operator = 0, operand1 = 0, operand2 = 0;
	private final int ADD_OPERATOR = 0, SUBSTRACT_OPERATOR = 1, MULTIPLY_OPERATOR = 2,
			DIVIDE_OPERATOR = 3;
	private String[] operators = { "+", "-", "*", "/" };
	private Random random;
	private TextView question, answerTxt, scoreTxt;
	private ImageView response;
	private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, enterBtn, clearBtn;
	// min and max values for the operands in all 4 operators, in each one of
	// the 3 levels
	private int[][] levelMin = { { 1, 11, 21 }, { 1, 5, 10 }, { 2, 5, 10 }, { 2, 3, 5 } };
	private int[][] levelMax = { { 10, 25, 50 }, { 10, 20, 30 }, { 5, 10, 15 }, { 10, 50, 100 } };
	private SharedPreferences gamePrefs;
	public static final String GAME_PREFS = "ArithmeticFile";
	private int enteredNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playgame);
		gamePrefs = getSharedPreferences(GAME_PREFS, 0);

		question = (TextView) findViewById(R.id.question);
		answerTxt = (TextView) findViewById(R.id.answer);
		scoreTxt = (TextView) findViewById(R.id.score);
		response = (ImageView) findViewById(R.id.response);
		response.setVisibility(View.INVISIBLE);

		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		btn5 = (Button) findViewById(R.id.btn5);
		btn6 = (Button) findViewById(R.id.btn6);
		btn7 = (Button) findViewById(R.id.btn7);
		btn8 = (Button) findViewById(R.id.btn8);
		btn9 = (Button) findViewById(R.id.btn9);
		btn0 = (Button) findViewById(R.id.btn0);
		enterBtn = (Button) findViewById(R.id.enter);
		clearBtn = (Button) findViewById(R.id.clear);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btn0.setOnClickListener(this);
		enterBtn.setOnClickListener(this);
		clearBtn.setOnClickListener(this);

		// check if there is an interrupted game saved
		if (savedInstanceState != null) {
			// restore state
			level = savedInstanceState.getInt("level");
			int exScore = savedInstanceState.getInt("score");
			scoreTxt.setText("Score: " + exScore);
		} else {
			Bundle extras = getIntent().getExtras();
			if (extras != null) {
				int passedLevel = extras.getInt("level", -1);
				if (passedLevel >= 0) {
					level = passedLevel;
				}
			}
		}
		random = new Random();

		chooseQuestion();

	}

	@Override
	protected void onDestroy() {
		// in case the Activity is destroyed, update high score list first
		setHighScore();
		super.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		// Store data in case Activity is destroyed
		int exScore = getScore();
		savedInstanceState.putInt("score", exScore);
		savedInstanceState.putInt("level", level);
		super.onSaveInstanceState(savedInstanceState);
	}

	private void chooseQuestion() {
		// get a question
		answerTxt.setText("= ?");
		operator = random.nextInt(operators.length);
		operand1 = getOperand();
		operand2 = getOperand();
		if (operator == SUBSTRACT_OPERATOR) {
			// to ensure not to get neg values in substr operation, re-generate
			// until op2<op1
			while (operand2 > operand1) {
				operand1 = getOperand();
				operand2 = getOperand();
			}
		} else if (operator == DIVIDE_OPERATOR) {
			// try (operand1 % operand2) > 0, seems better
			while ((((double) operand1 / (double) operand2) % 1 > 0) || (operand1 == operand2)) {
				operand1 = getOperand();
				operand2 = getOperand();
			}
		}
		switch (operator) {
		case ADD_OPERATOR:
			answer = operand1 + operand2;
			break;
		case SUBSTRACT_OPERATOR:
			answer = operand1 - operand2;
			break;
		case MULTIPLY_OPERATOR:
			answer = operand1 * operand2;
			break;
		case DIVIDE_OPERATOR:
			answer = operand1 / operand2;
		default:
			break;
		}
		// display the question
		question.setText(operand1 + " " + operators[operator] + " " + operand2);
	}

	private int getOperand() {
		// generate random operand number inside limits levelMin-levelMax
		return random.nextInt(levelMax[operator][level] - levelMin[operator][level] + 1)
				+ levelMin[operator][level];
	}

	@Override
	public void onClick(View v) {
		// respond to clicks
		if (v.getId() == R.id.enter) {
			// case enter
			String answerContent = answerTxt.getText().toString();
			if (!answerContent.endsWith("?")) {
				// checks that at least one number has been entered
				int enteredAnswer = Integer.parseInt(answerContent.substring(2));
				// stores the part of answerContent corresponding to the numeric
				// answer
				int exScore = getScore();
				if (enteredAnswer == answer) {
					// we got correct answer
					scoreTxt.setText("Score: " + (exScore + 1));
					response.setImageResource(R.drawable.yes);
					response.setVisibility(View.VISIBLE);
				} else {
					// we got wrong answer
					setHighScore();
					scoreTxt.setText("Score: 0");
					response.setImageResource(R.drawable.no);
					response.setVisibility(View.VISIBLE);
				}
				chooseQuestion();
			}

		}
		else if (v.getId() == R.id.clear) {
			// case clear
			answerTxt.setText("= ?");
		}
		// case numbers
		else {
			// don't display the tick or cross yet
			response.setVisibility(View.INVISIBLE);
			// store in a variable the tag of the clicked numeric button
			
			
			@SuppressWarnings("unused")
			String e1 = v.getTag().toString();
			
			int enteredNum;
			enteredNum = Integer.parseInt(v.getTag().toString());
			
			// if user is entering the 1st digit
			if (answerTxt.getText().toString().endsWith("?")) {
				answerTxt.setText("= " + enteredNum);
			}
			// if 2nd digit or more
			else {
				answerTxt.append("" + enteredNum);
			}

		}

	}

	private int getScore() {
		// returns the present score
		String scoreStr = scoreTxt.getText().toString();
		return Integer.parseInt(scoreStr.substring(scoreStr.lastIndexOf(" ") + 1));
	}

	private void setHighScore() {
		// set high score
		int exScore = getScore();
		if (exScore > 0) {
			// check valid value for exScore
			SharedPreferences.Editor scoreEdit = gamePrefs.edit();
			// create an editor object to write the scores
			DateFormat dateForm = new SimpleDateFormat("dd MMMM yyyy");
			String dateOutput = dateForm.format(new Date());
			String scores = gamePrefs.getString("highScores", "");
			if (scores.length() > 0) {
				// there are existing scores
				List<Score> scoreStrings = new ArrayList<Score>();
				String[] exScores = scores.split("\\|");
				for (String eSc : exScores) {
					String[] parts = eSc.split(" - ");
					scoreStrings.add(new Score(parts[0], Integer.parseInt(parts[1])));
				}
				Score newScore = new Score(dateOutput, exScore);
				scoreStrings.add(newScore);
				Collections.sort(scoreStrings);

				StringBuilder scoreBuild = new StringBuilder("");
				for (int s = 0; s < scoreStrings.size(); s++) {
					if (s > 10)
						break; // we only want 10
					if (s > 0) {
						scoreBuild.append("|"); // pipe separate the score
												// strings
						scoreBuild.append(scoreStrings.get(s).getScoreText());
					}
				}
				// write to prefs
				scoreEdit.putString("HighScores", scoreBuild.toString());
				scoreEdit.commit();
			} else {
				// no existing scores
				scoreEdit.putString("highScores", "" + dateOutput + " - " + exScore);
				scoreEdit.commit();
			}
		}

	}
}

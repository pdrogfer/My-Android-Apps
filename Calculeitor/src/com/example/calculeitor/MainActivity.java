package com.example.calculeitor;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
public class MainActivity extends Activity implements OnClickListener {

	Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0;
	Button btn_add, btn_rest, btn_mult, btn_divide, btn_clear, btn_result;
	TextView result;
	String display = "";
	String operator = "";
	String tempVal_1 = "";
	String tempVal_2 = "";
	int operand_1, operand_2, final_res;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_1 = (Button) findViewById(R.id.btn_1);
		btn_2 = (Button) findViewById(R.id.btn_2);
		btn_3 = (Button) findViewById(R.id.btn_3);
		btn_4 = (Button) findViewById(R.id.btn_4);
		btn_5 = (Button) findViewById(R.id.btn_5);
		btn_6 = (Button) findViewById(R.id.btn_6);
		btn_7 = (Button) findViewById(R.id.btn_7);
		btn_8 = (Button) findViewById(R.id.btn_8);
		btn_9 = (Button) findViewById(R.id.btn_9);
		btn_0 = (Button) findViewById(R.id.btn_0);
		btn_add = (Button) findViewById(R.id.btn_add);
		btn_rest = (Button) findViewById(R.id.btn_rest);
		btn_mult = (Button) findViewById(R.id.btn_mult);
		btn_divide = (Button) findViewById(R.id.btn_divide);
		btn_clear = (Button) findViewById(R.id.btn_clear);
		btn_result = (Button) findViewById(R.id.btn_result);
		result = (TextView) findViewById(R.id.tv_result);

		btn_1.setOnClickListener(this);
		btn_2.setOnClickListener(this);
		btn_3.setOnClickListener(this);
		btn_4.setOnClickListener(this);
		btn_5.setOnClickListener(this);
		btn_6.setOnClickListener(this);
		btn_7.setOnClickListener(this);
		btn_8.setOnClickListener(this);
		btn_9.setOnClickListener(this);
		btn_0.setOnClickListener(this);
		btn_add.setOnClickListener(this);
		btn_rest.setOnClickListener(this);
		btn_mult.setOnClickListener(this);
		btn_divide.setOnClickListener(this);
		btn_clear.setOnClickListener(this);
		btn_result.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	@Override
	public void onClick(View v) {
		// TODO key entry
		if (v.getId() == R.id.btn_1) {
			if (operator == "")
				tempVal_1 += "1";
			else
				tempVal_2 += "1";
		}
		if (v.getId() == R.id.btn_2) {
			if (operator == "")
				tempVal_1 += "2";
			else
				tempVal_2 += "2";
		}
		if (v.getId() == R.id.btn_3) {
			if (operator == "")
				tempVal_1 += "3";
			else
				tempVal_2 += "3";
		}
		if (v.getId() == R.id.btn_4) {
			if (operator == "")
				tempVal_1 += "4";
			else
				tempVal_2 += "4";
		}
		if (v.getId() == R.id.btn_5) {
			if (operator == "")
				tempVal_1 += "5";
			else
				tempVal_2 += "5";
		}
		if (v.getId() == R.id.btn_6) {
			if (operator == "")
				tempVal_1 += "6";
			else
				tempVal_2 += "6";
		}
		if (v.getId() == R.id.btn_7) {
			if (operator == "")
				tempVal_1 += "7";
			else
				tempVal_2 += "7";
		}
		if (v.getId() == R.id.btn_8) {
			if (operator == "")
				tempVal_1 += "8";
			else
				tempVal_2 += "8";
		}
		if (v.getId() == R.id.btn_9) {
			if (operator == "")
				tempVal_1 += "9";
			else
				tempVal_2 += "9";
		}
		if (v.getId() == R.id.btn_0) {
			if (operator == "")
				tempVal_1 += "0";
			else
				tempVal_2 += "0";
		}
		// now the operators
		if (v.getId() == R.id.btn_add) {
			operator = " + ";
		}
		if (v.getId() == R.id.btn_rest) {
			operator = " - ";
		}
		if (v.getId() == R.id.btn_mult) {
			operator = " * ";
		}
		if (v.getId() == R.id.btn_divide) {
			operator = " / ";
		}

		// to clear
		if (v.getId() == R.id.btn_clear) {
			tempVal_1 = "";
			tempVal_2 = "";
			operator = "";
		}
		// compute
		if (v.getId() == R.id.btn_result) {
			operand_1 = Integer.parseInt(tempVal_1);
			operand_2 = Integer.parseInt(tempVal_2);
			if (operator == " + ") {
				final_res = operand_1 + operand_2;
			}
			if (operator == " - ") {
				final_res = operand_1 - operand_2;
			}
			if (operator == " * ") {
				final_res = operand_1 * operand_2;
			}
			if (operator == " / ") {
				final_res = operand_1 / operand_2;
			}
			result.setText(String.valueOf(final_res));
		} else {
			result.setText(tempVal_1 + operator + tempVal_2);
		}
	}
}

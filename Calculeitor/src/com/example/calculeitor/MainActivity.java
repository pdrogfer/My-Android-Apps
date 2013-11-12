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

	Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9,
			btn_0;
	Button btn_add, btn_rest, btn_mult, btn_divide;
	TextView textView2;

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
		textView2 = (TextView) findViewById(R.id.textView2);

		btn_1.setOnClickListener(num_1);
		btn_2.setOnClickListener(num_2);
		btn_3.setOnClickListener(num_3);
		btn_4.setOnClickListener(num_4);
		btn_5.setOnClickListener(num_5);
		btn_6.setOnClickListener(num_6);
		btn_7.setOnClickListener(num_7);
		btn_8.setOnClickListener(num_8);
		btn_9.setOnClickListener(num_9);
		btn_0.setOnClickListener(num_0);
		btn_add.setOnClickListener(add);
		btn_rest.setOnClickListener(rest);
		btn_mult.setOnClickListener(mult);
		btn_divide.setOnClickListener(divide);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private OnClickListener num_1 = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			textView2.setText("1");
		}
	};
	private OnClickListener num_2 = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			textView2.setText("2");
		}
	};
	private OnClickListener num_3 = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			textView2.setText("3");
		}
	};
	private OnClickListener num_4 = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			textView2.setText("4");
		}
	};
	private OnClickListener num_5 = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			textView2.setText("5");
		}
	};
	private OnClickListener num_6 = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			textView2.setText("6");
		}
	};
	private OnClickListener num_7 = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			textView2.setText("7");
		}
	};
	private OnClickListener num_8 = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			textView2.setText("8");
		}
	};
	private OnClickListener num_9 = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			textView2.setText("9");
		}
	};
	private OnClickListener num_0 = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			textView2.setText("0");
		}
	};
	private OnClickListener add = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			textView2.setText(" + ");
		}
	};

	private OnClickListener rest = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			textView2.setText(" - ");
		}
	};
	private OnClickListener mult = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			textView2.setText(" x ");
		}
	};
	private OnClickListener divide = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			textView2.setText(" / ");
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

}

package com.example.drawingfun;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.UUID;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	// create an instance of class DrawingView called drawView
	private DrawingView drawView;
	private ImageButton currPaint, drawBtn, eraseBtn, newBtn, saveBtn;
	private float smallBrush, mediumBrush, largeBrush;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		drawView = (DrawingView) findViewById(R.id.drawing);
		drawBtn = (ImageButton) findViewById(R.id.draw_btn);
		drawBtn.setOnClickListener(this);
		eraseBtn = (ImageButton) findViewById(R.id.erase_btn);
		eraseBtn.setOnClickListener(this);
		newBtn = (ImageButton) findViewById(R.id.new_btn);
		newBtn.setOnClickListener(this);
		saveBtn = (ImageButton) findViewById(R.id.save_btn);
		saveBtn.setOnClickListener(this);

		smallBrush = getResources().getInteger(R.integer.small_size);
		mediumBrush = getResources().getInteger(R.integer.medium_size);
		largeBrush = getResources().getInteger(R.integer.large_size);
		// set default brush size
		drawView.setBrushSize(mediumBrush);

		LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paint_colors);
		currPaint = (ImageButton) paintLayout.getChildAt(0);
		currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void paintClicked(View view) {
		// if last action was erase, activate drawing again and restore brush
		// size
		drawView.setErase(false);
		drawView.setBrushSize(drawView.getLastBrushSize());
		// use chosen color
		if (view != currPaint) {
			// update color
			ImageButton imgView = (ImageButton) view;
			String color = view.getTag().toString();
			drawView.setColor(color);
			// update the UI to reflect the new chosen paint and set the old one
			// back to normal
			imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
			currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
			currPaint = (ImageButton) view;

		}
	}

	@Override
	public void onClick(View view) {
		// responds to clicks
		if (view.getId() == R.id.draw_btn) {
			// draw_button clicked
			final Dialog brushDialog = new Dialog(this);
			brushDialog.setTitle(R.string.brush_size);
			brushDialog.setContentView(R.layout.brush_chooser);

			ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);
			smallBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					drawView.setBrushSize(smallBrush);
					drawView.setLastBrushSize(smallBrush);
					drawView.setErase(false); // in case it's active because of
												// last action
					brushDialog.dismiss();
				}
			});
			ImageButton mediumBtn = (ImageButton) brushDialog.findViewById(R.id.medium_brush);
			mediumBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					drawView.setBrushSize(mediumBrush);
					drawView.setLastBrushSize(mediumBrush);
					drawView.setErase(false);
					brushDialog.dismiss();
				}
			});
			ImageButton largeBtn = (ImageButton) brushDialog.findViewById(R.id.large_brush);
			largeBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					drawView.setBrushSize(largeBrush);
					drawView.setLastBrushSize(largeBrush);
					drawView.setErase(false);
					brushDialog.dismiss();
				}
			});
			brushDialog.show();
		} else if (view.getId() == R.id.erase_btn) {
			// switch to erase mode, and choose size
			final Dialog brushDialog = new Dialog(this);
			brushDialog.setTitle(R.string.eraser_size);
			brushDialog.setContentView(R.layout.brush_chooser);

			ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);
			smallBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					drawView.setErase(true);
					drawView.setLastBrushSize(smallBrush);
					brushDialog.dismiss();
				}
			});
			ImageButton mediumBtn = (ImageButton) brushDialog.findViewById(R.id.medium_brush);
			mediumBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					drawView.setErase(true);
					drawView.setLastBrushSize(mediumBrush);
					brushDialog.dismiss();
				}
			});
			ImageButton largeBtn = (ImageButton) brushDialog.findViewById(R.id.large_brush);
			largeBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					drawView.setErase(true);
					drawView.setLastBrushSize(largeBrush);
					brushDialog.dismiss();
				}
			});
			brushDialog.show();
		} else if (view.getId() == R.id.new_btn) {
			// delete all and start a new drawing
			// first, the warning
			AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
			newDialog.setTitle(R.string.new_drawing);
			newDialog.setMessage(R.string.warning_new_drawing);
			newDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					drawView.startNew();
					dialog.dismiss();
				}
			});
			newDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			newDialog.show();
		} else if (view.getId() == R.id.save_btn) {
			// save drawing
			AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
			saveDialog.setTitle(R.string.save_drawing);
			saveDialog.setMessage(R.string.save_confirm);
			saveDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// save drawing
					drawView.setDrawingCacheEnabled(true);
					String imgSaved = MediaStore.Images.Media.insertImage(getContentResolver(),
							drawView.getDrawingCache(), UUID.randomUUID().toString() + ".png",
							"drawing");
					if (imgSaved != null) {
						Toast savedToast = Toast.makeText(getApplicationContext(),
								R.string.drawing_saved, Toast.LENGTH_SHORT);
						savedToast.show();
					} else {
						Toast unsavedToast = Toast.makeText(getApplicationContext(),
								R.string.drawing_not_saved, Toast.LENGTH_SHORT);
						unsavedToast.show();
					}
					drawView.destroyDrawingCache();
				}
			});
			saveDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// don't save
					dialog.cancel();
				}
			});
			saveDialog.show();
		}
	}
}

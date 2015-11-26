package cat.flx.calculadoracientifica;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import cat.flx.calculadoracientifica.Teclat.Operation;

public class MainActivity extends AppCompatActivity implements OnClickListener {
	GridLayout grid, grid2;
	TextView scr;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		grid = (GridLayout) findViewById(R.id.grid);
		grid2 = (GridLayout) findViewById(R.id.grid2);
		scr = (TextView) findViewById(R.id.textView1);
		clear();
		loadKeys(grid, Teclat.NORMAL, 4);
	}
	
	private void loadKeys(final GridLayout grid, final TeclaSpec[] teclas, final int columns) {
		grid.removeAllViews();
		ViewTreeObserver vto = grid.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override public void onGlobalLayout() {
				grid.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				int total = teclas.length;
				int rows = (int)Math.ceil((double)total / columns);
				int btnWidth = (grid.getWidth() - grid.getPaddingLeft() - grid.getPaddingRight()) / columns;
				int btnHeight = (grid.getHeight() - grid.getPaddingTop() - grid.getPaddingBottom()) / rows;
				grid.setColumnCount(columns);
				grid.setRowCount(rows);
				for(int i = 0; i < total; i++) {
					TeclaSpec ts = teclas[i];
					Button tv = new Button(MainActivity.this);
					tv.setTextSize(btnHeight / 5);
					tv.setPadding(0, 0, 0, 0);
					tv.setTag(ts);
					tv.setOnClickListener(MainActivity.this);
					tv.setText(ts.text);
					tv.setGravity(Gravity.CENTER);
					tv.setBackgroundResource(ts.res);
					GridLayout.LayoutParams param =new GridLayout.LayoutParams();
					param.leftMargin = param.rightMargin = 4;
					param.topMargin = param.bottomMargin = 4;
					param.height = btnHeight * ts.rowspan - 8;
					param.width = btnWidth * ts.colspan - 8;
					param.setGravity(Gravity.CENTER);
					param.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, ts.colspan );
					param.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, ts.rowspan );
					tv.setLayoutParams(param);
					grid.addView(tv);
				}
			}
		});
	}

	@Override public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_scientific) {
			if (grid2.getVisibility() == View.VISIBLE) { 
				grid2.setVisibility(View.GONE);
				loadKeys(grid, Teclat.NORMAL, 4);
			}
			else {
				loadKeys(grid2, Teclat.CIENTIFIC, 5);
				grid2.setVisibility(View.VISIBLE);
				grid2.setTag(0);
				loadKeys(grid, Teclat.NORMAL, 4);
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override public void onClick(View v) {
		TeclaSpec ts = (TeclaSpec) v.getTag();
		Operation op = ts.op;
		switch (op) {
		case CLR: clear(); break;
		case DIGIT: digit(ts.text); break;
		case DOT: dot(); break;
		case SIGN: sign(); break;
		case PI: pi(); break;
		case SQRT2: sqrt2(); break;

		// FALTA CODIFICAR LA RESTA DE FUNCIONS
		default:
			Log.i("flx", "Operation: " + op);
			break;
		}
	}
	
	private boolean inNumber;
	
	public void clear() {
		scr.setText("0");
		inNumber = false;
	}
	
	public void digit(String digit) {
		String txt = scr.getText().toString();
		if (inNumber) txt = txt + digit;
		else txt = digit;
		boolean neg = txt.startsWith("-");
		if (neg) txt = txt.substring(1);
		if (txt.startsWith("0") && (txt.length() > 1)) txt = txt.substring(1);
		if (txt.startsWith(".")) txt = "0" + txt;
		if (neg) txt = "-" + txt;
		scr.setText(txt);
		inNumber = true;
	}
	
	public void dot() {
		String txt = scr.getText().toString();
		if (txt.contains(".")) return;
		scr.setText(txt + ".");
		inNumber = true;
	}
	
	public void sign() {
		String txt = scr.getText().toString();
		if (txt.startsWith("-")) txt = txt.substring(1);
		else txt = "-" + txt;
		scr.setText(txt);
		inNumber = true;
	}
	
	public void pi() {
		scr.setText("3.14159265");
		inNumber = false;
	}
	
	public void sqrt2() {
		String txt = scr.getText().toString();
		double val = Double.valueOf(txt);
		val = Math.sqrt(val);
		txt = String.valueOf(val);
		if (txt.length() > 10) txt = txt.substring(0, 10);
		scr.setText(txt);
		inNumber = false;
	}

}

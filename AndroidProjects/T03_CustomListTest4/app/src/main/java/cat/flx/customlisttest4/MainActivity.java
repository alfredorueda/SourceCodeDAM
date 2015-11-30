package cat.flx.customlisttest4;

import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
implements OnItemClickListener, OnItemLongClickListener {

	final String[] columns = new String[] { "_id", "FirstName", "LastName" };
	final String[][] entries = new String[][] {
			{ "1", "Josep", "Pérez López" },
			{ "2", "Dalia", "Fernández Soler" },
			{ "3", "Manel", "Casamitjana Güell" },
			{ "4", "Anna Maria", "Vela Bosch" }
	};
	ListView listView1;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView1 = (ListView) findViewById(R.id.listView1);

		MatrixCursor cursor = new MatrixCursor(columns);
		for (String[] row : entries ) cursor.addRow(row);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				this, android.R.layout.two_line_list_item, cursor, 
				new String[] { "FirstName", "LastName" },
				new int[] { android.R.id.text1, android.R.id.text2 }, 
				0 );
		listView1.setAdapter(adapter);	
		listView1.setOnItemClickListener(this);
		listView1.setOnItemLongClickListener(this);
	}

	@Override public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.action_settings:
			alert(null, getResources().getString(R.string.configuring));
			return true;
		default:
			return false;
		}
	}

	public void alert(View view, String concept) {
		String msg = concept;
		if (view != null) {
			TextView textView1 = (TextView)view.findViewById(android.R.id.text1);
			TextView textView2 = (TextView)view.findViewById(android.R.id.text2);
			String firstName = textView1.getText().toString();
			String lastName = textView2.getText().toString();
			msg += " : " + firstName + " " + lastName;
		}
		Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
	}

	@Override public void onItemClick(
			AdapterView<?> parent, View view, int position, long id) {
		alert(view, getResources().getString(R.string.click_in_position, position));
	}

	// De aquí endavant...
	// Gestió del mode d'edición de llista (resposta al clic llarg)
	protected Object actionMode = null;
	public View selectedItemView = null;

	@Override public boolean onItemLongClick(
			AdapterView<?> parent, View view, int position, long id) {
		if (actionMode != null) return false;
		selectedItemView = view;
		ItemActionModeCallback callback = new ItemActionModeCallback();
		actionMode = MainActivity.this.startActionMode(callback);
		return true;
	}

	private class ItemActionModeCallback 
	implements ActionMode.Callback, OnItemClickListener {
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			listView1.setOnItemClickListener(this);
			listView1.setOnItemLongClickListener(null);
			if (selectedItemView != null)
				selectedItemView.setBackgroundResource(R.drawable.selected_item);
			MenuInflater inflater = mode.getMenuInflater();
			inflater.inflate(R.menu.item_selected, menu);
			return true;
		}

		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}

		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			if (selectedItemView == null) return false;
			switch (item.getItemId()) {
			case R.id.action_item_edit:
				alert(selectedItemView, getResources().getString(R.string.editing));
				return true;
			case R.id.action_item_delete:
				alert(selectedItemView, getResources().getString(R.string.removing));
				mode.finish();
				return true;
			default:
				return false;
			}
		}

		public void onDestroyActionMode(ActionMode mode) {
			actionMode = null;
			if (selectedItemView != null)
				selectedItemView.setBackgroundResource(0);
			selectedItemView = null;
			listView1.setOnItemClickListener(MainActivity.this);
			listView1.setOnItemLongClickListener(MainActivity.this);
		}

		@Override public void onItemClick(
				AdapterView<?> parent, View view, int position, long id) {
			if (selectedItemView != null) 
				selectedItemView.setBackgroundResource(0);
			selectedItemView = view;
			if (selectedItemView != null)
				selectedItemView.setBackgroundResource(R.drawable.selected_item);
		}
	}
}

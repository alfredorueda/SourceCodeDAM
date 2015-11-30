package cat.flx.t03_tests;

import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class SimpleCursorAdapter1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_cursor_adapter1);

        String[] columnNames = {"_id", "materia", "profe" };
        MatrixCursor cursor = new MatrixCursor(columnNames);
        String[] materies = getResources().getStringArray(R.array.materies_nom);
        String[] profes = getResources().getStringArray(R.array.materies_profes);
        String[] temp = new String[3];
        for(int i = 0; i < materies.length; i++) {
            temp[0] = Integer.toString(i);
            temp[1] = materies[i];
            temp[2] = profes[i];
            cursor.addRow(temp);
        }
        int[] ids = { android.R.id.text1, android.R.id.text2 };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[] { "materia", "profe" },
                new int[] { android.R.id.text1, android.R.id.text2 },
                0);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SimpleCursorAdapter1Activity.this, "Item: " + id, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package cat.flx.t03_tests;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayAdapter1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter1);
        String[] items = {
                "Centre", "Estudis", "Stucom", "DAM", "M08", "Programació multimèdia i mòbils",
                "Centre", "Estudis", "Stucom", "DAM", "M08", "Programació multimèdia i mòbils",
                "Centre", "Estudis", "Stucom", "DAM", "M08", "Programació multimèdia i mòbils",
                "Centre", "Estudis", "Stucom", "DAM", "M08", "Programació multimèdia i mòbils",
                "Centre", "Estudis", "Stucom", "DAM", "M08", "Programació multimèdia i mòbils"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items);
        ListView listView1 = (ListView) findViewById(R.id.listView);
        listView1.setAdapter(adapter);
    }
}

package cat.flx.t03_tests;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayAdapter3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter3);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.activity_array_adapter3_list_item,
                R.id.titol,
                getResources().getStringArray(R.array.items));
        ListView listView1 = (ListView) findViewById(R.id.listView);
        listView1.setAdapter(adapter);
    }
}

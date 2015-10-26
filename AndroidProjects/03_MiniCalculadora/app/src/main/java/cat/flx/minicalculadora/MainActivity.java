package cat.flx.minicalculadora;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit1, edit2;
    private Button btnAdd, btnSub, btnMul, btnDiv;
    private TextView result;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = (EditText) findViewById(R.id.editValue1);
        edit2 = (EditText) findViewById(R.id.editValue2);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnSub = (Button) findViewById(R.id.btn_sub);
        btnMul = (Button) findViewById(R.id.btn_mul);
        btnDiv = (Button) findViewById(R.id.btn_div);
        result = (TextView) findViewById(R.id.result);

        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
    }

    @Override public void onClick(View arg0) {
        float v1 = 0, v2 = 0;
        try { v1 = Float.parseFloat(edit1.getText().toString()); }
        catch (NumberFormatException e) {
            result.setText("NaN");
        }
        try { v2 = Float.parseFloat(edit2.getText().toString()); }
        catch (NumberFormatException e) {
            result.setText("NaN");
        }
        float res = 0;
        if (arg0.equals(btnAdd)) res = v1 + v2;
        else if (arg0.equals(btnSub)) res = v1 - v2;
        else if (arg0.equals(btnMul)) res = v1 * v2;
        else if (arg0.equals(btnDiv)) res = v1 / v2;
        result.setText(Float.toString(res));
    }
}

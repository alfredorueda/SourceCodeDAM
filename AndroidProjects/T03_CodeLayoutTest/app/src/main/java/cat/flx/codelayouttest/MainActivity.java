package cat.flx.codelayouttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup.LayoutParams;
import android.widget.AnalogClock;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		RelativeLayout rootView = new RelativeLayout(this);
		
		AnalogClock analogClock1 = new AnalogClock(this);
		analogClock1.setId(R.id.analogClock1);
		RelativeLayout.LayoutParams analogClock1Params =
				new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		analogClock1Params.addRule(RelativeLayout.CENTER_IN_PARENT);
		analogClock1.setLayoutParams(analogClock1Params);
		rootView.addView(analogClock1);

		TextView textView1 = new TextView(this);
		textView1.setId(R.id.textView1);
		RelativeLayout.LayoutParams textView1Params = 
				new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		textView1Params.addRule(RelativeLayout.ABOVE, R.id.analogClock1);
		textView1Params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		textView1.setLayoutParams(textView1Params);
		textView1.setTextSize(24);
		textView1.setText(getResources().getString(R.string.hello_world));
		rootView.addView(textView1);
		
		setContentView(rootView);
	}
}

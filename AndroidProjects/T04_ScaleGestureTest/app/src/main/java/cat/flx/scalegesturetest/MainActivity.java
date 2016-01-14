package cat.flx.scalegesturetest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ZoomImageView ziv;
    private Uri imgUri = null;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ziv = (ZoomImageView) findViewById(R.id.zoomImageView1);
        ziv.setDebugInfo(true);
        if (savedInstanceState != null) {
            String img = savedInstanceState.getString("imgUri");
            if (img != null) {
                imgUri = Uri.parse(img);
                if (imgUri != null) ziv.setImageURI(imgUri);
            }
        }
    }

    public void emptyImageViewer() { ziv.setImageURI(null); }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_fit_to_screen:
                ziv.fitToScreen();
                return true;
            case R.id.action_select_photo:
                selectPhoto();
                return true;
        }
        return false;
    }

    @Override public void onPause() {
        super.onPause();
        emptyImageViewer();
    }

    @Override public void onResume() {
        super.onResume();
        if (imgUri != null) ziv.setImageURI(imgUri);
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (imgUri != null) outState.putString("imgUri", imgUri.toString());
    }

    public void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Source"), 1);
    }

    @Override protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        emptyImageViewer();
        if (requestCode == 1) {      // se puso 1 en startActivityForResult()
            imgUri = data.getData();
            if (imgUri != null) ziv.setImageURI(imgUri);
        }
    }
}
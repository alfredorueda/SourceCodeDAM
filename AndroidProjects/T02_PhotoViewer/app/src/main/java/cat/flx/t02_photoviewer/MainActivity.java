package cat.flx.t02_photoviewer;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgView;
    private Button btn_select, btn_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgView = (ImageView) findViewById(R.id.imageView1);
        btn_select = (Button) findViewById(R.id.button1);
        btn_photo = (Button) findViewById(R.id.button2);
        btn_select.setOnClickListener(this);
        btn_photo.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        emptyImageViewer();
        super.onPause();
    }

    public void emptyImageViewer() {
        Drawable drawable = imgView.getDrawable();
        if (drawable != null) {
            ((BitmapDrawable)drawable).getBitmap().recycle();
        }
        imgView.setImageURI(null);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btn_select)) selectPhoto();
        else if (v.equals(btn_photo)) takePhoto();
    }

    private final static int SELECT_PHOTO = 1;
    private final static int CAPTURE_PHOTO = 2;

    public void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(
                Intent.createChooser(intent, "Select Source"), SELECT_PHOTO);
    }

    private Uri photoUri;

    public void takePhoto() {
        File dir = getExternalFilesDir(null);
        if (dir == null) {
            Toast.makeText(this, "Unable to mount the filesystem",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        File file = new File(dir, "photo.jpg");
        photoUri = Uri.fromFile(file);
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(i, CAPTURE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        emptyImageViewer();
        if (requestCode == CAPTURE_PHOTO) {
            imgView.setImageURI(photoUri);
        }
        else {
            Uri img = data.getData();
            if (img != null) {
                imgView.setImageURI(img);
            }
        }
        imgView.invalidate();
    }

}

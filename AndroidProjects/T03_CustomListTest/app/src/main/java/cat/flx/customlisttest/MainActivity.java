package cat.flx.customlisttest;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener {

	final String selection = Phone.IN_VISIBLE_GROUP + " = 1 AND " + 
								Phone.HAS_PHONE_NUMBER + " = 1";
	final String order = Phone.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
	final String selection2 = Phone.CONTACT_ID + " = ?";
	final String order2 = Phone.TYPE + " ASC";

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView contactList = (ListView)findViewById(R.id.listView1);
		ArrayList<Info> infos = new ArrayList<>();
		ContentResolver cr = getContentResolver();
		Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, 
				selection, null, order);
		while (cursor.moveToNext()) {
			long contactId = cursor.getLong(
					cursor.getColumnIndex(ContactsContract.Contacts._ID));
			String[] selArgs = { String.valueOf(contactId) };
			Cursor phones = 
					cr.query(Phone.CONTENT_URI, null, selection2, selArgs, order2);
			while (phones.moveToNext()) {
				Info info = infoFromCursor(phones);
				infos.add(info);
			}
			phones.close();
		}
		cursor.close();
		contactList.setAdapter(new ContactAdapter(this, infos));
	}
	
	private Info infoFromCursor(Cursor c) {
		Info info = new Info();
		info.name = c.getString(c.getColumnIndex(Phone.DISPLAY_NAME));
		info.number = c.getString(c.getColumnIndex(Phone.NUMBER));
		info.number = info.number.replaceAll("[^0-9]", "");
		info.type = c.getInt(c.getColumnIndex(Phone.TYPE));
		String thumbUriStr = c.getString(c.getColumnIndex(Phone.PHOTO_THUMBNAIL_URI));
		info.thumbUri = null;
		if (thumbUriStr != null) info.thumbUri = Uri.parse(thumbUriStr);
		return info;
	}
	
	@Override public void onClick(View v) {
		Info info = (Info) v.getTag();
		Uri numberUri = Uri.parse("tel:" + info.number);
		Intent intent = new Intent(Intent.ACTION_DIAL, numberUri);
		startActivity(intent);
	}
	
	private class Info {
		public String name;
		public int type;
		public String number;
		public Uri thumbUri;
	}
	
	private class ContactAdapter extends BaseAdapter {
		private Context context;
		private ArrayList<Info> infos;

		public ContactAdapter(Context context, ArrayList<Info> infos) { 
			this.context = context; 
			this.infos = infos;
		}

		@Override public int getCount() { return infos.size(); }
		@Override public Object getItem(int position) { return infos.get(position); }
		@Override public long getItemId(int position) { return position; }

		@Override public View getView(int position, View convertView, ViewGroup parent) {
			Info info = infos.get(position);
			LayoutInflater inflater = 
					(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.contact_list_item, parent, false);
			rowView.setTag(info);
			TextView text1 = (TextView) rowView.findViewById(R.id.text1);
			TextView text2 = (TextView) rowView.findViewById(R.id.text2);
			ImageView imageView1 = (ImageView) rowView.findViewById(R.id.imageView1);

			text1.setText(info.name);
			text2.setText(info.number);
			if (info.thumbUri != null) imageView1.setImageURI(info.thumbUri);
			int drId = R.drawable.work_phone_icon;
			if (info.type == Phone.TYPE_HOME)
				drId = R.drawable.home_phone_icon;
			else if (info.type == Phone.TYPE_MOBILE)
				drId = R.drawable.mobile_phone_icon;
            Drawable icon = ResourcesCompat.getDrawable(getResources(), drId, null);
			text2.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);

			rowView.setOnClickListener(MainActivity.this);
			return rowView;
		}
	}
}

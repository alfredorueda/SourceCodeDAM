package cat.flx.customlisttest2;

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
		ArrayList<Contact> contacts = new ArrayList<>();
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
				Contact contact = contactInfoFromCursor(phones);
				contacts.add(contact);
			}
			phones.close();
		}
		cursor.close();
		contactList.setAdapter(new ContactAdapter(this, contacts));
	}

	private Contact contactInfoFromCursor(Cursor c) {
		Contact info = new Contact();
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
		ViewInfo viewInfo = (ViewInfo) v.getTag();
		Contact contact = viewInfo.contact;
		Uri numberUri = Uri.parse("tel:" + contact.number);
		Intent intent = new Intent(Intent.ACTION_DIAL, numberUri);
		startActivity(intent);
	}

	private class Contact {
		String name;
		int type;
		String number;
		Uri thumbUri;
	}

	private class ViewInfo {
		TextView text1, text2;
		ImageView imageView1;
		Contact contact;
		
		public ViewInfo(View view) {
			text1 = (TextView) view.findViewById(R.id.text1);
			text2 = (TextView) view.findViewById(R.id.text2);
			imageView1 = (ImageView) view.findViewById(R.id.imageView1);
		}
		
		public void setContact(Contact contact) {
			this.contact = contact;
			text1.setText(contact.name);
			text2.setText(contact.number);
			if (contact.thumbUri != null)
				imageView1.setImageURI(contact.thumbUri);
			else
				imageView1.setImageResource(R.drawable.contact);
			Drawable icon;
			int drId = R.drawable.work_phone_icon;
			if (contact.type == Phone.TYPE_HOME)
				drId = R.drawable.home_phone_icon;
			else if (contact.type == Phone.TYPE_MOBILE)
				drId = R.drawable.mobile_phone_icon;
			icon = ResourcesCompat.getDrawable(getResources(), drId, null);
			text2.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
		}
	}

	private class ContactAdapter extends BaseAdapter {
		private Context context;
		private ArrayList<Contact> contacts;

		public ContactAdapter(Context context, ArrayList<Contact> contacts) { 
			this.context = context; 
			this.contacts = contacts;
		}

		@Override public int getCount() { return contacts.size(); }
		@Override public Object getItem(int position) { return contacts.get(position); }
		@Override public long getItemId(int position) { return position; }

		@Override 
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.contact_list_item, parent, false);
				ViewInfo viewInfo = new ViewInfo(view);
				view.setTag(viewInfo);
			}
			ViewInfo viewInfo = (ViewInfo) view.getTag();
			Contact contact = contacts.get(position);
			viewInfo.setContact(contact);
			view.setOnClickListener(MainActivity.this);
			return view;
		}
	}
}

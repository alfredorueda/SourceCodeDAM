package cat.flx.conversordivises;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Currencies {
	
	public class Currency {
		String currency;
		double rate;
		String name;
	}
	
	private String time;
	private ArrayList<Currency> currencies;

	public Currencies() {
		time = null;
		currencies = new ArrayList<Currency>();
	}
	
	public String getTime() { return time; }
	public ArrayList<Currency> getCurrencies() { return this.currencies; }
	
	public Currency getCurrency(String currency) {
		for(Currency c : currencies) {
			if (c.currency.equals(currency)) return c;
		}
		return null;
	}
	public int getPosition(String currency) {
		return currencies.indexOf(getCurrency(currency));
	}
	
	public boolean loadFromFile(Context context) {
		BufferedReader reader = null;
		try {
			File dir = context.getExternalFilesDir(null);
			if (dir == null) return false;
			File f = new File(dir, "currencies.csv");
			if (!f.exists()) return false;
			reader = new BufferedReader(new FileReader(f));
			time = reader.readLine();
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(":");
				if (parts.length != 2) continue;
				Currency c = new Currency();
				c.currency = parts[0];
				c.rate = Double.parseDouble(parts[1]);
				int resId = context.getResources().getIdentifier(
						c.currency, "string", context.getPackageName());
				c.name = context.getResources().getString(resId);
				currencies.add(c);
			}
			return true;
		}
		catch(Exception e) {
			Log.e("flx", "ERROR : " + e);
			return false;
		}
		finally {
			if (reader != null) {
				try { reader.close(); }
				catch (IOException e) { }
			}
		}
	}

	public double convert(double cur, String curFrom, String curTo) {
		Currency c1 = getCurrency(curFrom);
		if (c1 == null) return 0;
		Currency c2 = getCurrency(curTo);
		if (c2 == null) return 0;
		double out = cur / c1.rate * c2.rate;
		return Math.round(out * 1000000) / 1000000D;
	}
}

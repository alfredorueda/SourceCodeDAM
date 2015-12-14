package cat.flx.conversordivises;

import android.database.MatrixCursor;

public class CurrencyCursor extends MatrixCursor {

	public static final String[] COLNAMES = { "_id", "currency", "rate", "name" };
	
	public CurrencyCursor(Currencies currencies) { 
		super(COLNAMES);
		int n = 0;
		for (Currencies.Currency c : currencies.getCurrencies()) {
			String[] row = new String[4];
			row[0] = String.valueOf(++n);
			row[1] = c.currency;
			row[2] = String.valueOf(c.rate);
			row[3] = c.name;
			this.addRow(row);
		}
	}
}

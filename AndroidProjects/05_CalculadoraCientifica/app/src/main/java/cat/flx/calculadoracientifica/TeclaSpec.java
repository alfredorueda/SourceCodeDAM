package cat.flx.calculadoracientifica;

import cat.flx.calculadoracientifica.Teclat.Operation;

public class TeclaSpec {
	public String text;
	public Operation op;
	public int rowspan, colspan, res;

	public TeclaSpec(String text, Operation op, int rowspan, int colspan, int res) {
		this.text = text;
		this.op = op;
		this.rowspan = rowspan;
		this.colspan = colspan;
		this.res = res;
	}
}

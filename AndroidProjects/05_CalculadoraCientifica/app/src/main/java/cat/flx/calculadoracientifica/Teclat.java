package cat.flx.calculadoracientifica;

public class Teclat {
	public static enum Operation { 
		NOP, DIGIT, SIGN, DOT, 
		CLR, MCLR, MADD, MSUB, MRES,
		ADD, SUB, MUL, DIV, RESULT, 
		SIN, COS, TAN, POW10, POWE,
		ASIN, ACOS, ATAN, LOG10, LOGE,
		POW, SQR2, POW3, INV, FACT,
		SQRTN, SQRT2, SQRT3, PI, E
	};

	public final static TeclaSpec[] NORMAL = {
		new TeclaSpec("MC", Operation.MCLR, 1, 1, R.drawable.tecla_orange),
		new TeclaSpec("M+", Operation.MADD, 1, 1, R.drawable.tecla_orange),
		new TeclaSpec("M-", Operation.MSUB, 1, 1, R.drawable.tecla_orange),
		new TeclaSpec("MR", Operation.MRES, 1, 1, R.drawable.tecla_orange),
		new TeclaSpec("C", Operation.CLR, 1, 1, R.drawable.tecla_red),
		new TeclaSpec("±", Operation.SIGN, 1, 1, R.drawable.tecla_blue),
		new TeclaSpec("÷", Operation.DIV, 1, 1, R.drawable.tecla_blue),
		new TeclaSpec("×", Operation.MUL, 1, 1, R.drawable.tecla_blue),
		new TeclaSpec("7", Operation.DIGIT, 1, 1, R.drawable.tecla_lightgray),
		new TeclaSpec("8", Operation.DIGIT, 1, 1, R.drawable.tecla_lightgray),
		new TeclaSpec("9", Operation.DIGIT, 1, 1, R.drawable.tecla_lightgray),
		new TeclaSpec("-", Operation.SUB, 1, 1, R.drawable.tecla_blue),
		new TeclaSpec("4", Operation.DIGIT, 1, 1, R.drawable.tecla_lightgray),
		new TeclaSpec("5", Operation.DIGIT, 1, 1, R.drawable.tecla_lightgray),
		new TeclaSpec("6", Operation.DIGIT, 1, 1, R.drawable.tecla_lightgray),
		new TeclaSpec("+", Operation.ADD, 1, 1, R.drawable.tecla_blue),
		new TeclaSpec("1", Operation.DIGIT, 1, 1, R.drawable.tecla_lightgray),
		new TeclaSpec("2", Operation.DIGIT, 1, 1, R.drawable.tecla_lightgray),
		new TeclaSpec("3", Operation.DIGIT, 1, 1, R.drawable.tecla_lightgray),
		new TeclaSpec("=", Operation.RESULT, 2, 1, R.drawable.tecla_green),
		new TeclaSpec("0", Operation.DIGIT, 1, 2, R.drawable.tecla_lightgray),
		new TeclaSpec(".", Operation.DOT, 1, 1, R.drawable.tecla_lightgray),
	};

	public final static TeclaSpec[] CIENTIFIC = {
		
		new TeclaSpec("sin", Operation.SIN, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("cos", Operation.COS, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("tan", Operation.TAN, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("10ⁿ", Operation.POW10, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("eⁿ", Operation.POWE, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("sin⁻¹", Operation.ASIN, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("cos⁻¹", Operation.ACOS, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("tan⁻¹", Operation.ATAN, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("log", Operation.LOG10, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("ln", Operation.LOGE, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("xⁿ", Operation.POW, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("x²", Operation.SQR2, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("x³", Operation.POW3, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("⅟x", Operation.INV, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("x!", Operation.FACT, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("ⁿ√x", Operation.SQRTN, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("√", Operation.SQRT2, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("³√", Operation.SQRT3, 1, 1, R.drawable.tecla_darkgray),
		new TeclaSpec("π", Operation.PI, 1, 1, R.drawable.tecla_orange),
		new TeclaSpec("e", Operation.E, 1, 1, R.drawable.tecla_orange),
	};

}

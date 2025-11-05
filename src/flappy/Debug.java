package flappy;

public class Debug {
	private static String colorString(String msg, String colorCode) {
		return colorCode + msg + "\033[0m";
	}

	public static void log(String msg) {
		System.out.println(colorString("[LOG]", "\033[94m") + " " + msg);
	}
	public static void error(String msg) {
		System.out.println(colorString("[ERR]", "\033[31m") + " " + msg);
	}
	public static void warning(String msg) {
		System.out.println(colorString("[WAR]", "\033[33m") + " " + msg);
	}
}

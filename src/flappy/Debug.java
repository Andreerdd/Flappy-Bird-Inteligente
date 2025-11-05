package flappy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Debug {
	private static final DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm:ss");

	private static String colorirString(String msg, String colorCode) {
		return colorCode + msg + "\033[0m";
	}
	
	private static String dataFormatada() {
		return LocalDateTime.now().format(formatador);
	}

	public static void log(String msg) {
		System.out.println(colorirString("[" + dataFormatada() + " LOG]", "\033[94m") + " " + msg);
	}
	public static void error(String msg) {
		System.out.println(colorirString("[" + dataFormatada() + " ERR]", "\033[31m") + " " + msg);
	}
	public static void warning(String msg) {
		System.out.println(colorirString("[" + dataFormatada() + " WAR]", "\033[33m") + " " + msg);
	}
}

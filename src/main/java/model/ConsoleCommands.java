package model;

/**
 * 
 * @author Gabriele Giunchi
 * 
 * Classe che contiene l'elenco dei comandi utilizzabili dall'utente
 *
 */
public final class ConsoleCommands {
	
	/**
	 *	Comandi per gestire la comunicazione con i sistemi connessi.
	 */
	public static final class SystemCommandsCode {
		public static final String help = "help";
		public static final String back = "back";
		public static final String list = "list";
		public static final String set_on = "seton";
		public static final String set_off = "setoff";
		public static final String send_broadcast = "send";
		public static final String send_to = "sendto";
		public static final String set_name = "setname";
		public static final String stop_alarm = "stopalarm";
		public static final String detail = "detail";
		
		private SystemCommandsCode() { }
	}
	
	/**
	 *	Comandi utilizzati per la comunicazione seriale con Arduino.
	 */
	public static final class IOCommandsCode {
		public static final String io_help = "help";
		public static final String io_init = "init";
		public static final String io_send = "send";
		public static final String io_close = "close";
		public static final String io_back = "back";
		
		private IOCommandsCode() { }
	}
	
	/**
	 * 	Comandi utilizzabili nella sezione principale della linea di comando.
	 */
	public static final class MainCommandsCode {
		public static final String open_gui = "opengui";
		public static final String help = "help";
		public static final String net = "net";
		public static final String io = "io";
		public static final String exit = "exit";
		
		private MainCommandsCode() { }
	}

	private ConsoleCommands() { }
}

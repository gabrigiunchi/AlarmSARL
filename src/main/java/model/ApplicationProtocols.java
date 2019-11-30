package model;

/**
 * @author Gabriele Giunchi 
 * 
 * Classe che contiene i codice degli eventi 
 * e i comandi utilizzabili da linea di comando
 *
 */
public final class ApplicationProtocols {
	
	/**
	 * Porta sulla quale il server accetta connessioni TCP.
	 */
	public static final int SERVER_PORT = 1234;
	
	/* ******************** EVENTI GESTITI *******************/
	public static final int TURN_SYSTEM_ON = 32;
	public static final int TURN_SYSTEM_OFF = 33;
	public static final int TURN_ALARM_OFF = 34;
	public static final int STATE_REQUEST = 35;
	public static final int STATE_RESPONSE = 36;
	public static final int ID_REQUEST = 37;
	public static final int ID_RESPONSE = 38;
	
	private ApplicationProtocols() { }
}

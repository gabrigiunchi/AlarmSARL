package model;

/**
 * 
 * @author Gabriele Giunchi
 * 
 *	I comandi disponibili all'utente tramite linea di comando 
 *	sono stati divisi in base al loro ambito di applicazione.
 *	In questo modo è stato possibile implementare 
 *	separatamente ogni console handler che è identificato
 *	da un tipo e passare dinamicamente da uno all'altro.
 *	
 *	Esempio:
 *	All'avvio dell'applicazione l'utente si trova 
 *	nella sezione main in cui ha un set di comandi e passando ad un'altra sezione 
 *	cambiano anche i comandi disponibili.
 *
 */
public enum ConsoleHandlerType {
	
	/**
	 * Sezione di login dove l'utente 
	 * deve inserire la password per accedere all'applicazione.
	 */
	access,

	/**
	 * Sezione di configurazione in cui l'utente 
	 * deve inserire la nuova password per l'applicazione.
	 */
	configuration,
	
	/**
	 * Sezione in cui l'utente può interagire con i sistemi connessi.
	 */
	net,
	
	/**
	 * Sezione in cui l'utente può comunicare attraverso 
	 * connessione seriale con un micocontrollore Arduino.
	 */
	io,
	
	/**
	 * Sezione principale.
	 */
	main
}

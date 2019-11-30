package model;

import java.util.UUID;

/**
 * 
 * @author Gabriele Giunchi
 * 
 * Insieme di attributi di un sistema:
 * 
 * - stato
 * - nome
 * - id del sistema
 * - UUID dell'agente che lo gestisce
 *
 */
public interface SystemDetail {
	
	/**
	 * 
	 * @author Gabriele Giunchi
	 * 
	 * Possibilli stati di un sistema.
	 *
	 */
	enum SystemState {
		
		/**
		 * Stato sconosciuto/in fase di elaborazione. 
		 * Il sistema si trova in questo stato quando è in attesa 
		 * di ricevere dal dispositivo connesso lo stato reale
		 */
		unknown(47),
		
		/**
		 * Stato di calibrazione dei sensori.
		 */
		calibrating(48),
		
		/**
		 * Sistema è attivo.
		 */
		on(49),
		
		/**
		 * Sistema è connesso ma non attivo.
		 */
		off(50),
		
		/**
		 * Sistema in stato di allarme.
		 */
		alarm(51);
		
		private final int code;
		
		SystemState(final int code) {
			this.code = code;
		}
		
		/**
		 * 
		 * @return numero intero associato allo stato
		 */
		public int getCode() {
			return this.code;
		}
		
		/**
		 * Restituisce l'enumerazione associata al codice dato.
		 * @param code
		 * @return enum {@link SystemState}
		 */
		public static SystemState getStateById(final int code) {
			switch (code) {
				case 47 : return unknown;
				case 48 : return calibrating;
				case 49 : return on;
				case 50 : return off;
				case 51 : return alarm;
				default : return unknown;
			}
		}
	}
	
	/**
	 * Setta il nome del sistema.
	 * Il nome serve esclusivamente per rendere più identificabile un sistema da parte dell'utente
	 * perciò può essere cambiato senza nessuna ripercussione
	 * @param name Nome del sistema
	 */
	void setName(String name);
	
	/**
	 * Setta lo stato del sistema.
	 * @param state : enum {@link SystemState} che identifica lo stato del sistema
	 */
	void setState(SystemState state);
	
	/**
	 * Setta l'id del sistema.
	 * @param systemId : id da assegnare al sistema
	 */
	void setId(final String systemId);
	
	/**
	 * 
	 * @return nome del sistema
	 */
	String getName();
	
	/**
	 * 
	 * @return id del sistema
	 */
	String getId();
	
	/**
	 * 
	 * @return UUID dell'agente che controlla il sistema
	 */
	UUID getAgentId();
	
	/**
	 * 
	 * @return stato del sistema
	 */
	SystemState getState();
}

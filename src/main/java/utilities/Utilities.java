package utilities;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import model.SystemDetail;
import model.SystemDetail.SystemState;
import java.util.Map;

/**
 * @author Gabriele Giunchi
 * 
 * Classe che contiene alcuni metodo di utilità
 *
 */
public final class Utilities {
	
	/**
	 * Lista thread-safe contenente i sistemi attualmente connessi.
	 */
	private static final Map<Integer, SystemDetail> systems = new ConcurrentHashMap<Integer, SystemDetail>();
	
	public static final String images_folder = "/image/";
	public static final String green_circle_path = "green_circle.png";
	public static final String red_circle_path = "red_circle.png";
	public static final String grey_circle_path = "grey_circle.png";
	public static final String alarm_icon_path = "alarm_icon.png";
	public static final String calibration_icon_path = "calibration_icon.png";
	
	private static final int defaultIconWidth = 20;
	private static final int defaultIconHeight = 20;
	
	/**
	 * 
	 * @return lista dei sistemi connessi
	 */
	public static Map<Integer, SystemDetail> getSystems() {
		return systems;
	}
	
	/**
	 * Crea un istanza di {@link SerialPort} 
	 * utilizzabile per la comunicazione seriale con Arduino.
	 * 
	 * @param portName : nome della porta su cu creare la connessione
	 * @param baud : bit rate voluto
	 * @return oggetto {@link SerialPort}
	 * @throws NoSuchPortException se la porta non esiste
	 * @throws PortInUseException se la porta è già in uso
	 * @throws UnsupportedCommOperationException se l'operazione non è supportata dalla libreria rxtx
	 */
	public static SerialPort createSerialPort(final String portName, final int baud) throws NoSuchPortException, 
			PortInUseException, UnsupportedCommOperationException {
		
		final CommPortIdentifier port = CommPortIdentifier.getPortIdentifier(portName);
		
        if (port.isCurrentlyOwned()) {
            System.out.println("Error: Port is currently in use");
            throw new IllegalStateException("Port is currently in use");
        }
        
        System.out.println("Opening port " + port.getName());
        final CommPort commPort = port.open("Utilities", 2000);
        System.out.println("Creating serial port");
        
        final SerialPort serialPort = (SerialPort) commPort;
        serialPort.setSerialPortParams(baud, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        System.out.println("Communication set on port " + port.getName());
        
        return serialPort;
	}
	
	/**
	 * Restituisce un'immagine salvata nella cartella delle immagini (vedi {@link Utilities.images_folder)}.
	 * L'immagine è ridimensionata in base ai parametri width e height
	 * 
	 * @param path nome dell'immagine
	 * @param width larghezza voluta
	 * @param height altezza voluta
	 * @return oggetto {@link Icon} con l'immagine
	 */
	public static Icon loadImage(final String path, final int width, final int height) {
		return new ImageIcon(new ImageIcon(Utilities.class.getResource(images_folder + path))
				.getImage()
				.getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}
	
	/**
	 * Restituisce l'immagine associata ad un determinato stato del sistema.
	 * 
	 * @param state
	 * @return
	 */
	public static Icon getImageBySystemState(final SystemState state) {
		switch(state) {
			case calibrating : return loadImage(calibration_icon_path, defaultIconWidth, defaultIconHeight);
			case on : return loadImage(green_circle_path, defaultIconWidth, defaultIconHeight);
			case off : return loadImage(red_circle_path, defaultIconWidth, defaultIconHeight);
			case alarm : return loadImage(alarm_icon_path, defaultIconWidth, defaultIconHeight);
			case unknown : return loadImage(grey_circle_path, defaultIconWidth, defaultIconHeight);
			default: return loadImage(grey_circle_path, defaultIconWidth, defaultIconHeight);
		}
	}
	
	/**
	 * Restituisce una descrizione testuale dello stato di un sistema.
	 * @param state
	 * @return
	 */
	public static String getStateDescriptionForState(final SystemState state) {
		switch (state) {
			case calibrating : return "CALIBRATING...";
			case alarm : return "ALARM";
			case off : return "OFF";
			case on : return "ON";
			case unknown : return "LOADING...";
			default : return "LOADING....";
		}
	}
	
	/**
	 * Legge una stringa da un {@link InputStream}.
	 * La funzione termina quando lo stream non ha più byte da poter leggere
	 * @param inputStream : stream dal quale leggere la stringa
	 * @return stringa letta
	 * @throws IOException se si verifica un errore I/O
	 */
	public static String readStringFromStream(final InputStream inputStream) throws IOException {
		final int byteArrayLength = 1024;
		int byteRead = 1;
		final byte[] bytes = new byte[byteArrayLength];
		final StringBuilder stringBuilder = new StringBuilder();

		while (byteRead > 0) {
			byteRead = inputStream.read(bytes);

			if (byteRead > 0) {
				stringBuilder.append(new String(bytes, 0, byteRead));
			}
		}
		return stringBuilder.toString();
	}
	
	private Utilities() { }
}

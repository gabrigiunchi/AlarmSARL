package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import cryptography.MyEncryptor;

/**
 * 
 * @author Gabriele Giunchi
 * 
 * Classe che gestisce le impostazioni dell'utente
 *
 */
public final class UserSettings {
	
	/**
	 * Nome dell'applicazione.
	 */
	public static final String applicationName = "jalarm";
	
	/**
	 * Directory home dell'utente.
	 */
	public static final String userHome = System.getProperty("user.home");
	
	/**
	 * Directory che contiene i dati dell'applicazione.
	 */
	public static final String applicationFolder = userHome + "//." + applicationName;

	private static final String decryptionKeyPath = applicationFolder + "//key.bin";
	private static final String userPasswordPath = applicationFolder + "//password.bin";
	
	private static String password = "";
	
	/**
	 * @return true se l'applicazione non è stata inizializzata, false altrimenti
	 */
	public static boolean needInstallation() {
		return !new File(applicationFolder).exists();
	}
	
	/**
	 * Inizializza l'applicazione salvando la password generale e settando altri parametri.
	 * @param newPassword password generale dell'applicazione
	 */
	public static void configureApplication(final String newPassword) {
		// Creo la cartella dell'applicazione
		if (new File(applicationFolder).mkdir()) {
			// Genero nuova chiave per criptare la password
			final SecretKey key = MyEncryptor.generateKey();
			try {
				MyEncryptor.getInstance().init(key);
				saveEncryptionKey(key);
				savePassword(newPassword);
				password = newPassword;
				System.out.println("Configurazione effettuata con successo");
			} catch (IOException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
				System.err.println(e);
			}
		}
	}
	
	/**
	 * Carica le impostazioni dell'utente e inizializza le variabili opportune. 
	 * Questo metodo va eseguito all'avvio dell'applicazione
	 */
	public static void loadSettings() {
		if (!needInstallation()) {
			try {
				MyEncryptor.getInstance().init(loadEncryptionKey());
				password = loadPassword();
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
				System.err.println(e);
			}
		}
	}
	
	/**
	 * 
	 * @return password dell'applicazione
	 */
	public static String getPassword() {
		return password;
	}
	
	/**
	 * Carica in memoria la password generale dell'applicazione.
	 * @return password salvata, stringa vuota se si verifica un errore
	 */
	private static String loadPassword() {
		try {
			final InputStream input = new FileInputStream(userPasswordPath);
			final String cryptedPassword = Utilities.readStringFromStream(input);
			return MyEncryptor.getInstance().decrypt(cryptedPassword);
		} catch (IOException | IllegalBlockSizeException | BadPaddingException e) {
			
			System.err.println(e);
		}

		return "";
	}
	
	/**
	 * Salva la password.
	 * @param password
	 * @throws IOException
	 */
	private static void savePassword(final String password) throws IOException {
		String cryptedPassword = password;
		final OutputStream output = new FileOutputStream(userPasswordPath);
		try {
			cryptedPassword = MyEncryptor.getInstance().encrypt(password);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		output.write(cryptedPassword.getBytes());
		output.close();
	}
	
	/**
	 * Salva la chiave di criptazione usata per la cifratura dei dati.
	 * @param key
	 * @throws IOException
	 */
	private static void saveEncryptionKey(final Key key) throws IOException {
		final OutputStream output = new FileOutputStream(decryptionKeyPath);
		output.write(key.getEncoded());
		output.close();
	}
	
	/**
	 * Carica la chiave di criptazione salvata.
	 * @return
	 */
	private static byte[] loadEncryptionKey() {
		byte[] keyBytes = new byte[MyEncryptor.KEY_LENGTH];
		
		try {
			final InputStream inputStream = new FileInputStream(decryptionKeyPath);
			if (inputStream.read(keyBytes) < 0) {
				keyBytes = new byte[0];
			}
			inputStream.close();
			return keyBytes;
		} catch (IOException e) {
			System.err.println(e);
			keyBytes = new byte[0];
		}
		
		return keyBytes;
	}
	
	private UserSettings() { }
}

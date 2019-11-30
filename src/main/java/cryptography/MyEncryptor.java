package cryptography;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Gabriele Giunchi
 * 
 * Singleton di un oggetto {@link Encryptor} utilizzato globalmente
 * per la criptazione di dati che utilizza l'algoritmo AES a 128bit
 */
public final class MyEncryptor implements Encryptor {

	private static MyEncryptor SINGLETON;
	
	/**
	 * Algoritmo usato per codificare/decodificare i dati.
	 */
	public static final String ALGORITHM = "AES";
	
	/**
	 * Lunghezza della chiave di cifratura espressa in byte.
	 */
	public static final int KEY_LENGTH = AesEncryptor.KEY_LENGTH;
	
	private static final String DEFAULT_PASSWORD = "yxc537y90wFEeQLz";
	
	private Encryptor encryptor;

	private MyEncryptor() {
		try {
			final SecretKey secretKey = new SecretKeySpec(DEFAULT_PASSWORD.getBytes(), ALGORITHM);
			this.encryptor = new AesEncryptor(secretKey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			System.err.println(e);
		}
	}
	
	/**
	 * 
	 * @return unica istanza di {@link MyEncryptor}
	 */
	public static synchronized MyEncryptor getInstance() {
		if (SINGLETON == null) {
			SINGLETON = new MyEncryptor();
		}
		
		return SINGLETON;
	}

	/**
	 * Inizializza l'encryptor con la secret key data.
	 * @param secretKey
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	public Encryptor init(final SecretKey secretKey) throws InvalidKeyException, 
			NoSuchAlgorithmException, NoSuchPaddingException {
		
		encryptor = new AesEncryptor(secretKey);
		System.out.println("MyEncriptor inizializzato");
		return this;
	}

	/**
	 * Inizializza l'encryptor con la chiave memorizzata nell'array di byte dato.
	 * La lunghezza della chiave è espressa dalla costante KEY_LENGTH, 
	 * se tale vincolo non viene rispettato verrà lanciata un'eccezione.
	 * @param bytes : array di byte contenente la chiave
	 * @return istanza di {@link MyEncryptor}
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	public Encryptor init(final byte[] bytes) throws InvalidKeyException, 
			NoSuchAlgorithmException, NoSuchPaddingException {
		
		return init(new SecretKeySpec(bytes, ALGORITHM));
	}
	
	@Override
	public String encrypt(final String data) throws UnsupportedEncodingException, 
			IllegalBlockSizeException, BadPaddingException {
		
		return encryptor.encrypt(data);
	}

	@Override
	public String decrypt(final String data) throws UnsupportedEncodingException, 
			IllegalBlockSizeException, BadPaddingException {
		
		return encryptor.decrypt(data);
	}

	/**
	 * Genera una chiave di tipo {@link SecretKey} a 128 bit.
	 * @return chiave generata, null se si verifica un errore
	 */
	public static SecretKey generateKey() {
		try {
			final KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
			keyGenerator.init(KEY_LENGTH * 8);
			return keyGenerator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			System.err.println(e);
		}
		return null;
	}
}
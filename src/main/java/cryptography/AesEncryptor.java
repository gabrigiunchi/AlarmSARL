package cryptography;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * @author Gabriele Giunchi
 * 
 * Implementazione di {@link Encryptor} che utilizza l'algoritmo AES
 * con chiave di cifratura a 128 bit.
 */
public class AesEncryptor implements Encryptor {

	/**
	 * Lunghezza della chiave richiesta dall'algoritmo espressa in byte.
	 */
	public static final int KEY_LENGTH = 16;
	
	/**
	 * Nome dell'algoritmo usato per codificare/decodificare i dati.
	 */
    public static final String ALGORITHM = "AES";
	
	private final transient Cipher encryptor;
	private final transient Cipher decryptor;

	/**
	 * Crea un istanza di {@link AesEncryptor} con la chiave di cifratura data.
	 * @param key : oggetto {@link SecretKey} per la cifratura dei dati
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 */
	public AesEncryptor(final SecretKey key) throws NoSuchAlgorithmException, 
			NoSuchPaddingException, InvalidKeyException {
		
		this.encryptor = Cipher.getInstance(ALGORITHM);
		this.decryptor = Cipher.getInstance(ALGORITHM);
		this.encryptor.init(Cipher.ENCRYPT_MODE, key);
		this.decryptor.init(Cipher.DECRYPT_MODE, key);
	}

	@Override
	public String decrypt(final String data) throws UnsupportedEncodingException, 
			IllegalBlockSizeException, BadPaddingException {
		
		final byte[] dec = Base64.getDecoder().decode(data);
		final byte[] utf8 = this.decryptor.doFinal(dec);
		return new String(utf8, "UTF8");
	}

	@Override
	public String encrypt(final String data) throws UnsupportedEncodingException, 
			IllegalBlockSizeException, BadPaddingException {
		
		final byte[] utf8 = data.getBytes("UTF8");
		final byte[] enc = this.encryptor.doFinal(utf8);
		return Base64.getEncoder().encodeToString(enc);
	}
}

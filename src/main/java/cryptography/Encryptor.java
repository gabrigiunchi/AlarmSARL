package cryptography;

import java.io.UnsupportedEncodingException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

/**
 *
 * @author Gabriele Giunchi
 * 
 * Interfaccia di un codificare/decodificatore di stringhe
 *
 */
public interface Encryptor {
	/**
	 * Codice una stringa utilizzando l'algoritmo dell'oggetto {@link Encryptor}.
	 * 
	 * @param data : stringa da criptare
	 * @return stringa criptata
	 * @throws UnsupportedEncodingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	String encrypt(String data) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException;
	
	/**
	 * Decodifica una stringa utilizzando l'algoritmo dell'oggetto {@link Encryptor}.
	 * Se la stringa non è stata criptata con lo stesso algoritmo verrà lanciata un'eccezzione.
	 * 
	 * @param data : stringa da decriptare
	 * @return stringa decriptata
	 * @throws UnsupportedEncodingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	String decrypt(String data) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException;
}

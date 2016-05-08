package com.sample.app.encryption;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.sample.app.constant.CommonConstants;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
@Component
@Slf4j
public class Encrypter {
	private String ALGO = "AES";
	private String secretV1 = "rMLCFLXKTjUjtQZy";

	public String encrypt(TokenDto dto) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(tokenToString(dto).getBytes());

		String encryptedValue = new BASE64Encoder().encode(encVal);
		encryptedValue = URLEncoder.encode(encryptedValue, "UTF-8");
		return encryptedValue;
	}

	public TokenDto decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		encryptedData = URLDecoder.decode(encryptedData, "UTF-8");
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return StringToTokenDTo(decryptedValue);
	}

	private Key generateKey() throws Exception {
		String secret = secretV1;
		byte[] keyValue = secret.getBytes();
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}

	private String tokenToString(TokenDto dto) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				CommonConstants.DATE_FORMAT);
		String date = dateFormat.format(new Date()).toString();
		StringBuilder st = new StringBuilder().append(dto.getUuid())
				.append(CommonConstants.SPLITER).append(dto.getUserId())
				.append(CommonConstants.SPLITER).append(date.toString());
		return st.toString();
	}

	private TokenDto StringToTokenDTo(String st) throws ParseException {
		String[] arr = st.split(CommonConstants.SPLITER);
		TokenDto dto = new TokenDto();
		dto.setUuid(arr[0]);
		dto.setUserId(arr[1]);
		String date = arr[2];
		SimpleDateFormat formatter = new SimpleDateFormat(
				CommonConstants.DATE_FORMAT);
		Date date1 = formatter.parse(date);
		dto.setExpiry(date1);
		return dto;
	}
}

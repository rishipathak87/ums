package com.sample.app.encryption;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

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
		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < encVal.length; i++) {
			sb.append(Integer.toString((encVal[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		return sb.toString();
	}

	public TokenDto decrypt(String encryptedData) throws Exception {
		Key key = generateKey();

		HexBinaryAdapter adapter = new HexBinaryAdapter();
		byte[] bytes = adapter.unmarshal(encryptedData);
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decValue = c.doFinal(bytes);
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
		String date = dateFormat.format(dto.getExpiry().toString());

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

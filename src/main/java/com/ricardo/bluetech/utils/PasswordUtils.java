package com.ricardo.bluetech.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
	public PasswordUtils() {
		
	}
	
	public static String gerarBCrypt(String senha) {
		if(senha != null) {
			BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
			return bCrypt.encode(senha);
		}
		return null;
	}
}

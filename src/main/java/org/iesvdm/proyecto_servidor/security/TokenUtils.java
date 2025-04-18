package org.iesvdm.proyecto_servidor.security;

import org.antlr.v4.runtime.Token;
import org.iesvdm.proyecto_servidor.model.record.TokenData;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.Date;

@Component
@AllArgsConstructor
public class TokenUtils {

    private final EncryptionUtil encryptionUtil;

    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    public String generateToken(Authentication authentication) {
        return encryptionUtil.encrypt(String.format("%d#%s", new Date().getTime(), authentication.getName()));
    }

    public TokenData parseTokenData(String token) {
        String decrypt = encryptionUtil.decrypt(token);
        int i = decrypt.indexOf("#");
        long timestamp = Long.parseLong(decrypt.substring(0, i));
        String username = decrypt.substring(i + 1);
        return new TokenData(timestamp, username);
    }

}

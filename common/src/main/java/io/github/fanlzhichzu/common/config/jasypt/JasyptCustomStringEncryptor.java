package io.github.fanlzhichzu.common.config.jasypt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import io.github.fanlzhichzu.common.utils.gm.AESUtils;
import io.github.fanlzhichzu.common.utils.gm.SM4Utils;

@Slf4j
public class JasyptCustomStringEncryptor implements StringEncryptor {

    private static final SM4Utils SM_4_UTILS;

    @Value("${jasypt.encryptor.encryptedMethod:sm4}")
    private String encryptedMethod;

    static {
        String secretKey = "fanlz0558";
        String iv = "fanlz0558";
        boolean hexString = false;
        SM_4_UTILS = new SM4Utils(secretKey, iv, hexString);
    }

    @Override
    public String encrypt(String s) {
        if (!StringUtils.isEmpty(s)){
            try {
                s = SM_4_UTILS.encryptData_ECB(s);
                log.info("加密后：{}",s);
            }catch (Exception e){
                log.error("加密失败",e);
            }

        }
        return s;
    }

    @Override
    public String decrypt(String s) {
        log.info("get encrypted text：" + s);
        String encryptedText = null;
        if (StringUtils.isNotBlank(s)){
            try {
                String encodedPrefix = s.substring(0, s.indexOf("(")).toUpperCase();
                if (JasyptEncryptableDetector.ENCODED_HINT_ENC.equalsIgnoreCase(encodedPrefix)){
                    encodedPrefix = encryptedMethod;
                }

                String ciphertext = s.substring(s.indexOf("(") + 1, s.lastIndexOf(")"));
                if ("aes".equalsIgnoreCase(encodedPrefix)){
                    encryptedText = AESUtils.decrypt(ciphertext, "fanlz0558");
                } else if ("sm4".equalsIgnoreCase(encodedPrefix)){
                    encryptedText = SM_4_UTILS.decryptData_ECB(ciphertext);
                }

                // log.info("generate decrypted text："+ encryptedText);
                if (StringUtils.isNotBlank(encryptedText)){
                    log.info("decrypt text success!");
                } else {
                    log.error("decrypt text failed!");
                }
            } catch (Exception e) {
                log.error("decrypt text error!", e);
            }
        }
        return encryptedText;
    }
}

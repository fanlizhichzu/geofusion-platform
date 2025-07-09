package io.github.fanlzhichzu.common.config.jasypt;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 自定义 jasypt 发现器实现类
 */
@Slf4j
public class JasyptEncryptableDetector implements EncryptablePropertyDetector {

    private static final String[] ENCODED_HINTS = {"ENC", "AES", "SM4"};
    public static final String ENCODED_HINT_ENC = "ENC";

    // 判断是否是 jasypt 约定规则加密的属性
    @Override
    public boolean isEncrypted(String s) {
        // log.info("isEncrypted:{}", s);
        if (null != s && s.contains("(") && s.contains(")")) {
            s = s.trim();
            return Arrays.asList(ENCODED_HINTS).contains(s.substring(0, s.indexOf("(")).toUpperCase());
        }
        return false;
    }
    // 密文预处理。可以自定义返回去除掉前缀和后缀的真正加密的值。
    // 因解密方法需要加密提示判断加密的算法，此处不去除前缀和后缀
    @Override
    public String unwrapEncryptedValue(String s) {
        return s.trim();
    }
}

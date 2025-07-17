package io.github.fanlizhichzu.common.config.jasypt;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {
    @Bean
    @ConditionalOnProperty(name = "jasypt.encryptor.bean", havingValue = "jasyptCustomStringEncryptor")
    public JasyptCustomStringEncryptor jasyptCustomStringEncryptor(){
        return new JasyptCustomStringEncryptor();
    }

    @Bean("encryptablePropertyDetector")
    @ConditionalOnProperty(name = "jasypt.encryptor.bean", havingValue = "jasyptCustomStringEncryptor")
    public JasyptEncryptableDetector jasyptEncryptableDetector(){
        return new JasyptEncryptableDetector();
    }
}

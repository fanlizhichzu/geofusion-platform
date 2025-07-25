package io.github.fanlizhichzu.manager.typeHandle;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TypeHandleConfig {
    @Value("${typeHandle.globalJdbcType:postgresql}")
    private String globalJdbcType;

    @PostConstruct
    public void setGlobalJdbcType() {
        JsonTypeHandler.setJdbcType(globalJdbcType);
        BoolTypeHandler.setJdbcType(globalJdbcType);
    }
}

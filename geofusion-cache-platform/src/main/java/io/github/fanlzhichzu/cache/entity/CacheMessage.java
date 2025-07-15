package io.github.fanlzhichzu.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CacheMessage implements Serializable {
    private String cacheName;
    private Object key;
    private String operation;
    private long timestamp;
    private String sourceNode;
    
    public static final String EVICT = "evict";
    public static final String CLEAR = "clear";
}
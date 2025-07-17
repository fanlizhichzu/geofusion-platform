package io.github.fanlizhichzu.cache.config;

import io.github.fanlizhichzu.cache.entity.CacheMessage;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CacheUpdatePublisher {
    private final RedissonClient redissonClient;
    private static final String CACHE_UPDATE_TOPIC = "cache_update_topic";
    
    public void publishEvict(String cacheName, Object key) {
        CacheMessage message = new CacheMessage(
            cacheName, 
            key, 
            CacheMessage.EVICT, 
            System.currentTimeMillis(),
            getNodeId()
        );
        redissonClient.getTopic(CACHE_UPDATE_TOPIC, new JsonJacksonCodec())
                     .publish(message);
    }
    
    public void publishClear(String cacheName) {
        CacheMessage message = new CacheMessage(
            cacheName, 
            null, 
            CacheMessage.CLEAR, 
            System.currentTimeMillis(),
            getNodeId()
        );
        redissonClient.getTopic(CACHE_UPDATE_TOPIC, new JsonJacksonCodec())
                     .publish(message);
    }
    
    private String getNodeId() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return UUID.randomUUID().toString();
        }
    }
}
package io.github.fanlizhichzu.cache.config;

import io.github.fanlizhichzu.cache.entity.CacheMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CacheUpdateSubscriber {
    private final CacheManager cacheManager;
    private final RedissonClient redissonClient;
    private static final String CACHE_UPDATE_TOPIC = "cache_update_topic";

    @PostConstruct
    public void subscribe() {
        RTopic topic = redissonClient.getTopic(CACHE_UPDATE_TOPIC, new JsonJacksonCodec());
        topic.addListener(CacheMessage.class, (channel, msg) -> {
            // 忽略自己发布的消息
            if (!msg.getSourceNode().equals(getNodeId())) {
                handleCacheMessage(msg);
            }
        });
    }
    
    private void handleCacheMessage(CacheMessage message) {
        String operation = message.getOperation();
        String cacheName = message.getCacheName();
        Object key = message.getKey();
        
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) return;
        
        if (CacheMessage.EVICT.equals(operation)) {
            cache.evict(key);
        } else if (CacheMessage.CLEAR.equals(operation)) {
            cache.clear();
        }
    }
    
    private String getNodeId() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return UUID.randomUUID().toString();
        }
    }
}
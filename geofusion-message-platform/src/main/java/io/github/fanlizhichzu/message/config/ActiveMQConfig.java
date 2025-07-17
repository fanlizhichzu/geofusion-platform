package io.github.fanlizhichzu.message.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

@Configuration
public class ActiveMQConfig {

    @Value("${spring.activemq.user}")
    private String userName;

    @Value("${spring.activemq.password}")
    private String password;

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    /**
     * 工厂bean名称
     */
    private static final String givenConnectionFactory = "givenConnectionFactory";
    /**
     * queue监听器名称
     */
    public static final String queueListener = "queueListener";
    /**
     * topic监听器名称
     */
    public static final String topicListener = "topicListener";

    /**
     * 配置名字为givenConnectionFactory的连接工厂
     *
     * @return
     */
    @Bean(givenConnectionFactory)
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(userName, password,
                brokerUrl);
        RedeliveryPolicy policy = new RedeliveryPolicy();
        policy.setUseExponentialBackOff(Boolean.TRUE);
        policy.setMaximumRedeliveries(2);
        policy.setInitialRedeliveryDelay(1000L);
        policy.setBackOffMultiplier(2);
        policy.setMaximumRedeliveryDelay(1000L);
        activeMQConnectionFactory.setRedeliveryPolicy(policy);
        return activeMQConnectionFactory;
    }


    /**
     * 在Queue模式中，对消息的监听需要对containerFactory进行配置
     *
     * @param givenConnectionFactory
     * @return
     */
    @Bean(queueListener)
    public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory(
            ActiveMQConnectionFactory givenConnectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setSessionTransacted(false);
        factory.setSessionAcknowledgeMode(ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
        factory.setPubSubDomain(false);
        factory.setConnectionFactory(givenConnectionFactory);
        return factory;
    }

    /**
     * topic 模式
     *
     * @param givenConnectionFactory
     * @return
     */
    @Bean(topicListener)
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory givenConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(givenConnectionFactory);
        // 设置并发消费者数量
        bean.setConcurrency("3-10");
        // 配置事务
        bean.setSessionTransacted(true);
        return bean;
    }
}

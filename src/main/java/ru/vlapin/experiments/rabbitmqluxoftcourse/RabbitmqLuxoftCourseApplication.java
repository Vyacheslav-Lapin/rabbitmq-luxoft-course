package ru.vlapin.experiments.rabbitmqluxoftcourse;

import lombok.val;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.vlapin.experiments.rabbitmqluxoftcourse.service.ReceiveMessageHandler;

@SpringBootApplication
public class RabbitmqLuxoftCourseApplication {

  public static final String TOPIC_NAME = "mytopic";
  public static final String QUEUE_NAME = "myqueue";

  public static void main(String[] args) {
    SpringApplication.run(RabbitmqLuxoftCourseApplication.class, args);
  }

  @Bean
  Queue queue() {
    return new Queue(QUEUE_NAME, false);
  }

  @Bean
  TopicExchange topicExchange() {
    return new TopicExchange(TOPIC_NAME);
  }

  @Bean
  Binding binding() {
    return BindingBuilder.bind(queue()).to(topicExchange()).with("my.#");
  }

  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                           MessageListener messageListener) {
    val container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(QUEUE_NAME);
    container.setMessageListener(messageListener);
    return container;
  }

  @Bean
  MessageListenerAdapter messageListener(ReceiveMessageHandler receiveMessageHandler) {
    return new MessageListenerAdapter(receiveMessageHandler, "handleMessage");
  }
}

package ru.vlapin.experiments.rabbitmqluxoftcourse.controller;

import static ru.vlapin.experiments.rabbitmqluxoftcourse.RabbitmqLuxoftCourseApplication.TOPIC_NAME;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SendMessageController {

  RabbitTemplate rabbitTemplate;

  @PostMapping("send")
  @SneakyThrows
  @Contract(pure = true)
  public final @NotNull String sendMessage(@RequestParam @NotNull String message) {
    rabbitTemplate.convertAndSend(TOPIC_NAME, "my.springmessages", message);
    return "Message \"%s\" was send!".formatted(message);
  }


}

package ru.vlapin.experiments.rabbitmqluxoftcourse.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReceiveMessageHandler {

  @SneakyThrows
  @SuppressWarnings("unused")
  public final void handleMessage(@NotNull String messageBody) {
    log.info("Message received!");
    log.info(messageBody);
  }

}

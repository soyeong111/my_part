/*
 * package com.study.bookspace;
 * 
 * import org.springframework.stereotype.Service; import
 * reactor.core.publisher.Mono; import
 * com.linecorp.bot.model.message.PushMessage; import
 * com.linecorp.bot.client.LineMessagingClient;
 * 
 * import lombok.AllArgsConstructor; import lombok.extern.slf4j.Slf4j;
 * 
 * @Slf4j
 * 
 * @AllArgsConstructor
 * 
 * @Service public class MessengerService { private final LineMessagingClient
 * lineMessagingClient;
 * 
 * public Mono<Void> publishTextMessage(String message) { return
 * publishMessage("모노 타입의 PushMessage를 만들어보자."); }
 * 
 * private Mono<Void> publishMessage(Mono<PushMessage> pushMessageMono) { return
 * pushMessageMono .flatMap(pushMessage ->
 * Mono.fromCompletionStage(lineMessagingClient.pushMessage(pushMessage)))
 * .flatMap(botApiResponse -> Mono.empty()); } }
 */
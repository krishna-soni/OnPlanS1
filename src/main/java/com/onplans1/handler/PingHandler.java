package com.onplans1.handler;

import io.micrometer.core.annotation.Timed;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
//@XRayEnabled // <== uncomment this to enable x-ray, but currently getting error, so disabled. Need to debug
public class PingHandler {

  @Timed(value = "onplan.time", description = "Time taken to return ping")
  public Mono<ServerResponse> ping(ServerRequest serverRequest) {

    return ServerResponse.ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(
        Mono.just("pong....")
          .log(), String.class
      );
  }
}

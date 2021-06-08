package com.onplans1.handler;

import com.onplans1.common.OnPlanS1ServiceConfig;
import com.onplans1.common.OnPlanServiceConfig;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
//@XRayEnabled // <== uncomment this to enable x-ray, but currently getting error, so disabled. Need to debug
public class PingHandler {

  @Autowired
  private OnPlanServiceConfig onPlanServiceConfig;

  @Autowired
  private OnPlanS1ServiceConfig onPlanS1ServiceConfig;

  @Timed(value = "onplans1.time", description = "Time taken to return ping")
  public Mono<ServerResponse> ping(ServerRequest serverRequest) {

    return ServerResponse.ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(
        Mono.just(onPlanS1ServiceConfig.getMessage() + ":: pong....")
          .log(), String.class
      );
  }

  @Timed(value = "onplans1.time", description = "Time taken to return service request")
  public Mono<ServerResponse> pingRemote(ServerRequest serverRequest) {

    String onplan_base_uri = onPlanServiceConfig.getBaseUrl() + "/ping";
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(onplan_base_uri, String.class);
    return ServerResponse.ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(
        Mono.just(result)
          .log(), String.class
      );
  }
}

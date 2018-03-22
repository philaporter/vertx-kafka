package com.philaporter.handlers;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class HttpHandler {

  public static final String TRIGGER = "trigger";
  private Vertx vertx;
  private EventBus eb;

  public HttpHandler(Vertx vertx) {
    this.vertx = vertx;
    eb = this.vertx.eventBus();
  }

  //  public void handleTrigger(RoutingContext routingContext) {
  //    eb.publish(TRIGGER, "yessss");
  //    routingContext.response().end();
  //  }

  public void handleTrigger(RoutingContext routingContext) {
    HttpServerResponse response = routingContext.response();
    JsonObject json = routingContext.getBodyAsJson();
    eb.publish(TRIGGER, json);
    routingContext.response().end();
  }
}

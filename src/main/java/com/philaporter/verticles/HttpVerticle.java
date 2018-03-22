package com.philaporter.verticles;

import com.philaporter.handlers.HttpHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class HttpVerticle extends AbstractVerticle {

  private EventBus eb;

  public void start() {
    eb = vertx.eventBus();
    final HttpHandler handler = new HttpHandler(vertx);
    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());
    router.post("/trigger").handler(handler::handleTrigger);
    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
  }
}

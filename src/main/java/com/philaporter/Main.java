package com.philaporter;

import com.philaporter.verticles.HttpVerticle;
import com.philaporter.verticles.KafkaProducerVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class Main {

  public static void main(String[] args) {

    printBanner();
    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(
        KafkaProducerVerticle.class.getName(),
        res -> {
          if (res.succeeded()) {
            System.out.println(
                "Successfully deployed KafkaProducerVerticle with id: " + res.result());
            vertx.deployVerticle(
                HttpVerticle.class.getName(),
                res2 -> {
                  if (res2.succeeded()) {
                    System.out.println(
                        "Successfully deployed HttpVerticle with id: " + res2.result());
                  } else {
                    System.out.println("Failed to deploy HttpVerticle");
                  }
                });
          } else {
            System.out.println("Failed to deploy KafkaProducerVerticle");
          }
        });
  }

  public static void printBanner() {
    System.out.println("                        _              ");
    System.out.println("                       / |_            ");
    System.out.println(" _   __  .---.  _ .--.`| |-'   _   __  ");
    System.out.println("[ \\ [  ]/ /__\\\\[ `/'`\\]| |    [ \\ [  ] ");
    System.out.println(" \\ \\/ / | \\__., | |    | |, _  > '  <  ");
    System.out.println("  \\__/   '.__.'[___]   \\__/(_)[__]`\\_] ");
    System.out.println("        __        _                    ");
    System.out.println("       [  |      (_)                   ");
    System.out.println(" .--.   | |--.   __   ____             ");
    System.out.println("( (`\\]  | .-. | [  | [_   ]            ");
    System.out.println(" `'.'.  | | | |  | |  .' /_            ");
    System.out.println("[\\__) )[___]|__][___][_____]           ");
    System.out.println("                                       ");
  }
}

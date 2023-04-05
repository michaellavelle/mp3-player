package org.ml4j.thymeleaf.bootstrap;

import javafx.application.Platform;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        // Application entry-point
        for (Handler handler : Logger.getLogger("javafx").getHandlers()) {
            handler.setLevel(Level.OFF);
        }

        SpringApplication.run(Application.class, args);
    }

    public void run(String... args) throws Exception {
        startPlatform();
    }

    private void startPlatform() {
        Platform.startup(new Runnable() {
            @Override
            public void run() {
                System.out.println("Starting media player");
            }
        });
    }}

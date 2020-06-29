package fr.eulbobo.bloomdemo.items;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import fr.eulbobo.bloomdemo.Key;
import fr.eulbobo.bloomdemo.Tunnel;

public class HttpKeyCheck implements Key {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpKeyCheck.class);

    private final Consumer<ResponseEntity<String>> consumer;

    private final String key;

    private boolean answered = false;

    public HttpKeyCheck(final Consumer<ResponseEntity<String>> consumer, final String key) {
        this.consumer = consumer;
        this.key = key;
    }

    @Override
    public void keyAlreadyExists() {
        LOGGER.info("key exists");
        consumer.accept(ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Key already exists"));
        answered = true;
    }

    @Override
    public void keyAdded() {
        LOGGER.info("key added successfully");
        consumer.accept(ResponseEntity.status(HttpStatus.OK).body("Key added"));
        answered = true;
    }

    @Override
    public void sendKeyTo(final Tunnel pTunnel) {
        pTunnel.send(this);
        while (!answered) {
            LOGGER.debug("waiting for answer");
        }
    }

    @Override
    public String getKey() {
        return key;
    }

}

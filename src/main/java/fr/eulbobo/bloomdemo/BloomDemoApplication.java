package fr.eulbobo.bloomdemo;

import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import fr.eulbobo.bloomdemo.sync.Transfert;
import fr.eulbobo.bloomdemo.worker.BloomKeyRunner;

@SpringBootApplication
public class BloomDemoApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BloomDemoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void launchRunner() {
        Executors.newFixedThreadPool(1).execute(new BloomKeyRunner(Transfert.getQueue()));
    }

}

package fr.eulbobo.bloomdemo.worker;

import java.nio.charset.Charset;
import java.util.concurrent.LinkedTransferQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import fr.eulbobo.bloomdemo.Key;

public class BloomKeyRunner implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(BloomKeyRunner.class);

    private final LinkedTransferQueue<Key> link;

    public BloomKeyRunner(final LinkedTransferQueue<Key> link) {
        this.link = link;
    }

    @Override
    public void run() {
        final BloomFilter<String> filter = BloomFilter.create(
                Funnels.stringFunnel(Charset.defaultCharset()),
                500_000,
                0.01);

        LOGGER.info("runner started");

        while (true) {
            final Key readKey = link.poll();
            if (readKey != null) {

                final String keyValue = readKey.getKey();
                if (filter.mightContain(keyValue)) {
                    readKey.keyAlreadyExists();
                } else {
                    filter.put(keyValue);
                    readKey.keyAdded();
                }
            }

        }

    }

}

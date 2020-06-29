package fr.eulbobo.bloomdemo.sync;

import java.util.concurrent.LinkedTransferQueue;

import fr.eulbobo.bloomdemo.Key;

public class Transfert {

    private static final LinkedTransferQueue<Key> LINK = new LinkedTransferQueue<>();

    public static final LinkedTransferQueue<Key> getQueue() {
        return LINK;
    }
}

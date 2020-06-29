package fr.eulbobo.bloomdemo.items;

import java.util.concurrent.LinkedTransferQueue;

import fr.eulbobo.bloomdemo.Key;
import fr.eulbobo.bloomdemo.Tunnel;

public class SyncTunnel implements Tunnel {

    private final LinkedTransferQueue<Key> link;

    public SyncTunnel(final LinkedTransferQueue<Key> link) {
        this.link = link;
    }

    @Override
    public void send(final Key key) {
        link.put(key);
    }

}

package fr.eulbobo.bloomdemo;

public interface Key {

    String getKey();

    void keyAlreadyExists();

    void keyAdded();

    void sendKeyTo(Tunnel pTunnel);
}

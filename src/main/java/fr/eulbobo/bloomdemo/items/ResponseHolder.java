package fr.eulbobo.bloomdemo.items;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ResponseHolder<T> implements Supplier<T>, Consumer<T> {

    private T element;

    @Override
    public void accept(final T pT) {
        element = pT;
    }

    @Override
    public T get() {
        return element;
    }

}

package com.alevel.sandbox.threads.changelistener;

import com.alevel.sandbox.threads.stopping.Stoppable;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class ChangeListeningTask implements Runnable, Stoppable {

    private final AtomicBoolean running;

    private final AtomicReference<Object> currentState;

    private final AtomicReference<Object> snapshot;

    private final Consumer<Object> onChange;

    public ChangeListeningTask(Object initialState, Consumer<Object> onChange) {
        this.onChange = onChange;
        running = new AtomicBoolean(true);
        currentState = new AtomicReference<>(initialState);
        snapshot = new AtomicReference<>(initialState);
    }

    @Override
    public void run() {
        while (running.get()) {
            Object newValue = currentState.get();
            Object oldValue = snapshot.get();
            if (!Objects.equals(oldValue, newValue)) {
                snapshot.set(newValue);
                onChange.accept(newValue);
            }
        }
    }

    public void update(Object newValue) {
        currentState.set(newValue);
    }


    @Override
    public void stop() {
        running.set(false);
    }
}

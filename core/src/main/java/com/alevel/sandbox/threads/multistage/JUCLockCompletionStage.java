package com.alevel.sandbox.threads.multistage;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JUCLockCompletionStage implements CompletionState {

    private final Lock lock = new ReentrantLock();

    private final Condition completionCond = lock.newCondition();

    private final Map<Stage, Condition> stageConditions = new EnumMap<>(Stage.class);

    private final Set<Stage> incomplete = EnumSet.allOf(Stage.class);

    @Override
    public void completeStage(Stage stage) {
        if (lock.tryLock()) {
            try {
                if (incomplete.remove(stage)) {
                    if (incomplete.isEmpty()) {
                        completionCond.signalAll();
                    }
                    Condition stageCond = stageConditions.get(stage);
                    if (stageCond != null) {
                        stageCond.signalAll();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    @Override
    public void awaitCompletion() throws InterruptedException {
        lock.lock();
        try {
            if (!incomplete.isEmpty()) {
                completionCond.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public void awaitStage(Stage stage) throws InterruptedException {
        lock.lock();
        try {
            if (incomplete.contains(stage)) {
                Condition cond = stageConditions.computeIfAbsent(stage, key -> lock.newCondition());
                cond.await();
            }
        } finally {
            lock.unlock();
        }
    }
}

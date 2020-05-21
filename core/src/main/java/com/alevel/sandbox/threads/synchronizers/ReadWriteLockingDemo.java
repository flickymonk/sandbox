package com.alevel.sandbox.threads.synchronizers;

import java.util.concurrent.locks.*;

public class ReadWriteLockingDemo {

    public static class Matrix3x3 {

        private final int[][] matrix = new int[3][3];

        public int set(int row, int col, int val) {
            int before = matrix[row][col];
            matrix[row][col] = val;
            return before;
        }

        public int get(int row, int col) {
            return this.matrix[row][col];
        }

    }

    public static final class Matrix3x3Mutex extends Matrix3x3 {

        @Override
        public synchronized int set(int row, int col, int val) {
            return super.set(row, col, val);
        }

        @Override
        public synchronized int get(int row, int col) {
            return super.get(row, col);
        }

    }

    public static final class Matrix3x3Locking extends Matrix3x3 {

        private final Lock lock = new ReentrantLock();

        @Override
        public int set(int row, int col, int val) {
            lock.lock();
            try {
                return super.set(row, col, val);
            } finally {
                lock.unlock();
            }
        }

        @Override
        public int get(int row, int col) {
            lock.lock();
            try {
                return super.get(row, col);
            } finally {
                lock.unlock();
            }
        }
    }

    public static final class Matrix3x3ReadWriteLocking extends Matrix3x3 {

        private final ReadWriteLock lock = new ReentrantReadWriteLock();

        @Override
        public int set(int row, int col, int val) {
            if (get(row, col) == val) return val;

            lock.writeLock().lock();
            try {
                return super.set(row, col, val);
            } finally {
                lock.writeLock().unlock();
            }
        }

        @Override
        public int get(int row, int col) {
            lock.readLock().lock();
            try {
                return super.get(row, col);
            } finally {
                lock.readLock().unlock();
            }
        }
    }

    public static final class Matrix3x3OptimisticRWLocking extends Matrix3x3 {

        private final StampedLock lock = new StampedLock();

        @Override
        public int set(int row, int col, int val) {
            long stamp = lock.tryOptimisticRead();
            if (stamp != 0) {
                int old = super.get(row, col);
                if(lock.validate(stamp)) {
                    if (val == old) return val;
                    stamp = lock.tryConvertToWriteLock(stamp);
                    if (stamp == 0) stamp = lock.writeLock();
                    try {
                        return super.set(row, col, val);
                    } finally {
                        lock.unlockWrite(stamp);
                    }
                }
            }
            stamp = lock.tryConvertToReadLock(stamp);
            if (stamp == 0) stamp = lock.readLock();
            try {
                int old = super.get(row, col);
                if (old == val) return val;
                stamp = lock.tryConvertToWriteLock(stamp);
                if (stamp == 0) stamp = lock.writeLock();
                return super.set(row, col, val);
            } finally {
                lock.unlock(stamp);
            }
        }

        @Override
        public int get(int row, int col) {
            long stamp = lock.tryOptimisticRead();
            if (stamp != 0) {
                int value = super.get(row, col);
                if(lock.validate(stamp)) return value;
            }
            stamp = lock.tryConvertToReadLock(stamp);
            if (stamp == 0) stamp = lock.readLock();
            try {
                return super.get(row, col);
            } finally {
                lock.unlockRead(stamp);
            }
        }
    }

}

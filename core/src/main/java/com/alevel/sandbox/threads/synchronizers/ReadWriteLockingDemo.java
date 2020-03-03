package com.alevel.sandbox.threads.synchronizers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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

}

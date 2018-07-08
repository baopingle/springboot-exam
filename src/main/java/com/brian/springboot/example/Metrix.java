package com.brian.springboot.example;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Metrix {

    private static class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            if(this.compareAndSetState(0, 1)){
                this.setExclusiveOwnerThread(current);
                return true;
            }

            return false;
        }

        @Override
        protected boolean tryRelease(int releases) {
            final Thread current = Thread.currentThread();
            if(this.getState() != 1 || this.getExclusiveOwnerThread() != current){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return this.getState() == 1;
        }
    }

    private final Sync sync = new Sync();

    public void lock(){
        sync.acquire(1);
    }

    public void tryLock(){
        sync.tryAcquire(1);
    }

    public void unlock(){
        sync.release(1);
    }

    public boolean isLocked(){
        return sync.isHeldExclusively();
    }
}

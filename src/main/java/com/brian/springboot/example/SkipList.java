package com.brian.springboot.example;

import java.util.Random;

public class SkipList<V> {


    private SkipListEntry<V> head;
    private SkipListEntry<V> tail;

    // the count of total elements
    private int n;

    // the height of this skip list
    private int h;

    private static final Random random = new Random();

    class SkipListEntry<V> {
        public static final String NEF_INF = "-oo";
        public static final String POS_INF = "+oo";

        private String key;
        private V value;

        private SkipListEntry<V> up;
        private SkipListEntry<V> down;
        private SkipListEntry<V> right;
        private SkipListEntry<V> left;

        public SkipListEntry(String key, V value){
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public SkipListEntry<V> getUp() {
            return up;
        }

        public void setUp(SkipListEntry<V> up) {
            this.up = up;
        }

        public SkipListEntry<V> getDown() {
            return down;
        }

        public void setDown(SkipListEntry<V> down) {
            this.down = down;
        }

        public SkipListEntry<V> getRight() {
            return right;
        }

        public void setRight(SkipListEntry<V> right) {
            this.right = right;
        }

        public SkipListEntry<V> getLeft() {
            return left;
        }

        public void setLeft(SkipListEntry<V> left) {
            this.left = left;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SkipListEntry<?> that = (SkipListEntry<?>) o;

            if (!key.equals(that.key)) return false;
            return value != null ? value.equals(that.value) : that.value == null;
        }

        @Override
        public int hashCode() {
            int result = key.hashCode();
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }
    }

    public SkipList() {
        SkipListEntry<V> p1, p2;
        p1 = new SkipListEntry<V>(SkipListEntry.NEF_INF,null);
        p2 = new SkipListEntry<V>(SkipListEntry.POS_INF,null);

        head = p1;
        tail = p2;
        head.right = p2;
        tail.left = p1;

        n = 0;
        h = 0;
    }


    public SkipListEntry<V> findEntry(String key){
        SkipListEntry<V> p = head;
        for(;;){
            while (p.right.key != SkipListEntry.POS_INF && p.right.key.compareTo(key) <= 0){
                p = p.right;
            }

            if(p.down != null){
                p = p.down;
            }else{
                break;
            }
        }

        return p;
    }

    public V put(String key, V value){
        SkipListEntry<V> p, q;
        p = findEntry(key);
        if(p.key.equals(key)){
            V old = p.value;
            p.setValue(value);
            return old;
        }

        q = p.right;
        SkipListEntry<V> newElement = new SkipListEntry<V>(key, value);
        newElement.right = q;
        newElement.left = p;
        p.right = newElement;
        q.left = newElement;

        int i = 0;
        while (random.nextDouble() < 0.5){
            if(i > h){
                SkipListEntry p1, p2;
                p1 = new SkipListEntry(SkipListEntry.NEF_INF, null);
                p2 = new SkipListEntry(SkipListEntry.POS_INF, null);

                h++;
                p1.right = p2;
                p2.left = p1;
                p1.down = head;
                p2.down = tail;
                head.up = p1;
                tail.up = p2;
                head = p1;
                tail = p2;
            }

            while(p.up == null){
                p = p.left;
            }

            p = p.up;
            SkipListEntry e = new SkipListEntry(key,null);
            e.left = p;
            e.right = p.right;
            e.down = newElement;
            newElement.up = e;
            p.right.left = e;
            p.right = e;

            // loop for this new element
            newElement = e;
            i++;
        }
        n = n+1;
        return null;
    }

    public V remove(String key){
        SkipListEntry<V> element = this.findEntry(key);
        if(element.key.equals(key)){
            removeElement(element);
            n--;
            return element.value;
        }
        return null;
    }

    private void removeElement(SkipListEntry<V> element){
        if(element == null){
            return;
        }else{
            SkipListEntry<V> p = element.left;
            SkipListEntry<V> q = element.right;
            SkipListEntry<V> up = element.up;
            p.right = q;
            q.left = p;
            if(p.key.equals(SkipListEntry.NEF_INF) && q.key.equals(SkipListEntry.POS_INF)){
                head.down = null;
                tail.down = null;
                p.up = null;
                q.up = null;
                head = p;
                tail = q;
                h--;
            }
            element.left = null;
            element.right = null;
            up.down = null;
            element.up = null;
            removeElement(up);
        }
    }
}

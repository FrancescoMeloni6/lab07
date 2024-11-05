package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private T[] array;
    private int currentElement;

    public IterableWithPolicyImpl(T[] array){
        this.array = array;
        currentElement = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        
    }

    public class IteratorImpl implements Iterator<T>{ 

        @Override
        public boolean hasNext() {
            return currentElement < array.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            return array[currentElement++];
        }
    }    
}

package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private T[] array;
    private int currentElement;
    private Predicate<T> predicate;

    public IterableWithPolicyImpl(T[] array, Predicate<T> predicate){
        this.array = array;
        currentElement = 0;
        setIterationPolicy(predicate);
    }
    
    public IterableWithPolicyImpl(T[] array){
        this(array, new Predicate<>() {
                @Override
                public boolean test(T elem) {
                    return true;
                }
            }
        );
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.predicate = filter;
    }

    public class IteratorImpl implements Iterator<T>{ 

        @Override
        public boolean hasNext() {
            if (currentElement < array.length && !predicate.test(array[currentElement])) {
                currentElement++;
                hasNext();
            }
            return currentElement < array.length;
        }

        @Override
        public T next() {
            if (currentElement >= array.length) {
                throw new NoSuchElementException();
            }
            
            return array[currentElement++];
        }
    }    
}

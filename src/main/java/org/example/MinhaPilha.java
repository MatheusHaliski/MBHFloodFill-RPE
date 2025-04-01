package org.example;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MinhaPilha<T> {
    private LinkedList<T> lista = new LinkedList<>();

    public void push(T elemento) {
        lista.addLast(elemento);
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("A pilha está vazia!");
        }
        return lista.removeLast();
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }

    public int size() {
        return lista.size();
    }
}

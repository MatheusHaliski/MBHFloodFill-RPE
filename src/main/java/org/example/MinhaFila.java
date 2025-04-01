package org.example;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MinhaFila<T> {
    private LinkedList<T> lista = new LinkedList<>();

    public void enqueue(T elemento) {
        lista.addLast(elemento);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("A fila está vazia!");
        }
        return lista.removeFirst();
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }

    public int size() {
        return lista.size();
    }
}

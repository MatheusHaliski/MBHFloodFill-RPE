package org.example;
// Implementação da lista personalizada
class MinhaArrayList<T> {
    private T[] elementos;
    private int tamanho;

    private static final int CAPACIDADE_INICIAL = 10;

    public MinhaArrayList() {
        elementos = (T[]) new Object[CAPACIDADE_INICIAL];
        tamanho = 0;
    }

    public void add(T elemento) {
        if (tamanho == elementos.length) {
            aumentarCapacidade();
        }
        elementos[tamanho++] = elemento;
    }

    public T remove(int index) {
        if (index < 0 || index >= tamanho) {
            throw new IndexOutOfBoundsException("Índice fora do limite: " + index);
        }
        T elementoRemovido = elementos[index];

        // Desloca os elementos para a esquerda
        for (int i = index; i < tamanho - 1; i++) {
            elementos[i] = elementos[i + 1];
        }

        elementos[--tamanho] = null; // Remove a referência do último elemento
        return elementoRemovido;
    }

    public T get(int index) {
        if (index < 0 || index >= tamanho) {
            throw new IndexOutOfBoundsException("Índice fora do limite: " + index);
        }
        return elementos[index];
    }

    public int size() {
        return tamanho;
    }

    private void aumentarCapacidade() {
        int novaCapacidade = elementos.length * 2;
        elementos = java.util.Arrays.copyOf(elementos, novaCapacidade);
    }
}
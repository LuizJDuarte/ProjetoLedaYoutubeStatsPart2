package src.arvores;

import java.util.Comparator;
import java.util.function.Consumer;

public class ArvoreBinaria<T extends Comparable<T>> {
    private NoArvoreBinaria<T> raiz;

    public void inserir(T elemento, Comparator<T> comparador) {
        raiz = inserirRecursivo(raiz, elemento, comparador);
    }

    private NoArvoreBinaria<T> inserirRecursivo(NoArvoreBinaria<T> atual, T elemento, Comparator<T> comparador) {
        if (atual == null) {
            return new NoArvoreBinaria<>(elemento);
        }
        if (comparador.compare(elemento, atual.getElemento()) < 0) {
            atual.setEsquerda(inserirRecursivo(atual.getEsquerda(), elemento, comparador));
        } else if (comparador.compare(elemento, atual.getElemento()) > 0) {
            atual.setDireita(inserirRecursivo(atual.getDireita(), elemento, comparador));
        }
        return atual;
    }

    public void emOrdem(Consumer<T> consumidor) {
        emOrdemRecursivo(raiz, consumidor);
    }

    private void emOrdemRecursivo(NoArvoreBinaria<T> atual, Consumer<T> consumidor) {
        if (atual != null) {
            emOrdemRecursivo(atual.getEsquerda(), consumidor);
            consumidor.accept(atual.getElemento());
            emOrdemRecursivo(atual.getDireita(), consumidor);
        }
    }

    public void limpar() {
        raiz = null;
    }
}

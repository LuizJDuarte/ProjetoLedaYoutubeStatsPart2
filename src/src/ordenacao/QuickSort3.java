package src.ordenacao;

import src.StringArrayComparable;
import src.listas.ListaDuplamenteEncadeada;
import src.arvores.ArvoreBinaria;
import src.tabelas.TabelaHash;

import java.util.Comparator;

public class QuickSort3<T extends Comparable<T>> implements Ordenacao<T> {

    @Override
    public void ordenar(ListaDuplamenteEncadeada<T> lista, int indice) {
        quickSort(lista, 0, lista.getTamanho() - 1);
    }

    @Override
    public void ordenar(TabelaHash<String, T> tabela, int indice) {
        ListaDuplamenteEncadeada<T> lista = new ListaDuplamenteEncadeada<>();
        for (var bucket : tabela.getEntradas()) {
            for (var entrada : bucket) {
                lista.adicionar(entrada.getValor());
            }
        }
        quickSort(lista, 0, lista.getTamanho() - 1);
        int i = 0;
        for (var bucket : tabela.getEntradas()) {
            for (var entrada : bucket) {
                entrada.setValor(lista.get(i++));
            }
        }
    }

    @Override
    public void ordenar(ArvoreBinaria<T> arvore, int indice) {
        ListaDuplamenteEncadeada<T> lista = new ListaDuplamenteEncadeada<>();
        arvore.emOrdem(lista::adicionar);
        quickSort(lista, 0, lista.getTamanho() - 1);
        arvore.limpar();
        for (int i = 0; i < lista.getTamanho(); i++) {
            arvore.inserir(lista.get(i), Comparator.comparing(arr -> ((StringArrayComparable) arr).getArray()[indice]));
        }
    }

    private void quickSort(ListaDuplamenteEncadeada<T> lista, int esquerda, int direita) {
        if (esquerda < direita) {
            int pivo = particionar(lista, esquerda, direita);
            quickSort(lista, esquerda, pivo - 1);
            quickSort(lista, pivo + 1, direita);
        }
    }

    private int particionar(ListaDuplamenteEncadeada<T> lista, int esquerda, int direita) {
        int meio = (esquerda + direita) / 2;
        T pivo = medianaDeTres(lista, esquerda, meio, direita);
        lista.set(meio, lista.get(direita));
        lista.set(direita, pivo);
        int i = (esquerda - 1);
        for (int j = esquerda; j < direita; j++) {
            if (lista.get(j).compareTo(pivo) <= 0) {
                i++;
                T troca = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, troca);
            }
        }
        T troca = lista.get(i + 1);
        lista.set(i + 1, lista.get(direita));
        lista.set(direita, troca);
        return i + 1;
    }

    private T medianaDeTres(ListaDuplamenteEncadeada<T> lista, int esquerda, int meio, int direita) {
        if (lista.get(esquerda).compareTo(lista.get(meio)) > 0) {
            trocar(lista, esquerda, meio);
        }
        if (lista.get(esquerda).compareTo(lista.get(direita)) > 0) {
            trocar(lista, esquerda, direita);
        }
        if (lista.get(meio).compareTo(lista.get(direita)) > 0) {
            trocar(lista, meio, direita);
        }
        return lista.get(meio);
    }

    private void trocar(ListaDuplamenteEncadeada<T> lista, int i, int j) {
        T temp = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temp);
    }
}

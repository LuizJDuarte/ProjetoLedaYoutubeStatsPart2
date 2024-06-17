package src.ordenacao;

import src.StringArrayComparable;
import src.listas.ListaDuplamenteEncadeada;
import src.arvores.ArvoreBinaria;
import src.tabelas.TabelaHash;

import java.util.Comparator;

public class HeapSort<T extends Comparable<T>> implements Ordenacao<T> {

    @Override
    public void ordenar(ListaDuplamenteEncadeada<T> lista, int indice) {
        int tamanho = lista.getTamanho();
        for (int i = tamanho / 2 - 1; i >= 0; i--) {
            heapify(lista, tamanho, i);
        }
        for (int i = tamanho - 1; i >= 0; i--) {
            T temp = lista.get(0);
            lista.set(0, lista.get(i));
            lista.set(i, temp);
            heapify(lista, i, 0);
        }
    }

    @Override
    public void ordenar(TabelaHash<String, T> tabela, int indice) {
        ListaDuplamenteEncadeada<T> lista = new ListaDuplamenteEncadeada<>();
        for (var bucket : tabela.getEntradas()) {
            for (var entrada : bucket) {
                lista.adicionar(entrada.getValor());
            }
        }
        ordenar(lista, indice);
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
        ordenar(lista, indice);
        arvore.limpar();
        for (int i = 0; i < lista.getTamanho(); i++) {
            arvore.inserir(lista.get(i), Comparator.comparing(arr -> ((StringArrayComparable) arr).getArray()[indice]));
        }
    }

    private void heapify(ListaDuplamenteEncadeada<T> lista, int tamanho, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;
        if (esquerda < tamanho && lista.get(esquerda).compareTo(lista.get(maior)) > 0) {
            maior = esquerda;
        }
        if (direita < tamanho && lista.get(direita).compareTo(lista.get(maior)) > 0) {
            maior = direita;
        }
        if (maior != i) {
            T troca = lista.get(i);
            lista.set(i, lista.get(maior));
            lista.set(maior, troca);
            heapify(lista, tamanho, maior);
        }
    }
}

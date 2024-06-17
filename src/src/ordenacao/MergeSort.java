package src.ordenacao;

import src.StringArrayComparable;
import src.listas.ListaDuplamenteEncadeada;
import src.arvores.ArvoreBinaria;
import src.tabelas.TabelaHash;

import java.util.Comparator;

public class MergeSort<T extends Comparable<T>> implements Ordenacao<T> {

    @Override
    public void ordenar(ListaDuplamenteEncadeada<T> lista, int indice) {
        mergeSort(lista, 0, lista.getTamanho() - 1);
    }

    @Override
    public void ordenar(TabelaHash<String, T> tabela, int indice) {
        ListaDuplamenteEncadeada<T> lista = new ListaDuplamenteEncadeada<>();
        for (var bucket : tabela.getEntradas()) {
            for (var entrada : bucket) {
                lista.adicionar(entrada.getValor());
            }
        }
        mergeSort(lista, 0, lista.getTamanho() - 1);
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
        mergeSort(lista, 0, lista.getTamanho() - 1);
        arvore.limpar();
        for (int i = 0; i < lista.getTamanho(); i++) {
            arvore.inserir(lista.get(i), Comparator.comparing(arr -> ((StringArrayComparable) arr).getArray()[indice]));
        }
    }

    private void mergeSort(ListaDuplamenteEncadeada<T> lista, int esquerda, int direita) {
        if (esquerda < direita) {
            int meio = (esquerda + direita) / 2;
            mergeSort(lista, esquerda, meio);
            mergeSort(lista, meio + 1, direita);
            merge(lista, esquerda, meio, direita);
        }
    }

    private void merge(ListaDuplamenteEncadeada<T> lista, int esquerda, int meio, int direita) {
        int n1 = meio - esquerda + 1;
        int n2 = direita - meio;
        ListaDuplamenteEncadeada<T> esquerdaLista = new ListaDuplamenteEncadeada<>();
        ListaDuplamenteEncadeada<T> direitaLista = new ListaDuplamenteEncadeada<>();
        for (int i = 0; i < n1; i++) {
            esquerdaLista.adicionar(lista.get(esquerda + i));
        }
        for (int j = 0; j < n2; j++) {
            direitaLista.adicionar(lista.get(meio + 1 + j));
        }
        int i = 0, j = 0;
        int k = esquerda;
        while (i < n1 && j < n2) {
            if (esquerdaLista.get(i).compareTo(direitaLista.get(j)) <= 0) {
                lista.set(k, esquerdaLista.get(i));
                i++;
            } else {
                lista.set(k, direitaLista.get(j));
                j++;
            }
            k++;
        }
        while (i < n1) {
            lista.set(k, esquerdaLista.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            lista.set(k, direitaLista.get(j));
            j++;
            k++;
        }
    }
}

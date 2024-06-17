package src.ordenacao;

import src.StringArrayComparable;
import src.listas.ListaDuplamenteEncadeada;
import src.arvores.ArvoreBinaria;
import src.tabelas.TabelaHash;

import java.util.Comparator;

public class SelectionSort<T extends Comparable<T>> implements Ordenacao<T> {

    @Override
    public void ordenar(ListaDuplamenteEncadeada<T> lista, int indice) {
        int tamanho = lista.getTamanho();
        for (int i = 0; i < tamanho - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < tamanho; j++) {
                if (lista.get(j).compareTo(lista.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            T temp = lista.get(minIndex);
            lista.set(minIndex, lista.get(i));
            lista.set(i, temp);
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
}

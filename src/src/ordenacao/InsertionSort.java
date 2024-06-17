package src.ordenacao;

import src.StringArrayComparable;
import src.listas.ListaDuplamenteEncadeada;
import src.arvores.ArvoreBinaria;
import src.tabelas.TabelaHash;

import java.util.Comparator;

public class InsertionSort<T extends Comparable<T>> implements Ordenacao<T> {

    @Override
    public void ordenar(ListaDuplamenteEncadeada<T> lista, int indice) {
        int tamanho = lista.getTamanho();
        for (int i = 1; i < tamanho; i++) {
            T chave = lista.get(i);
            int j = i - 1;
            while (j >= 0 && lista.get(j).compareTo(chave) > 0) {
                lista.set(j + 1, lista.get(j));
                j = j - 1;
            }
            lista.set(j + 1, chave);
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

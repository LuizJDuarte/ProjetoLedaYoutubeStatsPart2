package src.ordenacao;

import src.StringArrayComparable;
import src.listas.ListaDuplamenteEncadeada;
import src.arvores.ArvoreBinaria;
import src.tabelas.TabelaHash;

public interface Ordenacao<T extends Comparable<T>> {
    void ordenar(ListaDuplamenteEncadeada<T> lista, int indice);
    void ordenar(TabelaHash<String, T> tabela, int indice);
    void ordenar(ArvoreBinaria<T> arvore, int indice);
}

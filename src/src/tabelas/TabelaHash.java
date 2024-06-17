package src.tabelas;

import java.util.LinkedList;

public class TabelaHash<K, V> {
    public static class Entrada<K, V> {
        private K chave;
        private V valor;

        public Entrada(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }

        public K getChave() {
            return chave;
        }

        public V getValor() {
            return valor;
        }

        public void setValor(V valor) {
            this.valor = valor;
        }
    }

    private LinkedList<Entrada<K, V>>[] tabela;
    private int capacidade;

    @SuppressWarnings("unchecked")
    public TabelaHash(int capacidade) {
        this.capacidade = capacidade;
        this.tabela = new LinkedList[capacidade];
        for (int i = 0; i < capacidade; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    public void inserir(K chave, V valor) {
        int indice = chave.hashCode() % capacidade;
        for (Entrada<K, V> entrada : tabela[indice]) {
            if (entrada.getChave().equals(chave)) {
                entrada.setValor(valor);
                return;
            }
        }
        tabela[indice].add(new Entrada<>(chave, valor));
    }

    public V buscar(K chave) {
        int indice = chave.hashCode() % capacidade;
        for (Entrada<K, V> entrada : tabela[indice]) {
            if (entrada.getChave().equals(chave)) {
                return entrada.getValor();
            }
        }
        return null;
    }

    public LinkedList<Entrada<K, V>>[] getEntradas() {
        return tabela;
    }
}

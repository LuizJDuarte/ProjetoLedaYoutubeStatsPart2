package src.tabelas;

public class NoHash<K, V> {
    private K chave;
    private V valor;
    private NoHash<K, V> proximo;

    public NoHash(K chave, V valor) {
        this.chave = chave;
        this.valor = valor;
        this.proximo = null;
    }

    public K getChave() {
        return chave;
    }

    public V getValor() {
        return valor;
    }

    public NoHash<K, V> getProximo() {
        return proximo;
    }

    public void setProximo(NoHash<K, V> proximo) {
        this.proximo = proximo;
    }
}

package src.listas;

public class ListaDuplamenteEncadeada<T> {
    private NoDuplo<T> cabeca;
    private NoDuplo<T> cauda;
    private int tamanho;

    public void adicionar(T elemento) {
        NoDuplo<T> novoNo = new NoDuplo<>(elemento);
        if (cauda == null) {
            cabeca = cauda = novoNo;
        } else {
            cauda.setProximo(novoNo);
            novoNo.setAnterior(cauda);
            cauda = novoNo;
        }
        tamanho++;
    }

    public T get(int index) {
        NoDuplo<T> atual = cabeca;
        for (int i = 0; i < index; i++) {
            atual = atual.getProximo();
        }
        return atual.getElemento();
    }

    public void set(int index, T elemento) {
        NoDuplo<T> atual = cabeca;
        for (int i = 0; i < index; i++) {
            atual = atual.getProximo();
        }
        atual.setElemento(elemento);
    }

    public int getTamanho() {
        return tamanho;
    }

    public NoDuplo<T> getCabeca() {
        return cabeca;
    }

    public static class NoDuplo<T> {
        private T elemento;
        private NoDuplo<T> proximo;
        private NoDuplo<T> anterior;

        public NoDuplo(T elemento) {
            this.elemento = elemento;
        }

        public T getElemento() {
            return elemento;
        }

        public void setElemento(T elemento) {
            this.elemento = elemento;
        }

        public NoDuplo<T> getProximo() {
            return proximo;
        }

        public void setProximo(NoDuplo<T> proximo) {
            this.proximo = proximo;
        }

        public NoDuplo<T> getAnterior() {
            return anterior;
        }

        public void setAnterior(NoDuplo<T> anterior) {
            this.anterior = anterior;
        }
    }
}

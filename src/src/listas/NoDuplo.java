package src.listas;

public class NoDuplo<T> {
    private T elemento;
    private NoDuplo<T> proximo;
    private NoDuplo<T> anterior;

    public NoDuplo(T elemento) {
        this.elemento = elemento;
        this.proximo = null;
        this.anterior = null;
    }

    public T getElemento() {
        return elemento;
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

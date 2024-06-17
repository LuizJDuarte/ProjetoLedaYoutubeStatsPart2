package src.arvores;

public class NoArvoreBinaria<T> {
    private T elemento;
    private NoArvoreBinaria<T> esquerda;
    private NoArvoreBinaria<T> direita;

    public NoArvoreBinaria(T elemento) {
        this.elemento = elemento;
        this.esquerda = null;
        this.direita = null;
    }

    public T getElemento() {
        return elemento;
    }

    public NoArvoreBinaria<T> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoArvoreBinaria<T> esquerda) {
        this.esquerda = esquerda;
    }

    public NoArvoreBinaria<T> getDireita() {
        return direita;
    }

    public void setDireita(NoArvoreBinaria<T> direita) {
        this.direita = direita;
    }
}

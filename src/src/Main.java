package src;

import src.ordenacao.*;
import src.listas.ListaDuplamenteEncadeada;
import src.arvores.ArvoreBinaria;
import src.tabelas.TabelaHash;
import src.listas.*;
import src.arvores.*;
import src.tabelas.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String caminhoArquivo = "C:/Users/Luiz José/git/repository4/ProjetoLeda/videos_T1.csv";

        try {
            // Teste para Lista Duplamente Encadeada
            executarOrdenacaoListaDupla(caminhoArquivo, "comment_count");
            executarOrdenacaoListaDupla(caminhoArquivo, "channel_title");
            executarOrdenacaoListaDupla(caminhoArquivo, "trending_full_date");

            // Teste para Tabela Hash
            executarOrdenacaoTabelaHash(caminhoArquivo, "comment_count");
            executarOrdenacaoTabelaHash(caminhoArquivo, "channel_title");
            executarOrdenacaoTabelaHash(caminhoArquivo, "trending_full_date");

            // Teste para Árvore Binária
            executarOrdenacaoArvore(caminhoArquivo, "comment_count");
            executarOrdenacaoArvore(caminhoArquivo, "channel_title");
            executarOrdenacaoArvore(caminhoArquivo, "trending_full_date");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void executarOrdenacaoListaDupla(String caminhoArquivo, String campoOrdenacao) throws IOException {
        System.out.println("Lendo dados do arquivo: " + caminhoArquivo);
        ListaDuplamenteEncadeada<StringArrayComparable> dados = UtilCSV.lerCSVListaDupla(caminhoArquivo);
        System.out.println("Dados lidos para Lista Duplamente Encadeada.");

        ordenarEDepositarListaDupla(dados, new QuickSort<>(), campoOrdenacao, "QuickSort", "ListaDuplamenteEncadeada", caminhoArquivo);
        ordenarEDepositarListaDupla(dados, new QuickSort3<>(), campoOrdenacao, "QuickSort3", "ListaDuplamenteEncadeada", caminhoArquivo);
        ordenarEDepositarListaDupla(dados, new MergeSort<>(), campoOrdenacao, "MergeSort", "ListaDuplamenteEncadeada", caminhoArquivo);
        ordenarEDepositarListaDupla(dados, new HeapSort<>(), campoOrdenacao, "HeapSort", "ListaDuplamenteEncadeada", caminhoArquivo);
        ordenarEDepositarListaDupla(dados, new SelectionSort<>(), campoOrdenacao, "SelectionSort", "ListaDuplamenteEncadeada", caminhoArquivo);
        ordenarEDepositarListaDupla(dados, new InsertionSort<>(), campoOrdenacao, "InsertionSort", "ListaDuplamenteEncadeada", caminhoArquivo);
    }

    private static void executarOrdenacaoTabelaHash(String caminhoArquivo, String campoOrdenacao) throws IOException {
        System.out.println("Lendo dados do arquivo: " + caminhoArquivo);
        TabelaHash<String, StringArrayComparable> dados = UtilCSV.lerCSVHash(caminhoArquivo);
        System.out.println("Dados lidos para Tabela Hash.");

        ordenarEDepositarTabelaHash(dados, new QuickSort<>(), campoOrdenacao, "QuickSort", "TabelaHash", caminhoArquivo);
        ordenarEDepositarTabelaHash(dados, new QuickSort3<>(), campoOrdenacao, "QuickSort3", "TabelaHash", caminhoArquivo);
        ordenarEDepositarTabelaHash(dados, new MergeSort<>(), campoOrdenacao, "MergeSort", "TabelaHash", caminhoArquivo);
        ordenarEDepositarTabelaHash(dados, new HeapSort<>(), campoOrdenacao, "HeapSort", "TabelaHash", caminhoArquivo);
        ordenarEDepositarTabelaHash(dados, new SelectionSort<>(), campoOrdenacao, "SelectionSort", "TabelaHash", caminhoArquivo);
        ordenarEDepositarTabelaHash(dados, new InsertionSort<>(), campoOrdenacao, "InsertionSort", "TabelaHash", caminhoArquivo);
    }

    private static void executarOrdenacaoArvore(String caminhoArquivo, String campoOrdenacao) throws IOException {
        System.out.println("Lendo dados do arquivo: " + caminhoArquivo);
        ArvoreBinaria<StringArrayComparable> dados = UtilCSV.lerCSVArvore(caminhoArquivo);
        System.out.println("Dados lidos para Árvore Binária.");

        ordenarEDepositarArvore(dados, new QuickSort<>(), campoOrdenacao, "QuickSort", "ArvoreBinaria", caminhoArquivo);
        ordenarEDepositarArvore(dados, new QuickSort3<>(), campoOrdenacao, "QuickSort3", "ArvoreBinaria", caminhoArquivo);
        ordenarEDepositarArvore(dados, new MergeSort<>(), campoOrdenacao, "MergeSort", "ArvoreBinaria", caminhoArquivo);
        ordenarEDepositarArvore(dados, new HeapSort<>(), campoOrdenacao, "HeapSort", "ArvoreBinaria", caminhoArquivo);
        ordenarEDepositarArvore(dados, new SelectionSort<>(), campoOrdenacao, "SelectionSort", "ArvoreBinaria", caminhoArquivo);
        ordenarEDepositarArvore(dados, new InsertionSort<>(), campoOrdenacao, "InsertionSort", "ArvoreBinaria", caminhoArquivo);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void ordenarEDepositarListaDupla(ListaDuplamenteEncadeada<T> dados, Ordenacao<T> algoritmoOrdenacao, String campoOrdenacao, String nomeAlgoritmo, String estruturaDados, String caminhoArquivo) throws IOException {
        String caso = "melhor";
        System.out.println("Ordenando dados pelo campo: " + campoOrdenacao + " usando " + nomeAlgoritmo + " para " + estruturaDados + " no caso " + caso);
        long inicio = System.currentTimeMillis();
        algoritmoOrdenacao.ordenar(dados, getIndiceCampo(campoOrdenacao));
        long fim = System.currentTimeMillis();
        System.out.println("Tempo de execução para " + nomeAlgoritmo + " no caso " + caso + ": " + (fim - inicio) + "ms");

        String caminhoSaida = caminhoArquivo.replace(".csv", "_" + campoOrdenacao + "_" + nomeAlgoritmo + "_" + caso + "_" + estruturaDados + ".csv");
        UtilCSV.escreverCSVListaDupla(caminhoSaida, (ListaDuplamenteEncadeada<StringArrayComparable>) dados);
        System.out.println("Dados ordenados salvos em: " + caminhoSaida);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void ordenarEDepositarTabelaHash(TabelaHash<String, T> dados, Ordenacao<T> algoritmoOrdenacao, String campoOrdenacao, String nomeAlgoritmo, String estruturaDados, String caminhoArquivo) throws IOException {
        String caso = "melhor";
        System.out.println("Ordenando dados pelo campo: " + campoOrdenacao + " usando " + nomeAlgoritmo + " para " + estruturaDados + " no caso " + caso);
        long inicio = System.currentTimeMillis();
        algoritmoOrdenacao.ordenar(dados, getIndiceCampo(campoOrdenacao));
        long fim = System.currentTimeMillis();
        System.out.println("Tempo de execução para " + nomeAlgoritmo + " no caso " + caso + ": " + (fim - inicio) + "ms");

        String caminhoSaida = caminhoArquivo.replace(".csv", "_" + campoOrdenacao + "_" + nomeAlgoritmo + "_" + caso + "_" + estruturaDados + ".csv");
        UtilCSV.escreverCSVHash(caminhoSaida, (TabelaHash<String, StringArrayComparable>) dados);
        System.out.println("Dados ordenados salvos em: " + caminhoSaida);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void ordenarEDepositarArvore(ArvoreBinaria<T> dados, Ordenacao<T> algoritmoOrdenacao, String campoOrdenacao, String nomeAlgoritmo, String estruturaDados, String caminhoArquivo) throws IOException {
        String caso = "melhor";
        System.out.println("Ordenando dados pelo campo: " + campoOrdenacao + " usando " + nomeAlgoritmo + " para " + estruturaDados + " no caso " + caso);
        long inicio = System.currentTimeMillis();
        algoritmoOrdenacao.ordenar(dados, getIndiceCampo(campoOrdenacao));
        long fim = System.currentTimeMillis();
        System.out.println("Tempo de execução para " + nomeAlgoritmo + " no caso " + caso + ": " + (fim - inicio) + "ms");

        String caminhoSaida = caminhoArquivo.replace(".csv", "_" + campoOrdenacao + "_" + nomeAlgoritmo + "_" + caso + "_" + estruturaDados + ".csv");
        UtilCSV.escreverCSVArvore(caminhoSaida, (ArvoreBinaria<StringArrayComparable>) dados);
        System.out.println("Dados ordenados salvos em: " + caminhoSaida);
    }

    private static int getIndiceCampo(String campoOrdenacao) {
        switch (campoOrdenacao) {
            case "comment_count":
                return 0;
            case "channel_title":
                return 1;
            case "trending_full_date":
                return 2;
            default:
                throw new IllegalArgumentException("Campo de ordenação inválido");
        }
    }
}

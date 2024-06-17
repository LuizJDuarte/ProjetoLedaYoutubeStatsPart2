package src;

import src.listas.ListaDuplamenteEncadeada;
import src.arvores.ArvoreBinaria;
import src.tabelas.TabelaHash;
import src.arvores.*;
import src.listas.*;
import src.ordenacao.*;
import src.tabelas.*;

import java.io.*;
import java.util.LinkedList;

public class UtilCSV {

    public static ListaDuplamenteEncadeada<StringArrayComparable> lerCSVListaDupla(String caminhoArquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        ListaDuplamenteEncadeada<StringArrayComparable> dados = new ListaDuplamenteEncadeada<>();

        while ((linha = reader.readLine()) != null) {
            dados.adicionar(new StringArrayComparable(linha.split(",")));
        }

        reader.close();
        return dados;
    }

    public static void escreverCSVListaDupla(String caminhoArquivo, ListaDuplamenteEncadeada<StringArrayComparable> dados) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo));

        ListaDuplamenteEncadeada.NoDuplo<StringArrayComparable> atual = dados.getCabeca();
        while (atual != null) {
            writer.write(String.join(",", atual.getElemento().getArray()));
            writer.newLine();
            atual = atual.getProximo();
        }

        writer.close();
    }

    public static TabelaHash<String, StringArrayComparable> lerCSVHash(String caminhoArquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        TabelaHash<String, StringArrayComparable> dados = new TabelaHash<>(100);

        while ((linha = reader.readLine()) != null) {
            String[] elementos = linha.split(",");
            dados.inserir(elementos[0], new StringArrayComparable(elementos));
        }

        reader.close();
        return dados;
    }

    public static void escreverCSVHash(String caminhoArquivo, TabelaHash<String, StringArrayComparable> dados) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo));

        for (var bucket : dados.getEntradas()) {
            for (var entrada : bucket) {
                writer.write(String.join(",", entrada.getValor().getArray()));
                writer.newLine();
            }
        }

        writer.close();
    }

    public static ArvoreBinaria<StringArrayComparable> lerCSVArvore(String caminhoArquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        ArvoreBinaria<StringArrayComparable> dados = new ArvoreBinaria<>();

        while ((linha = reader.readLine()) != null) {
            dados.inserir(new StringArrayComparable(linha.split(",")), (a, b) -> a.getArray()[0].compareTo(b.getArray()[0]));
        }

        reader.close();
        return dados;
    }

    public static void escreverCSVArvore(String caminhoArquivo, ArvoreBinaria<StringArrayComparable> dados) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo));

        dados.emOrdem((elemento) -> {
            try {
                writer.write(String.join(",", elemento.getArray()));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        writer.close();
    }
}

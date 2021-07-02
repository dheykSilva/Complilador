package SINTATICO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class superTabela {
    
    private static Map<String, Map<String, Integer>> tabelaSintatica;
    private static LinkedList<String> listaProducao;
    
    public superTabela(){
        this.tabelaSintatica = new HashMap<>();
        this.listaProducao = new LinkedList<>();
    }
    
    public static Map<String, Map<String, Integer>> getTabelaSintatica() {
        return tabelaSintatica;
    }

    public static void setTabelaSintatica(Map<String, Map<String, Integer>> aTabelaSintatica) {
        tabelaSintatica = aTabelaSintatica;
    }

    public static LinkedList<String> getListaProducao() {
        return listaProducao;
    }

    public static void setListaProducao(LinkedList<String> aListaProducao) {
        listaProducao = aListaProducao;
    }
}
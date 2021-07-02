package SINTATICO;

import java.util.HashMap;
import java.util.Map;

public class mapaTabela { 
    
    public Map<String, Map<String, Integer>> tabelaSintatica(){
        
        Map<String, Map<String, Integer>> mapaTabela_Sintatica = new HashMap<>();
        
        Map<String, Integer> tabela_Inicio = new HashMap<>();
        tabela_Inicio.put("PROGRAMA", 0);
        mapaTabela_Sintatica.put("TK_inicio", tabela_Inicio);
        
        Map<String, Integer> tabela_Fim = new HashMap<>();
        tabela_Fim.put("LISTA_COMANDOS", 2);
        mapaTabela_Sintatica.put("TK_fim", tabela_Fim);
        
        Map<String, Integer> tabela_inteiro = new HashMap<>();
        tabela_inteiro.put("LISTA_COMANDOS", 1);
        tabela_inteiro.put("COMANDOS", 15);
        tabela_inteiro.put("TIPOVARIAVEL", 43);
        mapaTabela_Sintatica.put("TK_int", tabela_inteiro);
        
        Map<String, Integer> tabela_abreparent = new HashMap<>();
        tabela_abreparent.put("ARGUMENTOAUX", 19);
        mapaTabela_Sintatica.put("TK_abreparent", tabela_abreparent);
        
        Map<String, Integer> tabela_fechaparent = new HashMap<>();
        tabela_fechaparent.put("ARGUMENTOEXP", 11);
        mapaTabela_Sintatica.put("TK_fechaparent", tabela_fechaparent);
        
        Map<String, Integer> tabela_fimLinha = new HashMap<>();
        tabela_fimLinha.put("ATRIBUICAO", 17);
        tabela_fimLinha.put("EXPRESSAOMAT", 22);
        tabela_fimLinha.put("FINALLINHA", 49);
        mapaTabela_Sintatica.put("TK_finallinha", tabela_fimLinha);
        
        Map<String, Integer> tabela_igual = new HashMap<>();
        tabela_igual.put("ATRIBUICAO", 16);
        tabela_igual.put("ARGUMENTO", 13);
        mapaTabela_Sintatica.put("TK_igual", tabela_igual);
        
        Map<String, Integer> tabela_igualIgual = new HashMap<>();
        tabela_igualIgual.put("SIMBOLO", 33);
        tabela_igualIgual.put("ARGUMENTOEXP", 10);
        mapaTabela_Sintatica.put("TK_igualigual", tabela_igualIgual);
        
        Map<String, Integer> tabela_incremento = new HashMap<>();
        tabela_incremento.put("ARGUMENTO", 14);
        tabela_incremento.put("ITERACAO", 38);
        mapaTabela_Sintatica.put("TK_incremento", tabela_incremento);
        
        Map<String, Integer> tabela_decremento = new HashMap<>();
        tabela_decremento.put("ARGUMENTO", 14);
        tabela_decremento.put("ITERACAO", 39);
        mapaTabela_Sintatica.put("TK_decremento", tabela_decremento);
        
        Map<String, Integer> tabela_maior = new HashMap<>();
        tabela_maior.put("SIMBOLO", 30);
        tabela_maior.put("ARGUMENTOEXP", 10);
        mapaTabela_Sintatica.put("TK_maior", tabela_maior);
        
        Map<String, Integer> tabela_menor = new HashMap<>();
        tabela_menor.put("SIMBOLO", 31);
        tabela_menor.put("ARGUMENTOEXP", 10);
        mapaTabela_Sintatica.put("TK_menor", tabela_menor);
        
        Map<String, Integer> tabela_diferente = new HashMap<>();
        tabela_diferente.put("SIMBOLO", 32);
        tabela_diferente.put("ARGUMENTOEXP", 10);
        mapaTabela_Sintatica.put("TK_diferente", tabela_diferente);
        
        Map<String, Integer> tabela_inicioBloco = new HashMap<>();
        tabela_inicioBloco.put("INICIOBLOCO", 47);
        mapaTabela_Sintatica.put("TK_iniciobloco", tabela_inicioBloco);
        
        Map<String, Integer> tabela_fimBloco = new HashMap<>();
        tabela_fimBloco.put("LISTA_COMANDOS", 2);
        tabela_fimBloco.put("SENAO", 8);
        tabela_fimBloco.put("FINALBLOCO", 48);
        mapaTabela_Sintatica.put("TK_fimbloco", tabela_fimBloco);
        
        Map<String, Integer> tabela_leia = new HashMap<>();
        tabela_leia.put("LISTA_COMANDOS", 1);
        tabela_leia.put("COMANDOS", 3);
        tabela_leia.put("LEIA", 40);
        mapaTabela_Sintatica.put("TK_leia", tabela_leia);
        
        Map<String, Integer> tabela_escreva = new HashMap<>();
        tabela_escreva.put("LISTA_COMANDOS", 1);
        tabela_escreva.put("COMANDOS", 4);
        tabela_escreva.put("ESCREVA", 41);
        mapaTabela_Sintatica.put("TK_escreva", tabela_escreva);
        
        Map<String, Integer> tabela_enquanto = new HashMap<>();
        tabela_enquanto.put("LISTA_COMANDOS", 1);
        tabela_enquanto.put("COMANDOS", 5);
        tabela_enquanto.put("ENQUANTO", 46);
        mapaTabela_Sintatica.put("TK_enquanto", tabela_enquanto);
        
        Map<String, Integer> tabela_se = new HashMap<>();
        tabela_se.put("LISTA_COMANDOS", 1);
        tabela_se.put("COMANDOS", 6);
        tabela_se.put("SE", 45);
        mapaTabela_Sintatica.put("TK_se", tabela_se);
        
        Map<String, Integer> tabela_senao = new HashMap<>();
        tabela_senao.put("LISTA_COMANDOS", 2);
        tabela_senao.put("SENAO", 7);
        mapaTabela_Sintatica.put("TK_senao", tabela_senao);
        
        Map<String, Integer> tabela_soma = new HashMap<>();
        tabela_soma.put("SIMBOLORMAT", 34);
        tabela_soma.put("EXPRESSAOMAT", 21);
        mapaTabela_Sintatica.put("TK_soma", tabela_soma);
        
        Map<String, Integer> tabela_sub = new HashMap<>();
        tabela_sub.put("SIMBOLORMAT", 35);
        tabela_sub.put("EXPRESSAOMAT", 21);
        mapaTabela_Sintatica.put("TK_sub", tabela_sub);
        
        Map<String, Integer> tabela_mult = new HashMap<>();
        tabela_mult.put("SIMBOLORMAT", 36);
        tabela_mult.put("EXPRESSAOMAT", 21);
        mapaTabela_Sintatica.put("TK_mult", tabela_mult);
        
        Map<String, Integer> tabela_div = new HashMap<>();
        tabela_div.put("SIMBOLORMAT", 37);
        tabela_div.put("EXPRESSAOMAT", 21);
        mapaTabela_Sintatica.put("TK_div", tabela_div);
        
        Map<String, Integer> tabela_string = new HashMap<>();
        tabela_string.put("STRING", 42);
        tabela_string.put("ATRIBUTO", 23);
        mapaTabela_Sintatica.put("TK_string", tabela_string);
        
        Map<String, Integer> tabela_numpos = new HashMap<>();
        tabela_numpos.put("NUMERO", 28);
        tabela_numpos.put("ATRIBUTO", 24);
        tabela_numpos.put("ARGUMENTOAUX", 18);
        tabela_numpos.put("EXPMATVARNUM", 20);
        tabela_numpos.put("VARNUMERO", 26);
        mapaTabela_Sintatica.put("TK_numpos", tabela_numpos);
        
        Map<String, Integer> tabela_numneg = new HashMap<>();
        tabela_numneg.put("NUMERO", 29);
        tabela_numneg.put("ATRIBUTO", 24);
        tabela_numneg.put("ARGUMENTOAUX", 18);
        tabela_numneg.put("EXPMATVARNUM", 20);
        tabela_numneg.put("VARNUMERO", 26);
        mapaTabela_Sintatica.put("TK_numneg", tabela_numneg);
        
        Map<String, Integer> tabela_variavel = new HashMap<>();
        tabela_variavel.put("LISTA_COMANDOS", 1);
        tabela_variavel.put("COMANDOS", 12);
        tabela_variavel.put("VARIAVEL", 44);
        tabela_variavel.put("ATRIBUTO", 25);
        tabela_variavel.put("ARGUMENTOAUX", 18);
        tabela_variavel.put("EXPMATVARNUM", 20);
        tabela_variavel.put("VARNUMERO", 27);
        tabela_variavel.put("EXPRESSAO", 9);
        mapaTabela_Sintatica.put("TK_variavel", tabela_variavel);
        
        return mapaTabela_Sintatica;
    }
}
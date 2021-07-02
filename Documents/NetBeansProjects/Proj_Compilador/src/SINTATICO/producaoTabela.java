package SINTATICO;

import java.util.Collections;
import java.util.LinkedList;

public class producaoTabela {
    
    public static LinkedList<String> producoes(int numeroProducao){
        
        LinkedList<String> listaProducao = new LinkedList<>();
        
        switch(numeroProducao){
            case 0:
                listaProducao.clear();
                listaProducao.add("TK_inicio");
                listaProducao.add("LISTA_COMANDOS");
                listaProducao.add("TK_fim");
                break;
                
            case 1:
                listaProducao.clear();
                listaProducao.add("COMANDOS");
                listaProducao.add("LISTA_COMANDOS");
                break;
                
            case 2:
                listaProducao.clear();
                break;
                
            case 3:
                listaProducao.clear();
                listaProducao.add("LEIA");
                listaProducao.add("TK_abreparent");
                listaProducao.add("ATRIBUTO");
                listaProducao.add("TK_fechaparent");
                listaProducao.add("FINALLINHA");
                break;
                
            case 4:
                listaProducao.clear();
                listaProducao.add("ESCREVA");
                listaProducao.add("TK_abreparent");
                listaProducao.add("ATRIBUTO");
                listaProducao.add("TK_fechaparent");
                listaProducao.add("FINALLINHA");
                break;
                
            case 5:
                listaProducao.clear();
                listaProducao.add("ENQUANTO");
                listaProducao.add("TK_abreparent");
                listaProducao.add("EXPRESSAO");
                listaProducao.add("TK_fechaparent");
                listaProducao.add("INICIOBLOCO");
                listaProducao.add("LISTA_COMANDOS");
                listaProducao.add("FINALBLOCO");
                break;
                
            case 6:
                listaProducao.clear();
                listaProducao.add("SE");
                listaProducao.add("TK_abreparent");
                listaProducao.add("EXPRESSAO");
                listaProducao.add("TK_fechaparent");
                listaProducao.add("INICIOBLOCO");
                listaProducao.add("LISTA_COMANDOS");
                listaProducao.add("SENAO");
                listaProducao.add("FINALBLOCO");
                break;
                
            case 7:
                listaProducao.clear();
                listaProducao.add("TK_senao");
                listaProducao.add("INICIOBLOCO");
                listaProducao.add("LISTA_COMANDOS");
                listaProducao.add("FINALBLOCO");
                break;
                
            case 8:
                listaProducao.clear();
                break;
                
            case 9:
                listaProducao.clear();
                listaProducao.add("VARIAVEL");
                listaProducao.add("ARGUMENTOEXP");
                break;
                
            case 10:
                listaProducao.clear();
                listaProducao.add("SIMBOLO");
                listaProducao.add("VARNUMERO");
                break;
                
            case 11:
                listaProducao.clear();
                listaProducao = null;
                break;
                
            case 12:
                listaProducao.clear();
                listaProducao.add("TK_variavel");
                listaProducao.add("ARGUMENTO");
                listaProducao.add("FINALLINHA");
                break;
                
            case 13:
                listaProducao.clear();
                listaProducao.add("TK_igual");
                listaProducao.add("ARGUMENTOAUX");
                break;
                
            case 14:
                listaProducao.clear();
                listaProducao.add("ITERACAO");
                break;
                
            case 15:
                listaProducao.clear();
                listaProducao.add("TIPOVARIAVEL");
                listaProducao.add("VARIAVEL");
                listaProducao.add("ATRIBUICAO");
                listaProducao.add("FINALLINHA");
                break;
                
            case 16:
                listaProducao.clear();
                listaProducao.add("TK_igual");
                listaProducao.add("ARGUMENTOAUX");
                break;
                
            case 17:
                listaProducao.clear();
                break;
                
            case 18:
                listaProducao.clear();
                listaProducao.add("EXPMATVARNUM");
                break;
                
            case 19:
                listaProducao.clear();
                listaProducao.add("TK_abreparent");
                listaProducao.add("STRING");
                listaProducao.add("TK_fechaparent");
                break;
                
            case 20:
                listaProducao.clear();
                listaProducao.add("VARNUMERO");
                listaProducao.add("EXPRESSAOMAT");
                break;
                
            case 21:
                listaProducao.clear();
                listaProducao.add("SIMBOLORMAT");
                listaProducao.add("EXPMATVARNUM");
                break;
                
            case 22:
                listaProducao.clear();
                break;
                
            case 23:
                listaProducao.clear();
                listaProducao.add("STRING");
                break;
                
            case 24:
                listaProducao.clear();
                listaProducao.add("NUMERO");
                break;
                
            case 25:
                listaProducao.clear();
                listaProducao.add("VARIAVEL");
                break;
                
            case 26:
                listaProducao.clear();
                listaProducao.add("NUMERO");
                break;
                
            case 27:
                listaProducao.clear();
                listaProducao.add("VARIAVEL");
                break;
                
            case 28:
                listaProducao.clear();
                listaProducao.add("TK_numpos");
                break;
                
            case 29:
                listaProducao.clear();
                listaProducao.add("TK_numneg");
                break;
                
            case 30:
                listaProducao.clear();
                listaProducao.add("TK_maior");
                break;
                
            case 31:
                listaProducao.clear();
                listaProducao.add("TK_menor");
                break;
                
            case 32:
                listaProducao.clear();
                listaProducao.add("TK_diferente");
                break;
                
            case 33:
                listaProducao.clear();
                listaProducao.add("TK_igualigual");
                break;
                
            case 34:
                listaProducao.clear();
                listaProducao.add("TK_soma");
                break;
                
            case 35:
                listaProducao.clear();
                listaProducao.add("TK_sub");
                break;
                
            case 36:
                listaProducao.clear();
                listaProducao.add("TK_mult");
                break;
                
            case 37:
                listaProducao.clear();
                listaProducao.add("TK_div");
                break;
                
            case 38:
                listaProducao.clear();
                listaProducao.add("TK_incremento");
                break;
                
            case 39:
                listaProducao.clear();
                listaProducao.add("TK_decremento");
                break;
                
            case 40:
                listaProducao.clear();
                listaProducao.add("TK_leia");
                break;
                
            case 41:
                listaProducao.clear();
                listaProducao.add("TK_escreva");
                break;
                
            case 42:
                listaProducao.clear();
                listaProducao.add("TK_string");
                break;
                
            case 43:
                listaProducao.clear();
                listaProducao.add("TK_int");
                break;
                
            case 44:
                listaProducao.clear();
                listaProducao.add("TK_variavel");
                break;
                
            case 45:
                listaProducao.clear();
                listaProducao.add("TK_se");
                break;
                
            case 46:
                listaProducao.clear();
                listaProducao.add("TK_enquanto");
                break;
                
            case 47:
                listaProducao.clear();
                listaProducao.add("TK_iniciobloco");
                break;
                
            case 48:
                listaProducao.clear();
                listaProducao.add("TK_fimbloco");
                break;
                
            case 49:
                listaProducao.clear();
                listaProducao.add("TK_finallinha");
                break;
        }
        
        Collections.reverse(listaProducao);
        return listaProducao;
    }
}
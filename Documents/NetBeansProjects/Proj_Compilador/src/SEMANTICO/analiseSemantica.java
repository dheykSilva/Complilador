package SEMANTICO;

import LEXICO.tokens;
import java.util.ArrayList;

public class analiseSemantica {
    
    static boolean erro = false;
    static String log = "";
    static String logErro = "";
    static int cont = 1;
    
    public static void analizadorSemantico(ArrayList<String> argumentos, ArrayList<tokens> token){
        variaveisIniciadas(argumentos, token);
        declaracaoVarRepetidas(argumentos, token);
        divisaoZero(argumentos, token);
    }
    
    public static void variaveisIniciadas(ArrayList<String> argumentos, ArrayList<tokens> token){
        
        ArrayList<String> varGeral = new ArrayList<>();
        ArrayList<String> varIniciadas = new ArrayList<>();
        log = "";
        logErro = "";
        
        log += ("\nInicio da verificação de declaração de variáveis.\n");
        
        for(tokens t : token){
            if(t.getNome().equals("TK_variavel")){
                log += ("\nEncontrou uma variavel.");
                varGeral.add(t.getLexemas());
                int indexAtual = token.indexOf(t);
                if(token.get(indexAtual - 1).getNome().equals("TK_int")){
                    log += ("\nVerificando declaração.");
                    varIniciadas.add(t.getLexemas());
                }
            }
        }
        
        log += ("\nVerificando se todas as variáveis foram declaradas.");
        
        varGeral.removeAll(varIniciadas);
        if(varGeral.isEmpty()){
            erro = false;
            log += ("\n\nVerificação de declaração de variaveis concluida com sucesso, todas as variáveis iniciadas!");
            imprimeLog(argumentos, erro, log, logErro);
        } else{
            erro = true;
            logErro += ("\n\nERRO SEMÂNTICO\n");
            logErro += ("\nVariavel: "+varGeral+" não iniciada!\n");
            imprimeLog(argumentos, erro, log, logErro);
            System.exit(0);
        }
    }
    
    public static void declaracaoVarRepetidas(ArrayList<String> argumentos, ArrayList<tokens> token){
        log = "";
        logErro = "";
        ArrayList<String> var = new ArrayList<>();
        
        log += ("\nInicio da verificação de declaração de variáveis.\n");
        
        for(tokens t : token){
            if(t.getNome().equals("TK_variavel")){
                log += ("\nEncontrou uma variavel.");
                int indexAtual = token.indexOf(t);
                if(token.get(indexAtual - 1).getNome().equals("TK_int")){
                    log += ("\nVerificando declaração.");
                    var.add(t.getLexemas());
                }
            }
        }
        
        log += ("\nVerificando declarações repetidas.");
        
        for(int i = 0; i < var.size(); i++){
            for(int j = 0; j < var.size(); j++){
                if(j != i){
                    if(var.get(i).equals(var.get(j))){
                        erro = true;
                        logErro += ("\n\nERRO SEMÂNTICO\n");
                        logErro += ("\nVariavel: "+var.get(i)+" possui mais de uma declaração!");
                        imprimeLog(argumentos, erro, log, logErro);
                        System.exit(0);
                    }
                }
            }
        }
        
        erro = false;
        log += ("\n\nVerificação de declaração de variaveis concluida com sucesso, nenhuma declaração de variável duplicada!\n");
        imprimeLog(argumentos, erro, log, logErro);
    }
    
    public static void divisaoZero(ArrayList<String> argumentos, ArrayList<tokens> token){
        log = "";
        logErro = "";
        String div = null;
        String valor = null;
        
        log += ("\nInicio da verificação de divisão por zero.\n");
        
        for(tokens t : token){
            if(t.getNome().equals("TK_div")){
                log += ("\nEncontrou uma divisão.");
                int indexAtual = token.indexOf(t);
                if(token.get(indexAtual + 1).getNome().equals("TK_variavel")){
                    log += ("\nEncontrou uma variavel.");
                    div = token.get(indexAtual + 1).getLexemas();
                    for(tokens t1 : token){
                        log += ("\nBuscando a variavel.");
                        if(t1.getLexemas().equals(div)){
                            int indexAtual2 = token.indexOf(t1);
                            if(token.get(indexAtual2 - 1).getNome().equals("TK_int")){
                                log += ("\nVerificando declaração.");
                                valor = token.get(indexAtual2 + 2).getLexemas();
                                log += ("\nVerificando o valor da variavel.");
                                if(valor.equals("0")){
                                    erro = true;
                                    logErro += ("\n\nERRO SEMÂNTICO\n");
                                    logErro += ("\nDivisão por zero. A variavel: "+div+" esta sendo usada em uma divisão!");
                                    imprimeLog(argumentos, erro, log, logErro);
                                    System.exit(0);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        for(tokens t : token){
            if(t.getNome().equals("TK_div")){
                int indexAtual = token.indexOf(t);
                if(token.get(indexAtual + 1).getNome().equals("TK_numpos")
                        | token.get(indexAtual + 1).getNome().equals("TK_numneg")){
                    log += ("\nEncontrou um número.");
                    valor = token.get(indexAtual + 1).getLexemas();
                    log += ("\nVerificando o valor do número.");
                    if(valor.equals("0")){
                        erro = true;
                        logErro += ("\n\nERRO SEMÂNTICO\n");
                        logErro += ("\nDivisão por zero. O valor: "+valor+" esta sendo usada em uma divisão\n!");
                        imprimeLog(argumentos, erro, log, logErro);
                        System.exit(0);
                    }
                }
            }
        }
        
        erro = false;
        log += ("\n\nVerificação de divisção por zero concluida com sucesso, nenhuma divisão por zero encontrada!\n");
        imprimeLog(argumentos, erro, log, logErro);
    }
    
    public static void imprimeLog(ArrayList<String> argumentos, boolean erro, String log, String logErro){
        if(erro == false && argumentos.contains("-lse")){
            if(cont > 0){
                System.out.println("\nAnálise semântica concluida com sucesso!\n");
                System.out.println("\n==================================================================\n");
                System.out.println("==============================LOG=================================\n");
                System.out.println("==================================================================\n");
                cont--;
            }
            System.out.println("\n"+log);
        }
        
        else if(erro == false){
            if(cont > 0){
                System.out.println("\nAnálise semântica concluida com sucesso!\n");
                cont--;
            }
        }
        else{
            System.out.println(logErro);
        }
    }
}
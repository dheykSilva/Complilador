package GERACODIGO;

import LEXICO.tokens;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class geradorCodigo{
    
    public static void geracaoCodigo(ArrayList<String> argumentos, ArrayList<tokens> token){
        
        ArrayList<String> codigo = new ArrayList<>();
        LinkedList<String> log = new LinkedList<>();
        String valor1 = "";
        String valor2 = "";
        String sinal = "";
        int controle = 0;
        int contSe = 0;
        int contEnq = 0;
        int numMsg = 0;
        int numVar = 0;
        int indexAtual = 0;
        boolean liberaSe = false;
        boolean liberaEnq = false;
        
        for(tokens t : token){
            if(t.getNome().equals("TK_inicio")){
                log.add("Bloco inicial:");
                log.add("Inclui informações basicas como: conjunto de instruções, modelo de endereçamento, calling convention, headers, etc.");
                log.add("Inclui uma nova seção de dados.");
                codigo.add(".386");
                codigo.add(".model flat, stdcall");
                codigo.add("option casemap: none");
                codigo.add("");
                codigo.add("include \\masm32\\include\\masm32rt.inc");
                codigo.add("");
                codigo.add(".data");
            }
            
            log.add("\nBloco TK_escreva:");
            log.add("Buscando token escreva.");
            if(t.getNome().equals("TK_escreva")){
                log.add("Encontrou token escreva.");
                indexAtual = token.indexOf(t);
                if(token.get(indexAtual + 2).getNome().equals("TK_string")){
                    log.add("Inclui uma variavel tipo string.");
                    codigo.add("msg"+ numMsg + " db " + token.get(indexAtual + 2).getLexemas() + ",13,10,0");
                    numMsg++;
                }
            }
            
            log.add("\nBloco geral de operadores matematicos:");
            log.add("Buscando operadores matematicos.");
            controle = 0;
            if(t.getNome().equals("TK_mult") | t.getNome().equals("TK_soma") 
                    | t.getNome().equals("TK_div") | t.getNome().equals("TK_sub") 
                    | t.getNome().equals("TK_leia")){
                log.add("Encontrou operadores matematicos.");
                if(controle <= 0){
                    log.add("Inclui uma variavel para armazenamento em memoria.\n");
                    codigo.add("var"+numVar+" dd 1");
                    controle++;
                    numVar++;
                }
                codigo.add("var"+numVar+" dd 1");
                numVar++;
            }
            
            log.add("\nBloco TK_se e TK_enquanto:");
            log.add("Buscando tokens.");
            controle = 0;
            if(t.getNome().equals("TK_se") | t.getNome().equals("TK_enquanto") ){
                log.add("Encontrou tokens.");
                if(liberaSe == false){
                    log.add("Incluindo codigo alimentado.");
                    codigo.add("var"+numVar+" dd 1");
                    controle++;
                    numVar++;
                    liberaSe = true;
                }
            }
        }

        log.add("\nInicia uma seção de código.");
        codigo.add("");
        codigo.add(".code");
        codigo.add("start:");
        
        controle = 0;
        numMsg = 0;
        numVar = 0;
        liberaSe = false;
        liberaEnq = false;
        
        for(tokens t : token){
            log.add("\nBloco TK_se:");
            log.add("Buscando token se.");
            controle = 0;
            if(t.getNome().equals("TK_se")){
                log.add("Encontrou token se.");
                indexAtual = token.indexOf(t);
                if(token.get(indexAtual + 2).getNome().equals("TK_variavel")){
                    log.add("Encontrou token de variavel.");
                    String variavel = token.get(indexAtual + 2).getLexemas();
                    for(tokens t1 : token){
                        if(t1.getLexemas().equals(variavel)){
                            int indexAtual1 = token.indexOf(t1);
                            if(token.get(indexAtual1 - 1).getNome().equals("TK_int")){
                                log.add("Encontrou token de declaração.");
                                valor1 = token.get(indexAtual1 + 2).getLexemas();
                            }
                        }
                    }
                } else{
                    if(token.get(indexAtual + 2).getNome().equals("TK_numpos") 
                            | token.get(indexAtual + 2).getNome().equals("TK_neg")){
                        int indexAtual1 = token.indexOf(t);
                        log.add("Encontrou token de numero.");
                        valor1 = token.get(indexAtual1 + 2).getLexemas();
                    }
                }
                if(token.get(indexAtual + 3).getNome().equals("TK_igualigual") 
                        | token.get(indexAtual + 3).getNome().equals("TK_maior") 
                        | token.get(indexAtual + 3).getNome().equals("TK_menor") 
                        | token.get(indexAtual + 3).getNome().equals("TK_diferente")){
                    log.add("Encontrou token de sinal.");
                    sinal = token.get(indexAtual + 3).getLexemas();
                    if(token.get(indexAtual + 4).getNome().equals("TK_variavel")){
                        log.add("Encontrou token de variavel.");
                        String variavel2 = token.get(indexAtual + 4).getLexemas();
                        for(tokens t2 : token){
                            if(t2.getLexemas().equals(variavel2)){
                                int indexAtual2 = token.indexOf(t2);
                                if(token.get(indexAtual2 - 1).getNome().equals("TK_int")){
                                    log.add("Encontrou token de declaração.");
                                    valor2 = token.get(indexAtual2 + 2).getLexemas();
                                }
                            }
                        }
                    }
                } else{
                    if(token.get(indexAtual + 4).getNome().equals("TK_numpos") 
                            | token.get(indexAtual + 4).getNome().equals("TK_neg")){
                        log.add("Encontrou token de numero.");
                        int indexAtual1 = token.indexOf(t);
                        valor2 = token.get(indexAtual1 + 4).getLexemas();
                    }
                }
                if(controle <= 0){
                    log.add("Incluindo codigo alimentado.");
                    if(sinal.equals("<>")){
                        sinal = "!=";
                    }
                    codigo.add("mov eax, " + valor1);
                    codigo.add("mov ebx, " + valor2);
                    codigo.add(".if eax " + sinal + " ebx");
                    controle++;
                    contSe++;
                    liberaSe = true;
                }
            }
            
            log.add("\nBloco TK_enquanto:");
            log.add("Buscando token de enquanto.");
            controle = 0;
            if(t.getNome().equals("TK_enquanto")){
                log.add("Encontrou token de enquanto.");
                indexAtual = token.indexOf(t);
                if(token.get(indexAtual + 2).getNome().equals("TK_variavel")){
                    log.add("Encontrou token de variavel.");
                    String variavel = token.get(indexAtual + 2).getLexemas();
                    for(tokens t1 : token){
                        if(t1.getLexemas().equals(variavel)){
                            int indexAtual1 = token.indexOf(t1);
                            if(token.get(indexAtual1 - 1).getNome().equals("TK_int")){
                                log.add("Encontrou token de declaração.");
                                valor1 = token.get(indexAtual1 + 2).getLexemas();
                            }
                        }
                    }
                } else{
                    if(token.get(indexAtual + 2).getNome().equals("TK_numpos") 
                            | token.get(indexAtual + 2).getNome().equals("TK_neg")){
                        log.add("Encontrou token de numero.");
                        int indexAtual1 = token.indexOf(t);
                        valor1 = token.get(indexAtual1 + 2).getLexemas();
                    }
                }
                if(token.get(indexAtual + 3).getNome().equals("TK_igualigual") 
                        | token.get(indexAtual + 3).getNome().equals("TK_maior") 
                        | token.get(indexAtual + 3).getNome().equals("TK_menor")
                        | token.get(indexAtual + 3).getNome().equals("TK_diferente")){
                    log.add("Encontrou token de sinal.");
                    sinal = token.get(indexAtual + 3).getLexemas();
                    if(token.get(indexAtual + 4).getNome().equals("TK_variavel")){
                        log.add("Encontrou token de variavel.");
                        String variavel2 = token.get(indexAtual + 4).getLexemas();
                        for(tokens t2 : token){
                            if(t2.getLexemas().equals(variavel2)){
                                int indexAtual2 = token.indexOf(t2);
                                if(token.get(indexAtual2 - 1).getNome().equals("TK_int")){
                                    log.add("Encontrou token de declaração.");
                                    valor2 = token.get(indexAtual2 + 2).getLexemas();
                                }
                            }
                        }
                    }
                } else{
                    if(token.get(indexAtual + 4).getNome().equals("TK_numpos") 
                            | token.get(indexAtual + 4).getNome().equals("TK_neg")){
                        log.add("Encontrou token de numero.");
                        int indexAtual1 = token.indexOf(t);
                        valor2 = token.get(indexAtual1 + 4).getLexemas();
                    }
                }
                if(controle <= 0){
                    log.add("Incluindo codigo alimentado.");
                    if(sinal.equals("<>")){
                        sinal = "!=";
                    }
                    codigo.add("mov eax, " + valor1);
                    codigo.add("mov ebx, " + valor2);
                    codigo.add(".while eax " + sinal + " ebx");
                    controle++;
                    contEnq++;
                    liberaEnq = true;
                }
            }
            
            log.add("\nBloco TK_mult:");
            log.add("Buscando token de multiplicação.");
            controle = 0;
            if(t.getNome().equals("TK_mult")){
                log.add("Encontrou token de multiplicação.");
                indexAtual = token.indexOf(t);
                if(token.get(indexAtual - 1).getNome().equals("TK_variavel")){
                    log.add("Encontrou token de variavel.");
                    String variavel = token.get(indexAtual - 1).getLexemas();
                    for(tokens t1 : token){
                        if(t1.getLexemas().equals(variavel)){
                            int indexAtual1 = token.indexOf(t1);
                            if(token.get(indexAtual1 - 1).getNome().equals("TK_int")){
                                log.add("Encontrou token de declaração.");
                                valor1 = token.get(indexAtual1 + 2).getLexemas();
                            }
                        }
                    }
                } else{
                    if(token.get(indexAtual - 1).getNome().equals("TK_numpos") 
                            | token.get(indexAtual - 1).getNome().equals("TK_neg")){
                        log.add("Encontrou token de numero.");
                        int indexAtual1 = token.indexOf(t);
                        valor1 = token.get(indexAtual1 - 1).getLexemas();
                    }
                }
                if(token.get(indexAtual + 1).getNome().equals("TK_variavel")){
                    log.add("Encontrou token de variavel.");
                    String variavel = token.get(indexAtual + 1).getLexemas();
                    for(tokens t1 : token){
                        if(t1.getLexemas().equals(variavel)){
                            int indexAtual1 = token.indexOf(t1);
                            if(token.get(indexAtual1 - 1).getNome().equals("TK_int")){
                                log.add("Encontrou token de declaração.");
                                valor2 = token.get(indexAtual1 + 2).getLexemas();
                            }
                        }
                    }
                } else{
                    log.add("Buscando token de numero.");
                    if(token.get(indexAtual + 1).getNome().equals("TK_numpos") 
                            | token.get(indexAtual + 1).getNome().equals("TK_neg")){
                        log.add("Encontrou token de numero.");
                        int indexAtual1 = token.indexOf(t);
                        valor2 = token.get(indexAtual1 + 1).getLexemas();
                    }
                }
                if(controle <= 0){
                    log.add("Incluindo codigo alimentado.");
                    codigo.add("mov eax, " + valor1);
                    codigo.add("mov ebx, " + valor2);
                    codigo.add("imul eax, ebx");
                    codigo.add("mov var"+numVar+", eax");
                    codigo.add("mov eax, var"+numVar);
                    controle++;
                    numVar++;
                }else{
                    codigo.add("mov ebx, " + valor2);
                    codigo.add("imul eax, ebx");
                    codigo.add("mov var"+numVar+", eax");
                    codigo.add("mov eax, var"+numVar);
                    controle++;
                    numVar++;
                }
            }
            
            controle = 0;
            log.add("\nBloco TK_div:");
            log.add("Buscando token de divisão.");
            if(t.getNome().equals("TK_div")){
                log.add("Encontrou token de divisão.");
                indexAtual = token.indexOf(t);
                if(token.get(indexAtual - 1).getNome().equals("TK_variavel")){
                    log.add("Encontrou token de variavel.");
                    String variavel = token.get(indexAtual - 1).getLexemas();
                    for(tokens t1 : token){
                        if(t1.getLexemas().equals(variavel)){
                            int indexAtual1 = token.indexOf(t1);
                            if(token.get(indexAtual1 - 1).getNome().equals("TK_int")){
                                log.add("Encontrou token de declaração.");
                                valor1 = token.get(indexAtual1 + 2).getLexemas();
                            }
                        }
                    }
                } else{
                    if(token.get(indexAtual - 1).getNome().equals("TK_numpos") 
                            | token.get(indexAtual - 1).getNome().equals("TK_neg")){
                        log.add("Encontrou token de numero.");
                        int indexAtual1 = token.indexOf(t);
                        valor1 = token.get(indexAtual1 - 1).getLexemas();
                    }
                }                
                if(token.get(indexAtual + 1).getNome().equals("TK_variavel")){
                    log.add("Encontrou token de variavel.");
                    String variavel = token.get(indexAtual + 1).getLexemas();
                    for(tokens t1 : token){
                        if(t1.getLexemas().equals(variavel)){
                            int indexAtual1 = token.indexOf(t1);
                            if(token.get(indexAtual1 - 1).getNome().equals("TK_int")){
                                log.add("Encontrou token de declaração.");
                                valor2 = token.get(indexAtual1 + 2).getLexemas();
                            }
                        }
                    }
                } else{
                    if(token.get(indexAtual + 1).getNome().equals("TK_numpos") 
                            | token.get(indexAtual - 1).getNome().equals("TK_neg")){
                        log.add("Encontrou token de numero.");
                        int indexAtual1 = token.indexOf(t);
                        valor2 = token.get(indexAtual1 + 1).getLexemas();
                    }
                }               
                if(controle <= 0){
                    log.add("Incluindo codigo alimentado.");
                    codigo.add("mov eax, " + valor1);
                    codigo.add("mov ebx, " + valor2);
                    codigo.add("sub edx, edx");
                    codigo.add("div ebx");
                    codigo.add("mov var"+numVar+", eax");
                    codigo.add("mov eax, var"+numVar);
                    controle++;
                    numVar++;
                }else{
                    codigo.add("mov ebx, " + valor2);
                    codigo.add("sub edx, edx");
                    codigo.add("div ebx");
                    codigo.add("mov var"+numVar+", eax");
                    codigo.add("mov eax, var"+numVar);
                    controle++;
                    numVar++;
                }
            }
            
            controle = 0;
            log.add("\nBloco TK_soma:");
            log.add("Buscando token de soma.");
            if(t.getNome().equals("TK_soma")){
                log.add("Encontrou token de soma.");
                indexAtual = token.indexOf(t);
                if(token.get(indexAtual - 1).getNome().equals("TK_variavel")){
                    log.add("Encontrou token de variavel.");
                    String variavel = token.get(indexAtual - 1).getLexemas();
                    for(tokens t1 : token){
                        if(t1.getLexemas().equals(variavel)){
                            int indexAtual1 = token.indexOf(t1);
                            if(token.get(indexAtual1 - 1).getNome().equals("TK_int")){
                                log.add("Encontrou token de declaração.");
                                valor1 = token.get(indexAtual1 + 2).getLexemas();
                            }
                        }
                    }
                } else{
                    if(token.get(indexAtual - 1).getNome().equals("TK_numpos") 
                            | token.get(indexAtual - 1).getNome().equals("TK_neg")){
                        log.add("Encontrou token de numero.");
                        int indexAtual1 = token.indexOf(t);
                        valor1 = token.get(indexAtual1 - 1).getLexemas();
                    }
                }                
                if(token.get(indexAtual + 1).getNome().equals("TK_variavel")){
                    log.add("Encontrou token de variavel.");
                    String variavel = token.get(indexAtual + 1).getLexemas();
                    for(tokens t1 : token){
                        if(t1.getLexemas().equals(variavel)){
                            int indexAtual1 = token.indexOf(t1);
                            if(token.get(indexAtual1 - 1).getNome().equals("TK_int")){
                                log.add("Encontrou token de declaração.");
                                valor2 = token.get(indexAtual1 + 2).getLexemas();
                            }
                        }
                    }
                } else{
                    if(token.get(indexAtual + 1).getNome().equals("TK_numpos") 
                            | token.get(indexAtual + 1).getNome().equals("TK_neg")){
                        log.add("Encontrou token de numero.");
                        int indexAtual1 = token.indexOf(t);
                        valor2 = token.get(indexAtual1 + 1).getLexemas();
                    }
                }               
                if(controle <= 0){
                    log.add("Incluindo codigo alimentado.");
                    codigo.add("mov eax, " + valor1);
                    codigo.add("mov ebx, " + valor2);
                    codigo.add("add eax, ebx");
                    codigo.add("mov var"+numVar+", eax");
                    codigo.add("mov eax, var"+numVar);
                    controle++;
                    numVar++;
                }else{
                    codigo.add("mov ebx, " + valor2);
                    codigo.add("add eax, ebx");
                    codigo.add("mov var"+numVar+", eax");
                    codigo.add("mov eax, var"+numVar);
                    controle++;
                    numVar++;
                }
            }
            
            controle = 0;
            log.add("\nBloco TK_sub:");
            log.add("Buscando token de subtração.");
            if(t.getNome().equals("TK_sub")){
                log.add("Encontrou token de subtração.");
                indexAtual = token.indexOf(t);
                if(token.get(indexAtual - 1).getNome().equals("TK_variavel")){
                    log.add("Encontrou token de variavel.");
                    String variavel = token.get(indexAtual - 1).getLexemas();
                    for(tokens t1 : token){
                        if(t1.getLexemas().equals(variavel)){
                            int indexAtual1 = token.indexOf(t1);
                            if(token.get(indexAtual1 - 1).getNome().equals("TK_int")){
                                log.add("Encontrou token de declaração.");
                                valor1 = token.get(indexAtual1 + 2).getLexemas();
                            }
                        }
                    }
                } else{
                    if(token.get(indexAtual - 1).getNome().equals("TK_numpos") 
                            | token.get(indexAtual - 1).getNome().equals("TK_neg")){
                        log.add("Encontrou token de numero.");
                        int indexAtual1 = token.indexOf(t);
                        valor1 = token.get(indexAtual1 - 1).getLexemas();
                    }
                } 
                if(token.get(indexAtual + 1).getNome().equals("TK_variavel")){
                    log.add("Encontrou token de variavel.");
                    String variavel = token.get(indexAtual + 1).getLexemas();
                    for(tokens t1 : token){
                        if(t1.getLexemas().equals(variavel)){
                            int indexAtual1 = token.indexOf(t1);
                            if(token.get(indexAtual1 - 1).getNome().equals("TK_int")){
                                log.add("Encontrou token de declaração.");
                                valor2 = token.get(indexAtual1 + 2).getLexemas();
                            }
                        }
                    }
                } else{
                    if(token.get(indexAtual + 1).getNome().equals("TK_numpos") 
                            | token.get(indexAtual + 1).getNome().equals("TK_neg")){
                        log.add("Encontrou token de numero.");
                        int indexAtual1 = token.indexOf(t);
                        valor2 = token.get(indexAtual1 + 1).getLexemas();
                    }
                } 
                if(controle <= 0){
                    log.add("Incluindo codigo alimentado.");
                    codigo.add("mov eax, " + valor1);
                    codigo.add("mov ebx, " + valor2);
                    codigo.add("sub eax, ebx");
                    codigo.add("mov var"+numVar+", eax");
                    codigo.add("mov eax, var"+numVar);
                    controle++;
                    numVar++;
                }else{
                    codigo.add("mov ebx, " + valor2);
                    codigo.add("sub eax, ebx");
                    codigo.add("mov var"+numVar+", eax");
                    codigo.add("mov eax, var"+numVar);
                    controle++;
                    numVar++;
                }
            }
            
            log.add("\nBloco TK_string:");
            log.add("Buscando token de string.");
            if(t.getNome().equals("TK_string")){
                log.add("Encontrou token de string.");
                log.add("Incluindo codigo alimentado.");
                codigo.add("push eax");
                codigo.add("printf(\"%s\", addr msg"+numMsg+")");
                codigo.add("printf(\"\\n\")");
                codigo.add("pop eax");
                numMsg++;
            }
            
            log.add("\nBloco TK_escreva:");
            log.add("Buscando token de escreva.");
            controle = 0;
            if(t.getNome().equals("TK_escreva")){
                log.add("Encontrou token de escreva.");
                indexAtual = token.indexOf(t);
                if(token.get(indexAtual + 2).getNome().equals("TK_variavel")){
                    log.add("Encontrou token de variavel.");
                    if(liberaSe = true){
                        log.add("Incluindo codigo alimentado.");
                        if(controle <= 0){
                            codigo.add("push eax");
                            codigo.add("mov var"+numVar+", eax");
                            codigo.add("mov eax, var"+numVar);
                            codigo.add("printf(\"%d\", eax)");
                            codigo.add("printf(\"\\n\")");
                            codigo.add("pop eax");
                            liberaSe = false;
                            controle++;
                        }
                    }
                    else if(liberaEnq = true){
                        log.add("Incluindo codigo alimentado.");
                        codigo.add("push eax");
                        codigo.add("mov var"+numVar+", eax");
                        codigo.add("mov eax, var"+numVar);
                        codigo.add("printf(\"%d\", eax)");
                        codigo.add("printf(\"\\n\")");
                        codigo.add("pop eax");
                    }
                    else{
                        log.add("Incluindo codigo alimentado.");
                        codigo.add("push eax");
                        codigo.add("printf(\"O valor e: %d\", var"+numVar+")");
                        codigo.add("printf(\"\\n\")");
                        codigo.add("pop eax");
                        numVar++;
                    }
                }
            }
            
            log.add("\nBloco TK_variavel:");
            log.add("Buscando token de variavel.");
            if(t.getNome().equals("TK_variavel")){
                log.add("Encontrou token de variavel.");
                indexAtual = token.indexOf(t);
                if(token.get(indexAtual + 1).getNome().equals("TK_incremento")){
                    log.add("Encontrou token de incremento.");
                    String variavel = token.get(indexAtual).getLexemas();
                    for(tokens t1 : token){
                        if(t1.getLexemas().equals(variavel)){
                            int indexAtual1 = token.indexOf(t1);
                            if(token.get(indexAtual1 - 1).getNome().equals("TK_int")){
                                log.add("Encontrou token de declaração.");
                                valor1 = token.get(indexAtual1 + 2).getLexemas();
                            }
                        }
                    }
                    log.add("Incluindo codigo alimentado.");
                    codigo.add("inc eax");
                    controle++; 
                }
            }
            
            log.add("\nBloco TK_variavel:");
            log.add("Buscando token de variavel.");
            if(t.getNome().equals("TK_variavel")){
                log.add("Encontrou token de variavel.");
                indexAtual = token.indexOf(t);
                if(token.get(indexAtual + 1).getNome().equals("TK_decremento")){
                    log.add("Encontrou token de decremento.");
                    String variavel = token.get(indexAtual).getLexemas();
                    for(tokens t1 : token){
                        if(t1.getLexemas().equals(variavel)){
                            int indexAtual1 = token.indexOf(t1);
                            if(token.get(indexAtual1 - 1).getNome().equals("TK_int")){
                                log.add("Encontrou token de declaração.");
                                valor1 = token.get(indexAtual1 + 2).getLexemas();
                            }
                        }
                    }
                    log.add("Incluindo codigo alimentado.");
                    codigo.add("dec eax");
                    controle++; 
                }
            }
            
            log.add("\nBloco TK_leia:");
            log.add("Buscando token de leia.");
            if(t.getNome().equals("TK_leia")){
                indexAtual = token.indexOf(t);
                if(token.get(indexAtual + 2).getNome().equals("TK_variavel")){
                    log.add("Encontrou token de variavel.");
                    String variavel = token.get(indexAtual).getLexemas();
                    for(tokens t1 : token){
                        if(t1.getLexemas().equals(variavel)){
                            int indexAtual1 = token.indexOf(t1);
                            if(token.get(indexAtual1 - 1).getNome().equals("TK_int")){
                                log.add("Encontrou token de declaração.");
                                valor1 = token.get(indexAtual1 + 2).getLexemas();
                            }
                        }
                    }
                }
                else if(token.get(indexAtual + 2).getNome().equals("TK_numpos") 
                        | token.get(indexAtual + 2).getNome().equals("TK_numneg") ){
                    log.add("Encontrou token de numero.");
                    valor1 = token.get(indexAtual + 2).getLexemas();
                }
                log.add("Incluindo codigo alimentado.");
                codigo.add("mov eax, " + valor1);
                codigo.add("mov var"+numVar+", eax");
                codigo.add("mov eax, var"+numVar);
                numVar++;
            }
            
            log.add("\nBloco TK_senao:");
            log.add("Buscando token de senao.");
            if(t.getNome().equals("TK_senao")){
                log.add("Encontrou token de senao.");
                log.add("Incluindo codigo alimentado.");
                codigo.add(".else");
            }
            
            log.add("\nBloco TK_fimbloco:");
            log.add("Buscando token de fim de bloco.");
            if(t.getNome().equals("TK_fimbloco")){
                log.add("Encontrou token de fim de bloco.");
                if(liberaEnq = true){
                    log.add("Incluindo codigo alimentado.");
                    while (contEnq > 0) {
                        codigo.add(".endw");
                        contEnq--;
                    }
                }
                if(liberaSe = true){
                    log.add("Incluindo codigo alimentado.");
                    while (contSe > 0) {
                        codigo.add(".endif");
                        contSe--;
                    }
                }
            }
               
            log.add("\nBloco TK_fim:");
            log.add("Buscando token de fim.");
            if(t.getNome().equals("TK_fim")){
                log.add("Encontrou token de fim.");
                log.add("Incluindo codigo alimentado.");
                codigo.add("inkey");
                codigo.add("end start");
            }
        }
        
        try {
            FileWriter arquivo = new FileWriter("codigo.asm");
            try (PrintWriter gravaArquivo = new PrintWriter(arquivo)) {
                for(int i = 0; i < codigo.size(); i++){
                    gravaArquivo.println(codigo.get(i));
                }
            }
            log.addFirst("\n==============================LOG=================================\n");
            log.addFirst("\nGeração de codigo:");
            log.addLast("\nGeração de codigo realizada com sucesso!");
            if(argumentos.contains("-lgc") | argumentos.contains("-tudo")){
                for(int i = 0; i < log.size(); i++){
                    System.out.println(log.get(i));
                }
            } else{
                System.out.println("\nGeração de codigo realizada com sucesso!");
            }
        } catch (IOException e){
            System.out.println("ERRO: Problema na criação do arquivo -> \n"+e);
        }
    }
}
package SINTATICO;

import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedList;

public class analiseSintatica extends superTabela{
    
    public static void analisadorSintatico(ArrayList<String> argumentos, LinkedList<String> listaTokens){
        
        mapaTabela mt = new mapaTabela();
        setTabelaSintatica(mt.tabelaSintatica());
        ArrayList<String> log = new ArrayList<>();
        boolean conclusao = false;
        
        LinkedList<String> listaProducao = new LinkedList<>();
        listaProducao.add("PROGRAMA");
        
        while (!conclusao){
            log.add("\nToken no começo da lista: "+listaTokens.peek()+"\n"+"Produção no começo da lista: "+listaProducao.peek()+"\n");
            if(getTabelaSintatica().containsKey(listaTokens.peek())){
                Map<String, Integer> mapaInterno = (getTabelaSintatica().get(listaTokens.peek()));
                if(mapaInterno.containsKey(listaProducao.peek())){
                    setListaProducao(SINTATICO.producaoTabela.producoes(mapaInterno.get(listaProducao.peek())));
                    log.add("Removeu a produção do começo da lista: "+listaProducao.peek()+"\n");
                    listaProducao.pop();
                    if(!getListaProducao().isEmpty()){
                        for(int x = 0; x < getListaProducao().size(); x++){
                            log.add("Adicionou a produção ao começo da lista: "+getListaProducao().get(x));
                            listaProducao.addFirst(getListaProducao().get(x));
                        }
                    } 
                } else{
                    if(listaTokens.isEmpty() && listaProducao.isEmpty()){
                        System.out.println("\nAnálise sintática concluida com sucesso!\n");
                        conclusao = true;
                    } else{
                        if(listaTokens.peek().equals(listaProducao.peek())){
                        log.add("Removeu o token do começo da lista: "+listaTokens.peek()+"\n"+"Removeu a produção do começo da lista: "+listaProducao.peek()+"\n");
                        listaTokens.pop();
                        listaProducao.pop();
                        } else{
                            System.out.println("\nErro sintático, token: "+listaTokens.peek()+" em posição irregular\n");
                            conclusao = true;
                            System.exit(0);
                        }
                    }
                }
            } else{
                if(listaTokens.isEmpty() && listaProducao.isEmpty()){
                    System.out.println("\nAnálise sintática concluida com sucesso!\n");
                    conclusao = true;
                } else{
                    if(listaTokens.peek().equals(listaProducao.peek())){
                    log.add("Removeu o token do começo da lista: "+listaTokens.peek()+"\n"+"Removeu a produção do começo da lista: "+listaProducao.peek()+"\n");
                    listaTokens.pop();
                    listaProducao.pop();
                    } else{
                        System.out.println("\nErro sintático, token: "+listaTokens.peek()+" em posição irregular\n");
                        conclusao = true;
                        System.exit(0);
                    }
                }
            }
        }
        
        if(argumentos.contains("-ls") | argumentos.contains("-tudo")){
            System.out.println("\n==================================================================\n");
            System.out.println("==============================LOG=================================\n");
            System.out.println("==================================================================\n");
            for(int x = 0; x < log.size(); x++){
                System.out.println(log.get(x));
            }
        }
    }
}
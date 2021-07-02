package USR;

import java.io.IOException;
import java.util.ArrayList;

public class Comeco {
    public static void main(String[] args) throws IOException{

        ArrayList<String> arguemtos = new ArrayList<>();
        boolean arg = false;

        if(args.length > 0){
            for(int x = 0; x < args.length; x++){
                arguemtos.add(args[x]);
            }
        }
        
        String arquivo = arguemtos.get(0);

        if(arguemtos.contains("-tudo") | arguemtos.contains("-lt") 
                | arguemtos.contains("-ls") | arguemtos.contains("-lse")
                | arguemtos.contains("-lgc")){
            arg = true;
        } else{
            arg = false;
        }

        try {
            ArrayList<String> arq = IO.leitura.lerArquivo(arquivo);
            if(!arq.isEmpty()){
                if(!arquivo.isEmpty()){
                    LEXICO.analiseLexica.ComparadorRegex(arg, arguemtos, arq);
                } else{
                    System.out.println("Erro: Arquivo esta vazio ou em branco.");
                }
            } else{
                System.out.println("Erro: Arquivo de configuração vazio.");
            }
        } catch (Exception e) {
            System.out.println("Erro: Problema na leitura do arquivo.\n" + e);
        }
    }
}
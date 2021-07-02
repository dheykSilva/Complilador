package IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class leitura {
    
    public static ArrayList<String> lerArquivo(String endereco){
        
        ArrayList<String> leituraArquivo = new ArrayList<>();
        
        try {
            FileReader arquivo = new FileReader(endereco);
            BufferedReader lerArquivo = new BufferedReader(arquivo);
            String linha = "";
            try {                
                while (linha != null) {
                   linha = lerArquivo.readLine();
                   leituraArquivo.add(linha);
                }
                arquivo.close();
                return leituraArquivo;
            } catch (IOException e) {
                System.out.println("Erro: Problema na leitura.\n" + e);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Aquivo não encontado.\n" + e);
        }
        return leituraArquivo;
    }   
}
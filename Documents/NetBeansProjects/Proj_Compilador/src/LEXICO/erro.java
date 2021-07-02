package LEXICO;

public class erro {
    private String nome;
    private int linha;
    private int coluna;
    
    public erro(String nome, int linha, int coluna){
        this.nome = nome;
        this.linha = linha;
        this.coluna = coluna;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
}
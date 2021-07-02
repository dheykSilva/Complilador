package LEXICO;

public class tokens {

    private String nome;
    private String lexemas;
    private int linha;
    private int coluna;

    public tokens(String nome, String lexemas, int linha, int coluna) {
        this.nome = nome;
        this.lexemas = lexemas;
        this.linha = linha;
        this.coluna = coluna;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLexemas() {
        return lexemas;
    }

    public void setLexemas(String lexemas) {
        this.lexemas = lexemas;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }
}
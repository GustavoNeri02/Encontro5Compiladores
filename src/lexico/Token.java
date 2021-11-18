package lexico;

public class Token {
    public TipoToken nome;
    public String lexema;

    public Token (TipoToken nome, String lexema){
        this.nome = nome;
        this.lexema = lexema;
    }

    //metodo para apresentar o token
    public String toString(){
        return "<" + nome + ", " + lexema + "> ";
    }


}

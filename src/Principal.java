
import lexico.Analizador;
import lexico.Token;

import javax.swing.*;

public class Principal {
    public static void main(String[] args) {
        Analizador analizador;
        Token t;
        String diretorio = "";
        JFileChooser fileChooser = new JFileChooser();
        int retorno = fileChooser.showOpenDialog(null);
        if(retorno == JFileChooser.APPROVE_OPTION){
            diretorio = fileChooser.getSelectedFile().getAbsolutePath();
            System.out.println(diretorio);
        }
        analizador = new Analizador(diretorio);

        while ((t = analizador.proximoToken()) != null){
            System.out.println(t);
        }

    }
}

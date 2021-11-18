package lexico;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Entrada {
    InputStream inputStream;

    public Entrada(String arquivoDiretorio) {
        try {
            inputStream = new FileInputStream(new File(arquivoDiretorio));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int LerProximoCaractere(){
        try{
            int pos = inputStream.read();
            System.out.println((char)pos);
            return pos;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }


}

package gerador;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EscritorDeArquivo {
    public boolean escreverArquivoTexto(String path, ArrayList<String> conteudo) {

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path));
            for (String aux : conteudo) {
                bw.append(aux + "\n");
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

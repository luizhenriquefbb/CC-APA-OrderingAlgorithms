package fileManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {

    public static void saveToFile(String caminho, String txt) {
        try {
            FileOutputStream arq = new FileOutputStream(caminho);
            PrintWriter pr = new PrintWriter(arq);
            pr.println(txt);
            pr.close();
            arq.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String readFromFile(String caminho) {
        String txt = "";
        FileInputStream arq;
        try {
            arq = new FileInputStream(caminho);
            InputStreamReader input = new InputStreamReader(arq, "UTF-8");
            BufferedReader br = new BufferedReader(input);
            String linha;
            do {
                linha = br.readLine();
                if (linha != null) {
                    txt += linha + "\n";
                }
            } while (linha != null);
            br.close();
            input.close();
            arq.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return txt;
    }
}

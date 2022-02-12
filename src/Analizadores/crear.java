package Analizadores;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author gohan
 */
public class crear {

    public static void main(String[] args) throws Exception {
        String ruta1 = "src/Analizadores/Lex.flex";
        String ruta2 = "src/Analizadores/LexC.flex";
        String[] rutaS = {"-parser", "Sintax", "src/Analizadores/Sintax.cup"};
        generar(ruta1, ruta2, rutaS);
    }
    public static void generar(String ruta1, String ruta2, String[] rutaS) throws IOException, Exception{
        File archivo;
        archivo = new File(ruta1);
        JFlex.Main.generate(archivo);
        archivo = new File(ruta2);
        JFlex.Main.generate(archivo);
        java_cup.Main.main(rutaS);
        
        Path rutaSym = Paths.get("src/Analizadores/sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get("sym.java"), 
                Paths.get("src/Analizadores/sym.java")
        );
        Path rutaSin = Paths.get("src/Analizadores/Sintax.java");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get("Sintax.java"), 
                Paths.get("src/Analizadores/Sintax.java")
        );
    }
    
}

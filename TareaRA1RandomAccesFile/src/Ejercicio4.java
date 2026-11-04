import java.io.RandomAccessFile;
import java.io.IOException;

public class Ejercicio4 {

    public static void main(String[] args) {

        try {
            RandomAccessFile fichero = new RandomAccessFile("nombres.bin", "rw");

            fichero.setLength(0);

            fichero.writeUTF("Dani");
            fichero.writeUTF("Maria");
            fichero.writeUTF("Alex");

            fichero.seek(0);

            System.out.println("Nombres guardados:");

            System.out.println(fichero.readUTF());
            System.out.println(fichero.readUTF());
            System.out.println(fichero.readUTF());

            fichero.close();

            System.out.println();
            System.out.println("No podemos saltar directamente al segundo nombre");
            System.out.println("porque writeUTF utiliza un tamaño variable.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
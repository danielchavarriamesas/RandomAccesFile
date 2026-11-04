import java.io.RandomAccessFile;
import java.io.IOException;

public class Ejercicio5 {

    public static void main(String[] args) {

        try {
            RandomAccessFile fichero = new RandomAccessFile("personas.bin", "rw");

            fichero.setLength(0);

            escribirRegistro(fichero, 1, "Dani");
            escribirRegistro(fichero, 2, "Maria");
            escribirRegistro(fichero, 3, "Alex");

            int numeroRegistro = 3;
            long posicion = (numeroRegistro - 1) * 24L;

            fichero.seek(posicion);

            int id = fichero.readInt();

            String nombre = "";
            for (int i = 0; i < 10; i++) {
                nombre += fichero.readChar();
            }

            System.out.println("Registro seleccionado: " + numeroRegistro);
            System.out.println("Posicion: " + posicion);
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nombre.trim());

            fichero.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void escribirRegistro(RandomAccessFile fichero, int id, String nombre) throws IOException {

        fichero.writeInt(id);

        while (nombre.length() < 10) {
            nombre += " ";
        }

        if (nombre.length() > 10) {
            nombre = nombre.substring(0, 10);
        }

        fichero.writeChars(nombre);
    }
}
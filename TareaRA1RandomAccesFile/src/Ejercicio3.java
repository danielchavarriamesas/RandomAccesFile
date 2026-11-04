import java.io.RandomAccessFile;
import java.io.IOException;

public class Ejercicio3 {

    public static void main(String[] args) {

        try {
            RandomAccessFile fichero = new RandomAccessFile("slots.bin", "rw");

            fichero.setLength(0);

            int[] numeros = {10, 20, 30, 40, 50};

            for (int numero : numeros) {
                fichero.writeInt(numero);
            }

            int slot = 3;
            long posicion = slot * 4L;

            fichero.seek(posicion);

            int numero = fichero.readInt();

            System.out.println("Slot seleccionado: " + slot);
            System.out.println("Posicion de lectura: " + posicion);
            System.out.println("Numero encontrado: " + numero);

            fichero.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
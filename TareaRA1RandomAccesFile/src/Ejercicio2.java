import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {

        try {
            RandomAccessFile fichero = new RandomAccessFile("nums.bin", "rw");
            Scanner teclado = new Scanner(System.in);

            for (int i = 0; i < 5; i++) {
                System.out.print("Introduce un numero entero: ");
                int numero = teclado.nextInt();
                fichero.writeInt(numero);
            }

            System.out.println("Posicion del puntero despues de escribir: " + fichero.getFilePointer());

            fichero.seek(0);

            System.out.println("Numeros guardados:");

            for (int i = 0; i < 5; i++) {
                int numero = fichero.readInt();
                System.out.println(numero);
            }

            fichero.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio7 {

    public static void main(String[] args) {

        try {
            RandomAccessFile personas = new RandomAccessFile("personas.dat", "rw");
            RandomAccessFile indice = new RandomAccessFile("personas.idx", "rw");
            Scanner teclado = new Scanner(System.in);

            personas.setLength(0);
            indice.setLength(0);

            escribirPersona(personas, 1, "Dani", 26);
            escribirPersona(personas, 2, "Maria", 30);
            escribirPersona(personas, 3, "Alex", 27);

            personas.seek(0);

            while (personas.getFilePointer() < personas.length()) {

                long offset = personas.getFilePointer();

                int id = personas.readInt();

                indice.writeInt(id);
                indice.writeLong(offset);

                personas.seek(offset + 48);
            }

            System.out.print("Introduce el ID que quieres buscar: ");
            int idBuscado = teclado.nextInt();

            indice.seek(0);

            long offsetEncontrado = -1;

            while (indice.getFilePointer() < indice.length()) {

                int id = indice.readInt();
                long offset = indice.readLong();

                if (id == idBuscado) {
                    offsetEncontrado = offset;
                    break;
                }
            }

            if (offsetEncontrado != -1) {

                personas.seek(offsetEncontrado);

                int id = personas.readInt();

                String nombre = "";
                for (int i = 0; i < 10; i++) {
                    nombre += personas.readChar();
                }

                int edad = personas.readInt();

                System.out.println("Registro encontrado.");
                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre.trim());
                System.out.println("Edad: " + edad);
                System.out.println("Offset utilizado: " + offsetEncontrado);

            } else {
                System.out.println("ID no encontrado.");
            }

            personas.close();
            indice.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void escribirPersona(RandomAccessFile fichero, int id, String nombre, int edad) throws IOException {

        fichero.writeInt(id);

        while (nombre.length() < 10) {
            nombre += " ";
        }

        if (nombre.length() > 10) {
            nombre = nombre.substring(0, 10);
        }

        fichero.writeChars(nombre);
        fichero.writeInt(edad);

        for (int i = 0; i < 20; i++) {
            fichero.writeByte(0);
        }
    }
}
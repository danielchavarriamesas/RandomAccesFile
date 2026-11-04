import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio6 {

    public static void main(String[] args) {

        try {
            RandomAccessFile fichero = new RandomAccessFile("personas.dat", "rw");
            Scanner teclado = new Scanner(System.in);

            fichero.setLength(0);

            escribirPersona(fichero, 1, "Dani", 26);
            escribirPersona(fichero, 2, "Maria", 25);
            escribirPersona(fichero, 3, "Alex", 27);

            System.out.print("Introduce el ID de la persona: ");
            int idBuscado = teclado.nextInt();

            System.out.print("Introduce la nueva edad: ");
            int nuevaEdad = teclado.nextInt();

            boolean encontrado = false;

            for (int i = 0; i < 3; i++) {

                long regInicio = i * 48L;

                fichero.seek(regInicio);

                int id = fichero.readInt();

                if (id == idBuscado) {

                    fichero.seek(regInicio + 24);

                    fichero.writeInt(nuevaEdad);

                    encontrado = true;

                    System.out.println("Registro encontrado.");
                    System.out.println("Edad actualizada.");

                    fichero.seek(regInicio);

                    int idLeido = fichero.readInt();

                    String nombre = "";
                    for (int j = 0; j < 10; j++) {
                        nombre += fichero.readChar();
                    }

                    int edad = fichero.readInt();

                    System.out.println("ID: " + idLeido);
                    System.out.println("Nombre: " + nombre.trim());
                    System.out.println("Edad: " + edad);

                    break;

                } else {
                    fichero.seek(regInicio + 48);
                }
            }

            if (!encontrado) {
                System.out.println("No se ha encontrado el ID.");
            }

            fichero.close();

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
import java.io.RandomAccessFile;
import java.io.IOException;

public class Ejercicio1 {

    public static void main(String[] args) {

        try {
            RandomAccessFile fichero = new RandomAccessFile("empleados.dat", "rw");

            fichero.setLength(0);

            int[] identificadores = {1, 2, 3};
            String[] apellidos = {"Garcia", "Martinez", "Lopez"};
            int[] departamentos = {10, 20, 30};
            double[] salarios = {1800.50, 2100.75, 1950.00};

            for (int i = 0; i < identificadores.length; i++) {
                fichero.writeInt(identificadores[i]);

                String apellido = apellidos[i];

                while (apellido.length() < 10) {
                    apellido += " ";
                }

                if (apellido.length() > 10) {
                    apellido = apellido.substring(0, 10);
                }

                fichero.writeChars(apellido);
                fichero.writeInt(departamentos[i]);
                fichero.writeDouble(salarios[i]);
            }

            fichero.seek(0);

            while (fichero.getFilePointer() < fichero.length()) {

                int identificador = fichero.readInt();

                String apellido = "";
                for (int i = 0; i < 10; i++) {
                    apellido += fichero.readChar();
                }

                int departamento = fichero.readInt();
                double salario = fichero.readDouble();

                System.out.println("ID: " + identificador);
                System.out.println("Apellido: " + apellido.trim());
                System.out.println("Departamento: " + departamento);
                System.out.println("Salario: " + salario);
                System.out.println("--------------------");
            }

            fichero.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
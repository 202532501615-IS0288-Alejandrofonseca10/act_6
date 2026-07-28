import java.util.Scanner;

public class Main {
    private static final String ARCHIVO = "inventario.txt";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ListaInventario inv = new ListaInventario();
        inv.cargarDesdeArchivo(ARCHIVO);

        int op;
        do {
            System.out.print("\n1.Ins.Inicio | 2.Ins.Final | 3.Ins.Posicion | 4.Buscar ID | 5.Buscar Nombre\n" +
                             "6.Eliminar ID | 7.Ordenar por Enlaces | 8.Mostrar | 9.Guardar y Salir\nOpción: ");
            op = sc.nextInt(); sc.nextLine();

            switch (op) {
                case 1 -> inv.insertarInicio(leerId(), leerCadena("Nombre"), leerInt("Cantidad"), leerDouble("Precio"));
                case 2 -> inv.insertarFinal(leerId(), leerCadena("Nombre"), leerInt("Cantidad"), leerDouble("Precio"));
                case 3 -> inv.insertarEnPosicion(leerId(), leerCadena("Nombre"), leerInt("Cantidad"), leerDouble("Precio"), leerInt("Posición"));
                case 4 -> System.out.println(inv.buscarPorId(leerId()));
                case 5 -> System.out.println(inv.buscarPorNombre(leerCadena("Nombre a buscar")));
                case 6 -> System.out.println(inv.eliminarPorId(leerId()) ? " Eliminado" : " No encontrado");
                case 7 -> { inv.ordenarPorIdPorEnlaces(); inv.mostrar(); }
                case 8 -> inv.mostrar();
                case 9 -> inv.guardarEnArchivo(ARCHIVO);
            }
        } while (op != 9);
    }

    
    private static int leerId() { return leerInt("ID"); }
    private static int leerInt(String msg) { System.out.print(msg + ": "); return sc.nextInt(); }
    private static double leerDouble(String msg) { System.out.print(msg + ": "); return sc.nextDouble(); }
    private static String leerCadena(String msg) { System.out.print(msg + ": "); return sc.next(); }
}
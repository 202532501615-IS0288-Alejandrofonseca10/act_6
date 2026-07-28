import java.io.*;

public class ListaInventario {

    static class Nodo {
        int id, cantidad;
        String nombre;
        double precio;
        Nodo siguiente;

        Nodo(int id, String nombre, int cantidad, double precio) {
            this.id = id;
            this.nombre = nombre;
            this.cantidad = cantidad;
            this.precio = precio;
        }

        @Override
        public String toString() {
            return String.format("ID: %-3d | Nombre: %-12s | Cant: %-4d | Precio: $%.2f", id, nombre, cantidad, precio);
        }
    }

    private Nodo cabeza;

   
    public void insertarInicio(int id, String nom, int cant, double prec) {
        Nodo nuevo = new Nodo(id, nom, cant, prec);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    public void insertarFinal(int id, String nom, int cant, double prec) {
        Nodo nuevo = new Nodo(id, nom, cant, prec);
        if (cabeza == null) { cabeza = nuevo; return; }
        Nodo aux = cabeza;
        while (aux.siguiente != null) aux = aux.siguiente;
        aux.siguiente = nuevo;
    }

    public void insertarEnPosicion(int id, String nom, int cant, double prec, int pos) {
        if (pos <= 0 || cabeza == null) { insertarInicio(id, nom, cant, prec); return; }
        Nodo nuevo = new Nodo(id, nom, cant, prec);
        Nodo aux = cabeza;
        for (int i = 0; aux.siguiente != null && i < pos - 1; i++) aux = aux.siguiente;
        nuevo.siguiente = aux.siguiente;
        aux.siguiente = nuevo;
    }

    
    public Nodo buscarPorId(int id) {
        for (Nodo aux = cabeza; aux != null; aux = aux.siguiente)
            if (aux.id == id) return aux;
        return null;
    }

    public Nodo buscarPorNombre(String nombre) {
        for (Nodo aux = cabeza; aux != null; aux = aux.siguiente)
            if (aux.nombre.equalsIgnoreCase(nombre)) return aux;
        return null;
    }

    public boolean eliminarPorId(int id) {
        if (cabeza == null) return false;
        if (cabeza.id == id) { cabeza = cabeza.siguiente; return true; }
        
        Nodo aux = cabeza;
        while (aux.siguiente != null && aux.siguiente.id != id) aux = aux.siguiente;
        
        if (aux.siguiente != null) {
            aux.siguiente = aux.siguiente.siguiente;
            return true;
        }
        return false;
    }

    
    public void ordenarPorIdPorEnlaces() {
        if (cabeza == null || cabeza.siguiente == null) return;
        boolean cambio;
        do {
            cambio = false;
            Nodo prev = null, act = cabeza, sig = cabeza.siguiente;
            while (sig != null) {
                if (act.id > sig.id) {
                    cambio = true;
                    act.siguiente = sig.siguiente;
                    sig.siguiente = act;
                    if (prev == null) cabeza = sig; 
                    else prev.siguiente = sig;
                    
                    prev = sig;
                    sig = act.siguiente;
                } else {
                    prev = act;
                    act = sig;
                    sig = sig.siguiente;
                }
            }
        } while (cambio);
    }

    
    public void guardarEnArchivo(String ruta) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            for (Nodo aux = cabeza; aux != null; aux = aux.siguiente)
                pw.println(aux.id + "," + aux.nombre + "," + aux.cantidad + "," + aux.precio);
        } catch (IOException e) { System.out.println("Error al guardar: " + e.getMessage()); }
    }

    public void cargarDesdeArchivo(String ruta) {
        cabeza = null;
        File f = new File(ruta);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String l;
            while ((l = br.readLine()) != null) {
                String[] p = l.split(",");
                insertarFinal(Integer.parseInt(p[0]), p[1], Integer.parseInt(p[2]), Double.parseDouble(p[3]));
            }
        } catch (Exception e) { System.out.println("Error al cargar: " + e.getMessage()); }
    }

    public void mostrar() {
        if (cabeza == null) { System.out.println("Lista vacía."); return; }
        for (Nodo aux = cabeza; aux != null; aux = aux.siguiente) System.out.println(aux);
    }
}
public class Producto {
    private int id;
    private String nombre;
    private int cantidad;
    private double precio;

    public Producto(int id, String nombre, int cantidad, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public double getPrecio() { return precio; }

    
    public String aTextoPlano() {
        return id + "," + nombre + "," + cantidad + "," + precio;
    }

    
    public static Producto desdeTextoPlano(String linea) {
        String[] partes = linea.split(",");
        int id = Integer.parseInt(partes[0]);
        String nombre = partes[1];
        int cantidad = Integer.parseInt(partes[2]);
        double precio = Double.parseDouble(partes[3]);
        return new Producto(id, nombre, cantidad, precio);
    }

    @Override
    public String toString() {
        return String.format("ID: %-4d | Nombre: %-15s | Cantidad: %-5d | Precio: $%.2f", 
                             id, nombre, cantidad, precio);
    }
}
import java.util.ArrayList;
import java.util.List;

public class InventarioSystem {

    private List<Cliente> clientes = new ArrayList<>();
    private List<Proveedor> proveedores = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void agregarProveedor(Proveedor proveedor) {
        proveedores.add(proveedor);
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    public void mostrarProveedores() {
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
            return;
        }
        for (Proveedor pr : proveedores) {
            System.out.println("ID:" + pr.getProveedorId() + " - " + pr);
        }
    }

    public void mostrarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }

    public void valorTotalInventario() {
        double total = 0;

        for (Producto p : productos) {
            total += p.calcularValorInventario();
        }

        System.out.println("Valor total del inventario: $" + total);
    }

    public Proveedor getProveedorById(int id) {
        for (Proveedor p : proveedores) {
            if (p.getProveedorId() == id) return p;
        }
        return null;
    }

    public int nextProductId() {
        int max = 0;
        for (Producto p : productos) {
            if (p.productoId > max) max = p.productoId;
        }
        return max + 1;
    }

    // Getters para acceso seguro desde la UI
    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public List<Producto> getProductos() {
        return productos;
    }

}

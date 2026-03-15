import javax.swing.*;
import java.awt.*;

public class Main {

    private InventarioSystem sistema = new InventarioSystem();
    private JFrame frame;
    private JTextArea outputArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
    }

    private void createAndShowGUI() {
        // Datos de ejemplo
        Proveedor proveedor1 = new Proveedor(1, "ElectroMax", "8112345678", "contacto@electromax.com", "Av. Tecnológico 123");
        Proveedor proveedor2 = new Proveedor(2, "TecnoWorld", "8123456789", "ventas@tecnoworld.com", "Calle Reforma 456");
        sistema.agregarProveedor(proveedor1);
        sistema.agregarProveedor(proveedor2);
        sistema.agregarCliente(new Cliente(1, "Juan", "Pérez", "8110001111", "juan.perez@email.com", "Calle Falsa 123"));
        sistema.agregarProducto(new ProductoComputadora(1, "Laptop Dell", "Laptop 15 pulgadas", "Computadoras", 15000, 10, proveedor1));
        sistema.agregarProducto(new ProductoAccesorio(2, "Mouse Logitech", "Mouse inalámbrico", "Accesorios", 500, 50, proveedor2));
        sistema.agregarProducto(new ProductoCelular(3, "Samsung Galaxy", "Smartphone Android", "Celulares", 20000, 8, proveedor2));

        frame = new JFrame("SISTEMA DE GESTION DE INVENTARIO - Electronia ACME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(Color.WHITE);

        // Header (banner + título + autor)
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        String bannerPath = "D:\\Universidad _Ciudadana\\POA_ACT4\\IDS-ActF4-Georgeadis_Arias\\Banner.jpg";
        ImageIcon bannerIcon = null;
        try {
            Image img = new ImageIcon(bannerPath).getImage();
            Image scaled = img.getScaledInstance(200, 80, Image.SCALE_SMOOTH);
            bannerIcon = new ImageIcon(scaled);
        } catch (Exception ex) {
            bannerIcon = null;
        }
        JLabel lblBanner = bannerIcon != null ? new JLabel(bannerIcon) : new JLabel();
        lblBanner.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 12));
        JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftHeader.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("<html><div style='font-size:18px; font-weight:bold;'>SISTEMA DE GESTIÓN DE INVENTARIO</div><div style='font-size:12px;'>Electronia ACME</div></html>");
        leftHeader.add(lblBanner);
        leftHeader.add(titleLabel);
        JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightHeader.setBackground(Color.WHITE);
        JLabel authorLabel = new JLabel("<html><div style='font-size:12px;color:#666666;'>Por: Georgeadis Arias</div></html>");
        authorLabel.setBorder(BorderFactory.createEmptyBorder(6,12,6,12));
        rightHeader.add(authorLabel);
        header.add(leftHeader, BorderLayout.CENTER);
        header.add(rightHeader, BorderLayout.EAST);
        frame.add(header, BorderLayout.NORTH);

        // Panel de botones
        JPanel left = new JPanel();
        left.setLayout(new GridLayout(6, 1, 8, 8));
        left.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        left.setBackground(new Color(245, 247, 250));

        JButton bMostrarProveedores = new JButton("Mostrar proveedores");
        JButton bMostrarProductos = new JButton("Mostrar productos");
        JButton bMostrarClientes = new JButton("Mostrar clientes");
        JButton bAgregarProducto = new JButton("Agregar producto");
        JButton bValorTotal = new JButton("Ver valor total");
        JButton bSalir = new JButton("Salir");

        // Aplicar estilo a botones
        Font btnFont = new Font("SansSerif", Font.PLAIN, 13);
        JButton[] botones = new JButton[]{bMostrarProveedores, bMostrarProductos, bMostrarClientes, bAgregarProducto, bValorTotal, bSalir};
        for (JButton b : botones) {
            b.setFont(btnFont);
            b.setFocusPainted(false);
            b.setBackground(Color.WHITE);
            b.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(220,220,220)), BorderFactory.createEmptyBorder(6,8,6,8)));
            left.add(b);
        }

        // Área de salida
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        outputArea.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JScrollPane scroll = new JScrollPane(outputArea);

        frame.add(left, BorderLayout.WEST);
        frame.add(scroll, BorderLayout.CENTER);

        // Listeners
        bMostrarProveedores.addActionListener(e -> mostrarProveedoresUI());
        bMostrarProductos.addActionListener(e -> mostrarProductosUI());
        bMostrarClientes.addActionListener(e -> mostrarClientesUI());
        bAgregarProducto.addActionListener(e -> agregarProductoDialog());
        bValorTotal.addActionListener(e -> mostrarValorTotalUI());
        bSalir.addActionListener(e -> frame.dispose());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void mostrarProveedoresUI() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== PROVEEDORES ===\n");
        for (Proveedor p : sistema.getProveedores()) {
            sb.append("ID:").append(p.getProveedorId()).append(" - ").append(p).append('\n');
        }
        outputArea.setText(sb.toString());
    }

    private void mostrarProductosUI() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== PRODUCTOS ===\n");
        for (Producto p : sistema.getProductos()) {
            sb.append(p).append('\n');
        }
        outputArea.setText(sb.toString());
    }

    private void mostrarClientesUI() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== CLIENTES ===\n");
        for (Cliente c : sistema.getClientes()) {
            sb.append(c).append('\n');
        }
        outputArea.setText(sb.toString());
    }

    private void mostrarValorTotalUI() {
        // calcular total
        double total = 0;
        for (Producto p : sistema.getProductos()) total += p.calcularValorInventario();
        outputArea.setText(String.format("Valor total del inventario: $%.2f", total));
    }

    private void agregarProductoDialog() {
        JDialog dialog = new JDialog(frame, "Agregar producto", true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(0, 2, 6, 6));

        dialog.add(new JLabel("Tipo:"));
        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Computadora", "Accesorio", "Celular"});
        dialog.add(cbTipo);

        dialog.add(new JLabel("Nombre:"));
        JTextField tfNombre = new JTextField();
        dialog.add(tfNombre);

        dialog.add(new JLabel("Descripcion:"));
        JTextField tfDesc = new JTextField();
        dialog.add(tfDesc);

        dialog.add(new JLabel("Categoria:"));
        JTextField tfCat = new JTextField();
        dialog.add(tfCat);

        dialog.add(new JLabel("Precio unitario:"));
        JTextField tfPrecio = new JTextField();
        dialog.add(tfPrecio);

        dialog.add(new JLabel("Stock inicial:"));
        JTextField tfStock = new JTextField();
        dialog.add(tfStock);

        dialog.add(new JLabel("Proveedor (ID):"));
        JComboBox<Integer> cbProv = new JComboBox<>();
        for (Proveedor p : sistema.getProveedores()) cbProv.addItem(p.getProveedorId());
        dialog.add(cbProv);

        JButton bAdd = new JButton("Agregar");
        JButton bCancel = new JButton("Cancelar");
        dialog.add(bAdd);
        dialog.add(bCancel);

        bCancel.addActionListener(ev -> dialog.dispose());
        bAdd.addActionListener(ev -> {
            try {
                String tipo = (String) cbTipo.getSelectedItem();
                String nombre = tfNombre.getText().trim();
                String desc = tfDesc.getText().trim();
                String cat = tfCat.getText().trim();
                double precio = Double.parseDouble(tfPrecio.getText().trim());
                int stock = Integer.parseInt(tfStock.getText().trim());
                int provId = (Integer) cbProv.getSelectedItem();
                Proveedor prov = sistema.getProveedorById(provId);
                int id = sistema.nextProductId();
                Producto nuevo = null;
                if ("Computadora".equals(tipo)) nuevo = new ProductoComputadora(id, nombre, desc, cat, precio, stock, prov);
                else if ("Accesorio".equals(tipo)) nuevo = new ProductoAccesorio(id, nombre, desc, cat, precio, stock, prov);
                else if ("Celular".equals(tipo)) nuevo = new ProductoCelular(id, nombre, desc, cat, precio, stock, prov);
                if (nuevo != null) {
                    sistema.agregarProducto(nuevo);
                    mostrarProductosUI();
                    dialog.dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Datos invalidos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

}

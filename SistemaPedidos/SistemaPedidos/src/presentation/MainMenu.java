package presentation;

import model.Cliente;
import model.Producto;
import model.Pedido;
import service.ClienteService;
import service.ProductoService;
import service.PedidoService;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    
    private static Scanner scanner = new Scanner(System.in);
    
    // Servicios (Capa de Negocio)
    private static ClienteService clienteService = new ClienteService();
    private static ProductoService productoService = new ProductoService();
    private static PedidoService pedidoService = new PedidoService();
    
    public static void main(String[] args) {

        System.out.println("SISTEMA DE PEDIDOS");
        System.out.println("Arquitectura de Tres Capas\n");

        
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1:
                    menuClientes();
                    break;
                case 2:
                    menuProductos();
                    break;
                case 3:
                    menuPedidos();
                    break;
                case 4:
                    System.out.println("\n¡Gracias por usar el sistema!\n");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 4);
        
        scanner.close();
    }
    
    // Menu principal
    
    private static void mostrarMenuPrincipal() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Gestión de Clientes");
        System.out.println("2. Gestión de Productos");
        System.out.println("3. Gestión de Pedidos");
        System.out.println("4. Salir");
    }
    
    // Menu de clientes
    
    private static void menuClientes() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar Cliente");
            System.out.println("4. Actualizar Cliente");
            System.out.println("5. Eliminar Cliente");
            System.out.println("6. Volver");
            
            opcion = leerEntero("Opción: ");
            
            switch (opcion) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    buscarCliente();
                    break;
                case 4:
                    actualizarCliente();
                    break;
                case 5:
                    eliminarCliente();
                    break;
                case 6:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 6);
    }
    
    private static void registrarCliente() {
        System.out.println("\n--- REGISTRAR CLIENTE ---");
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        
        Cliente cliente = new Cliente(nombre, apellido, email, telefono, direccion);
        
        if (clienteService.registrar(cliente)) {
            System.out.println(" Cliente registrado con ID: " + cliente.getIdCliente());
        } else {
            System.out.println(" Error al registrar cliente.");
        }
    }
    
    private static void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        List<Cliente> clientes = clienteService.listar();
        
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.println("ID | Nombre Completo | Email");
            System.out.println("--------------------------------");
            for (Cliente c : clientes) {
                System.out.println(c.getIdCliente() + " | " + c.getNombre() + " " + c.getApellido() + " | " + c.getEmail());
            }
        }
    }
    
    private static void buscarCliente() {
        System.out.println("\n--- BUSCAR CLIENTE ---");
        int id = leerEntero("Ingrese ID del cliente: ");
        
        Cliente cliente = clienteService.buscar(id);
        
        if (cliente != null) {
            System.out.println("\n Cliente encontrado:");
            System.out.println("ID: " + cliente.getIdCliente());
            System.out.println("Nombre: " + cliente.getNombre() + " " + cliente.getApellido());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Teléfono: " + cliente.getTelefono());
            System.out.println("Dirección: " + cliente.getDireccion());
            System.out.println("Fecha Registro: " + cliente.getFechaRegistro());
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
    
    private static void actualizarCliente() {
        System.out.println("\n--- ACTUALIZAR CLIENTE ---");
        int id = leerEntero("Ingrese ID del cliente a actualizar: ");
        
        Cliente cliente = clienteService.buscar(id);
        
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        
        System.out.println("Deje en blanco para mantener el valor actual.");
        
        System.out.print("Nombre (" + cliente.getNombre() + "): ");
        String nombre = scanner.nextLine();
        if (!nombre.trim().isEmpty()) cliente.setNombre(nombre);
        
        System.out.print("Apellido (" + cliente.getApellido() + "): ");
        String apellido = scanner.nextLine();
        if (!apellido.trim().isEmpty()) cliente.setApellido(apellido);
        
        System.out.print("Email (" + cliente.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.trim().isEmpty()) cliente.setEmail(email);
        
        System.out.print("Teléfono (" + cliente.getTelefono() + "): ");
        String telefono = scanner.nextLine();
        if (!telefono.trim().isEmpty()) cliente.setTelefono(telefono);
        
        System.out.print("Dirección (" + cliente.getDireccion() + "): ");
        String direccion = scanner.nextLine();
        if (!direccion.trim().isEmpty()) cliente.setDireccion(direccion);
        
        if (clienteService.actualizar(cliente)) {
            System.out.println("Cliente actualizado correctamente.");
        } else {
            System.out.println("Error al actualizar cliente.");
        }
    }
    
    private static void eliminarCliente() {
        System.out.println("\n--- ELIMINAR CLIENTE ---");
        int id = leerEntero("Ingrese ID del cliente a eliminar: ");
        
        Cliente cliente = clienteService.buscar(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        
        System.out.print("¿Está seguro de eliminar a " + cliente.getNombre() + "? (s/n): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("s")) {
            if (clienteService.eliminar(id)) {
                System.out.println("Cliente eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar cliente.");
            }
        } else {
            System.out.println("Operación cancelada.");
        }
    }
    
    // Menu de productos
    
    private static void menuProductos() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
            System.out.println("1. Registrar Producto");
            System.out.println("2. Listar Productos");
            System.out.println("3. Buscar Producto");
            System.out.println("4. Verificar Stock");
            System.out.println("5. Listar por Categoría");
            System.out.println("6. Volver");
            
            opcion = leerEntero("Opción: ");
            
            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    buscarProducto();
                    break;
                case 4:
                    verificarStock();
                    break;
                case 5:
                    listarPorCategoria();
                    break;
                case 6:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }
    
    private static void registrarProducto() {
        System.out.println("\n--- REGISTRAR PRODUCTO ---");
        
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        
        double precio = leerDouble("Precio: ");
        
        int stock = leerEntero("Stock inicial: ");
        
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();
        
        Producto producto = new Producto(nombre, descripcion, precio, stock, categoria);
        
        if (productoService.registrar(producto)) {
            System.out.println("Producto registrado con ID: " + producto.getIdProducto());
        } else {
            System.out.println("Error al registrar producto.");
        }
    }
    
    private static void listarProductos() {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        List<Producto> productos = productoService.listar();
        
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("ID | Nombre | Precio | Stock | Categoría");
            System.out.println("------------------------------------------------");
            for (Producto p : productos) {
                System.out.println(p.getIdProducto() + " | " + p.getNombreProducto() + " | $" + p.getPrecio() + " | " + p.getStock() + " | " + p.getCategoria());
            }
        }
    }
    
    private static void buscarProducto() {
        System.out.println("\n--- BUSCAR PRODUCTO ---");
        int id = leerEntero("Ingrese ID del producto: ");
        
        Producto producto = productoService.buscar(id);
        
        if (producto != null) {
            System.out.println("\n Producto encontrado:");
            System.out.println("ID: " + producto.getIdProducto());
            System.out.println("Nombre: " + producto.getNombreProducto());
            System.out.println("Descripción: " + producto.getDescripcion());
            System.out.println("Precio: $" + producto.getPrecio());
            System.out.println("Stock: " + producto.getStock());
            System.out.println("Categoría: " + producto.getCategoria());
            System.out.println("Fecha de alta: " + producto.getFechaAlta());
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
    
    private static void verificarStock() {
        System.out.println("\n--- VERIFICAR STOCK ---");
        int id = leerEntero("Ingrese ID del producto: ");
        
        int stock = productoService.obtenerStockActual(id);
        
        if (stock >= 0) {
            System.out.println("Stock actual: " + stock + " unidades.");
            if (stock == 0) {
                System.out.println("Producto agotado.");
            } else if (stock < 5) {
                System.out.println("Stock bajo. ¡Reabastecer pronto!");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
    
    private static void listarPorCategoria() {
        System.out.println("\n--- LISTAR POR CATEGORÍA ---");
        System.out.print("Ingrese categoría: ");
        String categoria = scanner.nextLine();
        
        List<Producto> productos = productoService.listarPorCategoria(categoria);
        
        if (productos.isEmpty()) {
            System.out.println("No hay productos en la categoría '" + categoria + "'.");
        } else {
            System.out.println("Productos en '" + categoria + "':");
            for (Producto p : productos) {
                System.out.println("  - " + p.getNombreProducto() + " | $" + p.getPrecio() + " | Stock: " + p.getStock());
            }
        }
    }
    
    // Menu de pedidos
    
    private static void menuPedidos() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
            System.out.println("1. Realizar Pedido");
            System.out.println("2. Listar Pedidos");
            System.out.println("3. Buscar Pedidos por Cliente");
            System.out.println("4. Actualizar Estado de Pedido");
            System.out.println("5. Volver");
            
            opcion = leerEntero("Opción: ");
            
            switch (opcion) {
                case 1:
                    realizarPedido();
                    break;
                case 2:
                    listarPedidos();
                    break;
                case 3:
                    buscarPedidosPorCliente();
                    break;
                case 4:
                    actualizarEstadoPedido();
                    break;
                case 5:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }
    
    private static void realizarPedido() {
        System.out.println("\n--- REALIZAR PEDIDO ---");
        
        // Mostrar clientes disponibles
        listarClientes();
        int idCliente = leerEntero("\nIngrese ID del cliente: ");
        
        // Mostrar productos disponibles
        listarProductos();
        int idProducto = leerEntero("\nIngrese ID del producto: ");
        
        int cantidad = leerEntero("Ingrese cantidad: ");
        
        int idPedido = pedidoService.realizarPedido(idCliente, idProducto, cantidad);
        
        if (idPedido > 0) {
            System.out.println("Pedido realizado con éxito. Número de pedido: " + idPedido);
        } else {
            System.out.println("Error al realizar el pedido. Verifique stock o datos ingresados.");
        }
    }
    
    private static void listarPedidos() {
        System.out.println("\n--- LISTA DE PEDIDOS ---");
        List<Pedido> pedidos = pedidoService.listarPedidos();
        
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
        } else {
            System.out.println("ID | Cliente ID | Fecha | Total | Estado");
            System.out.println("--------------------------------------------");
            for (Pedido p : pedidos) {
                System.out.println(p.getIdPedido() + " | " + p.getIdCliente() + " | " + p.getFechaPedido() + " | $" + p.getTotal() + " | " + p.getEstado());
            }
        }
    }
    
    private static void buscarPedidosPorCliente() {
        System.out.println("\n--- PEDIDOS POR CLIENTE ---");
        int idCliente = leerEntero("Ingrese ID del cliente: ");
        
        List<Pedido> pedidos = pedidoService.listarPedidosPorCliente(idCliente);
        
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos para este cliente.");
        } else {
            System.out.println("Pedidos del cliente ID " + idCliente + ":");
            for (Pedido p : pedidos) {
                System.out.println("  - Pedido #" + p.getIdPedido() + " | $" + p.getTotal() + " | " + p.getEstado() + " | " + p.getFechaPedido());
            }
        }
    }
    
    private static void actualizarEstadoPedido() {
        System.out.println("\n--- ACTUALIZAR ESTADO DE PEDIDO ---");
        int idPedido = leerEntero("Ingrese ID del pedido: ");
        
        System.out.println("Estados disponibles: PENDIENTE, COMPLETADO, CANCELADO");
        String estado = leerTexto("Nuevo estado: ");
        
        if (pedidoService.cambiarEstadoPedido(idPedido, estado)) {
            System.out.println("Estado actualizado correctamente.");
        } else {
            System.out.println("Error al actualizar estado.");
        }
    }
    
    // Metodos auxiliares
    
    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Debe ingresar un número. " + mensaje);
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }
    
    private static double leerDouble(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.print("Debe ingresar un número decimal. " + mensaje);
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpia buffer
        return valor;
    }
    
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
}
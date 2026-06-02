package service;

import model.Producto;
import dao.ProductoDAO;
import java.util.List;

public class ProductoService {
    
    private ProductoDAO productoDAO;
    
    public ProductoService() {
        this.productoDAO = new ProductoDAO();
    }
    
    //Validaciones
    
    private boolean validar(Producto producto) {
        if (producto.getNombreProducto() == null || producto.getNombreProducto().trim().isEmpty()) {
            System.out.println("Error: El nombre del producto es obligatorio");
            return false;
        }
        
        if (producto.getPrecio() < 0) {
            System.out.println("Error: El precio no puede ser negativo");
            return false;
        }
        
        if (producto.getStock() < 0) {
            System.out.println("Error: El stock no puede ser negativo");
            return false;
        }
        
        if (producto.getCategoria() == null || producto.getCategoria().trim().isEmpty()) {
            System.out.println("Error: La categoría es obligatoria");
            return false;
        }
        
        return true;
    }
    
    
    //Crud
    
    public boolean registrar(Producto producto) {
        if (!validar(producto)) {
            return false;
        }
        return productoDAO.insertar(producto);
    }
    
    public List<Producto> listar() {
        return productoDAO.obtenerTodos();
    }
    
    public Producto buscar(int id) {
        if (id <= 0) {
            System.out.println("Error: ID de producto inválido");
            return null;
        }
        return productoDAO.obtenerPorId(id);
    }
    
    public boolean actualizarStock(int idProducto, int cantidadVendida) {
        if (idProducto <= 0) {
            System.out.println("Error: ID de producto inválido");
            return false;
        }
        
        if (cantidadVendida <= 0) {
            System.out.println("Error: La cantidad debe ser mayor a 0");
            return false;
        }
        
        // Verificar que hay suficiente stock
        Producto producto = productoDAO.obtenerPorId(idProducto);
        if (producto == null) {
            System.out.println("Error: Producto no encontrado");
            return false;
        }
        
        if (producto.getStock() < cantidadVendida) {
            System.out.println("Error: Stock insuficiente. Stock actual: " + producto.getStock());
            return false;
        }
        
        return productoDAO.actualizarStock(idProducto, cantidadVendida);
    }
    
    // Metodos Utilies
    
    public boolean existeProducto(int id) {
        Producto p = productoDAO.obtenerPorId(id);
        return p != null;
    }
    
    public boolean hayStock(int idProducto, int cantidadNecesaria) {
        Producto producto = productoDAO.obtenerPorId(idProducto);
        if (producto == null) {
            return false;
        }
        return producto.getStock() >= cantidadNecesaria;
    }
    
    public int obtenerStockActual(int idProducto) {
        Producto producto = productoDAO.obtenerPorId(idProducto);
        if (producto == null) {
            return -1;
        }
        return producto.getStock();
    }
    
    public List<Producto> listarPorCategoria(String categoria) {
        List<Producto> todos = productoDAO.obtenerTodos();
        List<Producto> filtrados = new java.util.ArrayList<>();
        
        for (Producto p : todos) {
            if (p.getCategoria() != null && p.getCategoria().equalsIgnoreCase(categoria)) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }
    
    public List<Producto> listarConStockDisponible() {
        List<Producto> todos = productoDAO.obtenerTodos();
        List<Producto> conStock = new java.util.ArrayList<>();
        
        for (Producto p : todos) {
            if (p.getStock() > 0) {
                conStock.add(p);
            }
        }
        return conStock;
    }
}
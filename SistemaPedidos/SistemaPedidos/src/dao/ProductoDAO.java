package dao;

import model.Producto;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    
    public boolean insertar(Producto producto) {
        String sql = "INSERT INTO Producto (nombreProducto, descripcion, precio, stock, categoria) "
                   + "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, producto.getNombreProducto());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setDouble(3, producto.getPrecio());
            pstmt.setInt(4, producto.getStock());
            pstmt.setString(5, producto.getCategoria());
            
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    producto.setIdProducto(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error en ProductoDAO.insertar: " + e.getMessage());
        }
        return false;
    }
    
    public List<Producto> obtenerTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM Producto";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombreProducto(rs.getString("nombreProducto"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setCategoria(rs.getString("categoria"));
                p.setFechaAlta(rs.getString("fechaAlta"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error en ProductoDAO.obtenerTodos: " + e.getMessage());
        }
        return lista;
    }
    
    public Producto obtenerPorId(int id) {
        String sql = "SELECT * FROM Producto WHERE idProducto = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombreProducto(rs.getString("nombreProducto"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setCategoria(rs.getString("categoria"));
                p.setFechaAlta(rs.getString("fechaAlta"));
                return p;
            }
        } catch (SQLException e) {
            System.err.println("Error en ProductoDAO.obtenerPorId: " + e.getMessage());
        }
        return null;
    }
    
    public boolean actualizarStock(int idProducto, int cantidadVendida) {
        String sql = "UPDATE Producto SET stock = stock - ? WHERE idProducto = ? AND stock >= ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, cantidadVendida);
            pstmt.setInt(2, idProducto);
            pstmt.setInt(3, cantidadVendida);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en ProductoDAO.actualizarStock: " + e.getMessage());
        }
        return false;
    }
}
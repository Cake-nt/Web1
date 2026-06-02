package dao;  // ← Cambiado: ahora está en DAO, no en model

import model.Pedido;        // ← Importar la entidad desde model
import model.DetallePedido; // ← Importar la entidad desde model
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    
    // Registrar un pedido completo (usando stored procedure)
    public int registrarPedido(int idCliente, int idProducto, int cantidad) {
        String sql = "{call sp_RegistrarPedido(?, ?, ?)}";
        
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            
            cstmt.setInt(1, idCliente);
            cstmt.setInt(2, idProducto);
            cstmt.setInt(3, cantidad);
            
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("idPedido");
            }
        } catch (SQLException e) {
            System.err.println("Error registrando pedido: " + e.getMessage());
        }
        return -1;
    }
    
    // Obtener todos los pedidos
    public List<Pedido> obtenerTodosPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido ORDER BY fechaPedido DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt("idPedido"));
                p.setIdCliente(rs.getInt("idCliente"));
                p.setFechaPedido(rs.getString("fechaPedido"));
                p.setTotal(rs.getDouble("total"));
                p.setEstado(rs.getString("estado"));
                p.setMetodoPago(rs.getString("metodoPago"));
                pedidos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo pedidos: " + e.getMessage());
        }
        return pedidos;
    }
    
    // Obtener pedidos por cliente
    public List<Pedido> obtenerPedidosPorCliente(int idCliente) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido WHERE idCliente = ? ORDER BY fechaPedido DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idCliente);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt("idPedido"));
                p.setIdCliente(rs.getInt("idCliente"));
                p.setFechaPedido(rs.getString("fechaPedido"));
                p.setTotal(rs.getDouble("total"));
                p.setEstado(rs.getString("estado"));
                p.setMetodoPago(rs.getString("metodoPago"));
                pedidos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo pedidos del cliente: " + e.getMessage());
        }
        return pedidos;
    }
    
    // Obtener detalles de un pedido específico
    public List<DetallePedido> obtenerDetallesPedido(int idPedido) {
        List<DetallePedido> detalles = new ArrayList<>();
        String sql = "SELECT * FROM DetallePedido WHERE idPedido = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idPedido);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                DetallePedido dp = new DetallePedido();
                dp.setIdDetalle(rs.getInt("idDetalle"));
                dp.setIdPedido(rs.getInt("idPedido"));
                dp.setIdProducto(rs.getInt("idProducto"));
                dp.setCantidad(rs.getInt("cantidad"));
                dp.setPrecioUnitario(rs.getDouble("precioUnitario"));
                dp.setSubtotal(rs.getDouble("subtotal"));
                detalles.add(dp);
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo detalles: " + e.getMessage());
        }
        return detalles;
    }
    
    // Actualizar estado del pedido
    public boolean actualizarEstadoPedido(int idPedido, String nuevoEstado) {
        String sql = "UPDATE Pedido SET estado = ? WHERE idPedido = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nuevoEstado);
            pstmt.setInt(2, idPedido);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error actualizando estado: " + e.getMessage());
        }
        return false;
    }
}
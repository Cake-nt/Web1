package dao;

import model.Cliente;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    
    public boolean insertar(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nombre, apellido, email, telefono, direccion) "
                   + "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4, cliente.getTelefono());
            pstmt.setString(5, cliente.getDireccion());
            
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error en ClienteDAO.insertar: " + e.getMessage());
        }
        return false;
    }
    
    public List<Cliente> obtenerTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente WHERE activo = 1";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("idCliente"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setEmail(rs.getString("email"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                c.setFechaRegistro(rs.getString("fechaRegistro"));
                c.setActivo(rs.getBoolean("activo"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error en ClienteDAO.obtenerTodos: " + e.getMessage());
        }
        return lista;
    }
    
    public Cliente obtenerPorId(int id) {
        String sql = "SELECT * FROM Cliente WHERE idCliente = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("idCliente"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setEmail(rs.getString("email"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                c.setFechaRegistro(rs.getString("fechaRegistro"));
                c.setActivo(rs.getBoolean("activo"));
                return c;
            }
        } catch (SQLException e) {
            System.err.println("Error en ClienteDAO.obtenerPorId: " + e.getMessage());
        }
        return null;
    }
    
    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombre=?, apellido=?, email=?, telefono=?, direccion=? "
                   + "WHERE idCliente=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4, cliente.getTelefono());
            pstmt.setString(5, cliente.getDireccion());
            pstmt.setInt(6, cliente.getIdCliente());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en ClienteDAO.actualizar: " + e.getMessage());
        }
        return false;
    }
    
    public boolean eliminar(int id) {
        String sql = "UPDATE Cliente SET activo = 0 WHERE idCliente = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en ClienteDAO.eliminar: " + e.getMessage());
        }
        return false;
    }
}
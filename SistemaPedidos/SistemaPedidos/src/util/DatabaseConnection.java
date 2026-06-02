package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    // Configuracion para Autenticacion de Windows
    private static final String URL = "jdbc:sqlserver://localhost:1433;"
                                    + "databaseName=SistemaPedidos;"
                                    + "integratedSecurity=true;"      //
                                    + "encrypt=false;"
                                    + "trustServerCertificate=true";
    
    
    private static Connection connection = null;
    
    // Carga el driver JDBC
    
    static {
        try {
            // Cargar el driver de SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver JDBC cargado correctamente.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error cargando el driver JDBC: " + e.getMessage());
        }
    }
    
    // Obtiene la conexión
    
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            // IMPORTANTE: No se pasan usuario y contraseña
            connection = DriverManager.getConnection(URL);
            System.out.println("Conexión establecida con SQL Server (Windows Authentication)");
        }
        return connection;
    }
    
    // Cierra la conexión
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error cerrando conexión: " + e.getMessage());
            }
        }
    }
    
    // Método de prueba (opcional)
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ ¡Conexión exitosa a SQL Server!");
            }
            closeConnection();
        } catch (SQLException e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
        }
    }
}


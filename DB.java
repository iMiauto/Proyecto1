import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// import com.mysql.jdbc.Connection;
// import com.mysql.jdbc.PreparedStatement;

import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class DB {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DB().mostrarGUI());
    }

    public void mostrarGUI() {
         JFrame frame = new JFrame("Administración de Base de Datos");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JTabbedPane pestañas = new JTabbedPane();

        // Panel de CARRERAS
        JPanel panelCarreras = new JPanel(new BorderLayout(10, 10));
        panelCarreras.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel controlesCarreras = new JPanel(new GridLayout(5, 2, 10, 10));
        JTextField txtIdCarrera = new JTextField();
        JTextField txtChofer = new JTextField();
        JTextField txtPasajeros = new JTextField();
        JTextField txtCL = new JTextField();
        JTextField txtLC = new JTextField();

        controlesCarreras.add(new JLabel("ID Carrera"));
        controlesCarreras.add(txtIdCarrera);
        controlesCarreras.add(new JLabel("Nombre Chofer"));
        controlesCarreras.add(txtChofer);
        controlesCarreras.add(new JLabel("Pasajeros"));
        controlesCarreras.add(txtPasajeros);
        controlesCarreras.add(new JLabel("Cañas → Liberia"));
        controlesCarreras.add(txtCL);
        controlesCarreras.add(new JLabel("Liberia → Cañas"));
        controlesCarreras.add(txtLC);

        JPanel botonesCarreras = new JPanel();
        botonesCarreras.add(crearBoton("Insertar"));
        botonesCarreras.add(crearBoton("Actualizar"));
        botonesCarreras.add(crearBoton("Eliminar"));
        botonesCarreras.add(crearBoton("Ver Todos"));

        JTable tablaCarreras = new JTable(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Chofer", "Pasajeros", "Cañas_Lib", "Liberia_Cañas"}
        ));
        JScrollPane scrollCarreras = new JScrollPane(tablaCarreras);

        JPanel centroCarreras = new JPanel(new BorderLayout(5, 5));
        centroCarreras.add(controlesCarreras, BorderLayout.NORTH);
        centroCarreras.add(botonesCarreras, BorderLayout.CENTER);

        panelCarreras.add(new JLabel("Gestión de Carreras", SwingConstants.CENTER), BorderLayout.NORTH);
        panelCarreras.add(centroCarreras, BorderLayout.WEST);
        panelCarreras.add(scrollCarreras, BorderLayout.CENTER);

        // Panel de USUARIOS
        JPanel panelUsuarios = new JPanel(new BorderLayout(10, 10));
        panelUsuarios.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel controlesUsuarios = new JPanel(new GridLayout(7, 2, 10, 10));
        JTextField txtCedula = new JTextField();
        JTextField txtNombre1 = new JTextField();
        JTextField txtNombre2 = new JTextField();
        JTextField txtApellido1 = new JTextField();
        JTextField txtApellido2 = new JTextField();
        JTextField txtLogin = new JTextField();
        JTextField txtPassword = new JTextField();

        controlesUsuarios.add(new JLabel("Cédula"));
        controlesUsuarios.add(txtCedula);
        controlesUsuarios.add(new JLabel("Nombre 1"));
        controlesUsuarios.add(txtNombre1);
        controlesUsuarios.add(new JLabel("Nombre 2"));
        controlesUsuarios.add(txtNombre2);
        controlesUsuarios.add(new JLabel("Apellido 1"));
        controlesUsuarios.add(txtApellido1);
        controlesUsuarios.add(new JLabel("Apellido 2"));
        controlesUsuarios.add(txtApellido2);
        controlesUsuarios.add(new JLabel("Login"));
        controlesUsuarios.add(txtLogin);
        controlesUsuarios.add(new JLabel("Contraseña"));
        controlesUsuarios.add(txtPassword);

        JPanel botonesUsuarios = new JPanel();
        botonesUsuarios.add(crearBoton("Insertar"));
        botonesUsuarios.add(crearBoton("Actualizar"));
        botonesUsuarios.add(crearBoton("Eliminar"));
        botonesUsuarios.add(crearBoton("Ver Todos"));

        JTable tablaUsuarios = new JTable(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Cédula", "Nombre1", "Nombre2", "Apellido1", "Apellido2", "Login", "Contraseña"}
        ));
        JScrollPane scrollUsuarios = new JScrollPane(tablaUsuarios);

        JPanel centroUsuarios = new JPanel(new BorderLayout(5, 5));
        centroUsuarios.add(controlesUsuarios, BorderLayout.NORTH);
        centroUsuarios.add(botonesUsuarios, BorderLayout.CENTER);

        panelUsuarios.add(new JLabel("Gestión de Usuarios", SwingConstants.CENTER), BorderLayout.NORTH);
        panelUsuarios.add(centroUsuarios, BorderLayout.WEST);
        panelUsuarios.add(scrollUsuarios, BorderLayout.CENTER);

        pestañas.add("Carreras", panelCarreras);
        pestañas.add("Autentificación", panelUsuarios);

        frame.add(pestañas);
        frame.setVisible(true);

        botonesUsuarios.getComponent(0).addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        try (Connection conn =  conectarDB()) {
            String sql = "INSERT INTO autentificacion (Cedula, Nombre1, Nombre2, Apellido1, Apellido2, Login, Contraseña) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, txtCedula.getText().trim());
            stmt.setString(2, txtNombre1.getText().trim());
            stmt.setString(3, txtNombre2.getText().trim());
            stmt.setString(4, txtApellido1.getText().trim());
            stmt.setString(5, txtApellido2.getText().trim());
            stmt.setString(6, txtLogin.getText().trim());
            stmt.setString(7, txtPassword.getText().trim());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
                // Opcional: Limpiar campos
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar: " + ex.getMessage());
        }
    }
});

    botonesUsuarios.getComponent(3).addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            DefaultTableModel model = (DefaultTableModel) tablaUsuarios.getModel();
            model.setRowCount(0); 

            try (Connection conn = conectarDB()) {
                String sql = "SELECT * FROM autentificacion";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Object[] fila = {
                        rs.getString("Cedula"),
                        rs.getString("Nombre1"),
                        rs.getString("Nombre2"),
                        rs.getString("Apellido1"),
                        rs.getString("Apellido2"),
                        rs.getString("Login"),
                        rs.getString("Contraseña")
                    };
                    model.addRow(fila);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al consultar registros: " + ex.getMessage());
            }
        }
    });

botonesUsuarios.getComponent(1).addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        String cedula = txtCedula.getText().trim();
        String nombre1 = txtNombre1.getText().trim();
        String nombre2 = txtNombre2.getText().trim();
        String apellido1 = txtApellido1.getText().trim();
        String apellido2 = txtApellido2.getText().trim();
        String login = txtLogin.getText().trim();
        String contraseña = txtPassword.getText().trim();

        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar la cédula del usuario que desea actualizar.", "Campo obligatorio", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = conectarDB()) {
            String sql = "UPDATE autentificacion SET Nombre1=?, Nombre2=?, Apellido1=?, Apellido2=?, Login=?, Contraseña=? WHERE Cedula=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre1);
            stmt.setString(2, nombre2);
            stmt.setString(3, apellido1);
            stmt.setString(4, apellido2);
            stmt.setString(5, login);
            stmt.setString(6, contraseña);
            stmt.setString(7, cedula);

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un usuario con esa cédula.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario: " + ex.getMessage());
        }
    }
});


        
    }

  private java.sql.Connection conectarDB() throws SQLException {
    String url = "jdbc:mysql://localhost:3306/proyecto1";
    String usuario = "root";
    String contraseña = "Tree23815";
    return DriverManager.getConnection(url, usuario, contraseña);
}


    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        Color base = new Color(20, 113, 159);
        Color hover = new Color(114, 182, 216);

        btn.setBackground(base);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(base);
            }
        });

        return btn;
    }
}




import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DB {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DB().mostrarGUI());
    }

    public void mostrarGUI() {
        JFrame frame = new JFrame("Administración de Base de Datos");
        JFrame frame = new JFrame("Administración de Base de Datos");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(new Color(180, 60, 60));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.setPreferredSize(new Dimension(120, 35));
        btnRegresar.addActionListener(e -> frame.dispose());

        JPanel panelInferior = new JPanel();
        panelInferior.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelInferior.add(btnRegresar);
        frame.add(panelInferior, BorderLayout.SOUTH);

        JTabbedPane pestañas = new JTabbedPane();

        // COMPONENTES CARRERAS
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
        JButton btnInsertarCarrera = crearBoton("Insertar");
        JButton btnActualizarCarrera = crearBoton("Actualizar");
        JButton btnEliminarCarrera = crearBoton("Eliminar");
        JButton btnVerCarrera = crearBoton("Ver Todos");
        botonesCarreras.add(btnInsertarCarrera);
        botonesCarreras.add(btnActualizarCarrera);
        botonesCarreras.add(btnEliminarCarrera);
        botonesCarreras.add(btnVerCarrera);

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

        // COMPONENTES USUARIOS
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
        JButton btnInsertarUsuario = crearBoton("Insertar");
        JButton btnActualizarUsuario = crearBoton("Actualizar");
        JButton btnEliminarUsuario = crearBoton("Eliminar");
        JButton btnVerUsuario = crearBoton("Ver Todos");
        botonesUsuarios.add(btnInsertarUsuario);
        botonesUsuarios.add(btnActualizarUsuario);
        botonesUsuarios.add(btnEliminarUsuario);
        botonesUsuarios.add(btnVerUsuario);

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

        // Eventos
        btnInsertarCarrera.addActionListener(e -> {
            try (Connection conn = conectarDB()) {
                String sql = "INSERT INTO carreras (idCarreras, nombreChofer, pasajeros, Cañas_Liberia_Total, Liberia_Cañas_Total) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(txtIdCarrera.getText().trim()));
                stmt.setString(2, txtChofer.getText().trim());
                stmt.setString(3, txtPasajeros.getText().trim());
                stmt.setString(4, txtCL.getText().trim());
                stmt.setString(5, txtLC.getText().trim());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Carrera registrada correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
            txtIdCarrera.setText(""); txtChofer.setText(""); txtPasajeros.setText(""); txtCL.setText(""); txtLC.setText("");
        });

        btnActualizarCarrera.addActionListener(e -> {
            try (Connection conn = conectarDB()) {
                String sql = "UPDATE carreras SET nombreChofer=?, pasajeros=?, Cañas_Liberia_Total=?, Liberia_Cañas_Total=? WHERE idCarreras=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, txtChofer.getText().trim());
                stmt.setString(2, txtPasajeros.getText().trim());
                stmt.setString(3, txtCL.getText().trim());
                stmt.setString(4, txtLC.getText().trim());
                stmt.setInt(5, Integer.parseInt(txtIdCarrera.getText().trim()));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Carrera actualizada correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
            txtIdCarrera.setText(""); txtChofer.setText(""); txtPasajeros.setText(""); txtCL.setText(""); txtLC.setText("");
        });

        btnEliminarCarrera.addActionListener(e -> {
            try (Connection conn = conectarDB()) {
                String sql = "DELETE FROM carreras WHERE idCarreras=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(txtIdCarrera.getText().trim()));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Carrera eliminada correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
            txtIdCarrera.setText(""); txtChofer.setText(""); txtPasajeros.setText(""); txtCL.setText(""); txtLC.setText("");
        });

        btnInsertarUsuario.addActionListener(e -> {
            try (Connection conn = conectarDB()) {
                String sql = "INSERT INTO autentificacion (Cedula, Nombre1, Nombre2, Apellido1, Apellido2, Login, Contraseña) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, txtCedula.getText().trim());
                stmt.setString(2, txtNombre1.getText().trim());
                stmt.setString(3, txtNombre2.getText().trim());
                stmt.setString(4, txtApellido1.getText().trim());
                stmt.setString(5, txtApellido2.getText().trim());
                stmt.setString(6, txtLogin.getText().trim());
                stmt.setString(7, txtPassword.getText().trim());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
            txtCedula.setText(""); txtNombre1.setText(""); txtNombre2.setText(""); txtApellido1.setText(""); txtApellido2.setText(""); txtLogin.setText(""); txtPassword.setText("");
        });

        btnActualizarUsuario.addActionListener(e -> {
            try (Connection conn = conectarDB()) {
                String sql = "UPDATE autentificacion SET Nombre1=?, Nombre2=?, Apellido1=?, Apellido2=?, Login=?, Contraseña=? WHERE Cedula=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, txtNombre1.getText().trim());
                stmt.setString(2, txtNombre2.getText().trim());
                stmt.setString(3, txtApellido1.getText().trim());
                stmt.setString(4, txtApellido2.getText().trim());
                stmt.setString(5, txtLogin.getText().trim());
                stmt.setString(6, txtPassword.getText().trim());
                stmt.setString(7, txtCedula.getText().trim());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
            txtCedula.setText(""); txtNombre1.setText(""); txtNombre2.setText(""); txtApellido1.setText(""); txtApellido2.setText(""); txtLogin.setText(""); txtPassword.setText("");
        });

        btnEliminarUsuario.addActionListener(e -> {
            try (Connection conn = conectarDB()) {
                String sql = "DELETE FROM autentificacion WHERE Cedula=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, txtCedula.getText().trim());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
            txtCedula.setText(""); txtNombre1.setText(""); txtNombre2.setText(""); txtApellido1.setText(""); txtApellido2.setText(""); txtLogin.setText(""); txtPassword.setText("");
        });

        btnVerCarrera.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) tablaCarreras.getModel();
            model.setRowCount(0);
            try (Connection conn = conectarDB()) {
                String sql = "SELECT * FROM carreras";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getInt("idCarreras"),
                            rs.getString("nombreChofer"),
                            rs.getString("pasajeros"),
                            rs.getString("Cañas_Liberia_Total"),
                            rs.getString("Liberia_Cañas_Total")
                    });
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        btnVerUsuario.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) tablaUsuarios.getModel();
            model.setRowCount(0);
            try (Connection conn = conectarDB()) {
                String sql = "SELECT * FROM autentificacion";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getString("Cedula"),
                            rs.getString("Nombre1"),
                            rs.getString("Nombre2"),
                            rs.getString("Apellido1"),
                            rs.getString("Apellido2"),
                            rs.getString("Login"),
                            rs.getString("Contraseña")
                    });
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });




    }

    private Connection conectarDB() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto1", "root", "Tree23815");
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
            public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
            @Override
            public void mouseExited(MouseEvent e) { btn.setBackground(base); }
        });
        return btn;
    }
}


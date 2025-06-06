
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class liberia_Cañas extends Carreras{
    //Atributos
    public double  totalGanancias_LiberiaCañas = 0;

private Connection conn;
private int idCarrera;
private String nombreChofer;
private double precio;

public liberia_Cañas(int idCarrera, String nombreChofer, int sentido, double precio, Connection conn) {
    super(idCarrera, nombreChofer, sentido, precio); // Llama a Carreras

    this.idCarrera = idCarrera;
    this.nombreChofer = nombreChofer;
    this.precio = precio;
    this.conn = conn;

    if (sentido == 2) {
        inicioCarrera();
    }

    // Continúa con la interfaz como antes
}

public void CalcularPorSentido(String sentido, double totalTiket) {
        if (sentido.equals("Liberia-Cañas")) {
            totalGanancias_LiberiaCañas += totalTiket;

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/busesinurbanos", "root", "")) {
                String sql = "UPDATE carreras SET Liberia_Cañas_Total = Liberia_Cañas_Total + ? WHERE idCarreras = ? AND nombreChofer = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDouble(1, totalTiket);
                ps.setInt(2, getUnidad());
                ps.setString(3, nombreChofer);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el total por sentido: " + e.getMessage());
            }
        }
    }

    @Override
    public void sentidoCarera() {
    System.out.println("Carrera en el sentido Liberia - Cañas.");
    }


        @Override
    public void inicioCarrera() {
        JOptionPane.showMessageDialog(null, "Inicio de carrera Liberia - Cañas");
        setPasajeros(0);
        setTotalCarrera(0);
        setTotalTiket(0);
            
            JFrame frame = new JFrame("Iniciar Carrera");
            frame.setResizable(false);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
            Container contenedor = frame.getContentPane();
            contenedor.setLayout(null);
            contenedor.setBackground(new Color(246, 239, 239));
        
            JLabel labelDestino, labelCantidad;
            JButton pagar, terminar;
            JTextField fieldCantidad;
            JComboBox<String> comboDestino;
            
        
            String[] paradas = new String[]{"El salto (400 colones)", "Pijije (550 colones)", "Bagaces (800 colones)", 
                                    "Montenegro (1200 colones)", "Entrada Upala (1200 colones)", "Los Pumas (1600 colones)", 
                                    "Cañas (1800 colones)"};
            double[] tarifas = new double[]{400, 550, 800, 1200, 1200, 1600, 1800};
                // sentido = "Cañas-Liberia"; // Removed: sentido is likely an int, not a String
        
            
        
        
            labelDestino = new JLabel("Destino:");
            labelDestino.setFont(new Font("Times New Roman", Font.BOLD, 14));
            labelDestino.setBounds(20, 20, 100, 30);
            contenedor.add(labelDestino);
        
        
            comboDestino = new JComboBox<>(paradas);
            comboDestino.setBounds(130, 20, 220, 30);
            contenedor.add(comboDestino);
        
        
            labelCantidad = new JLabel("Cantidad de pasajeros:");
            labelCantidad.setFont(new Font("Times New Roman", Font.BOLD, 14));
            labelCantidad.setBounds(20, 70, 200, 30);
            contenedor.add(labelCantidad);
        
        
            fieldCantidad = new JTextField();
            fieldCantidad.setBounds(200, 70, 150, 30);
            contenedor.add(fieldCantidad);
        
            
            pagar = new JButton("Pagar");
            pagar.setBounds(50, 130, 120, 40);
            pagar.setBackground(new Color(20, 113, 159));
            pagar.setForeground(Color.WHITE);
            contenedor.add(pagar);
        
            
            terminar = new JButton("Terminar");
            terminar.setBounds(200, 130, 120, 40);
            terminar.setBackground(new Color(20, 113, 159));
            terminar.setForeground(Color.WHITE);
            contenedor.add(terminar);
        
        
            pagar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean validacion = true;
        int indice = 0;
        String opcionSelec = (String) comboDestino.getSelectedItem();

        try {
            pasajeros = Integer.parseInt(fieldCantidad.getText().trim());
            if (pasajeros <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ingrese una cantidad válida de pasajeros", "Error", JOptionPane.ERROR_MESSAGE);
            validacion = false;
        }

        if (comboDestino == null || opcionSelec == null) {
            JOptionPane.showMessageDialog(frame, "Seleccione un destino válido", "Error", JOptionPane.ERROR_MESSAGE);
            validacion = false;
        }

        for (int i = 0; i < paradas.length; i++) {
            if (opcionSelec.equals(paradas[i])) {
                indice = i;
            }
        }

        if (validacion) {
            double total = pasajeros * precio;

            try {
                String sql = "UPDATE carreras SET Liberia_Cañas_Total = ?, Pasajeros = ? WHERE idCarreras = ? AND nombreChofer = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDouble(1, total);
                ps.setInt(2, pasajeros);
                ps.setInt(3, idCarrera);
                ps.setString(4, nombreChofer);

                ps.executeUpdate();
                ps.close();

                mostrarFactura(paradas[indice], precio, total);
                JOptionPane.showMessageDialog(frame, "Datos guardados correctamente en la base de datos.");

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error al guardar en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
});

        
            
            terminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calcularGanancias(totalTiket);
                    setTotalPasajeros(totalPasajeros);
                    frame.dispose();
                }
            });
        
        
            frame.setVisible(true);
        }
        
        
            public void mostrarFactura(String parada, double tarifa, double totalPgar) {
                StringBuilder factura = new StringBuilder();
                factura.append("********** FACTURA **********\n");
                factura.append("Sentido de Carrera: ").append(sentido).append("\n");
                factura.append("Parada Final: ").append(parada).append("\n");
                factura.append("Tarifa por pasajero: ").append(tarifa).append(" colones\n");
                factura.append("Número de Pasajeros: ").append(pasajeros).append("\n");
                factura.append("Monto Total: ").append(totalPgar).append(" colones\n");
                factura.append("*");
        
                JOptionPane.showMessageDialog(null, factura.toString(), "Factura", JOptionPane.INFORMATION_MESSAGE); 
            }
            @Override
            public String toString() {
            return "Unidad: " + getUnidad() +
               "\nChofer: " + nombreChofer +
               "\nPasajeros: " + getPasajeros() +
               "\nTotal Carrera: " + getTotalCarrera() +
            "\nTotal Tiket: " + getTotalTiket() +
               "\nGanancias Liberia-Cañas: " + totalGanancias_LiberiaCañas;
            }
    
}

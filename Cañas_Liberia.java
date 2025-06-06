

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Cañas_Liberia extends Carreras {
    
    public double totalGanancias_CañasLiberia;

private Connection conn;
private int idCarrera;
private String nombreChofer;
private double precio;

public Cañas_Liberia(int idCarrera, String nombreChofer, int sentido, double precio, Connection conn) {
    super(idCarrera, nombreChofer, sentido, precio); // Llamada al constructor de Carreras

    this.idCarrera = idCarrera;
    this.nombreChofer = nombreChofer;
    this.precio = precio;
    this.conn = conn;

    if (sentido == 2) {
        inicioCarrera();
    }

    // Acá va el resto de tu interfaz o lógica
}

  

    @Override
    public void sentidoCarera() {
        System.out.println("Carrera en el sentido Cañas - Liberia.");
    }
    @Override
    public void CalcularPorSentido(String sentido, double totalTiket){
        totalGanancias_CañasLiberia += totalTiket;
    }



    @Override
    public void inicioCarrera() {
    totalTiket=0; 
     pasajeros =0; 
     
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
    

    String[] paradas = new String[]{"Los Pumas (500 colones)", "Montenegro (800 colones)", "Bagaces (800 colones)", 
                               "Pijije (1200 colones)", "Llanos del Cortéz (1200 colones)", "El Salto (1600 colones)", 
                               "Liberia (1800 colones)"};
     double[] tarifas = new double[]{500, 800, 800, 1200, 1200, 1600, 1800};
        sentido = "Cañas-Liberia";
  
    

   
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
        public void actionPerformed(ActionEvent e) {pagar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean validacion = true; 
        double totalTickets = 0; 
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
            totalTickets = pasajeros * precio;

            try {
                String sql = "UPDATE carreras SET Cañas_Liberia_Total = ?, Pasajeros = ? WHERE idCarreras = ? AND nombreChofer = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDouble(1, totalTickets);
                ps.setInt(2, pasajeros);
                ps.setInt(3, idCarrera);
                ps.setString(4, nombreChofer);
                ps.executeUpdate();
                ps.close();

                mostrarFactura(paradas[indice], precio, totalTickets);
                JOptionPane.showMessageDialog(frame, "Datos guardados correctamente en la base de datos.");

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error al guardar los datos en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
});

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
        return "Cañas-Liberia" + "\n" 
                +  "totalTiket="  + "\n" 
                + " totalGanancias_CañasLiberia=" + getTotalGanancias() 
                + "pasajeros=" + getTotalPasajeros()  + "\n" ; 
                
    }
}
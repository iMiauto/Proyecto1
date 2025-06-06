

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

public class Cañas_Liberia extends Carreras {
    
    public double totalGanancias_CañasLiberia = 0;

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
 public void CalcularPorSentido(String sentido, double totalTiket) {
        if (sentido.equals("Cañas-Liberia")) {
            totalGanancias_CañasLiberia += totalTiket;

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/busesinurbanos", "root", "")) {
                String sql = "UPDATE carreras SET Cañas_Liberia_Total = Cañas_Liberia_Total + ? WHERE idCarreras = ? AND nombreChofer = ?";
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
    public void inicioCarrera() {
        JOptionPane.showMessageDialog(null, "Inicio de carrera Cañas - Liberia");
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
    

    String[] paradas = new String[]{"Los Pumas (500 colones)", "Montenegro (800 colones)", "Bagaces (800 colones)", 
                            "Pijije (1200 colones)", "Llanos del Cortéz (1200 colones)", "El Salto (1600 colones)", 
                            "Liberia (1800 colones)"};
    double[] tarifas = new double[]{500, 800, 800, 1200, 1200, 1600, 1800};
        String sentidoCarrera = "Cañas-Liberia";

    


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
         double valoTickets; 
         int indice = 0; 
         String opcionSelec = (String) comboDestino.getSelectedItem(); 
         try{
         pasajeros = Integer.parseInt(fieldCantidad.getText().trim());
         if (pasajeros <=0){
             throw new NumberFormatException (); 
         }
         }catch (NumberFormatException ex){
             JOptionPane.showMessageDialog(frame , "Ingrese cantidad de pasajeros valido", "Error", JOptionPane.ERROR_MESSAGE);
             validacion = false; 
         }
         
         if(comboDestino == null){
             JOptionPane.showMessageDialog(frame, "Ingrese un destino valido", "Error", JOptionPane.ERROR_MESSAGE);
             validacion = false; 
         }
         
         for(String parada : paradas){

            for (int i =0; i<paradas.length; i++){

                if (opcionSelec.equals(paradas[i])){
                    indice = i; 
                }
            }
         }
         
         
         if (validacion == true){
            totalPasajeros += pasajeros;
             totalTiket = tarifas[indice]*pasajeros; 
             mostrarFactura(paradas[indice], tarifas[indice],totalTiket);
             CalcularPorSentido(sentidoCarrera, totalTiket);
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
        factura.append("Sentido de Carrera: ").append("Cañas-Liberia").append("\n");
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
               "\nGanancias Cañas-Liberia: " + totalGanancias_CañasLiberia;
    }
}
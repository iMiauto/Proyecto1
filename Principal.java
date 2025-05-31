import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
public class Principal extends JFrame implements Serializable{
public ArrayList<Carreras> arrayCarrera = new ArrayList(); 


    public Principal() {

        setTitle("Reina del Campo S.A.");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        menuPrincipal();
        setVisible(true);
    }

    private void menuPrincipal() {

        Container contenedor = getContentPane();
        contenedor.setLayout(null);
        contenedor.setBackground(new Color(246, 239, 239));

        // Etiqueta de título
        JLabel labelTitulo = new JLabel("Reina del Campo S.A");
        labelTitulo.setFont(new Font("Times New Roman", Font.BOLD, 20));
        labelTitulo.setBounds(100, 20, 200, 30);
        labelTitulo.setForeground(new Color(20, 113, 159));
        contenedor.add(labelTitulo);


        JButton buttonCarrera = new JButton("Carrera");
        buttonCarrera.setBounds(50, 80, 120, 40);
        buttonCarrera.setBackground(new Color(20, 113, 159));
        buttonCarrera.setForeground(Color.WHITE);
        contenedor.add(buttonCarrera);


        JButton buttonCrear = new JButton("Agregar unidades");
        buttonCrear.setBounds(200, 80, 140, 40);
        buttonCrear.setBackground(new Color(20, 113, 159));
        buttonCrear.setForeground(Color.WHITE);
        contenedor.add(buttonCrear);


        JButton buttonMuestra = new JButton("Ingresos");
        buttonMuestra.setBounds(125, 150, 120, 40);
        buttonMuestra.setBackground(new Color(20, 113, 159));
        buttonMuestra.setForeground(Color.WHITE);
        contenedor.add(buttonMuestra);


        buttonCarrera.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonCarrera.setBackground(new Color(114, 182, 216)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonCarrera.setBackground(new Color(20, 113, 159)); // Restaurar color al salir del mouse
            }
            @Override
            public void mouseClicked (MouseEvent e){
                carrera();
            }
            
        });
        
        buttonCrear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonCrear.setBackground(new Color(114, 182, 216)); // Cambiar color al pasar el mouse
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonCrear.setBackground(new Color(20, 113, 159)); // Restaurar color al salir del mouse
            }
            @Override
            public void mouseClicked (MouseEvent e){
                int funcionamiento = 1 ; 
                String nombre =  ""; 
               
                

                Carreras control_Cañas = new Cañas_Liberia(funcionamiento,nombre,3,1.1);
                arrayCarrera.add(control_Cañas); 

                for (Carreras carreras : arrayCarrera) {

                     funcionamiento = carreras.getUnidad();   
                     nombre = carreras.getNombreUnidad();   
                     Carreras control__Liberia = new liberia_Cañas(funcionamiento, nombre, 3, 1.1);
                     arrayCarrera.add(control__Liberia);

                }
  
            }

        });
           buttonMuestra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonMuestra.setBackground(new Color(114, 182, 216)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonMuestra.setBackground(new Color(20, 113, 159)); 
            }
            @Override
            public void mouseClicked (MouseEvent e){
                ingresosPorUnidad();
            }
        });
        

    }
    /**
     * 
     */
    public void ingresosPorUnidad() {
        JFrame frame = new JFrame("Ingresos por Unidad");
        frame.setResizable(false);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        Container contenedor = frame.getContentPane();
        contenedor.setLayout(null);
        contenedor.setBackground(new Color(246, 239, 239));
    
    
        JLabel lblTitulo = new JLabel("Consultar Ingresos por Unidad");
        lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblTitulo.setBounds(120, 20, 300, 30);
        contenedor.add(lblTitulo);
    
        JLabel lblUnidad = new JLabel("Número de Unidad:");
        lblUnidad.setBounds(50, 80, 150, 20);
        contenedor.add(lblUnidad);
    
        JTextField txtUnidad = new JTextField();
        txtUnidad.setBounds(200, 80, 150, 25);
        contenedor.add(txtUnidad);
    
        JTextArea areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultados);
        scroll.setBounds(50, 150, 400, 150);
        contenedor.add(scroll);
    
        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(180, 320, 120, 30);
        btnConsultar.setBackground(new Color(20, 113, 159));
        btnConsultar.setForeground(Color.WHITE);
        contenedor.add(btnConsultar);
    
      
        btnConsultar.addActionListener(e -> {
            try {
                int numeroUnidad = Integer.parseInt(txtUnidad.getText().trim());
                boolean unidadEncontrada = false;
                double totalIngresos = 0.0;
                int totalPasajeros = 0;
                StringBuilder detalles = new StringBuilder();
    
                
                for (Carreras carrera : arrayCarrera) {
                    if (carrera != null && carrera.A != null && carrera.A.getNumeroUnidad() == numeroUnidad && carrera.sentido != null) {
                        unidadEncontrada = true;
                
                        if (carrera instanceof Cañas_Liberia) {
                            totalIngresos += ((Cañas_Liberia) carrera).totalGanancias_CañasLiberia;
                        } else if (carrera instanceof liberia_Cañas) {
                            totalIngresos += ((liberia_Cañas) carrera).totalGanancias_LiberiaCañas;
                        }
                
                        totalPasajeros += carrera.getTotalPasajeros();
                
                        detalles.append("Sentido: ").append(carrera.sentido)
                               .append(" | Pasajeros: ").append(carrera.getTotalPasajeros())
                               .append(" | Ingresos: ").append(carrera.totalTiket)
                               .append("\n");
                    }
                }
                
    
                if (unidadEncontrada) {
                    areaResultados.setText(
                        "=== Resumen de Unidad " + numeroUnidad + " ===\n" +
                        "Chofer: " + arrayCarrera.stream()
                            .filter(c -> c.A != null && c.A.getNumeroUnidad() == numeroUnidad)
                            .findFirst()
                            .map(c -> c.A.getNombre())
                            .orElse("No asignado") + "\n" +
                        "Total Pasajeros: " + totalPasajeros + "\n" +
                        "Total Ingresos: ₡" + totalIngresos + "\n\n" +
                        "=== Detalles por Carrera ===\n" +
                        detalles.toString()
                    );
                } else {
                    JOptionPane.showMessageDialog(frame, "Unidad no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Ingrese un número de unidad válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        frame.setVisible(true);
    }
   
     
public void carrera() { 
    JFrame frame = new JFrame("Validación");
    frame.setResizable(false);
    frame.setSize(400, 400);  
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    Container contenedor = frame.getContentPane();
    contenedor.setLayout(null);
    contenedor.setBackground(new Color(246, 239, 239));

    // Etiqueta de título
    JLabel labelTitulo = new JLabel("Validación de Carrera");
    labelTitulo.setFont(new Font("Times New Roman", Font.BOLD, 18));
    labelTitulo.setBounds(100, 10, 200, 30);
    labelTitulo.setForeground(new Color(20, 113, 159));
    contenedor.add(labelTitulo);

    // Etiqueta: Nombre del chofer
    JLabel labelNombre = new JLabel("Ingrese usuario:");
    labelNombre.setFont(new Font("Times New Roman", Font.BOLD, 14));
    labelNombre.setBounds(20, 50, 200, 20);
    contenedor.add(labelNombre);

    // Campo de texto: Nombre del chofer
    JTextField Chofer = new JTextField();
    Chofer.setBounds(200, 50, 150, 25);
    contenedor.add(Chofer);

    // Etiqueta: Número de unidad
    JLabel labelUnidad = new JLabel("Ingrese Código:");
    labelUnidad.setFont(new Font("Times New Roman", Font.BOLD, 14));
    labelUnidad.setBounds(20, 90, 200, 20);
    contenedor.add(labelUnidad);

    // Campo de texto: Número de unidad
    JTextField numeroUnidad = new JTextField();
    numeroUnidad.setBounds(200, 90, 150, 25);
    contenedor.add(numeroUnidad);

    // Etiqueta: Selección de carrera
    JLabel inicioCarrera = new JLabel("Seleccione el sentido de Carrera:");
    inicioCarrera.setFont(new Font("Times New Roman", Font.BOLD, 14));
    inicioCarrera.setBounds(20, 130, 250, 20);
    contenedor.add(inicioCarrera);

    // Opciones de carrera con JCheckBox
    JCheckBox bCañas_Liberia = new JCheckBox("Cañas - Liberia");
    bCañas_Liberia.setBounds(50, 160, 150, 20);
    bCañas_Liberia.setBackground(new Color(246, 239, 239));
    contenedor.add(bCañas_Liberia);
    

    JCheckBox bLiberia_Cañas = new JCheckBox("Liberia - Cañas");
    bLiberia_Cañas.setBounds(50, 190, 150, 20);
    bLiberia_Cañas.setBackground(new Color(246, 239, 239));
    contenedor.add(bLiberia_Cañas);
    
    ButtonGroup selecion = new ButtonGroup(); 
    selecion.add(bCañas_Liberia);
    selecion.add(bLiberia_Cañas);
    

    
    JTextArea explicacion = new JTextArea(
        "Aclaraciones para el uso durante las carreras:\n"
        + "- Nombre del chofer: Usuario\n"
        + "- Número de unidad: Clave de ingreso"
    );
    explicacion.setBounds(20, 220, 350, 60);
    explicacion.setEditable(false);
    explicacion.setFont(new Font("Arial", Font.PLAIN, 12));
    explicacion.setBackground(new Color(230, 230, 230));
    contenedor.add(explicacion);


    JButton aceptar = new JButton("Aceptar");
    aceptar.setBounds(140, 300, 120, 30);
    aceptar.setBackground(new Color(20, 133, 159));
    aceptar.setForeground(Color.white);
    contenedor.add(aceptar);

    
    aceptar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            String nombreChofer = Chofer.getText().trim();
            if (nombreChofer.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Ingrese un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

          
            int numeroComparacio;
            try {
                numeroComparacio = Integer.parseInt(numeroUnidad.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Ingrese un número válido para la unidad.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

          
            if (arrayCarrera.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No hay choferes registrados.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

           
            boolean validacion = false;
            for (Carreras buscar : arrayCarrera) {
                if (buscar.A.getNumeroUnidad() == numeroComparacio && nombreChofer.equals(buscar.A.getNombre())) {
                    validacion = true;
                    break;
                }
            }

            if ( validacion == false) {
                JOptionPane.showMessageDialog(frame, "El usuario o número de unidad son incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }

            if(validacion == true){
               if (bCañas_Liberia.isSelected()) {
                Carreras controlCarreras = new Cañas_Liberia(1, "No necesario", 2, 1.1);
                arrayCarrera.add(controlCarreras);
            }
            if (bLiberia_Cañas.isSelected()) {
              
                Carreras controlCarreras = new liberia_Cañas(1, "No necesario", 2, 1.2);    
                arrayCarrera.add(controlCarreras);
            }  
            }
           
        }
    });

    frame.setVisible(true);
}

   private void validacion (){

    int sentido; 
}

    public static void main(String[] args) {
        new Principal(); 
    }
}
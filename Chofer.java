
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
public class Chofer extends JFrame implements Serializable{
   public String nombre; 
   public int numeroUnidad; 
   public double sentido; 

    public Chofer() {
       JFrame frame = new JFrame("Agregar Unidades");
        frame.setResizable(false);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBackground(new Color(20, 113, 159));
        frame.setVisible(true);
        
        Container contenedor = frame.getContentPane();
        contenedor.setLayout(null);
        contenedor.setBackground(new Color(246, 239, 239));

        // Herramientas
        JLabel labelNombre, labelUnidad;
        JButton aceptar;
        JTextField numeroUnidadd, chofer;
        JTextArea explicacion;

        // Etiqueta: Nombre del chofer
        labelNombre = new JLabel("Ingrese el nombre del chofer:");
        labelNombre.setFont(new Font("Times New Roman", Font.BOLD, 14));
        labelNombre.setBounds(20, 20, 200, 20);
        contenedor.add(labelNombre);

        // Campo de texto: Nombre del chofer
        chofer = new JTextField();
        chofer.setBounds(220, 20, 150, 25);
        contenedor.add(chofer);

        // Etiqueta: Número de unidad
        labelUnidad = new JLabel("Ingrese el número de unidad:");
        labelUnidad.setFont(new Font("Times New Roman", Font.BOLD, 14));
        labelUnidad.setBounds(20, 60, 200, 20);
        contenedor.add(labelUnidad);

        // Campo de texto: Número de unidad
        numeroUnidadd = new JTextField();
        numeroUnidadd.setBounds(220, 60, 150, 25);
        contenedor.add(numeroUnidadd);

        // Área de texto: Explicación
        explicacion = new JTextArea(
                "Aclaraciones para el uso durante las carreras:\n"
                + "- Nombre del chofer: Usuario\n"
                + "- Número de unidad: Clave de ingreso"
        );
        explicacion.setBounds(20, 100, 350, 60);
        explicacion.setEditable(false);
        explicacion.setFont(new Font("Arial", Font.PLAIN, 12));
        explicacion.setBackground(new Color(230, 230, 230));
        contenedor.add(explicacion);

        // Botón: Aceptar
        aceptar = new JButton("Aceptar");
        aceptar.setBounds(150, 200, 100, 30);
        aceptar.setBackground(new Color(20, 133, 159));
        aceptar.setForeground(Color.white);
        contenedor.add(aceptar);

        // Acción del botón
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean validacion = true;
                
                
                String nombreChofer = chofer.getText();
                if (nombreChofer.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Ingrese el nombre del chofer.", "Error", JOptionPane.ERROR_MESSAGE);
                    validacion = false;
                }
                setNombre(nombreChofer);
                
              
                int unidad = 0; 
                try {
                    unidad = Integer.parseInt(numeroUnidadd.getText().trim());
                    if (unidad <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ingrese un valor numérico válido para la unidad.", "Error", JOptionPane.ERROR_MESSAGE);
                    validacion = false;
                }
                setNumeroUnidad(unidad);
                
                if (validacion ==true) {
                    JOptionPane.showMessageDialog(frame, "Unidad registrada correctamente.");
                    frame.dispose();
                }
            }
        });
       
             aceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                aceptar.setBackground(new Color(114, 182, 216)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                aceptar.setBackground(new Color(20, 113, 159)); 
            }
        });
       
    }
    public String muestra (){
    String buildString = "Nombre del chofer: " + getNombre() + "\n" +
            "Número de unidad: " + getNumeroUnidad() + "\n"
            +  "\n" ;
    return buildString;
    }
        
    

    public String getNombre() {
        return nombre;
    }

    public int getNumeroUnidad() {
        return numeroUnidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumeroUnidad(int numeroUnidad) {
        this.numeroUnidad = numeroUnidad;
    }
    public void setSentido(double sentido) {
        this.sentido = sentido;
    }
    public double getSentido() {
        return sentido;
    }
      
}
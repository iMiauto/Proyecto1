
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*; 
import java.io.*;

public abstract class Carreras implements Serializable {

    protected int pasajeros, totalPasajeros, unidad;
    protected static double  totalGanancias;
    protected double totalCarrera;
    protected double totalTiket; 
    protected String sentido, nombreUnidad;
    protected static Chofer A; 

    public Carreras(int u, String n, int s, double p) {
        switch (s) {
            case 1:
                A.setNombre(n);
                A.setNumeroUnidad(u);
                A.setSentido(s);
                break;
           
            case 3: 
                A = new Chofer();
                break;
           }
    }
    public static  void calcularGanancias(double totalTiket) {
        totalGanancias += totalTiket;
    }
    
    public abstract void sentidoCarera();
    public abstract void inicioCarrera();
    public abstract void CalcularPorSentido(String sentido, double totalTiket);
    public abstract String toString();
  
    protected int getUnidad() {
        return unidad;
    }
    
    protected void setUnidad(int totalPasajeros) {
        this.unidad = unidad;
    }
    protected void setTotalPasajeros(int totalPasajeros) {
        this.totalPasajeros = totalPasajeros;
    }
    public String  muestraGeneral (){
        String  texto = " "; 
        texto = A.muestra() + toString(); 
        return texto;
    }
    protected int getTotalPasajeros() {
        return totalPasajeros;
    }
    public int getPasajeros() {
        return pasajeros;
    }
    
    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }
    
    public static double getTotalGanancias() {
        return totalGanancias;
    }
    
    public static void setTotalGanancias(double totalGanancias) {
        Carreras.totalGanancias = totalGanancias;
    }
    
    public double getTotalCarrera() {
        return totalCarrera;
    }
    
    public void setTotalCarrera(double totalCarrera) {
        this.totalCarrera = totalCarrera;
    }
    
    public double getTotalTiket() {
        return totalTiket;
    }
    
    public void setTotalTiket(double totalTiket) {
        this.totalTiket = totalTiket;
    }
    
    public String getSentido() {
        return sentido;
    }
    
    public void setSentido(String sentido) {
        this.sentido = sentido;
    }
    
    protected String getNombreUnidad() {
        return nombreUnidad;
    }
    
    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

}
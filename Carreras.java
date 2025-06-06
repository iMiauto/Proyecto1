
import java.io.*;
import javax.swing.*;

public abstract class Carreras extends JFrame implements Serializable {
    

    protected int pasajeros, totalPasajeros;
    protected static double  totalGanancias;
    protected double totalCarrera;
    protected double totalTiket; 
    protected String nombreUnidad;
    protected int unidad;
protected String nombreChofer;
protected int sentido;
protected double precio;



public Carreras(int unidad, String nombreChofer, int sentido, double precio) {
    this.unidad = unidad;
    this.nombreChofer = nombreChofer;
    this.sentido = sentido;
    this.precio = precio;
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
    
    public int getSentido() {
        return sentido;
    }
    
    public void setSentido(int sentido) {
        this.sentido = sentido;
    }
    
    protected String getNombreUnidad() {
        return nombreUnidad;
    }
    
    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

}
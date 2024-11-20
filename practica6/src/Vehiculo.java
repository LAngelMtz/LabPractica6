import java.io.Serializable;

public class Vehiculo implements Serializable {
    protected String marca;
    protected String modelo;
    protected int yearFrabricacion;
    protected static int idGlobal = 0;
    protected int id;

    public Vehiculo(String marca, String modelo, int yearFrabricacion) {
        this.marca = marca;
        this.modelo = modelo;
        this.yearFrabricacion = yearFrabricacion;
        id = ++idGlobal;
    }

    protected int getId(){return id;}

    public String mostrarInformacion(){
        return "ID: " + id + ", Marca: "+marca + ", Modelo: " + modelo + ", año de fabricacion: " + yearFrabricacion +", ";
    }
}

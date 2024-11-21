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
    protected void setId(int id){this.id = id;}
    protected String getMarca(){return marca;}
    protected String getModelo(){return modelo;}
    protected int getYearFrabricacion(){return yearFrabricacion;}

    public String mostrarInformacion(){
        return "ID: " + id + ", Marca: "+marca + ", Modelo: " + modelo + ", año de fabricacion: " + yearFrabricacion +", ";
    }
}

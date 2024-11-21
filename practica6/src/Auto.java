public class Auto extends Vehiculo{
    private int cantidadPuertas;

    public Auto(String marca, String modelo, int yearFabricacion, int cantidadPuertas) {
        super(marca,modelo,yearFabricacion);
        this.cantidadPuertas = cantidadPuertas;
    }

    public int getCantidadPuertas() {return cantidadPuertas;}

    public String mostrarInformacion(){
        return super.mostrarInformacion()+"Tipo: Auto, Cantidad de puertas: "+cantidadPuertas;
    }
}

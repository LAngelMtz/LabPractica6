public class Camion extends Vehiculo{
    private double capacidadCarga;

    public Camion(String marca, String modelo, int yearFabricacion, double capacidadCarga) {
        super(marca, modelo, yearFabricacion);
        this.capacidadCarga = capacidadCarga;
    }

    public String mostrarInformacion(){
        return super.mostrarInformacion() + "Tipo: Camion, Capacidad de carga: " + capacidadCarga;
    }
}

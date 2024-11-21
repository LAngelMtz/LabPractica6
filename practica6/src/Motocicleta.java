public class Motocicleta extends Vehiculo{
    private String tipoMotor;

    public Motocicleta(String marca, String modelo, int yearFabricacion, String tipoMotor) {
        super(marca, modelo, yearFabricacion);
        this.tipoMotor = tipoMotor;
    }

    public String getTipoMotor() {return tipoMotor;}

    public String mostrarInformacion(){
        return super.mostrarInformacion() + "Tipo: Motocicleta, Tipo de Motor: " + tipoMotor;
    }
}

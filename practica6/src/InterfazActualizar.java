import javax.swing.*;
import java.util.ArrayList;

public class InterfazActualizar {
    private int opcion;
    private ArrayList<Vehiculo> listaVehiculos;
    private JLabel labelMarca;
    private JTextField textMarca;
    private JLabel labelModelo;
    private JTextField textModelo;
    private JLabel labelYear;
    private JTextField textYear;
    private JLabel labelPuertas;
    private JSpinner cantidadPuertas;
    private SpinnerNumberModel modeloCantidad;
    private JLabel tipoMotor;
    private JTextField textTipoMotor;
    private JLabel capacidadCarga;
    private JTextField textCapacidadCarga;
    private JButton botonConfirmar;
    private JButton botonLimpiar;
    private JLabel labelBuscar;
    private JTextField textBuscar;
    private JButton botonBuscar;
    private int idMod;

    public InterfazActualizar(JPanel panel) {
        definirElementos();
        agregarPanel(panel);
        setVisible(false);
        setVisibleModificar(false);
    }

    private void definirElementos(){
        // Creacion de los elementos del panel
        labelMarca = new JLabel("Marca:");
        textMarca = new JTextField();
        labelModelo = new JLabel("Modelo:");
        textModelo = new JTextField();
        labelYear = new JLabel("Año de fabricacion:");
        textYear = new JTextField();
        labelPuertas = new JLabel("Cantidad de puertas:");
        cantidadPuertas = new JSpinner();
        modeloCantidad = new SpinnerNumberModel(4, 2, 8, 2);
        cantidadPuertas.setModel(modeloCantidad);
        tipoMotor = new JLabel("Tipo de Motor:");
        textTipoMotor = new JTextField();
        capacidadCarga = new JLabel("Capacidad de carga:");
        textCapacidadCarga = new JTextField();
        botonConfirmar = new JButton("Modificar");
        botonLimpiar = new JButton("Limpiar");
        labelBuscar = new JLabel("ID:");
        textBuscar = new JTextField();
        botonBuscar = new JButton("Buscar");

        botonConfirmar.addActionListener(e -> { // Accion del boton de confirmar o registrar vehiculo
            try{
                String marca = textMarca.getText();
                String modelo = textModelo.getText();
                int year = Integer.parseInt(textYear.getText());

                listaVehiculos.removeIf(v -> v.getId() == idMod); // Remover el objeto original
                switch (opcion){
                    case 1: // Caso auto
                        int cantPuertas = (int) cantidadPuertas.getValue();
                        listaVehiculos.add(new Auto(marca, modelo, year, cantPuertas));
                        break;
                    case 2: // Caso Moto
                        String motor = textTipoMotor.getText();
                        listaVehiculos.add(new Motocicleta(marca, modelo, year, motor));
                        break;
                    case 3: // Caso Camion
                        double carga = Double.parseDouble(textCapacidadCarga.getText());
                        listaVehiculos.add(new Camion(marca, modelo, year, carga));
                        break;
                }
                listaVehiculos.getLast().setId(idMod);
                listaVehiculos.sort((v1,v2) -> Integer.compare(v1.getId(),v2.getId()));
                JOptionPane.showMessageDialog(null,"Se ha modificado exitosamente!", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null,"Campo nulo o error en formato de numero", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        botonLimpiar.addActionListener(e -> {
            limpiar();
        });

        botonBuscar.addActionListener(e -> {
            try{
                int buscarId = Integer.parseInt(textBuscar.getText());

                listaVehiculos.stream().filter(v -> v.getId() == buscarId).findFirst().ifPresentOrElse(v -> {
                    // El ID es encontrado
                    setVisibleModificar(true);
                    setVisible(false);
                    idMod = buscarId;

                    textMarca.setText(v.getMarca());
                    textModelo.setText(v.getModelo());
                    textYear.setText(""+v.getYearFrabricacion());

                    // Define si es un auto, moto, camion
                    if(v instanceof Auto){
                        opcion = 1;
                        cantidadPuertas.setValue(((Auto) v).getCantidadPuertas());
                    } else if (v instanceof Motocicleta) {
                        opcion = 2;
                        textTipoMotor.setText(((Motocicleta) v).getTipoMotor());
                    } else if (v instanceof Camion) {
                        opcion = 3;
                        textCapacidadCarga.setText(""+((Camion) v).getCapacidadCarga());
                    }
                    activar(opcion);

                        },
                        // Caso de no encontrar el ID
                        () -> JOptionPane.showMessageDialog(null,"No se ha encontrado el ID", "Error", JOptionPane.INFORMATION_MESSAGE));
            }catch (Exception ex){
                // Caso de error al introducir ID
                JOptionPane.showMessageDialog(null,"Campo nulo o error en formato de numero", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
    }
    private void agregarPanel(JPanel panel) {
        panel.add(labelMarca);
        panel.add(textMarca);
        panel.add(labelModelo);
        panel.add(textModelo);
        panel.add(labelYear);
        panel.add(textYear);
        panel.add(labelPuertas);
        panel.add(cantidadPuertas);
        panel.add(tipoMotor);
        panel.add(textTipoMotor);
        panel.add(capacidadCarga);
        panel.add(textCapacidadCarga);
        panel.add(botonConfirmar);
        panel.add(botonLimpiar);
        panel.add(labelBuscar);
        panel.add(textBuscar);
        panel.add(botonBuscar);

        int altura = 25;

        // Posiciones de los label
        labelMarca.setBounds(30,10,120,altura);
        labelModelo.setBounds(30,45,120,altura);
        labelYear.setBounds(30,80,120,altura);
        labelPuertas.setBounds(30,115,120,altura);
        tipoMotor.setBounds(30,150,120,altura);
        capacidadCarga.setBounds(30,185,120,altura);
        labelBuscar.setBounds(30,100,30,altura);

        // Posiciones de los text fields
        textMarca.setBounds(150,10,120,altura);
        textModelo.setBounds(150,45,120,altura);
        textYear.setBounds(150,80,120,altura);
        cantidadPuertas.setBounds(150,115,120,altura);
        textTipoMotor.setBounds(150,150,120,altura);
        textCapacidadCarga.setBounds(150,185,120,altura);
        textBuscar.setBounds(60,100,120,altura);

        // Posiciones botones
        botonConfirmar.setBounds(40,230,100,altura);
        botonLimpiar.setBounds(160,230,100,altura);
        botonBuscar.setBounds(185,100,75,altura);
    }

    public void setVisible(boolean visible) {
        labelBuscar.setVisible(visible);
        textBuscar.setVisible(visible);
        botonBuscar.setVisible(visible);
    }

    public void setVisibleModificar(boolean visible){
        labelMarca.setVisible(visible);
        textMarca.setVisible(visible);
        labelModelo.setVisible(visible);
        textModelo.setVisible(visible);
        labelYear.setVisible(visible);
        textYear.setVisible(visible);
        labelPuertas.setVisible(visible);
        cantidadPuertas.setVisible(visible);
        tipoMotor.setVisible(visible);
        textTipoMotor.setVisible(visible);
        capacidadCarga.setVisible(visible);
        textCapacidadCarga.setVisible(visible);
        botonConfirmar.setVisible(visible);
        botonLimpiar.setVisible(visible);
    }

    public void setVisibleTodo(boolean visible){
        setVisible(visible);
        setVisibleModificar(visible);
    }

    public void activar(int opcion){
        cantidadPuertas.setEnabled(false);
        tipoMotor.setEnabled(false);
        capacidadCarga.setEnabled(false);
        textTipoMotor.setEnabled(false);
        textCapacidadCarga.setEnabled(false);
        this.opcion = opcion;

        // Dependiendo si es un auto, moto o camion se activan los espacios
        switch (opcion){
            case 1:
                cantidadPuertas.setEnabled(true);
                break;
            case 2:
                tipoMotor.setEnabled(true);
                textTipoMotor.setEnabled(true);
                break;
            case 3:
                capacidadCarga.setEnabled(true);
                textCapacidadCarga.setEnabled(true);
                break;
        }
    }

    public void limpiar(){
        textMarca.setText("");
        textModelo.setText("");
        textYear.setText("");
        textCapacidadCarga.setText("");
        cantidadPuertas.setValue(4);
        textTipoMotor.setText("");
    }

    public void definirLista(ArrayList<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }
}

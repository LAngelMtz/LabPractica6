import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Ventana extends JFrame implements ActionListener {
    private JPanel panel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuCrear;
    private JMenuItem menuLeer;
    private JMenuItem menuActualizar;
    private JMenuItem menuEliminar;
    private JMenuItem menuGuardar;
    private InterfazCrear interfazCrear;
    private InterfazLeer interfazLeer;
    private InterfazActualizar interfazActualizar;
    private InterfazEliminar interfazEliminar;
    private ArrayList<Vehiculo> listaVehiculos;
    private JLabel labelTipo;
    private JButton botonAuto;
    private JButton botonMoto;
    private JButton botonCamion;

    public Ventana() {
        listaVehiculos = new ArrayList<>();
        cargarLista();
        inicializarVentana();
        setTitle("Gestor de vehiculos"); // Establece el título de la ventana
        setSize(325,360); // Establece el tamaño de la ventana
        setLocationRelativeTo(null); /* La ventana se posiciona en el centro de la pantalla */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Establece que el botón de cerrar permitirá salir de la aplicación
        setResizable(false); /* Establece que el tamaño de la ventana no puede cambiar */
        setVisible(true);
    }

    public void inicializarVentana() {
        // Definir el panel
        panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        // Definir los elementos
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuCrear = new JMenuItem("Crear vehiculo");
        menuLeer = new JMenuItem("Leer vehiculo");
        menuActualizar = new JMenuItem("Actualizar vehiculo");
        menuEliminar = new JMenuItem("Eliminar vehiculo");
        menuGuardar = new JMenuItem("Guardar datos");
        labelTipo = new JLabel("Tipo de vehiculo:");
        botonAuto = new JButton("Auto");
        botonMoto = new JButton("Moto");
        botonCamion = new JButton("Camion");

        // Agregar elementos a la barra de menu y al menu
        setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(menuCrear);
        menu.add(menuLeer);
        menu.add(menuActualizar);
        menu.add(menuEliminar);
        menu.add(menuGuardar);

        // Agregar elementos al panel y posicion
        panel.add(labelTipo);
        panel.add(botonAuto);
        panel.add(botonMoto);
        panel.add(botonCamion);
        labelTipo.setBounds(105,80,100,30);
        botonAuto.setBounds(25,130,80,30);
        botonMoto.setBounds(115,130,80,30);
        botonCamion.setBounds(205,130,80,30);

        // Agregar a las opciones el listener que realiza las acciones
        menuCrear.addActionListener(this);
        menuLeer.addActionListener(this);
        menuActualizar.addActionListener(this);
        menuEliminar.addActionListener(this);
        menuGuardar.addActionListener(this);
        botonAuto.addActionListener(this);
        botonMoto.addActionListener(this);
        botonCamion.addActionListener(this);

        // Crear interfaz
        interfazCrear = new InterfazCrear(panel);
        interfazLeer = new InterfazLeer(panel);
        interfazActualizar = new InterfazActualizar(panel);
        interfazEliminar = new InterfazEliminar(panel);

        ocultarTodo();
    }

    private void llamarCrear(int opcion){
        ocultarTodo();
        interfazCrear.setVisible(true);
        interfazCrear.limpiar();
        interfazCrear.activar(opcion);
        interfazCrear.definirLista(listaVehiculos);
    }

    private void ocultarTodo(){
        labelTipo.setVisible(false);
        botonAuto.setVisible(false);
        botonMoto.setVisible(false);
        botonCamion.setVisible(false);
        interfazCrear.setVisible(false);
        interfazLeer.setVisible(false);
        interfazActualizar.setVisibleTodo(false);
        interfazEliminar.setVisible(false);
    }

    private void cargarLista(){
        File archivo = new File("guardado.txt");

        // Verificar si el archivo existe
        if(archivo.exists()){
            try{
                // Hoja
                FileInputStream fileStream = new FileInputStream("guardado.txt");

                // Lapiz
                ObjectInputStream input = new ObjectInputStream(fileStream);
                listaVehiculos = (ArrayList<Vehiculo>) input.readObject();
                input.close();

                if(!listaVehiculos.isEmpty()){
                    Vehiculo.idGlobal = listaVehiculos.getLast().getId();
                }

            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Error al cargar el archivo", "Error Detectado", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == menuCrear) {
            ocultarTodo();
            labelTipo.setVisible(true);
            botonAuto.setVisible(true);
            botonMoto.setVisible(true);
            botonCamion.setVisible(true);
        }
        else if (evento.getSource() == menuLeer) {
            ocultarTodo();
            listaVehiculos.forEach(v -> System.out.println(v.mostrarInformacion()));
            interfazLeer.setVisible(true);
            interfazLeer.definirLista(listaVehiculos);
        }
        else if (evento.getSource() == menuActualizar) {
            ocultarTodo();
            interfazActualizar.definirLista(listaVehiculos);
            interfazActualizar.setVisible(true);
        }
        else if (evento.getSource() == menuEliminar) {
            ocultarTodo();
            interfazEliminar.definirLista(listaVehiculos);
            interfazEliminar.setVisible(true);
        }
        else if (evento.getSource() == menuGuardar) {
            if(!listaVehiculos.isEmpty() || true){
                try{
                    // Hoja
                    FileOutputStream file = new FileOutputStream("guardado.txt");

                    // Lapiz
                    ObjectOutputStream output = new ObjectOutputStream(file);

                    // Escribe el arrayList en el archivo
                    output.writeObject(listaVehiculos);
                    output.close();
                    JOptionPane.showMessageDialog(null,"Se han guardado los datos exitosamente!", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"Error al guardar", "Error Detectado", JOptionPane.ERROR_MESSAGE);
                }
            }else{JOptionPane.showMessageDialog(null,"No hay elementos a guardar!", "Error", JOptionPane.INFORMATION_MESSAGE);}
        }
        else if (evento.getSource() == botonAuto) {llamarCrear(1);}
        else if (evento.getSource() == botonMoto) {llamarCrear(2);}
        else if (evento.getSource() == botonCamion) {llamarCrear(3);}
    }
}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class InterfazLeer implements ActionListener {
    private ArrayList<Vehiculo> listaVehiculos;
    private JTextArea textArea;
    private JScrollPane scroll;
    private JButton botonTodo;
    private JButton botonBuscar;
    private JLabel labelId;
    private JTextField textId;

    public InterfazLeer(JPanel panel) {
        definirElementos();
        agregarPanel(panel);
    }

    private void definirElementos(){
        textArea = new JTextArea();
        scroll = new JScrollPane(textArea);
        botonTodo = new JButton("Todo");
        botonBuscar = new JButton("Buscar");
        labelId = new JLabel("ID:");
        textId = new JTextField();

        // agregar listener
        botonTodo.addActionListener(this);
        botonBuscar.addActionListener(this);

        // Caracteristicas del area
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Caracteristicas del scroll
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void agregarPanel(JPanel panel){
        panel.add(scroll);
        panel.add(labelId);
        panel.add(textId);
        panel.add(botonTodo);
        panel.add(botonBuscar);

        // Definir posiciones
        scroll.setBounds(10,10,290,250);
        labelId.setBounds(10,270,20,20);
        textId.setBounds(30,270,100,20);
        botonBuscar.setBounds(135,270,75,20);
        botonTodo.setBounds(235,270,65,20);
    }

    public void setVisible(boolean visible){
        textArea.setVisible(visible);
        scroll.setVisible(visible);
        labelId.setVisible(visible);
        textId.setVisible(visible);
        botonTodo.setVisible(visible);
        botonBuscar.setVisible(visible);
        limpiar();
    }

    private void limpiar(){
        textId.setText("");
        textArea.setText("");
    }

    public void definirLista(ArrayList<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }



    @Override
    public void actionPerformed(ActionEvent evento) {
        // Si se presiona el boton buscar
        if(evento.getSource() == botonBuscar){
            int idBuscar;
            System.out.println(textId.getText());
            try{
                idBuscar = Integer.parseInt(textId.getText());
                textId.setText("");

                if(!listaVehiculos.isEmpty()){ // Si el array no esta vacio
                    if(listaVehiculos.stream().anyMatch(v -> v.getId() == idBuscar)){ // Si se encuentra el id
                        listaVehiculos.stream().filter(v -> v.getId() == idBuscar).findFirst().ifPresent(v -> {textArea.setText(v.mostrarInformacion());}); // Mostrar informacion})
                    }else{JOptionPane.showMessageDialog(null,"No se encontro el elemento con el ID indicado", "Error", JOptionPane.INFORMATION_MESSAGE);}
                }else{JOptionPane.showMessageDialog(null,"No hay elementos registrados", "Error", JOptionPane.INFORMATION_MESSAGE);}
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Campo nulo o error en formato de numero", "Error", JOptionPane.ERROR_MESSAGE);
            }



        }
        if(evento.getSource() == botonTodo){
            if(!listaVehiculos.isEmpty()){
                textArea.setText("");
                listaVehiculos.forEach(v -> textArea.append(v.mostrarInformacion()+"\n\n"));
            }else{JOptionPane.showMessageDialog(null,"No hay elementos registrados", "Error", JOptionPane.INFORMATION_MESSAGE);}
        }
    }
}

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Estudiante;
import modelo.TransaccionesBD;
import vista.VistaEstudiante;

public class Controlador implements ActionListener {

    VistaEstudiante vista;
    TransaccionesBD modelo;
    Estudiante estudiante;

    public Controlador(VistaEstudiante vista, TransaccionesBD modelo, Estudiante estudiante) {
        this.vista = vista;
        this.modelo = modelo;
        this.estudiante = estudiante;
        vista.btnInsertar.addActionListener(this);
        vista.btnModificar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnAnterior.addActionListener(this);
        vista.btnSiguiente.addActionListener(this);
        vista.btnBuscar.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);

    }

    public void iniciar() {
        vista.setTitle("Estudiante");
        vista.setLocationRelativeTo(null);
    }

    public void limpiar() {
        vista.txtBuscar.setText(null);
        vista.txtNombre.setText(null);
        vista.cbGenero.setSelectedIndex(0);
        vista.cbMateria.setSelectedIndex(0);
        vista.txtNota.setText(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnInsertar) {
            estudiante.setNombre(vista.txtNombre.getText());
            estudiante.setNota(Double.parseDouble(vista.txtNota.getText()));
            estudiante.setGenero(vista.cbGenero.getSelectedItem().toString());
            estudiante.setMateria(vista.cbMateria.getSelectedItem().toString());
            if (modelo.insertar(estudiante)) {
                JOptionPane.showMessageDialog(null, "Registro insertado correctamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "No se inserto el registro");
            }
        }
        if (e.getSource() == vista.btnModificar) {
            estudiante.setNombre(vista.txtNombre.getText());
            estudiante.setNota(Double.parseDouble(vista.txtNota.getText()));
            estudiante.setGenero(vista.cbGenero.getSelectedItem().toString());
            estudiante.setMateria(vista.cbMateria.getSelectedItem().toString());
            estudiante.setCodigo(Integer.parseInt(vista.txtBuscar.getText()));
            if (modelo.modificar(estudiante)) {
                JOptionPane.showMessageDialog(null, "Registro modificado correctamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo modificar");
            }
        }
        if (e.getSource() == vista.btnEliminar) {

            estudiante.setCodigo(Integer.parseInt(vista.txtBuscar.getText()));
            if (modelo.eliminar(estudiante)) {
                JOptionPane.showMessageDialog(null, "Registro eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el registro");
            }
        }
        if (e.getSource() == vista.btnBuscar) {
            estudiante.setCodigo(Integer.parseInt(vista.txtBuscar.getText()));
            if (modelo.buscar(estudiante)) {

                vista.txtNombre.setText(estudiante.getNombre());
                vista.cbGenero.setSelectedItem(estudiante.getGenero());
                vista.cbMateria.setSelectedItem(estudiante.getMateria());
                vista.txtNota.setText(String.valueOf(estudiante.getNota()));

            } else {

                JOptionPane.showMessageDialog(null, "No existe el registro");
            }

        }
        if (e.getSource() == vista.btnLimpiar) {
            limpiar();
        }

        if (e.getSource() == vista.btnSiguiente) {

            modelo.siguiente(estudiante);

            vista.txtNombre.setText(estudiante.getNombre());
            vista.cbMateria.setSelectedItem(estudiante.getMateria());
            vista.cbGenero.setSelectedItem(estudiante.getGenero());
            vista.txtNota.setText(String.valueOf(estudiante.getNota()));

//            
        }

        if (e.getSource() == vista.btnAnterior) {
            modelo.anterior(estudiante);
            vista.txtNombre.setText(estudiante.getNombre());
            vista.cbMateria.setSelectedItem(estudiante.getMateria());
            vista.cbGenero.setSelectedItem(estudiante.getGenero());
            vista.txtNota.setText(String.valueOf(estudiante.getNota()));

        }
    }
}

package estudiantemvc;

import controlador.Controlador;
import modelo.Estudiante;
import modelo.TransaccionesBD;
import vista.VistaEstudiante;

public class EstudianteMvc {

    public static void main(String[] args) {
        VistaEstudiante vista = new VistaEstudiante();
        TransaccionesBD modelo = new TransaccionesBD();
        Estudiante estudiante = new Estudiante();
        Controlador controlador = new Controlador(vista, modelo, estudiante);
        controlador.iniciar();
        vista.setVisible(true);
    }

}

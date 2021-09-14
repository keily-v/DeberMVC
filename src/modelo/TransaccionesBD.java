package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TransaccionesBD extends Conexion {

    PreparedStatement ps;
    ResultSet rs;
    Estudiante estudiante;

    private boolean cargarDatos(Estudiante estudiante) {

        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("select * from estudiante", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery();
            rs.next();
            llenaTabla(estudiante);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No existen mas estudiantes");
        }
return true;
    }

    private boolean llenaTabla(Estudiante estudiante) {
        try {
            estudiante.setNombre(rs.getString(2));
            estudiante.setMateria(rs.getString(5));
            estudiante.setGenero(rs.getString(4));
            estudiante.setNota(Double.parseDouble(rs.getString(3)));
            
        } catch (Exception ex) {
            System.err.println("error datossss");
        }
        return true;
    }

    public boolean insertar(Estudiante estudiante) {
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("insert into estudiante (nombreE,nota,sexo,materia) values(?,?,?,?)");
            ps.setString(1, estudiante.getNombre());
            ps.setDouble(2, estudiante.getNota());
            ps.setString(3, estudiante.getGenero());
            ps.setString(4, estudiante.getMateria());

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                return true;

            } else {
                return false;
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return true;

    }

    public boolean modificar(Estudiante estudiante) {
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("update estudiante set nombreE=?,nota=?,sexo=?,materia=? where codigoE=?");
            ps.setString(1, estudiante.getNombre());
            ps.setDouble(2, estudiante.getNota());
            ps.setString(3, estudiante.getGenero());
            ps.setString(4, estudiante.getMateria());
            ps.setInt(5, estudiante.getCodigo());
            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                return true;

            } else {
                return false;
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return true;

    }

    public boolean eliminar(Estudiante estudiante) {

        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("delete from estudiante where codigoE=?");
            ps.setInt(1, estudiante.getCodigo());
            var resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;

            } else {
                return false;
            }

        } catch (Exception ex) {
            System.err.println("Error" + ex);
        }
        return true;

    }

    public boolean siguiente(Estudiante estudiante) {
      
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("select * from estudiante", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery();
           
            rs.first();
            llenaTabla(estudiante);
            
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No existen mas estudiantes");
        }

        return true;

    }

    public boolean anterior(Estudiante estudiante) {

        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("select * from estudiante", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery();
            while(rs.next()){
            
            llenaTabla(estudiante);
            }
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ha llegado al inicio de la lista");
        }
        return true;

    }

    public boolean buscar(Estudiante estudiante) {

        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("select * from estudiante where codigoE=?");
            ps.setInt(1, estudiante.getCodigo());
            rs = ps.executeQuery();
            if (rs.next()) {

                estudiante.setNombre(rs.getString("nombreE"));
                estudiante.setGenero(rs.getString("sexo"));
                estudiante.setMateria(rs.getString("materia"));
                estudiante.setNota(rs.getDouble("nota"));

            } else {
                JOptionPane.showMessageDialog(null, "No existe el registro");
            }
            conexion.close();

        } catch (Exception ex) {
            System.err.println("Error " + ex);
        }
        return true;

    }

}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Formulario {
    private JPanel rootPanel;
    private JLabel main_label;
    private JTextField codigo_in;
    private JTextField cedula_in;
    private JTextField nombre_in;
    private JTextField fechaNac_in;
    private JComboBox signo_select;
    private JLabel codigo_label;
    private JLabel cedula_label;
    private JLabel nombre_label;
    private JLabel fechaNac_label;
    private JLabel signo_label;
    private JButton buscarPorCodigoButton;
    private JButton buscarPorNombreButton;
    private JButton buscarPorSignoButton;
    private JButton borrarElPresenteRegistroButton;
    private JButton actualizarElPresenteRegistroButton;
    private JButton ingresarElPresenteRegistroButton;
    private JButton limpiarFormularioButton;

    //conexion con MySQL
    final static String DB_URL="jdbc:mysql://localhost/registropersonas"; //cadena de conexion
    final static String USER="root"; //usuario
    final static String PASS="melA01.2"; //password

    //objeto para la clase Persona
    Persona persona = new Persona();

    public Formulario() {
        buscarPorCodigoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (codigo_in.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Llena el campo para iniciar la búsqueda!","Error",JOptionPane.ERROR_MESSAGE);
                } else {
                    String QUERY = "SELECT * from personas where codigo_pers='"+codigo_in.getText()+"'";
                    buscarPersona(QUERY);
                }
            }
        });
        buscarPorNombreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nombre_in.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Llena el campo para iniciar la búsqueda!","Error",JOptionPane.ERROR_MESSAGE);
                } else{
                    String QUERY = "SELECT * from personas where nombre_pers='"+nombre_in.getText()+"'";
                    buscarPersona(QUERY);
                }
            }
        });
        buscarPorSignoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String QUERY = "SELECT * from personas where signoZod_pers='"+signo_select.getSelectedItem()+"'";
                buscarPersona(QUERY);
            }
        });
        borrarElPresenteRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deleteQuery = "DELETE FROM personas WHERE codigo_pers="+codigo_in.getText();
                modificarRegistro(deleteQuery);
                JOptionPane.showMessageDialog(null,"Registro borrado exitosamente","Borrado Correcto",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        actualizarElPresenteRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombre_in.getText();
                String fechaNac = fechaNac_in.getText();
                String signoZod = String.valueOf(signo_select.getSelectedItem());

                String updateQuery = "UPDATE personas SET nombre_pers='"+nombre+"',fechaNac_pers='"+fechaNac+"',signoZod_pers='"+signoZod+"'WHERE codigo_pers='"+codigo_in.getText()+"'";

                modificarRegistro(updateQuery);

                JOptionPane.showMessageDialog(null,"Registro actualizado exitosamente","Actualización Correcta",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        ingresarElPresenteRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = codigo_in.getText();
                String cedula = cedula_in.getText();
                String nombre = nombre_in.getText();
                String fechaNac = fechaNac_in.getText();
                String signoZod = String.valueOf(signo_select.getSelectedItem());

                //Validacion por campos vacios
                if(codigo.isEmpty() || cedula.isEmpty() || nombre.isEmpty() || fechaNac.isEmpty() || signoZod == null){
                    JOptionPane.showMessageDialog(null,"Por favor, llena todos los campos para completar el registro","Error",JOptionPane.ERROR_MESSAGE);
                } else {
                    Persona nuevaPersona = new Persona(codigo,cedula,nombre,fechaNac,signoZod);
                    insertPersona(nuevaPersona);
                    JOptionPane.showMessageDialog(null,"Persona registrada exitosamente","Registro Correcto",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        limpiarFormularioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codigo_in.setText(null);
                cedula_in.setText(null);
                nombre_in.setText(null);
                fechaNac_in.setText(null);
                signo_select.setSelectedItem(null);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulario");
        frame.setContentPane(new Formulario().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(700,280);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    //---------------------------------------------------------------------------
    //Metodo para buscar un registro
    private void buscarPersona(String QUERY){
        try(
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);
        ){
            if (rs.next()){
                //Recorre la DB y guarda los datos en los atributos de la clase Persona
                persona.setCodigo(rs.getString("codigo_pers"));
                persona.setCedula(rs.getString("cedula_pers"));
                persona.setNombre(rs.getString("nombre_pers"));
                persona.setFechaNac(rs.getString("fechaNac_pers"));
                persona.setSignoZod(rs.getString("signoZod_pers"));

                actualizarDatosPersona();
            } else{
                JOptionPane.showMessageDialog(null,"Persona no encontrada!","Error",JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void modificarRegistro(String QUERY){
        try(Connection connection = DriverManager.getConnection(DB_URL,USER,PASS)){
            try(Statement statement = connection.createStatement()){
                statement.executeUpdate(QUERY);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //Metodo para insertar registro
    private void insertPersona(Persona persona){
        String insertQuery = "INSERT INTO personas VALUES ('"+persona.getCodigo()+"','"+persona.getCedula()+"','"+persona.getNombre()+"','"+persona.getFechaNac()+"','"+persona.getSignoZod()+"')";
        modificarRegistro(insertQuery);
    }

    //Metodo para actualizar los atributos de la clase Persona
    private void actualizarDatosPersona(){
        codigo_in.setText(persona.getCodigo());
        cedula_in.setText(persona.getCedula());
        nombre_in.setText(persona.getNombre());
        fechaNac_in.setText(persona.getFechaNac());
        signo_select.setSelectedItem(persona.getSignoZod());
    }
}

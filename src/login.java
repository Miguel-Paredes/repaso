import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login {
    private JPanel JPanel;
    private JButton ingresarButton;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JTextField usuarioTextField;
    private JPasswordField passwordField;
    static public String DB_URL="jdbc:mysql://localhost/poo";
    static public String USER="root";
    static public String PASSWORD="root_bas3";
    static public String QUERRY="Select * From Estudiantes";
    public static String nombrei;
    public static String clavei;
    public static String usuariox;
    public static String clavex;


    public login (){

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuariox=usuarioTextField.getText().trim();
                clavex=new String(passwordField.getPassword()).trim();
                conexion();
            }
        });

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuariox=usuarioTextField.getText().trim();
                clavex=new String(clavei);
                agregar(usuariox, clavex);
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuariox=usuarioTextField.getText().trim();
                clavex=new String(clavei);
                eliminar(usuariox);
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuariox=usuarioTextField.getText().trim();
                clavex=new String(clavei);
                actualizar(usuariox,clavex);
            }
        });
    }

    public void conexion(){
        try(Connection conn = DriverManager(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet  rs =stmt.executeQuery(QUERRY);
        {
            boolean encontrado=false;
            while(rs.next()){
                nombrei=rs.getString("Nombre");
                clavei=rs.getString("Clave");
                if(nombrei.equals(usuariox) && clavei.equals(clavex)){
                    System.out.printf("Nombre: "+rs.getString("Nombre"));
                    System.out.printf("Clave: "+rs.getString("Clave"));
                }
            }
            if(!encontrado){
                System.out.printf("Usuario o contraseña incorrectos");
            }
    } catch (SQLClientInfoException e){
            throw new RuntimeException(e);
        }
}

    public void agregar(String usu, String clav){
        String querry1="Inster into Estudiantes values('"+usu+"','"+clav+"')";
        try(Connection conn = DriverManager(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();){
                ResultSet rs = stmt.executeQuery(querry1);
                System.out.printf("Usuario ingresado con exito");
        }catch (Exception ea){
            throw new RuntimeException(ea);
        }
    }

    public void eliminar(String usu) {
        String querry2 = "Delete From Estudantes where Nombre='" + usu + "'";
        try (Connection conn = DriverManager(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(querry2);
            System.out.printf("Usuario eliminado");
        }catch (Exception ee){
            throw new RuntimeException(ee);
        }
    }

    public void actualizar(String usu, String clav){
        String querry3="update Estudiantes Set Clave='"+clav+"'"+"Where Nombre='"+usu+"'";
        try(Connection conn = DriverManager(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();){
            ResultSet rs = stmt.executeQuery(querry3);
            System.out.printf("Usuario actualizado");
        }catch (RuntimeException eac){
            throw new RuntimeException(eac);
        }
    }
}

package jgit;

import javax.swing.*;

public class Usuario {
    public static String[] ingresoUsuario(){
        JPanel panel = new JPanel();
        JLabel label1 = new JLabel("Ingrese Nombre");
        JLabel label2 = new JLabel("y Contraseña");
        JTextField user = new JTextField(16);
        JPasswordField pass = new JPasswordField(16);
        String[] cuenta={"",""};
        panel.add(label1);
        panel.add(label2);
        panel.add(user);
        panel.add(pass);
        String[] options = new String[]{"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(null, panel, "cuenta",
                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if(option == 0) // pressing OK button
        {
            String usuario = user.getText();
            char[] password = pass.getPassword();
            System.out.println("Your password is: " + new String(password)+"\n Your user: "+ usuario);
            cuenta[0]=usuario;
            cuenta[1]=new String(password);
            return cuenta;
        }
        else{
            return cuenta;
        }
    }
}

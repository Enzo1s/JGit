package jgit;


import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
// comment
public class JgitAplication extends  Usuario {
    public static  void aplicacionGit() throws GitAPIException, IOException, URISyntaxException {
        boolean salir =true;
        //String local, remoto;
        Jgit jgit = new Jgit();
        int op;
        do{

            op = Integer.parseInt(JOptionPane.showInputDialog("Ingresar Opción:" +
                    "\n1) Iniciar Repositorio Local (git init)" +
                    "\n2) Clonar Repositorio Remoto (git clone url)" +
                    "\n3) Ver Área de estado (git status)" +
                    "\n4) Iniciar seguimiento de archivos (git add)" +
                    "\n5) Generar Commit (git commit -m<mensaje>)" +
                    "\n6) Crear Rama (git branch <rama>)" +
                    "\n7) Mover entre ramas (git Checkout <rama>)" +
                    "\n8) Fusionar Ramas (git merge)" +
                    "\n9) Vincular con Repositorio Remoto (git push)" +
                    "\n10) Seleccionr repositorio existente" +
                    "\n11) Actualizar Repositorio local (git pull)" +
                    "\n12) Salir"));
            if (op<=0 || op>=13){
                JOptionPane.showMessageDialog(null,"Valor Incorrecto");
                continue;
            }
            switch (op) {
                case 1:
                    if (jgit.getLocalPath()==null) {
                        jgit.setLocalPath(JOptionPane.showInputDialog("Ingrese el repositorio local"));
                    }
                    jgit.crearRepositorio(jgit.getLocalPath());
                    break;
                case 2:
                    switch (Integer.parseInt(JOptionPane.showInputDialog("1)Repositorio publico\n2)Repositorio privado"))){
                        case 1:
                            if(jgit.getLocalPath()==null){
                                jgit.setLocalPath(JOptionPane.showInputDialog("Ingrese repositorio local"));
                                if (jgit.getRemotePath()==null){
                                    jgit.setRemotePath(JOptionPane.showInputDialog("Ingrese repositorio remoto"));
                                }
                                jgit.clonarRepositorioPub();
                            }
                            break;
                        case 2:
                            String[] cuenta= Usuario.ingresoUsuario();
                            jgit.setName(cuenta[0]);
                            jgit.setPassword(cuenta[1]);
                            if(jgit.getLocalPath()==null){
                                jgit.setLocalPath(JOptionPane.showInputDialog("Ingrese repositorio local"));
                                if (jgit.getRemotePath()==null){
                                    jgit.setRemotePath(JOptionPane.showInputDialog("Ingrese repositorio remoto"));
                                }
                                jgit.clonarRepositorioPriv();
                            }
                            break;
                    }
                    jgit.iniciarRepo();
                    break;
                case 3:
                    jgit.gitStatus();
                    break;
                case 4:
                    int opcion = Integer.parseInt(JOptionPane.showInputDialog("" +
                            "1) Agregar un elemento\n" +
                            "2) Agregar varios elementos\n" +
                            "3) Agregar todos los elementos\n" +
                            "4) Ninguna de las ateriores"));
                    String elemento;
                    if (opcion<1 || opcion>4) break;
                    switch (opcion){
                        case 1:
                            elemento = JOptionPane.showInputDialog("Ingrese nombre del elemento, si no conoce el nombre inrese status; para listar elementos");
                            while(elemento.equals("status;")){
                                jgit.gitStatus();
                                elemento = JOptionPane.showInputDialog("Ingrse nombre elemento");
                            }
                            jgit.agregarElement(elemento);
                            break;
                        case 2:
                            elemento = JOptionPane.showInputDialog("Ingrese los elemntos separados por ; elemento1;elemento2");
                            String[] elementos = elemento.split(";");
                            for (String ele:elementos) {
                                jgit.agregarElement(ele);
                            }
                            break;
                        case 3:
                            jgit.agregarElement(".");
                            break;
                        case 4:
                            continue;
                    }
                    break;
                case 5:
                    jgit.commit(JOptionPane.showInputDialog("Ingrese mensaje commit"));
                    break;
                case 6:
                    jgit.crearBranch(JOptionPane.showInputDialog("Ingrese nombre de rama"));
                    break;
                case 7:
                    jgit.listarBranch();
                    jgit.cambiarBranch(JOptionPane.showInputDialog("Ingrese el nombre de la rama a la cual apuntar"));
                    break;
                case 8:
                    jgit.cambiarBranch(JOptionPane.showInputDialog("Ingrese el nombre de la rama a la cual apuntar"));
                    jgit.fusionarBranch(JOptionPane.showInputDialog("Ingrese nombre de rama a fusionar"));
                    break;
                case 9:
                    if(jgit.getName()==null){
                        String[] cuenta= Usuario.ingresoUsuario();
                        jgit.setName(cuenta[0]);
                        jgit.setPassword(cuenta[1]);
                    }
                    jgit.gitPush();
                    break;
                case 10:
                    jgit.setLocalPath(JOptionPane.showInputDialog("Ingrese repositorio local"));
                    jgit.iniciarRepo();
                    break;
                case 11:
                    jgit.gitPull();
                    break;
                case 12:
                    salir=false;
                    break;
            }
        }while (salir);
    }
    public static void main(String[] args) throws IOException, InvalidRemoteException, TransportException, URISyntaxException, GitAPIException{
        aplicacionGit();
    }

}

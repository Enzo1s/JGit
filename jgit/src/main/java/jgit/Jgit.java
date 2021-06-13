package jgit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Jgit {

    private String localPath, remotePath;
    private Repository localRepo;
    private Git git;
    private CredentialsProvider cp;
    private String name ;
    private String password;
    public void crearRepositorio(String localPath) {
        File localPath1 = new File(localPath);//Asigna a la variable localPath1 la direccion del proyecto a seguir
        try  {//Inicializa el repositorio == git init
            git = Git.init().setDirectory(localPath1).call();
            JOptionPane.showMessageDialog(null,"creado: "+  git.getRepository().getDirectory());
        } catch ( GitAPIException e){
            JOptionPane.showMessageDialog(null,"No se pudo inicializar el repositorio");
        }
    }

    public void iniciarRepo(){
        try  {
            git = Git.open(new File(localPath));
          localRepo = git.getRepository();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"No se tiene acceso al repositorio");
        }
    }

    public void clonarRepositorioPub() {
        try {
            Git.cloneRepository()
                    .setURI(this.remotePath)
                    .setDirectory(new File(this.localPath))
                    .call();
        } catch (GitAPIException e){
            JOptionPane.showMessageDialog(null, "No se pudo clonar el repositorio");
        } catch (JGitInternalException e){
            JOptionPane.showMessageDialog(null, "Ya Hay un repositorio creado");
        }
    }
    public void clonarRepositorioPriv() {
        try {
            cp = new UsernamePasswordCredentialsProvider(name,password);
            Git.cloneRepository().setCredentialsProvider(cp)
                    .setURI(this.remotePath)
                    .setDirectory(new File(this.localPath))
                    .call();
        } catch (GitAPIException e){
            JOptionPane.showMessageDialog(null, "No se pudo clonar el repositorio");
        }
    }

    public void agregarElement(String elemento){
        try {
            //git.add().setUpdate(true).addFilepattern(elemento).call();
            new File(git.getRepository().getDirectory().getParent(), elemento);
            // run the add-call
            git.add().addFilepattern(elemento).call();
        } catch (GitAPIException e){
            JOptionPane.showMessageDialog(null, "No se pudo agregar el elemento");
        }
    }

    public void commit(String msj){
        try {
            git.commit().setMessage(msj).call();
        } catch (GitAPIException e){
            JOptionPane.showMessageDialog(null, "Fallo commit");
        }
    }

    public void crearBranch(String name){
        try {
            git.checkout().setCreateBranch(true).setName(name).call();
            JOptionPane.showMessageDialog(null,"Se creo la rama "+ git.getRepository().getFullBranch());
        } catch (GitAPIException e){
            JOptionPane.showMessageDialog(null,"No se pudo crear la rama");
        } catch (IOException e){
            JOptionPane.showMessageDialog(null,"No se encontro la rama");
        }
    }

    public void cambiarBranch(String name){
        try {
            JOptionPane.showMessageDialog(null,"Usted esta en la rama "+ git.getRepository().getFullBranch());
            git.checkout().setName(name).call();
            JOptionPane.showMessageDialog(null,"Usted se movio a la rama "+ git.getRepository().getFullBranch());
        } catch (GitAPIException e){
            JOptionPane.showMessageDialog(null,"La rama no existe");
        } catch (IOException e){
            JOptionPane.showMessageDialog(null,"no se encontro la rama");
        }
    }

    public void listarBranch(){
        try {
            List<Ref> call = git.branchList().call();
            for (Ref ref : call) {
                System.out.println("Branch: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
            }
        } catch (GitAPIException e){
            JOptionPane.showMessageDialog(null,"Error al mostar las ramas");
        }
    }
    public void fusionarBranch(String name){
        try {
            ObjectId mergeBase = git.getRepository().resolve(name);
            MergeResult merge = git.merge().
                    include(mergeBase).
                    setCommit(true).
                    setFastForward(MergeCommand.FastForwardMode.NO_FF).
                    //setSquash(false).
                            setMessage("Se fusionó "+ name).
                            call();
            JOptionPane.showMessageDialog(null,"se fuisionaron las ramas");
        } catch (GitAPIException e){
            JOptionPane.showMessageDialog(null,"Error al fusionar las ramas");
        } catch (IOException e){
            JOptionPane.showMessageDialog(null,"No se encntro la rama");
        }
    }

    public void gitStatus(){
        try {
            Status status = git.status().call(); //Guarda los elementos que tienen o no seguimiento y los que estan comitiados

            Set<String> added = status.getAdded();//Lista los elementos añadidos (add)
            for (String add : added) {
                System.out.println("Agregado: " + add);
            }
            Set<String> uncommittedChanges = status.getUncommittedChanges(); //Lista los elementos modificados con seguimiento
            for (String uncommitted : uncommittedChanges) {
                System.out.println("Modificados: " + uncommitted);
            }

            Set<String> untracked = status.getUntracked(); //Lista los elementos sin seguimiento
            for (String untrack : untracked) {
                System.out.println("Sin seguimiento: " + untrack);
            }
        } catch (GitAPIException e){
            JOptionPane.showMessageDialog(null,"Error de acceso");
        }
    }

    public void commitHead() {
        try {
            ObjectId lastCommitId = git.getRepository().resolve(Constants.HEAD);
            System.out.println("El Head apunta al commit: " + lastCommitId.getName());
        } catch (IOException e){
            JOptionPane.showMessageDialog(null,"No se puede leer el head");
        }
    }

    public void gitPush(){
        try {
            CredentialsProvider cp;
            this.remotePath=JOptionPane.showInputDialog("Ingrese url del repo");
            cp = new UsernamePasswordCredentialsProvider(this.name, this.password);
            PushCommand pc = git.push();
            pc.setCredentialsProvider(cp)
                    .setForce(true)
                    .setPushAll().setRemote(this.remotePath);
            Iterator<PushResult> it = pc.call().iterator();
            if (it.hasNext()) {
                System.out.println(it.next().toString());
            }
        } catch (GitAPIException e){
            JOptionPane.showMessageDialog(null,"No error al acceder a repositorio remoto\nRevise configuración remoto");
        }
    }

    public void gitPull(){
        try{
            git.pull().call();
            JOptionPane.showMessageDialog(null,"Repositorio local actualizado");
        } catch (GitAPIException e){
            JOptionPane.showMessageDialog(null,"No se pudo actualizar el repositorio local");
        }
    }
}

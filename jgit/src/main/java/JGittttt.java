import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JGittttt {
//   /* public static void main(String[] args) throws IOException, InvalidRemoteException, TransportException, URISyntaxException, GitAPIException {
//
//        File localPath1 = new File("/home/enzo/Documentos/PracticaJGit");//Asigna a la variable localPath1 la direccion del proyecto a seguir
//        try (Git git = Git.init().setDirectory(localPath1).call()) {//Inicializa el repositorio == git init
//            System.out.println("Created repository: " + git.getRepository().getDirectory());
//            File myFile = new File(git.getRepository().getDirectory().getParent(), "README");
//            if (!myFile.createNewFile()) {//Lanza la excepcion si no crea el archivo
//                throw new IOException("Could not create file " + myFile);
//            }
//            //Clonar
//            *//*Git.cloneRepository()
//                    .setURI("https://github.com/Enzo1s/TP-GitDevOps.git")
//                    .setDirectory(new File("/home/enzo/Documentos/PracticaJGit")) // #1
//                    .call();*//*
//            // run the add-call
//            git.add().addFilepattern("README").call();//agrega el archivo readme para seguimiento == git add README
//            git.commit().setMessage("Initial commit").call();//Crea el primer commit == git commit -m "Initial commit"
//            System.out.println("Committed file " + myFile + " to repository at " + git.getRepository().getDirectory());
//            // Ramas
//            git.checkout().setCreateBranch(true).setName("development").call(); //Crea la rama local development y te mueve a ella == git branch develop
//            git.checkout().setName("master").call(); //Cambiar de rama a la rama master
//
//            //Merge
//            ObjectId mergeBase = git.getRepository().resolve("changes");
//            MergeResult merge = git.merge().
//                    include(mergeBase).
//                    setCommit(true).
//                    setFastForward(MergeCommand.FastForwardMode.NO_FF).
//                    //setSquash(false).
//                            setMessage("Merged changes").
//                            call();
//
//            //Listar las ramas
//            List<Ref> call = git.branchList().call();
//            for (Ref ref : call ) {
//                System.out.println("Branch: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
//            }
//            //Configuración del repositorio remoto == git remote add origin https://github.com/Enzo1s/TeamEast.git
//            RemoteAddCommand remoteAddCommand = git.remoteAdd();
//            remoteAddCommand.setName("origin");
//            remoteAddCommand.setUri(new URIish("https://github.com/Enzo1s/TeamEast.git"));
//            remoteAddCommand.call();
//            /// Configuracion para acceso a repositorio remoto git push
//            CredentialsProvider cp;
//            String name = "Enzo1s";
//
//            cp = new UsernamePasswordCredentialsProvider(name, password);
//            PushCommand pc = git.push();
//            pc.setCredentialsProvider(cp)
//                    .setForce(true)
//                    .setPushAll();
//                Iterator<PushResult> it = pc.call().iterator();
//                if (it.hasNext()) {
//                    System.out.println(it.next().toString());
//                }
//            //Git pull
//            //git.pull().call();
//
//            //status
//            Status status = git.status().call(); //Guarda los elementos que tienen o no seguimiento y los que estan comitiados
//
//            Set<String> added = status.getAdded();//Lista los elementos añadidos (add)
//            for (String add : added) {
//                System.out.println("Agregado: " + add);
//            }
//            Set<String> uncommittedChanges = status.getUncommittedChanges(); //Lista los elementos modificados con seguimiento
//            for (String uncommitted : uncommittedChanges) {
//                System.out.println("Modificados: " + uncommitted);
//            }
//
//            Set<String> untracked = status.getUntracked(); //Lista los elementos sin seguimiento
//            for (String untrack : untracked) {
//                System.out.println("Sin seguimiento: " + untrack);
//            }
//
//            //Obtiene el HEAD del ultimo commit
//            ObjectId lastCommitId = git.getRepository().resolve(Constants.HEAD);
//            System.out.println("Head points to the following commit :" + lastCommitId.getName());
//        }
//
//        }
}

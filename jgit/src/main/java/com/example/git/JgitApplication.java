package com.example.git;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class JgitApplication {

	public static void main(String[] args) throws IOException, InvalidRemoteException, TransportException, URISyntaxException, GitAPIException {
		SpringApplication.run(JgitApplication.class, args);
	}

}

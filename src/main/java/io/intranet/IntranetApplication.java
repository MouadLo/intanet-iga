package io.intranet;

import io.intranet.entities.Etudiant;
import io.intranet.entities.Group;
import io.intranet.repositories.EtudiantRepository;
import io.intranet.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class IntranetApplication implements CommandLineRunner {

	@Autowired
	private EtudiantRepository etudiantRepository;


	public static void main(String[] args) {
		SpringApplication.run(IntranetApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

	}
}

package io.intranet.repositories;

import io.intranet.entities.Etudiant;
import io.intranet.entities.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    public Page<Etudiant> findByNomContains(String keyword, Pageable pageable);
}

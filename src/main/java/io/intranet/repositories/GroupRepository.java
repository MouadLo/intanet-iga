package io.intranet.repositories;

import io.intranet.entities.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository  extends JpaRepository<Group, Long> {
    public Page<Group> findByNomContains(String keyword, Pageable pageable);
}

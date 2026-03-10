package microproject.user.repository;

import microproject.user.domain.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, UUID id);

    @Query("""
    SELECT p FROM Permission p
    WHERE
        LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
        LOWER(p.resource) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
        LOWER(p.action) LIKE LOWER(CONCAT('%', :keyword, '%'))
        ORDER BY p.id DESC
    """)
    Page<Permission> search(String keyword, Pageable pageable);

}

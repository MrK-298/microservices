package microproject.user.repository;

import microproject.user.domain.UserLoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserLoginHistoryRepository extends JpaRepository<UserLoginHistory, UUID> {
}

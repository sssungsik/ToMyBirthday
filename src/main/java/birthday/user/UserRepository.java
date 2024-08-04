package birthday.user;

import birthday.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByusername(String username);
    List<SiteUser> findByUsername(String username);
    Optional<SiteUser> findByKakaoId(String kakaoId);
}
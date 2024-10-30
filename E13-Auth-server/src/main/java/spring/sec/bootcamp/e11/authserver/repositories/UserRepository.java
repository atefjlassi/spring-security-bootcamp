package spring.sec.bootcamp.e11.authserver.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.sec.bootcamp.e11.authserver.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  @Query("select u from User u where u.username = :username")
  Optional<User> findUserByUsername(String username);
}

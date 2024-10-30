package spring.sec.bootcamp.e3.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.sec.bootcamp.e3.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  @Query(value = """
      SELECT u from User u where u.username= :username
    """)
  Optional<User> findUserByUsername(String username);
}

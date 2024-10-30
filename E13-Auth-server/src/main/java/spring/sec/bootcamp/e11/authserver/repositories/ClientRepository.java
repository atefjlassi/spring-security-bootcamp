package spring.sec.bootcamp.e11.authserver.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.sec.bootcamp.e11.authserver.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

  @Query("select c from Client c where c.clientId = :clientId")
  Optional<Client> findClientByClientId(String clientId);
}

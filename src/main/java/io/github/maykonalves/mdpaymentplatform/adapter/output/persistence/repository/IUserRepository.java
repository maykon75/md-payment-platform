package io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.repository;

import io.github.maykonalves.mdpaymentplatform.adapter.output.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByCpfCnpj(String cpfCnpj);

    boolean existsByEmail(String email);
}

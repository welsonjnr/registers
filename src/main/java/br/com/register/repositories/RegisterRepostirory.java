package br.com.register.repositories;

import br.com.register.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterRepostirory extends JpaRepository<Register, Long> {
    Optional<Register> findRegisterByCnes(String cnes);

    Optional<Register> findRegisterByCepFimBetween(int cepInicio, int cepFim);
}

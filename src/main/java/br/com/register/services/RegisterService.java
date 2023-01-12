package br.com.register.services;

import br.com.register.exceptions.InvalidRegisterException;
import br.com.register.model.Register;
import br.com.register.repositories.RegisterRepostirory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepostirory repository;

    public Register save(Register register) throws InvalidRegisterException {

        if(isRegisterValid(register)){
            throw new InvalidRegisterException("Ja existe um registro com esse Intervalo de CEP ou com esse CNES no banco!");
        }

        Register newRegister = repository.save(register);
        return newRegister;
    }

    private boolean isRegisterValid(Register register){

        Optional<Register> optional = repository.findRegisterByCnes(register.getCnes());
        Optional<Register> optionalBetween = repository.findRegisterByCepFimBetween(register.getCepInicio(), register.getCepFim());

        return (optional.isPresent() || optionalBetween.isPresent());
    }
}

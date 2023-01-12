package br.com.register.controllers;

import br.com.register.exceptions.InvalidRegisterException;
import br.com.register.model.Register;
import br.com.register.repositories.RegisterRepostirory;
import br.com.register.services.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
@Slf4j
public class RegisterController implements Serializable {
    private Register register = new Register();

    private List<Register> registers = new ArrayList<>();

    @Autowired
    private RegisterService service;

    @Autowired
    private RegisterRepostirory repository;

    public void fetchAll() {
        log.info("RegisterController :: fetchAll");
        registers = repository.findAll();
    }

    public void save(){
        log.info("RegisterController :: save :: ID -> " + register.getId() + ", CNES " + register.getCnes());

        try{
            service.save(register);
            FacesMessage message = new FacesMessage("Registro Criado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (InvalidRegisterException e){
            log.info("RegisterController :: save :: InvalidObjectException -> " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, message);

        }

        register = new Register();
        registers = repository.findAll();
    }

    public void edit(Register register) {
        this.register = register;
    }

    public void refresh() {
        register = new Register();
    }

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(List<Register> registers) {
        this.registers = registers;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }
}

package com.codingideas.cashkeeper.controllers;

import com.codingideas.cashkeeper.dto.ClientDTO;
import com.codingideas.cashkeeper.mapper.MapperUser;
import com.codingideas.cashkeeper.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "/user")
@RestController
@RequiredArgsConstructor
@Transactional
public class UserController {

    @PersistenceContext
    private final EntityManager entityManager;


    @RequestMapping(value = "get/clients")
    public List<ClientDTO> getClients(){
            List<User> users  = entityManager.createQuery("FROM User where rol!=1").getResultList();

        MapperUser mapperUser = new MapperUser();

        return users.stream().map(mapperUser::userToClientDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "delete/client",method = RequestMethod.DELETE)
    public String deleteClient(@RequestBody String id, @RequestHeader(value = "Authorization") boolean auth){
        User user = entityManager.find(User.class,id);

        if(!auth) return "El tiempo de estadia ha expirado, por favor inicie sesion nuevamente";

        entityManager.remove(user);

        return "cliente eliminado con exito";
    }

    @RequestMapping(value = "add/client",method = RequestMethod.POST)
    public String addClient(@RequestBody User user,@RequestHeader boolean auth){

        if (!auth) return "El tiempo de estadia ha expirado, por favor inicie sesion nuevamente";

        if (entityManager.find(User.class,user.getId())!=null) return "Ya se encuentra un cliente registrado con ese numero de identidad";

        entityManager.merge(user);

        return  "El usuario ha sido añadido exitosamente";
    }

    @RequestMapping(value = "edit/client")
    public String editClient(@RequestBody User userEdited){

        User user = entityManager.find(User.class,userEdited.getId());

        user.setNombre(userEdited.getNombre());
        user.setDireccion(userEdited.getDireccion());
        user.setTelefono(userEdited.getTelefono());

        entityManager.merge(user);

        return "Usuario editado con exito";
    }
}

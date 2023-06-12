package com.codingideas.cashkeeper.service;

import com.codingideas.cashkeeper.interfaces.IUserService;
import com.codingideas.cashkeeper.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public boolean ediClient(User userEdited, boolean auth) {

        if(!auth) return false;

        User user = entityManager.find(User.class,userEdited.getId());

        user.setNombre(userEdited.getNombre());
        user.setDireccion(userEdited.getDireccion());
        user.setTelefono(userEdited.getTelefono());

        entityManager.merge(user);

        return true;
    }

    @Override
    public List getClients() {
        List<User> users  = entityManager.createQuery("FROM User where rol!=1").getResultList();

        com.codingideas.cashkeeper.mapper.MapperUser mapperUser = new com.codingideas.cashkeeper.mapper.MapperUser();

        return users.stream().map(mapperUser::userToClientDto).collect(Collectors.toList());
    }

    @Override
    public String addClient(User user, boolean auth) {
        if (!auth) return "El tiempo de estadia ha expirado, por favor inicie sesion nuevamente";

        if (entityManager.find(User.class,user.getId())!=null) return "Ya se encuentra un cliente registrado con ese numero de identidad";

        entityManager.merge(user);

        return  "El usuario ha sido añadido exitosamente";
    }

    @Override
    public boolean removeClient(String id, boolean auth) {
        User user = entityManager.find(User.class,id);

        if(!auth) return false;

        entityManager.remove(user);

        return true;
    }
}

package com.codingideas.cashkeeper.service;

import com.codingideas.cashkeeper.interfaces.IUserService;
import com.codingideas.cashkeeper.models.User;
import com.codingideas.cashkeeper.repository.UserRepository;
import com.codingideas.cashkeeper.utils.JWTUtil;
import com.codingideas.cashkeeper.utils.mapper.MapperUser;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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

    private final UserRepository userRepository;


    @Override
    public boolean ediClient(User userEdited, boolean token) {

        if(!token) return false;

        User user = userRepository.findUser(userEdited.getId());

        user.setNombre(userEdited.getNombre());
        user.setDireccion(userEdited.getDireccion());
        user.setTelefono(userEdited.getTelefono());

        userRepository.mergeUser(user);

        return true;
    }

    @Override
    public List getClients(boolean auth) {
        if (!auth) return null;

        MapperUser mapperUser= new MapperUser();

        return userRepository.getClients().stream().map(mapperUser::userToClientDto).collect(Collectors.toList());
    }

    @Override
    public String addClient(User user, boolean token) {
        if (!token) return "El tiempo de estadia ha expirado, por favor inicie sesion nuevamente";

        if (userRepository.findUser(user.getId())!=null) return "Ya se encuentra un cliente registrado con ese numero de identidad";

        userRepository.mergeUser(user);

        return  "El usuario ha sido añadido exitosamente";
    }

    @Override
    public boolean removeClient(String id, boolean token) {
        User user = userRepository.findUser(id);

        if(!token) return false;

        user.setRol(3);
        userRepository.mergeUser(user);

        return true;
    }

    @Override
    public String addUser(User user) {

        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashedPassword = argon.hash(3,1024,2,user.getPassword());
        user.setPassword(hashedPassword);

        User id = userRepository.findUser(user.getId());

        User email = userRepository.findUserForEmail(user.getEmail());

        if (id != null) return "Ya existe una cuenta asociada a este numero de identidad";
        if (email!=null) return "Ya existe una cuenta asociada a este correo";

        userRepository.mergeUser(user);
        return "Registro exitoso";

    }
}

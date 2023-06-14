package com.codingideas.cashkeeper.service;

import com.codingideas.cashkeeper.interfaces.IUserService;
import com.codingideas.cashkeeper.models.User;
import com.codingideas.cashkeeper.repository.UserRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public boolean ediClient(User userEdited, boolean auth) {

        if(!auth) return false;

        User user = userRepository.findUser(userEdited.getId());

        user.setNombre(userEdited.getNombre());
        user.setDireccion(userEdited.getDireccion());
        user.setTelefono(userEdited.getTelefono());

        userRepository.mergeUser(user);

        return true;
    }

    @Override
    public List getClients() {
        List<User> users  = userRepository.getClients();

        com.codingideas.cashkeeper.mapper.MapperUser mapperUser = new com.codingideas.cashkeeper.mapper.MapperUser();

        return users.stream().map(mapperUser::userToClientDto).collect(Collectors.toList());
    }

    @Override
    public String addClient(User user, boolean auth) {
        if (!auth) return "El tiempo de estadia ha expirado, por favor inicie sesion nuevamente";

        if (userRepository.findUser(user.getId())!=null) return "Ya se encuentra un cliente registrado con ese numero de identidad";

        userRepository.mergeUser(user);

        return  "El usuario ha sido añadido exitosamente";
    }

    @Override
    public boolean removeClient(String id, boolean auth) {
        User user = userRepository.findUser(id);

        if(!auth) return false;

        userRepository.removeUser(user);

        return true;
    }

    @Override
    public String addUser(@NotNull User user) {

        User userId = userRepository.findUser(user.getId());
        User userEmail = userRepository.findUserForEmail(user.getEmail());

        if (userEmail!=null) return "Ya existe una cuenta asociada a este correo";
        if (userId!=null) return "Ya existe una cuenta asociada a este numero de identidad";

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashedPassword = argon2.hash(3,1024,2,user.getPassword());
        user.setPassword(hashedPassword);

        userRepository.mergeUser(user);
        return "OK";
    }
}

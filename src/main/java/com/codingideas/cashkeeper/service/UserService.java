package com.codingideas.cashkeeper.service;

import com.codingideas.cashkeeper.interfaces.IUserService;
import com.codingideas.cashkeeper.models.User;
import com.codingideas.cashkeeper.repository.UserRepository;
import com.codingideas.cashkeeper.utils.JWTUtil;
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

    private final JWTUtil jwtUtil;

    public boolean verifyToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        System.out.println(usuarioId);
        return usuarioId!=null;
    }

    @Override
    public boolean ediClient(User userEdited, String token) {

        if(!verifyToken(token)) return false;

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
    public String addClient(User user, String token) {
        if (!verifyToken(token)) return "El tiempo de estadia ha expirado, por favor inicie sesion nuevamente";

        if (userRepository.findUser(user.getId())!=null) return "Ya se encuentra un cliente registrado con ese numero de identidad";

        userRepository.mergeUser(user);

        return  "El usuario ha sido añadido exitosamente";
    }

    @Override
    public boolean removeClient(String id, String token) {
        User user = userRepository.findUser(id);

        if(!verifyToken(token)) return false;

        userRepository.removeUser(user);

        return true;
    }

    @Override
    public String addUser(User user) {

        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashedPassword = argon.hash(3,1024,2,user.getPassword());
        user.setPassword(hashedPassword);

        User id = user;

        User email = userRepository.findUserForEmail(user.getEmail());

        if (id != null) return "Ya existe una cuenta asociada a este numero de identidad";
        if (email!=null) return "Ya existe una cuenta asociada a este correo";

        userRepository.mergeUser(user);
        return "Registro exitoso";

    }
}

package com.codingideas.cashkeeper.utils.mapper;

import com.codingideas.cashkeeper.dto.ClientDTO;
import com.codingideas.cashkeeper.models.User;

public class MapperUser {

    public ClientDTO userToClientDto(User user){

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setDireccion(user.getDireccion());
        clientDTO.setNombre(user.getNombre());
        clientDTO.setId(user.getId());
        clientDTO.setTelefono(user.getTelefono());

        return clientDTO;
    }
}

package com.codingideas.cashkeeper.controllers;

import com.codingideas.cashkeeper.dto.ClientDTO;
import com.codingideas.cashkeeper.interfaces.IUserService;
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
public class UserController {

    private final IUserService iUserService;
 
    @RequestMapping(value = "get/clients")
    public List<ClientDTO> getClients(){
        return iUserService.getClients();
    }

    @RequestMapping(value = "delete/client",method = RequestMethod.DELETE)
    public boolean deleteClient(@RequestBody String id, @RequestHeader(value = "Authorization") String token){
        return iUserService.removeClient(id,token);
    }

    @RequestMapping(value = "add/client",method = RequestMethod.POST)
    public String addClient(@RequestBody User user,@RequestHeader(value = "Authorization") String token){
       return iUserService.addClient(user,token);
    }

    @RequestMapping(value = "edit/client",method = RequestMethod.POST)
    public boolean editClient(@RequestBody User userEdited,@RequestHeader(value="Authorization") String token){
        return iUserService.ediClient(userEdited,token);
    }
}

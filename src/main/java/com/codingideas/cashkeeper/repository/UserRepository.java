package com.codingideas.cashkeeper.repository;

import com.codingideas.cashkeeper.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class UserRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public void mergeUser(User user){
        entityManager.merge(user);
    }

    public User findUser(String id){
        return entityManager.find(User.class,id);
    }

    public List<User> getClients(){
        return entityManager.createQuery("FROM User where rol!=1").getResultList();
    }

    public void removeUser(User user){
        entityManager.remove(user);
    }

    public User findUserForEmail(String email){
        return (User) entityManager.createQuery("FROM User where email='"+email+"'")
                .getResultList().get(0);
    }
}


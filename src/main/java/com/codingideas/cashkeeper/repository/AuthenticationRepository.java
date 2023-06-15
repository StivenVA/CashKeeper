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
public class AuthenticationRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public User findUserforEmail(String email){
       List<User> user= entityManager
                .createQuery("FROM User where email='"+email+"'").getResultList();

       return user.get(0);
    }
}

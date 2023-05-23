package com.example.restservice.services;

import com.example.restservice.models.Accounts;
import com.example.restservice.repositories.AccountsJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountsService {

    @Autowired
    private AccountsJPARepository accountsJPARepository; //property inject

    //constructor injection
    public AccountsService(){}

    public List<Accounts> findAll(){
        return accountsJPARepository.findAll();
    }

    public Accounts findById(Long id){
        return accountsJPARepository.findByUserId(id);
    }
    public List<Accounts> findByUserName(String userName){
        return accountsJPARepository.findByUsernameContains(userName);
    }
    public Accounts saveAccount(Accounts account){
        return accountsJPARepository.save(account);
    }

    public void deleteById(Long id){
         accountsJPARepository.deleteById(id);
    }

    public void updateAccount(Accounts accounts){
         accountsJPARepository.updateUserNamePasswordEmail(accounts.getUsername(), accounts.getPassword(),
                accounts.getEmail(), accounts.getUserId());
    }


}

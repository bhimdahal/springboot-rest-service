package com.example.restservice.controllers;


import com.example.restservice.models.Accounts;
import com.example.restservice.services.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class AccountController {
    @Autowired
    AccountsService accountsService;


    @GetMapping(value = "/accounts" )
    public ResponseEntity<List<Accounts>> getAllAccounts(){
        List<Accounts> accounts = new ArrayList<>();
       this.accountsService.findAll().forEach(accounts1 -> accounts.add(accounts1));
       try{
           return new ResponseEntity<>(accounts, HttpStatus.OK);

       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
    @GetMapping(value = "/accounts/{id}" )
    public ResponseEntity<Accounts> getAccountById(@PathVariable("id") long userId) {
        try{
            Accounts account = this.accountsService.findById(userId);
            return new ResponseEntity<>(account, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.valueOf(e.getMessage()));
        }
    }

    @GetMapping(value = "/accounts/matching/{username}" )
    @CrossOrigin
    public ResponseEntity<List<Accounts>> getAccountByUserName(@PathVariable("username") String username) {
        try{
            List<Accounts> accounts = this.accountsService.findByUserName(username);
            return new ResponseEntity<>(accounts, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.valueOf(e.getMessage()));
        }
    }

    @PostMapping(value = "/accounts/create" )
    public ResponseEntity<Accounts> createAccount(@RequestBody Accounts account) {
        try{
            Accounts response = this.accountsService.saveAccount(new Accounts(account.getUsername(),
                    account.getPassword(), account.getEmail()));
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.valueOf(e.getMessage()));
        }
    }

    @DeleteMapping(value = "/accounts/delete-by-id/{id}" )
    public ResponseEntity deleteAccountByUserId(@PathVariable("id") Long id) {
        try{
            this.accountsService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/accounts/update-by-id" )
    public ResponseEntity<Accounts> updateAccount(@RequestBody Accounts account) {
        try{
            Accounts accountToUpdate = this.accountsService.findById(account.getUserId());
            if(String.valueOf(accountToUpdate.getUserId()) != null){
                this.accountsService.updateAccount(account);
                return new ResponseEntity<>(accountToUpdate, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }


        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

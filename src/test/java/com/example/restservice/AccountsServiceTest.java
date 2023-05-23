package com.example.restservice;

import com.example.restservice.models.Accounts;
import com.example.restservice.repositories.AccountsJPARepository;
import com.example.restservice.services.AccountsService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootTest

public class AccountsServiceTest {

    @MockBean
    private AccountsJPARepository accountsJPARepository;

    @Autowired
    private AccountsService accountsService;

//    @Before
//    public void setup(){
//        Accounts test1 = new Accounts(1,"test1","password1", "test1@gmail.com",
//                new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
//
//        Mockito.when(accountsJPARepository.findByUserId(test1.getUserId()))
//                .thenReturn(test1);
//    }

    @Test
    public void whenValidId_thenAccountShouldBeFound() {
        Accounts test1 = new Accounts(1,"test1","password1", "test1@gmail.com",
                new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));

        Mockito.when(accountsJPARepository.findByUserId(test1.getUserId()))
                .thenReturn(test1);
        Accounts found = accountsService.findById(1l);

        Assert.assertEquals(found.getUsername(), "test1");
    }

    @Test
    public void whenUserNameMatches_thenAccountListShouldReturn() {
        Accounts test1 = new Accounts(1,"test1","password1", "test1@gmail.com",
                new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
        Accounts test2 = new Accounts(2,"test2","password12", "test2@gmail.com",
                new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
        Accounts test3 = new Accounts(3,"test3","password13", "test3@gmail.com",
                new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));

        List<Accounts> accountsList = new ArrayList<>();
        accountsList.add(test1);
        accountsList.add(test2);
        accountsList.add(test3);

        Mockito.when(accountsJPARepository.findByUsernameContains("test"))
                .thenReturn(accountsList);
        List<Accounts> accounts = accountsService.findByUserName("test");

        Assert.assertEquals(accounts.size(), 3);
        Assert.assertNotEquals(accounts.size(), 2);
        Assert.assertEquals(accounts.get(2).getUsername(), "test3");
    }


}

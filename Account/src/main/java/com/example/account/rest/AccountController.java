package com.example.account.rest;

import com.example.account.persistence.domain.Account;
import com.example.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {
    private final AccountService service;
    private final RestTemplate rest;

    @Autowired
    public AccountController(AccountService service, @Autowired RestTemplate rest){
        super();
        this.service = service;
        this.rest = rest;
    }

//    @PutMapping("/addToQ/{message}")
//    public void addToQ(@PathVariable String message) {
//        this.service.sendMessage(message);
//    }

    @PostMapping("/create")
    public ResponseEntity<Account> create(@RequestBody Account account){
        //Should this be here?
        String accNum = this.rest.getForObject("http://numgen-api/accNum/get", String.class);
        String prizeNum = this.rest.getForObject(String.format("http://prizegen-api/prize/get/%s",accNum), String.class);
        Account createdObject = this.service.create(account, accNum, prizeNum);
        return new ResponseEntity<>(createdObject, HttpStatus.CREATED);
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<Account>> readAll() {
        return ResponseEntity.ok(this.service.readAll());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Account> updateById(@PathVariable Long id, @RequestBody Account account) {
        Account updatedObject = this.service.updateById(id, account);
        return new ResponseEntity<>(updatedObject, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if (this.service.deleteById(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Account> readById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.readById(id));
    }
}
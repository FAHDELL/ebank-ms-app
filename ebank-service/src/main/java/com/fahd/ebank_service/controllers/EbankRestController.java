package com.fahd.ebank_service.controllers;

import com.fahd.ebank_service.entities.BankAccount;
import com.fahd.ebank_service.service.EbankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EbankRestController {

    private EbankService ebankService;

    public EbankRestController(EbankService ebankService) {
        this.ebankService = ebankService;
    }

    @GetMapping("/account")
    public List<BankAccount> getAllBankAccount(){
        return ebankService.getAllBankAccount();
    }

    @GetMapping("/account/{id}")
    public BankAccount getBankAccountById(@PathVariable String id){
        return ebankService.getBankAccountById(id);
    }

    @PostMapping("/account")
    public BankAccount save(@RequestBody BankAccount bankAccount){
        return ebankService.save(bankAccount);
    }
}

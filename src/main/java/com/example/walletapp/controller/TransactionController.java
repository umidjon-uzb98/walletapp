package com.example.walletapp.controller;

import com.example.walletapp.entity.Transaction;
import com.example.walletapp.service.TransactionService;
import com.example.walletapp.service.ValidationErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
@CrossOrigin
public class TransactionController {

    private final TransactionService transactionService;
    private final ValidationErrorService validationService;

    @GetMapping("/{wallet_id}")
    public ResponseEntity<?> geAll(@PathVariable Long wallet_id) {
        return new ResponseEntity<>(transactionService.getAll(wallet_id), HttpStatus.OK);
    }

    @GetMapping("/{wallet_id}/{id}")
    public ResponseEntity<?> getById(@PathVariable Long wallet_id,
                                     @PathVariable Long id){
        return new ResponseEntity<>(transactionService.getById(wallet_id,id), HttpStatus.OK);
    }

    @PostMapping("/{wallet_id}")
    public ResponseEntity<?> create(@PathVariable Long wallet_id, @Valid @RequestBody Transaction transaction, BindingResult result) {
        ResponseEntity<?> errors = validationService.validate(result);
        if (errors != null) return errors;
        Transaction transactionSaved = transactionService.createOrUpdate(wallet_id, transaction);
        return new ResponseEntity<>(transactionSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{wallet_id}/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @PathVariable Long wallet_id,
                                    @Valid @RequestBody Transaction transaction,
                                    BindingResult result) {
        ResponseEntity errors = validationService.validate(result);
        if (errors != null) return errors;
        transaction.setId(id);
        Transaction transactionSaved = transactionService.createOrUpdate(wallet_id, transaction);
        return new ResponseEntity<>(transactionSaved, HttpStatus.OK);
    }

    @DeleteMapping("/{wallet_id}/{id}")
    public ResponseEntity<?> delete(@PathVariable Long wallet_id,
                                    @PathVariable Long id) {
        transactionService.delete(wallet_id, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}


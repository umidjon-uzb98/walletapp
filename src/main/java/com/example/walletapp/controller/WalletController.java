package com.example.walletapp.controller;

import com.example.walletapp.entity.Wallet;
import com.example.walletapp.service.ValidationErrorService;
import com.example.walletapp.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;
    private final ValidationErrorService validateService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Wallet wallet, BindingResult result) {
        ResponseEntity errors = validateService.validate(result);
        if (errors != null) return errors;

        Wallet walletSaved = walletService.createOrUpdate(wallet);
        return new ResponseEntity<>(walletSaved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        walletService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

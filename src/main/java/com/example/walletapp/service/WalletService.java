package com.example.walletapp.service;

import com.example.walletapp.entity.Wallet;
import com.example.walletapp.exception.WalletException;
import com.example.walletapp.repo.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    @Autowired
    private WalletRepo walletRepo;

    public List<Wallet> getAll(){
        return walletRepo.findAllByOrderByPriority();
    }

    public Wallet getById(Long id){
        Optional<Wallet> wallet = walletRepo.findById(id);
        if(wallet.isPresent()){
            return wallet.get();
        }

        throw  new WalletException("Wallet with "+id+" does not exists");
    }

    public Wallet createOrUpdate(Wallet wallet) {
        System.out.println(wallet);
        if (wallet.getId() == null) {
            wallet = walletRepo.save(wallet);
        } else {
            wallet = walletRepo.save(wallet);
        }
        return wallet;
    }

    public boolean delete(Long id) {
        Optional<Wallet> wallet = walletRepo.findById(id);
        if(wallet.isPresent()){
            walletRepo.delete(wallet.get());
            return true;
        }

        throw  new WalletException("Wallet with "+id+" does not exists");
//        throw  new Exception("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }
}

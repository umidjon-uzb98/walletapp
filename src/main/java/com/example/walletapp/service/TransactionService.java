package com.example.walletapp.service;

import com.example.walletapp.entity.Transaction;
import com.example.walletapp.entity.Wallet;
import com.example.walletapp.exception.WalletException;
import com.example.walletapp.repo.TransactionRepo;
import com.example.walletapp.repo.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private WalletRepo walletRepo;

    public List<Transaction> getAll(Long walletId){
        Optional<Wallet> wallet = walletRepo.findById(walletId);
        return wallet.map(value -> transactionRepo.findByWallet(value)).orElse(null);
    }

    public Transaction getById(Long wallet_id, Long id){
        Optional<Wallet> wallet = walletRepo.findById(wallet_id);
        if (wallet.isPresent()){
            Optional<Transaction> transaction = transactionRepo.findById(id);
            if(transaction.isPresent()){
                return transaction.get();
            }
        }
        throw  new WalletException("Transaction with "+id+" does not exists");
    }

    public Transaction createOrUpdate(Long walletId, Transaction transaction) {
        Optional<Wallet> wallet = walletRepo.findById(walletId);
        if (wallet.isPresent()) {
            transaction.setWallet(wallet.get());
            transactionRepo.save(transaction);
            return transaction;
        }
        return null;
    }

    public boolean delete(Long wallet_id, Long id) {
        Optional<Wallet> wallet = walletRepo.findById(wallet_id);
        if (wallet.isPresent()) {
            Optional<Transaction> transaction = transactionRepo.findById(id);
            if(transaction.isPresent()){
                transactionRepo.delete(transaction.get());
                return true;
            }
        }
        throw  new WalletException("Transaction with "+id+" does not exists");
////        throw  new Exception("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }
}

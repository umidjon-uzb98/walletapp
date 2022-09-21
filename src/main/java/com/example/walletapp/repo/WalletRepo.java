package com.example.walletapp.repo;

import com.example.walletapp.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Long> {



}

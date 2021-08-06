package com.example.service;

import com.example.service.TxrService;
import com.example.model.Account;
import com.example.repository.AccountRepository;
import com.example.repository.JdbcAccountRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("txrService")

public class NEFTTxrService implements TxrService{

        private final static Logger logger =  Logger.getLogger("txr-service");

        @Autowired
        @Qualifier("jdbc")
        private AccountRepository accountRepository;

        public NEFTTxrService (@Qualifier("jdbc") AccountRepository accountRepository) {
            this.accountRepository=accountRepository;
            logger.info("NEFTTxrService instance created");
        }

        @Override
        public boolean txr(double amount, String fromAccNum, String toAccNum) {
            logger.info("txr-service");
            //AccountRepository accountRepository=new JdbcAccountRepository();

            Account fromAccount=accountRepository.load(fromAccNum);
            Account toAccount=accountRepository.load(toAccNum);
            fromAccount.setBalance(fromAccount.getBalance()-amount);
            toAccount.setBalance(toAccount.getBalance()+amount);
            accountRepository.update(fromAccount);
            accountRepository.update(toAccount);
            return true;
        }
    }


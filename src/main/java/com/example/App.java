package com.example;

import com.example.service.NEFTTxrService;
import com.example.service.TxrService;
import com.example.model.Account;
import com.example.repository.AccountRepository;
import com.example.repository.JPAAccountRepository;
import com.example.repository.JdbcAccountRepository;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private final static Logger logger = Logger.getLogger("txr-service");

    public static void main(String[] args) {

        //-------------------------------
        //init/booting phase
        //----------------------------------

//        AccountRepository jdbcAccountrepository=new JdbcAccountRepository();
//        AccountRepository jpaAccountRepository=new JPAAccountRepository();
//        TxrService txrService=new NEFTTxrService(jpaAccountRepository);
        //create spring container

        ConfigurableApplicationContext applicationContext=null;
        applicationContext=new ClassPathXmlApplicationContext("txr-service.xml");

        System.out.println("*".repeat(30));

        //-------------------------------
        //use
        //----------------------------------

        TxrService txrService=applicationContext.getBean("txrService",TxrService.class);

        txrService.txr(1000.00,"1","2");
        System.out.println("-".repeat(30));
        txrService.txr(1000.00,"2","1");
        System.out.println("~".repeat(30));;

        //-------------------------------
        //destroy
        //----------------------------------

        applicationContext.close();
    }

}

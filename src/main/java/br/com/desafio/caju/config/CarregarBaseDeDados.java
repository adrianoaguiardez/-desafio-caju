 package br.com.desafio.caju.config;

 import br.com.desafio.caju.model.Account;
 import br.com.desafio.caju.service.AccountService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.CommandLineRunner;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;

 import java.math.BigDecimal;


 @Configuration
 public class CarregarBaseDeDados {

     @Autowired
     private AccountService accountService;

 	@Bean
 	CommandLineRunner executar() {

 		return args -> {



            Account accountUm = new Account(1L, "123");
            accountUm.adicionarBalance(new BigDecimal("200"));
            accountService.salvarAccount(accountUm);

            Account accountDois = new Account(2L, "124");
            accountDois.adicionarBalance(new BigDecimal("100"));
            accountService.salvarAccount(accountDois);


            Account accountTres = new Account(3L, "125");
            accountTres.adicionarBalance(new BigDecimal("0"));

            accountService.salvarAccount(accountTres);







 		};
 	}

 }

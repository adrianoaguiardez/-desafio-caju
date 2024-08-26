package br.com.desafio.caju.repository;


import br.com.desafio.caju.model.TransactionAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TransactionRepository extends JpaRepository<TransactionAccount, Long> {



}

package br.com.desafio.caju.repository;

import br.com.desafio.caju.model.Account;
import br.com.desafio.caju.model.ClassificacaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    @Query(value = "select a from Account a LEFT JOIN FETCH a.balances b where a.id = ?1 and b.classificacao = ?2")
    Optional<Account> findByIdBalance(Long codigo, ClassificacaoEnum classificacao);

    Optional<Account> findByAccount(String account);
}

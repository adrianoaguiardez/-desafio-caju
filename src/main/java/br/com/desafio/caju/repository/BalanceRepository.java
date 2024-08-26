package br.com.desafio.caju.repository;

import br.com.desafio.caju.model.Balance;
import br.com.desafio.caju.model.ClassificacaoEnum;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    String SKIP_LOCKED = "-2";
    @QueryHints({ @QueryHint(name = "jakarta.persistence.lock.timeout", value = SKIP_LOCKED) })
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select b from Balance b where b.account.account = ?1 and b.classificacao = ?2")
    Balance findByIdBalanceAndAccount(String account, ClassificacaoEnum classificacao);
}

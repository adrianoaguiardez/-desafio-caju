package br.com.desafio.caju.service;

import br.com.desafio.caju.model.Account;
import br.com.desafio.caju.repository.AccountRepository;
import br.com.desafio.caju.repository.BalanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final BalanceRepository balanceRepository;

    public AccountService(AccountRepository accountRepository, BalanceRepository balanceRepository) {
        this.accountRepository = accountRepository;
        this.balanceRepository = balanceRepository;
    }

    @Transactional
    public Account salvarAccount(Account account) {

        Optional<Account> verificarConta = accountRepository.findByAccount(account.getAccount());
        if (verificarConta.isEmpty()) {

            Account save = accountRepository.save(account);
            account.getBalances().forEach(balanceRepository::save);


        }
        return account;
    }
}

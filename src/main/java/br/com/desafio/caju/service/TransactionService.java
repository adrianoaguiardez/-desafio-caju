package br.com.desafio.caju.service;

import br.com.desafio.caju.exceptions.AprovacaoException;
import br.com.desafio.caju.model.Balance;
import br.com.desafio.caju.model.ClassificacaoEnum;
import br.com.desafio.caju.model.TipoClassificacao;
import br.com.desafio.caju.model.TransactionAccount;
import br.com.desafio.caju.payload.TransactionRequest;
import br.com.desafio.caju.payload.TransactionResponse;
import br.com.desafio.caju.repository.AccountRepository;
import br.com.desafio.caju.repository.BalanceRepository;
import br.com.desafio.caju.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {



    private final TransactionRepository transactionRepository;
    private final BalanceRepository balanceRepository;

    public TransactionService(TransactionRepository transactionRepository, BalanceRepository balanceRepository) {

        this.transactionRepository = transactionRepository;
        this.balanceRepository = balanceRepository;
    }

    @Transactional
    public TransactionResponse processarTrasaction(TransactionRequest transactionRequest) {
        TipoClassificacao tipoClassificacao = TipoClassificacao.fromMcc(transactionRequest.getMcc());

        if(tipoClassificacao.equals(TipoClassificacao.DEFAULT)){
            tipoClassificacao = TipoClassificacao.fromName(transactionRequest.getMerchant());
        }

        Balance balance = balanceRepository.findByIdBalanceAndAccount(transactionRequest.getAccount(), tipoClassificacao.getClassificacao());

        if (balance == null) {
            balance = balanceRepository.findByIdBalanceAndAccount(transactionRequest.getAccount(), ClassificacaoEnum.CASH);

            TransactionAccount transactionAccount = transactionRequest.toModel(balance.getAccount());
            if (balance.getAmount().compareTo(transactionAccount.getTotalAmount()) >= 0) {
                balance.debitar(transactionAccount.getTotalAmount());
                balanceRepository.save(balance);

                return new TransactionResponse("00");

            }
        }


        TransactionAccount transactionAccount = transactionRequest.toModel(balance.getAccount());
        if (balance.getAmount().compareTo(transactionAccount.getTotalAmount()) >= 0) {
            balance.debitar(transactionAccount.getTotalAmount());
            balanceRepository.save(balance);
            transactionRepository.save(transactionAccount);
            return new TransactionResponse("00");

        }

        Balance balanceCash = balanceRepository.findByIdBalanceAndAccount(transactionRequest.getAccount(), ClassificacaoEnum.CASH);
        if (balanceCash.getAmount().compareTo(transactionAccount.getTotalAmount()) >= 0) {
            balanceCash.debitar(transactionAccount.getTotalAmount());
            balanceRepository.save(balanceCash);

            transactionRepository.save(transactionAccount);
            return new TransactionResponse("00");

        }

        if (balance.getAmount().compareTo(transactionAccount.getTotalAmount()) < 0) {
            balance.debitar(transactionAccount.getTotalAmount());
            balanceRepository.save(balance);
            throw new AprovacaoException("51");

        }

        if (tipoClassificacao.getClassificacao().equals(ClassificacaoEnum.CASH) && balance.getAmount().compareTo(transactionAccount.getTotalAmount()) <= 0) {
            balance.debitar(transactionAccount.getTotalAmount());
            balanceRepository.save(balance);

            throw new AprovacaoException("51");

        }

        throw new AprovacaoException("07");
    }
}

package br.com.desafio.caju.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tbbalance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "classificacao")
    @Enumerated(EnumType.STRING)
    private ClassificacaoEnum classificacao;
    @Column(name = "amount")
    private BigDecimal amount = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "id_account" )
    private Account account;




    @Deprecated
    public Balance() {

    }

    public Balance(Long id, ClassificacaoEnum classificacao, BigDecimal amount, Account account) {
        this.id = id;
        this.classificacao = classificacao;
        this.amount = amount;
        this.account = account;
    }


    public Long getId() {
        return id;
    }

    public ClassificacaoEnum getClassificacao() {
        return classificacao;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
    }

    public void debitar(BigDecimal amount) {
       this.amount =  this.amount.subtract(amount);
    }
}

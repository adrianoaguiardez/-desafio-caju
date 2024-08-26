package br.com.desafio.caju.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "tbaccount")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    private Long id;

    private String account;

    @OneToMany(mappedBy = "account")
    private List<Balance>  balances;

    @Deprecated
    public Account() {

    }
    public Account(Long id, String account) {
        this.id = id;
        this.account = account;

    }


    public void adicionarBalance(BigDecimal amout){

        if(balances == null){
            balances = new ArrayList<>();
        }
        List<ClassificacaoEnum> classifications = Arrays.asList(ClassificacaoEnum.values());
        classifications.forEach(t->{
            Balance balance = new Balance(null, t, amout, this);
            this.balances.add(balance);
        });
    }


    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public List<Balance> getBalances() {
        return balances;
    }


}

package br.com.desafio.caju.model;


import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tbtransaction")
public class TransactionAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @Column(name = "mcc")
    private int mcc;
    @Column(name = "merchant")
    private String merchant;

    @Deprecated
    public TransactionAccount() {
    }

    public TransactionAccount(Long id, Account account, BigDecimal totalAmount, int mcc, String merchant) {
        this.id = id;
        this.account = account;
        this.totalAmount = totalAmount;
        this.mcc = mcc;
        this.merchant = merchant;
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public int getMcc() {
        return mcc;
    }

    public String getMerchant() {
        return merchant;
    }
}

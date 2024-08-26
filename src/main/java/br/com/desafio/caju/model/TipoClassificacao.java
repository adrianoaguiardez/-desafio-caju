package br.com.desafio.caju.model;

public enum TipoClassificacao {
    MCC_5411 ("UBER TRIP                   SAO PAULO BR") {
        @Override
        public ClassificacaoEnum getClassificacao() {
            return ClassificacaoEnum.FOOD;
        }
    },
    MCC_5412 ("UBER EATS                   SAO PAULO BR") {
        @Override
        public ClassificacaoEnum getClassificacao() {
            return ClassificacaoEnum.FOOD;
        }
    },
    MCC_5811 ("PAG*JoseDaSilva          RIO DE JANEI BR") {
        @Override
        public ClassificacaoEnum getClassificacao() {
            return ClassificacaoEnum.MEAL;
        }
    },
    MCC_5812 ("PICPAY*BILHETEUNICO           GOIANIA BR ") {
        @Override
        public ClassificacaoEnum getClassificacao() {
            return ClassificacaoEnum.MEAL;
        }
    },
    DEFAULT ("")  {
        @Override
        public ClassificacaoEnum getClassificacao() {
            return ClassificacaoEnum.CASH;
        }
    };

    public abstract ClassificacaoEnum getClassificacao();

    private String name;

    TipoClassificacao(String name) {


        this.name = name;

    }
    public static TipoClassificacao fromMcc(int mcc) {
        try {
            return TipoClassificacao.valueOf("MCC_" + mcc);
        } catch (IllegalArgumentException e) {
            return DEFAULT;
        }
    }
    public static TipoClassificacao fromName(String name) {
        for (TipoClassificacao tipo : TipoClassificacao.values()) {
            if (tipo.name.equalsIgnoreCase(name.trim())) {
                return tipo;
            }
        }
        return DEFAULT;
    }

    public String getName() {
        return name;
    }
}

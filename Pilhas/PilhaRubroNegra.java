public class PilhaRubroNegra {
    private int capacidade;
    private Object[] a;
    private int tVermelho;
    private int tPreto;

    public PilhaRubroNegra(int capacidade) {
        this.capacidade = capacidade;
        this.tVermelho = -1;
        this.tPreto = capacidade;
        this.a = new Object[capacidade];
    }

    public class PilhaVaziaExcecao extends RuntimeException {
        public PilhaVaziaExcecao(String error) {
            super(error);
        }
    }

    private int sizeVermelho() {
        return tVermelho + 1;
    }

    private int sizePreto() {
        return capacidade - tPreto;
    }

    private int qtdElementos() {
        return sizeVermelho() + sizePreto();
    }

    public int getCapacidade() {
        return capacidade;
    }

    private void realocarElementosArray() {
        Object b[] = new Object[capacidade];
        for (int f = 0; f <= tVermelho; f++) {
            b[f] = a[f];
        }

        int qtdPretos = a.length - tPreto;
        int novoTPreto = capacidade - qtdPretos;
        int indiceNovoAux = novoTPreto;

        for (int i = 0; i < qtdPretos; i++) {
            b[indiceNovoAux] = a[tPreto + i];
            indiceNovoAux++;
        }

        a = b;
        tPreto = novoTPreto;
    }

    public void pushVermelho(Object o) {
        if (qtdElementos() >= capacidade) {
            capacidade *= 2;
            realocarElementosArray();
        }
        a[++tVermelho] = o;
    }

    public void pushPreto(Object o) {
        if (qtdElementos() >= capacidade) {
            capacidade *= 2;
            realocarElementosArray();
        }
        a[--tPreto] = o;
    }

    public Object popVermelho() throws PilhaVaziaExcecao {
        if (isEmptyVermelho()) {
            throw new PilhaVaziaExcecao("Pilha vermelha vazia");
        }
        Object elemento = a[tVermelho--];
        if (qtdElementos() * 3 <= capacidade && capacidade > 3) {
            capacidade /= 2;
            realocarElementosArray();
        }
        return elemento;
    }

    public Object popPreto() throws PilhaVaziaExcecao {
        if (isEmptyPreto())
            throw new PilhaVaziaExcecao("Pilha preta vazia");
        Object elemento = a[tPreto++];
        if (qtdElementos() * 3 <= capacidade && capacidade > 3) {
            capacidade /= 2;
            realocarElementosArray();
        }
        return elemento;
    }

    public boolean isEmptyVermelho() {
        return tVermelho == -1;
    }

    public boolean isEmptyPreto() {
        return tPreto == capacidade;
    }

    public Object topVermelho() throws PilhaVaziaExcecao {
        if (isEmptyVermelho()) throw new PilhaVaziaExcecao("Pilha vermelha vazia");
        return a[tVermelho];
    }

    public Object topPreto() throws PilhaVaziaExcecao {
        if (isEmptyPreto()) throw new PilhaVaziaExcecao("Pilha preta vazia");
        return a[tPreto];
    }

    public int retornaIndiceTopPreto() { return tPreto; }
    public int retornaIndiceTopVermelho() { return tVermelho; }

    public void imprimirArrayInterno() {
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String BLACK_BG = "\u001B[40m\u001B[37m";

        System.out.print("[ ");
        for (int i = 0; i < capacidade; i++) {
            if (i <= tVermelho) {
                System.out.print(RED + a[i] + RESET + " ");
            } else if (i >= tPreto) {
                System.out.print(BLACK_BG + a[i] + RESET + " ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println("]");
    }
}
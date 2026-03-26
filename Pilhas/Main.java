import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Defina a capacidade inicial da Pilha Rubro-Negra: ");
        int capInicial = scanner.nextInt();
        PilhaRubroNegra pilha = new PilhaRubroNegra(capInicial);

        boolean sair = false;
        while (!sair) {
            System.out.println("\n--- MENU PILHA RUBRO-NEGRA ---");
            System.out.println("1. Push Vermelho    2. Push Preto");
            System.out.println("3. Pop Vermelho     4. Pop Preto");
            System.out.println("5. Top Vermelho     6. Top Preto");
            System.out.println("7. Info (Status)    8. Visualizar Array");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Valor para Pilha Vermelha: ");
                        pilha.pushVermelho(scanner.next());
                        break;
                    case 2:
                        System.out.print("Valor para Pilha Preta: ");
                        pilha.pushPreto(scanner.next());
                        break;
                    case 3:
                        System.out.println("Removido da Vermelha: " + pilha.popVermelho());
                        break;
                    case 4:
                        System.out.println("Removido da Preta: " + pilha.popPreto());
                        break;
                    case 5:
                        System.out.println("Topo Vermelho: " + pilha.topVermelho());
                        break;
                    case 6:
                        System.out.println("Topo Preto: " + pilha.topPreto());
                        break;
                    case 7:
                        exibirStatus(pilha);
                        break;
                    case 8:
                        System.out.println("\nEstrutura do Array Interno:");
                        pilha.imprimirArrayInterno();
                        System.out.println("Capacidade Real: " + pilha.getCapacidade());
                        break;
                    case 0:
                        sair = true;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (PilhaRubroNegra.PilhaVaziaExcecao e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void exibirStatus(PilhaRubroNegra p) {
        System.out.println("\n--- STATUS ---");
        System.out.println("Índice Topo Vermelho: " + p.retornaIndiceTopVermelho());
        System.out.println("Índice Topo Preto: " + p.retornaIndiceTopPreto());
        System.out.println("Capacidade Total: " + p.getCapacidade());
    }
}
package app;

import java.util.List;
import java.util.Scanner;

import dao.CarroDAO;
import model.Carro;

public class Aplicacao {
    
    private static CarroDAO carroDAO = new CarroDAO();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) throws Exception {
        int opcao;
        
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner
            
            switch (opcao) {
                case 1:
                    inserirCarro();
                    break;
                case 2:
                    listarCarros();
                    break;
                case 3:
                    atualizarCarro();
                    break;
                case 4:
                    excluirCarro();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 5);
        
        scanner.close();
    }
    
    private static void exibirMenu() {
        System.out.println("\n==== Menu ====");
        System.out.println("1) Inserir");
        System.out.println("2) Listar");
        System.out.println("3) Atualizar");
        System.out.println("4) Excluir");
        System.out.println("5) Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private static void inserirCarro() {
        System.out.println("\n==== Inserir carro ====");
        System.out.print("Informe o código: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        
        System.out.print("Informe a marca: ");
        String marca = scanner.nextLine();
        
        System.out.print("Informe o modelo: ");
        String modelo = scanner.nextLine();
        
        System.out.print("Informe a cor: ");
        String cor = scanner.nextLine();
        
        System.out.print("Informe a condição (N - Novo | U - Usado): ");
        String condicaoStr = scanner.nextLine();
        char condicao = condicaoStr.charAt(0);
        
        Carro carro = new Carro(codigo, marca, modelo, cor, condicao);
        
        if (carroDAO.insert(carro)) {
            System.out.println("Carro inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir carro.");
        }
    }
    
    private static void listarCarros() {
        System.out.println("\n==== Listar carros ====");
        List<Carro> carros = carroDAO.get();
        
        if (carros.isEmpty()) {
            System.out.println("Nenhum carro cadastrado.");
        } else {
            for (Carro carro : carros) {
                System.out.println(carro.toString());
            }
        }
    }
    
    private static void atualizarCarro() {
        System.out.println("\n==== Atualizar carro ====");
        System.out.print("Informe o código do carro a ser atualizado: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        
        Carro carro = carroDAO.get(codigo);
        
        if (carro == null) {
            System.out.println("Carro não encontrado.");
        } else {
            System.out.print("Informe a nova marca: ");
            String marca = scanner.nextLine();
            
            System.out.print("Informe o novo modelo: ");
            String modelo = scanner.nextLine();
            
            System.out.print("Informe a nova cor: ");
            String cor = scanner.nextLine();
            
            System.out.print("Informe a nova condição (N - Novo | U - Usado): ");
            String condicaoStr = scanner.nextLine();
            char condicao = condicaoStr.charAt(0);
            
            carro.setMarca(marca);
            carro.setModelo(modelo);
            carro.setCor(cor);
            carro.setCondicao(condicao);
            
            if (carroDAO.update(carro)) {
                System.out.println("Carro atualizado com sucesso!");
            } else {
                System.out.println("Erro ao atualizar carro.");
            }
        }
    }
    
    private static void excluirCarro() {
        System.out.println("\n==== Excluir carro ====");
        System.out.print("Informe o código do carro a ser excluído: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        
        Carro carro = carroDAO.get(codigo);
        
        if (carro == null) {
            System.out.println("Carro não encontrado.");
        } else if (carroDAO.delete(codigo)) {
            System.out.println("Carro excluído com sucesso!");
        } else {
            System.out.println("Erro ao excluir carro.");
        }
    }
}

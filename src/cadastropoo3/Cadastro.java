import Model.PessoaFisicaRepo;
import Model.PessoaJuridicaRepo;
import Model.PessoaFisica;
import Model.PessoaJuridica;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cadastro {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

        int opcao;
        while (true) {
            System.out.println("########################");
            System.out.println("#Selecione uma opção: #");
            System.out.println("#1 - Incluir           #");
            System.out.println("#2 - Alterar           #");
            System.out.println("#3 - Excluir           #");
            System.out.println("#4 - Exibir pelo ID    #");
            System.out.println("#5 - Exibir todos      #");
            System.out.println("#6 - Salvar dados      #");
            System.out.println("#7 - Recuperar dados   #");
            System.out.println("#0 - Sair              #");
            System.out.println("########################");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> incluirOpcao(repoFisica, repoJuridica, scanner);
                case 2 -> alterarOpcao(repoFisica, repoJuridica, scanner);
                case 3 -> excluirOpcao(repoFisica, repoJuridica, scanner);
                case 4 -> exibirPorIdOpcao(repoFisica, repoJuridica, scanner);
                case 5 -> exibirTodosOpcao(repoFisica, repoJuridica);
                case 6 -> salvarOpcao(repoFisica, repoJuridica);
                case 7 -> recuperarOpcao(repoFisica, repoJuridica);
                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void incluirOpcao(PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica, Scanner scanner) {
        System.out.println("Selecione o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
        int tipo = scanner.nextInt();
        switch (tipo) {
            case 1 -> incluirPessoa(repoFisica, scanner);
            case 2 -> incluirPessoa(repoJuridica, scanner);
            default -> System.out.println("Tipo inválido!");
        }
    }

    private static void incluirPessoa(PessoaFisicaRepo repo, Scanner scanner) {
        System.out.println("Digite o ID:");
        int id = scanner.nextInt();
        System.out.println("Digite o nome:");
        String nome = scanner.next();
        System.out.println("Digite o CPF:");
        String cpf = scanner.next();
        System.out.println("Digite a idade:");
        int idade = scanner.nextInt();
        PessoaFisica pessoa = new PessoaFisica(id, nome, cpf, idade);
        repo.inserir(pessoa);
        System.out.println("Pessoa física incluída com sucesso!");
    }

    private static void incluirPessoa(PessoaJuridicaRepo repo, Scanner scanner) {
        System.out.println("Digite o ID:");
        int id = scanner.nextInt();
        System.out.println("Digite o nome:");
        String nome = scanner.next();
        System.out.println("Digite o CNPJ:");
        String cnpj = scanner.next();
        PessoaJuridica empresa = new PessoaJuridica(id, nome, cnpj);
        repo.inserir(empresa);
        System.out.println("Pessoa jurídica incluída com sucesso!");
    }

    private static void alterarOpcao(PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica, Scanner scanner) {
        System.out.println("Selecione o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
        int tipo = scanner.nextInt();
        System.out.println("Digite o ID:");
        int id = scanner.nextInt();
        switch (tipo) {
            case 1 -> alterarPessoa(repoFisica, id, scanner);
            case 2 -> alterarPessoa(repoJuridica, id, scanner);
            default -> System.out.println("Tipo inválido!");
        }
    }

    private static void alterarPessoa(PessoaFisicaRepo repo, int id, Scanner scanner) {
    PessoaFisica pessoa = repo.obterPorId(id);
    if (pessoa != null) {
        System.out.println("Digite o novo nome:");
        String novoNome = scanner.next();
        System.out.println("Digite o novo CPF:");
        String novoCpf = scanner.next();
        System.out.println("Digite a nova idade:");
        int novaIdade = scanner.nextInt();
        pessoa.setNome(novoNome);
        pessoa.setCpf(novoCpf);
        pessoa.setIdade(novaIdade);
        System.out.println("Pessoa física alterada com sucesso!");
        repo.atualizar(pessoa);
    } else {
        System.out.println("Pessoa física não encontrada!");
    }
}


    private static void alterarPessoa(PessoaJuridicaRepo repo, int id, Scanner scanner) {
        PessoaJuridica empresa = (PessoaJuridica) repo.obterPorId(id);
        if (empresa != null) {
            System.out.println("Digite o novo nome:");
            String novoNome = scanner.next();
            System.out.println("Digite o novo CNPJ:");
            String novoCnpj = scanner.next();
            empresa.setNome(novoNome);
            empresa.setCnpj(novoCnpj);
            System.out.println("Pessoa jurídica alterada com sucesso!");
            repo.atualizar(empresa);
        } else {
            System.out.println("Pessoa jurídica não encontrada!");
        }
    }
    
    private static void excluirOpcao(PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica, Scanner scanner) {
        System.out.println("Digite o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        System.out.println("Digite o ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        if (tipo == 1) {
            boolean removido = repoFisica.remover(id);
            if (removido) {
                System.out.println("Pessoa física removida com sucesso!");
            } else {
                System.out.println("Pessoa física não encontrada!");
            }
        } else if (tipo == 2) {
            boolean removido = repoJuridica.remover(id);
            if (removido) {
                System.out.println("Pessoa jurídica removida com sucesso!");
            } else {
                System.out.println("Pessoa jurídica não encontrada!");
            }
        } else {
            System.out.println("Tipo inválido. Operação de exclusão cancelada.");
        }
    }

     private static void exibirPorIdOpcao(PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica, Scanner scanner) {
        System.out.println("Digite o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Digite o ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        if (tipo == 1) {
            PessoaFisica pessoa = repoFisica.obterPorId(id);
            if (pessoa != null) {
                System.out.println("Pessoa física encontrada:");
                System.out.println("Nome: " + pessoa.getNome());
                System.out.println("CPF: " + pessoa.getCPF());
                System.out.println("Idade: " + pessoa.getIdade());
            } else {
                System.out.println("Pessoa física não encontrada!");
            }
        } else if (tipo == 2) {
            PessoaJuridica pessoa = repoJuridica.obterPorId(id);
            if (pessoa != null) {
                System.out.println("Pessoa jurídica encontrada:");
                System.out.println("Nome: " + pessoa.getNome());
                System.out.println("CNPJ: " + pessoa.getCnpj());
            } else {
                System.out.println("Pessoa jurídica não encontrada!");
            }
        } else {
            System.out.println("Tipo inválido. Operação de consulta por ID cancelada.");
        }
    }
    
     public static void exibirTodosOpcao(PessoaFisicaRepo pessoaFisicaRepo, PessoaJuridicaRepo pessoaJuridicaRepo) {
    System.out.println("Pessoas físicas:");
    List<PessoaFisica> pessoasFisicas = pessoaFisicaRepo.obterTodos();
    for (PessoaFisica pessoa : pessoasFisicas) {
        pessoa.exibir();
    }

    System.out.println("Pessoas jurídicas:");
    List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaRepo.obterTodos();
    for (PessoaJuridica pessoa : pessoasJuridicas) {
        pessoa.exibir();
    }
}


    private static void salvarOpcao(PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("dados.txt"))) {
        writer.write("Pessoas Físicas:");
        writer.newLine();
        for (PessoaFisica pessoaFisica : repoFisica.obterTodos()) {
            writer.write(pessoaFisica.toString());
            writer.newLine();
        }

        writer.write("Pessoas Jurídicas:");
        writer.newLine();
        for (PessoaJuridica pessoaJuridica : repoJuridica.obterTodos()) {
            writer.write(pessoaJuridica.toString());
            writer.newLine();
        }

        System.out.println("Dados salvos com sucesso!");
    } catch (IOException e) {
        System.err.println("Erro ao salvar os dados: " + e.getMessage());
    }
}
 
    
 private static void recuperarOpcao(PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
    try (BufferedReader reader = new BufferedReader(new FileReader("dados.txt"))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            System.out.println(linha);
        }
        System.out.println("Dados recuperados com sucesso!");
    } catch (IOException e) {
        System.err.println("Erro ao recuperar os dados: " + e.getMessage());
    }
}

}

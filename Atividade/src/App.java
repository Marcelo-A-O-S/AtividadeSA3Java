
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Scanner;

import Models.Pessoa;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println(
                "Ola, tudo bem? Seja bem vindo a atividade da SA3\nDigite uma das opcoes do menu abaixo para prosseguir:");
        Menu();

    }

    private static void Menu() {
        var leia = new Scanner(System.in);
        System.out.println(
                "-------------------MENU PRINCIPAL-------------------------\nDigite uma das opcões abaixo e aperte enter para prosseguir: \n1- Cadastrar usuários \n2- Listar usuários\n3-Buscar \n0- Sair");
        String operador = leia.next();

        switch (operador) {
            case "0":
                Fechar();
                break;
            case "1":
                Cadastrar();
                break;
            case "2":
                Listar();
                break;
            case "3":
                Buscar();
                break;
        }
        leia.close();
    }

    /**
     * 
     */
    private static void Buscar() {
        var leia = new Scanner(System.in);
        var arquivoExistente = new File(Caminho());
        if (arquivoExistente.exists() == false) {
            System.out.println(
                    "Infelizmente não foi encontrado nenhum registro cadastrado salvo, cadastre um usuário antes de prosseguir.\nEscolha uma das opcões a seguir e aperte enter: \n1- Leve-me para cadastrar um usuário\n2- Menu \n3- Sair da aplicacão");
            String operador = leia.next();
            switch (operador) {
                case "1":
                    Cadastrar();
                    break;
                case "2":
                    Menu();
                    break;
                case "3":
                    Fechar();
                    break;
                default:
                    System.out.println(
                            "A tecla digitada não corresponde a nenhuma opcão, retornando para o menu.");
                    Menu();

                    break;

            }
        }
        String[] quebra = new String[2];
        System.out.println("Digite o nome que deseja buscar:");
        String nome_busca = leia.next();
        try {
            var path = Paths.get(Caminho());
            var linhas = Files.readAllLines(path);
            for (String string : linhas) {
                if (!string.contains("Nome")) {
                    quebra = string.split(",");
                    System.out.println(quebra[0] + "," + quebra[1]);
                    var comparacao = quebra[0];
                    if (comparacao.equals(nome_busca)) {
                        System.out.println("O nome " + quebra[0] + " está cadastrado");
                    }
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void Listar() {
        var leia = new Scanner(System.in);
        System.out.println("-------------Lista de Usuarios-------------");

        var arquivoExistente = new File(Caminho());
        if (arquivoExistente.exists() == false) {
            System.out.println(
                    "Infelizmente não foi encontrado nenhum cadastro salvo, cadastre um usuário antes de prosseguir.\nEscolha uma das opcões a seguir e aperte enter: \n1- Leve-me para cadastrar um usuário\n2- Menu \n3- Sair da aplicacão");
            String operador = leia.next();
            switch (operador) {
                case "1":
                    Cadastrar();
                    break;
                case "2":
                    Menu();
                    break;
                case "3":
                    Fechar();
                    break;
                default:

                    System.out.println(
                            "A tecla digitada não corresponde a nenhuma opcão, retornando para o menu.");

                    Menu();

                    break;
            }
        }
        try {
            var path = Paths.get(Caminho());
            var linhas = Files.readAllLines(path);
            for (String string : linhas) {
                System.out.println(string);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-----------------------------");
        System.out.println(
                "Deseja cadastrar um novo usuário? Escolha uma opcão e aperte enter para prosseguir. \n1- Cadastrar um novo usuário\n2- Ir para o menu \n3- Sair da aplicacão");
        String operador = leia.next();
        switch (operador) {
            case "1":
                Cadastrar();
                break;
            case "2":
                Menu();
                break;
            case "3":
                Fechar();
                break;
            default:
                System.out.println("Erro, nenhuma tecla correspondente digitada, retornando para corrigir a acão.");
                Listar();
                break;

        }
    }

    private static void Fechar() {
        var leia = new Scanner(System.in);
        System.out.println("Tem certeza disso? \n1- Sim\n2 -Não, retorne para o menu");
        String operador = leia.next();
        switch (operador) {
            case "1":
                System.exit(0);
                break;
            case "2":
                Menu();
                break;
            default:
                System.out.println("Erro, nenhuma tecla correspondente digitada, retornando para corrigir a acão.");
                Fechar();
                break;

        }

    }

    private static void Cadastrar() {
        var armazenar = new Scanner(System.in);
        Pessoa p = new Pessoa();
        System.out.println("-------------Cadastro de Usuario-------------");
        System.out.println("Digite as informacões necessarias.");
        System.out.println("Digite seu nome:");
        p.Nome = armazenar.nextLine();
        System.out.println("Digite sua idade:");
        p.Idade = armazenar.nextInt();
        Salvar(p);
        System.out.println("Cadastrar outro usuário?\n1- Sim \n2- Não, quero voltar para o menu principal");
        String operador = armazenar.next();
        switch (operador) {
            case "1":
                Cadastrar();
                break;
            case "2":
                Menu();
                break;
            default:
                System.out.println();
                Menu();
                break;
        }
        armazenar.close();

    }

    private static void Salvar(Pessoa p) {
        var arquivoExistente = new File(Caminho());
        if (arquivoExistente.exists() == false) {
            try {
                var criarArquivo = new File(Caminho());
                criarArquivo.createNewFile();
                var escreva = new PrintWriter(criarArquivo);
                escreva.println("Nome,Idade");
                escreva.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            String conteudo = p.Nome + "," + Integer.toString(p.Idade);
            var arquivo = new FileWriter(Caminho(), true);
            var buffer = new BufferedWriter(arquivo);
            var escreva = new PrintWriter(buffer);
            escreva.append(conteudo + "\n");
            escreva.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static String Caminho() {
        // C:\AtividadeSA3Java\Atividade\src\Registros\Pessoas.txt
        String diretorio = System.getProperty("user.dir");
        String caminho = diretorio + "/src/Registros/Pessoas.txt";
        return caminho;
    }
}

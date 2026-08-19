package br.pucpr.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuarioPrinter {
    private static final int TAMANHO_TABELA = 74;
    private static final String FORMATO_TABELA = "| %-5s | %-20s | %-22s | %-14s |%n";

    public record Usuario(Long id, String nome, String email, String cpf) {
    }

    public void print(List<Usuario> lista, boolean mascarar_CPF, boolean alignRight, String theme) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("ERRO: Lista de usuários vazia ou nula.");
            return;
        }

        var tabela = formatar_tabela(lista, mascarar_CPF, borderCharFor(theme));
        if (alignRight) {
            for (var line : tabela.split("\n")) {
                System.out.println("                    " + line);
            }
        } else {
            System.out.print(tabela);
        }
    }

    private String formatar_tabela(List<Usuario> usuarios, boolean mascarar_CPF, String borderChar) {
        var border = borderChar.repeat(TAMANHO_TABELA);
        var table = new StringBuilder()
                .append(border).append("\n")
                .append(String.format(FORMATO_TABELA, "ID", "NOME", "EMAIL", "CPF"))
                .append(border).append("\n");

        for (var usuario : usuarios) {
            if (usuario != null) {
                table.append(formatUsuario(usuario, mascarar_CPF));
            }
        }

        return table.append(border).append("\n").toString();
    }

    private String formatUsuario(Usuario usuario, boolean maskCpf) {
        var id = usuario.id() == null ? "0" : usuario.id().toString();
        return String.format(FORMATO_TABELA, id, formatNome(usuario.nome()), formatEmail(usuario.email()),
                formatCpf(usuario.cpf(), maskCpf));
    }

    private String formatNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            return "NÃO INFORMADO";
        }
        return nome.length() > 20 ? nome.substring(0, 17) + "..." : nome;
    }

    private String formatEmail(String email) {
        return email == null || !email.contains("@") ? "INVALIDO" : email;
    }

    private String formatCpf(String cpf, boolean maskCpf) {
        if (cpf == null || cpf.length() != 11) {
            return "CPF INVALIDO";
        }
        var suffix = cpf.substring(3, 6) + "." + cpf.substring(6, 9);
        return maskCpf ? "***." + suffix + "-**"
                : cpf.substring(0, 3) + "." + suffix + "-" + cpf.substring(9, 11);
    }

    private String borderCharFor(String theme) {
        if (Objects.equals(theme, "DARK")) {
            return "#";
        }
        if (Objects.equals(theme, "LIGHT")) {
            return "-";
        }
        return "=";
    }

    public static void main(String[] args) {
        var usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario(101L, "Carlos Eduardo de Souza", "carlos.souza@email.com", "12345678901"));
        usuarios.add(new Usuario(102L, "Ana Maria Silva", "ana.silva@email.com", "98765432100"));
        usuarios.add(new Usuario(103L, "João Pedro de Alcântara Bragança", "joao.pedro@email.com", "45678912345"));
        usuarios.add(new Usuario(104L, "Mariana Costa", "marianacosta.email.com", "11122233344"));
        usuarios.add(new Usuario(105L, "Lucas Mendes", "lucas@email.com", "12345"));
        usuarios.add(new Usuario(106L, "", "beatriz@email.com", "55566677788"));

        var printer = new UsuarioPrinter();
        printer.print(usuarios, true, true, "LIGHT");
    }
}
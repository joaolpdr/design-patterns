package br.pucpr.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuarioPrinter {
  private static final int TAMANHO_TABELA = 74;
  private static final String FORMATO_TABELA = "| %-5s | %-20s | %-22s | %-14s |%n";

  public record Usuario(Long id, String nome, String email, String cpf) {}

  public void print(
      List<Usuario> usuarios, boolean mascararCpf, boolean alinharDireita, String tema) {
    if (usuarios == null || usuarios.isEmpty()) {
      System.out.println("ERRO: Lista de usuários vazia ou nula.");
      return;
    }

    var tabela = formatarTabela(usuarios, mascararCpf, caractereBordaPara(tema));
    if (alinharDireita) {
      for (var linha : tabela.split("\n")) {
        System.out.println("                    " + linha);
      }
    } else {
      System.out.print(tabela);
    }
  }

  private String formatarTabela(
      List<Usuario> usuarios, boolean mascararCpf, String caractereBorda) {
    var borda = caractereBorda.repeat(TAMANHO_TABELA);
    var tabela =
        new StringBuilder()
            .append(borda)
            .append("\n")
            .append(String.format(FORMATO_TABELA, "ID", "NOME", "EMAIL", "CPF"))
            .append(borda)
            .append("\n");

    for (var usuario : usuarios) {
      if (usuario != null) {
        tabela.append(formatarUsuario(usuario, mascararCpf));
      }
    }

    return tabela.append(borda).append("\n").toString();
  }

  private String formatarUsuario(Usuario usuario, boolean mascararCpf) {
    var id = usuario.id() == null ? "0" : usuario.id().toString();
    return String.format(
        FORMATO_TABELA,
        id,
        formatarNome(usuario.nome()),
        formatarEmail(usuario.email()),
        formatarCpf(usuario.cpf(), mascararCpf));
  }

  private String formatarNome(String nome) {
    if (nome == null || nome.isEmpty()) {
      return "NÃO INFORMADO";
    }
    return nome.length() > 20 ? nome.substring(0, 17) + "..." : nome;
  }

  private String formatarEmail(String email) {
    return email == null || !email.contains("@") ? "INVALIDO" : email;
  }

  private String formatarCpf(String cpf, boolean mascararCpf) {
    if (cpf == null || cpf.length() != 11) {
      return "CPF INVALIDO";
    }
    var sufixo = cpf.substring(3, 6) + "." + cpf.substring(6, 9);
    return mascararCpf
        ? "***." + sufixo + "-**"
        : cpf.substring(0, 3) + "." + sufixo + "-" + cpf.substring(9, 11);
  }

  private String caractereBordaPara(String tema) {
    if (Objects.equals(tema, "ESCURO")) {
      return "#";
    }
    if (Objects.equals(tema, "CLARO")) {
      return "-";
    }
    return "=";
  }

  public static void main(String[] args) {
    var usuarios = new ArrayList<Usuario>();
    usuarios.add(
        new Usuario(101L, "Carlos Eduardo de Souza", "carlos.souza@email.com", "12345678901"));
    usuarios.add(new Usuario(102L, "Ana Maria Silva", "ana.silva@email.com", "98765432100"));
    usuarios.add(
        new Usuario(
            103L, "João Pedro de Alcântara Bragança", "joao.pedro@email.com", "45678912345"));
    usuarios.add(new Usuario(104L, "Mariana Costa", "marianacosta.email.com", "11122233344"));
    usuarios.add(new Usuario(105L, "Lucas Mendes", "lucas@email.com", "12345"));
    usuarios.add(new Usuario(106L, "", "beatriz@email.com", "55566677788"));

    var impressora = new UsuarioPrinter();
    impressora.print(usuarios, true, true, "CLARO");
  }
}

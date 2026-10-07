package br.pucpr.usuario;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.Test;

class UsuarioPrinterTest {
  @Test
  void printDoesNotThrow() {
    var usuarios = List.of(new UsuarioPrinter.Usuario(1L, "Ana", "ana@email.com", "12345678901"));
    assertDoesNotThrow(() -> new UsuarioPrinter().print(usuarios, true, false, "ESCURO"));
  }
}

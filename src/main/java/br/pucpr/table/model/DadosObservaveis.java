package br.pucpr.table.model;

import java.util.ArrayList;
import java.util.List;

public abstract class DadosObservaveis implements TableData {
  private final List<ObservadorDados> observadores = new ArrayList<>();

  @Override
  public void adicionarObservador(ObservadorDados observador) {
    if (observador == null) {
      throw new IllegalArgumentException("O observador não pode ser nulo");
    }
    observadores.add(observador);
  }

  @Override
  public void removerObservador(ObservadorDados observador) {
    observadores.remove(observador);
  }

  protected void notificarObservadores() {
    for (var observador : List.copyOf(observadores)) {
      observador.dadosAlterados();
    }
  }
}

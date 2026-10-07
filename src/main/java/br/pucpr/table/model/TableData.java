package br.pucpr.table.model;

public interface TableData {
  int rowCount();

  int colCount();

  String header(int col);

  String get(int row, int col);

  void adicionarObservador(ObservadorDados observador);

  void removerObservador(ObservadorDados observador);
}

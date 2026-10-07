package br.pucpr.table.model;

public class TabelaPaginada extends DadosObservaveis {
  private final TableData dados;
  private final int tamanhoPagina;
  private int pagina;

  public TabelaPaginada(TableData dados, int tamanhoPagina) {
    if (dados == null) {
      throw new IllegalArgumentException("Os dados não podem ser nulos");
    }
    if (tamanhoPagina <= 0) {
      throw new IllegalArgumentException("O tamanho da página deve ser positivo");
    }
    this.dados = dados;
    this.tamanhoPagina = tamanhoPagina;
    this.pagina = 0;
    dados.adicionarObservador(this::dadosOriginaisAlterados);
  }

  private void dadosOriginaisAlterados() {
    if (pagina >= totalPaginas()) {
      pagina = totalPaginas() - 1;
    }
    notificarObservadores();
  }

  public int getPagina() {
    return pagina;
  }

  public void setPagina(int pagina) {
    if (pagina < 0 || pagina >= totalPaginas()) {
      throw new IllegalArgumentException("Página inválida: " + pagina);
    }
    if (this.pagina != pagina) {
      this.pagina = pagina;
      notificarObservadores();
    }
  }

  public int totalPaginas() {
    return Math.max(1, (dados.rowCount() + tamanhoPagina - 1) / tamanhoPagina);
  }

  private int deslocamento() {
    return pagina * tamanhoPagina;
  }

  @Override
  public int rowCount() {
    return Math.min(tamanhoPagina, dados.rowCount() - deslocamento());
  }

  @Override
  public int colCount() {
    return dados.colCount();
  }

  @Override
  public String header(int col) {
    return dados.header(col);
  }

  @Override
  public String get(int row, int col) {
    return dados.get(deslocamento() + row, col);
  }
}

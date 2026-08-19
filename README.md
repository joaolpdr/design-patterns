# Exemplo Prático: Análise de Código e Qualidade de Software

Este repositório contém um exemplo didático em Java desenvolvido para a disciplina de **Design Patterns**.

O objetivo deste projeto é servir de base para o estudo prático de manutenibilidade, legibilidade e boas práticas de arquitetura e código.

---

## 🎯 Objetivo da Atividade

A classe `UsuarioPrinter` implementa uma funcionalidade completa e operacional: ela recebe uma lista de usuários e renderiza uma tabela formatada no console.

Apesar de o programa funcionar perfeitamente do ponto de vista funcional, a estrutura interna do código contém diversas fragilidades de design que impactam diretamente a sua evolução, legibilidade e facilidade de testes.

Sua missão como aluno é analisar o código-fonte, identificar os pontos de melhoria estruturais e aplicar as refatorações necessárias para elevar a qualidade do projeto sem alterar a saída gerada no console.

---

## 📋 Proposta de Exercício

1. **Análise Crítica:** Esquadrinhe o método `print` e identifique as violações de boas práticas de programação e orientação a objetos.
2. **Mapeamento:** Liste quais sintomas de código deteriorado (*Code Smells*) estão presentes e quais princípios de design foram violados.
3. **Refatoração:** Aplique técnicas de refatoração para transformar o código em uma solução limpa, bem estruturada e fácil de manter.

## 🔎 Análise e Refatoração

O método `print` original concentrava responsabilidades diferentes: validação da entrada, escolha do tema, formatação de nome, e-mail e CPF, montagem da tabela e escrita no console. Essa concentração caracterizava os seguintes *code smells*:

* **Método longo** e **muitas responsabilidades**, dificultando leitura, manutenção e testes isolados.
* **Condicionais complexas**, repetidas para decidir como cada campo deveria ser formatado.
* **Acoplamento a detalhes**, como a implementação `ArrayList` e a escrita direta durante a montagem da tabela.
* **Código incompatível com o requisito Java 17**, pois `StringBuilder` não possui o método `repeat`.
* **Efeito colateral dentro do laço**, que imprimia a tabela parcialmente a cada usuário em vez de imprimir o resultado completo uma vez.

A solução aplica **Extract Method**, separa a montagem pura da tabela da impressão e centraliza constantes de formatação. Também usa `List<Usuario>` na API, preserva a validação de entradas nulas e mantém as regras de apresentação existentes: temas, alinhamento, máscara de CPF e valores inválidos.

Os principais princípios envolvidos são **Responsabilidade Única (SRP)**, **programar para uma abstração** e **separar lógica de apresentação de efeitos colaterais**, o que torna a formatação testável sem depender do console.

---

## 🛠️ Requisitos para Execução

* **Linguagem:** Java 17 ou superior

### Como Executar

```bash
javac UsuarioPrinter.java
java UsuarioPrinter
```

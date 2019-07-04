ALTER TABLE pagamento
  ADD  codigo_orcamento BIGINT(20) REFERENCES orcamento(codigo);

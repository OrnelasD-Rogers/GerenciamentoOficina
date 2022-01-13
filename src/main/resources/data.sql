set foreign_key_checks = 0;

delete from aparelho;
delete from cliente;
delete from contato;
delete from cor;
delete from marca;
delete from modelo;
delete from pagamento;
delete from tecnico;
delete from telefone;
delete from tipo;

set foreign_key_checks = 1;

ALTER TABLE aparelho AUTO_INCREMENT = 1;
ALTER TABLE cliente AUTO_INCREMENT = 1;
ALTER TABLE contato AUTO_INCREMENT = 1;
ALTER TABLE cor AUTO_INCREMENT = 1;
ALTER TABLE marca AUTO_INCREMENT = 1;
ALTER TABLE modelo AUTO_INCREMENT = 1;
ALTER TABLE pagamento AUTO_INCREMENT = 1;
ALTER TABLE tecnico AUTO_INCREMENT = 1;
ALTER TABLE telefone AUTO_INCREMENT = 1;
ALTER TABLE tipo AUTO_INCREMENT = 1;

insert into cliente (idCliente, cpf, data_cadastro, nome, sexo) values (null, '02587459830', '2016-12-01 12:32:00', 'Vinicius', 'MASCULINO');
insert into cliente (idCliente, cpf, data_cadastro, nome, sexo) values (null, '55887412547', '2018-12-03 14:30:00', 'Luiz', 'MASCULINO');

insert into telefone(idTelefone, id_cliente, tipo_numero, numero, whats) values (null, 1, 'CELULAR', '45988774411', 1);
insert into telefone(idTelefone, id_cliente, tipo_numero, numero, whats) values (null, 1, 'TELEFONE', '4430552144', 0);
insert into telefone(idTelefone, id_cliente, tipo_numero, numero, whats) values (null, 2, 'CELULAR', '44998556332', 1);


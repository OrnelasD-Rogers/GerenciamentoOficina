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
delete from aparelho_cor;

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

insert into tipo(idTipo, tipo_aparelho) values (null, 'Lavadora');
insert into tipo(idTipo, tipo_aparelho) values (null, 'Ferro a Vapor');

insert into marca(idMarca, marca_aparelho) values (null, 'Electrolux');
insert into marca(idMarca, marca_aparelho) values (null, 'Arno');

insert into modelo(idModelo, modelo_aparelho) values (null, 'XTSSSE');
insert into modelo(idModelo, modelo_aparelho) values (null, 'ABCDE21');

insert into cor(idCor, cor) values(null, 'Preto');
insert into cor(idCor, cor) values(null, 'Azul');
insert into cor(idCor, cor) values(null, 'Branco');
insert into cor(idCor, cor) values(null, 'Amarelo');

insert into aparelho(idAparelho, id_cliente, id_tipo, id_marca, id_modelo, situacao_aparelho, data_entrada, data_saida, data_alteracao, problema, observacao, urgencia, revisao, mao_de_obra, orcamento)
values(null, 1, 1, 1, 1, 'NFV', '2022-01-01 14:32:00', null, null, 'Não funciona', 'Semi novo', 0, 0, null, null);
insert into aparelho_cor(id_aparelho, id_cor) values (1,1);
insert into aparelho_cor(id_aparelho, id_cor) values (1,3);
insert into aparelho(idAparelho, id_cliente, id_tipo, id_marca, id_modelo, situacao_aparelho, data_entrada, data_saida, data_alteracao, problema, observacao, urgencia, revisao, mao_de_obra, orcamento)
values(null, 2, 2, 2, 2, 'NFV', '2022-02-01 14:32:00', null, null, 'Não aquece', 'Sujo', 0, 0, null, null);
insert into aparelho_cor(id_aparelho, id_cor) values (2,2);



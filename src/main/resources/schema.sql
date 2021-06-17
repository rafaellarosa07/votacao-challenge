
-- -----------------------------------------------------
-- Table PAUTA
-- -----------------------------------------------------


CREATE TABLE  PAUTA (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(250) NOT NULL,
  data_criacao DATETIME NOT NULL
);


-- -----------------------------------------------------
-- Table ITEM_PAUTA
-- -----------------------------------------------------

CREATE TABLE  ITEM_PAUTA (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(90) NOT NULL,
  fk_pauta INT NOT NULL,
  foreign key (fk_pauta) references PAUTA(ID));



-- -----------------------------------------------------
-- Table SESSAO
-- -----------------------------------------------------
CREATE TABLE SESSAO (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  data_inicio DATETIME NOT NULL,
  data_fim DATETIME NOT NULL,
  fk_pauta INT NOT NULL,
  foreign key (fk_pauta) references PAUTA(ID));



-- -----------------------------------------------------
-- Table ASSOCIADO
-- -----------------------------------------------------
CREATE TABLE ASSOCIADO (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(90) NOT NULL,
  cpf VARCHAR(11) NOT NULL
  );



-- -----------------------------------------------------
-- Table ASSOCIADO_SESSAO
-- -----------------------------------------------------
CREATE TABLE ASSOCIADO_SESSAO (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ASSOCIADO_id INT NOT NULL,
  SESSAO_id INT NOT NULL,
  voto CHAR(1) NOT NULL,
  foreign key (ASSOCIADO_id) references ASSOCIADO(ID),
  foreign key (SESSAO_id) references SESSAO(ID));



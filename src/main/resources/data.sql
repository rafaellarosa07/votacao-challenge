
-- -----------------------------------------------------
-- Table `PAUTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PAUTA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(250) NOT NULL,
  `data_criacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ITEM_PAUTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ITEM_PAUTA` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(90) NOT NULL,
  `fk_pauta` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ITEM_PAUTA_PAUTA_idx` (`fk_pauta` ASC) VISIBLE,
  CONSTRAINT `fk_ITEM_PAUTA_PAUTA`
    FOREIGN KEY (`fk_pauta`)
    REFERENCES `PAUTA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)



-- -----------------------------------------------------
-- Table `SESSAO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SESSAO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_inicio` DATETIME NOT NULL,
  `data_fim` DATETIME NOT NULL,
  `fk_pauta` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_SESSAO_PAUTA1_idx` (`fk_pauta` ASC) VISIBLE,
  CONSTRAINT `fk_SESSAO_PAUTA1`
    FOREIGN KEY (`fk_pauta`)
    REFERENCES `PAUTA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)



-- -----------------------------------------------------
-- Table `ASSOCIADO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASSOCIADO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(90) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`id`))



-- -----------------------------------------------------
-- Table `ASSOCIADO_SESSAO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASSOCIADO_SESSAO` (
  `ASSOCIADO_id` INT NOT NULL,
  `SESSAO_id` INT NOT NULL,
  `voto` CHAR(1) NOT NULL,
  PRIMARY KEY (`ASSOCIADO_id`, `SESSAO_id`),
  INDEX `fk_ASSOCIADO_SESSAO_SESSAO1_idx` (`SESSAO_id` ASC) VISIBLE,
  INDEX `fk_ASSOCIADO_SESSAO_ASSOCIADO1_idx` (`ASSOCIADO_id` ASC) VISIBLE,
  CONSTRAINT `fk_ASSOCIADO_SESSAO_ASSOCIADO1`
    FOREIGN KEY (`ASSOCIADO_id`)
    REFERENCES `ASSOCIADO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ASSOCIADO_SESSAO_SESSAO1`
    FOREIGN KEY (`SESSAO_id`)
    REFERENCES `SESSAO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)



SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';



DROP SCHEMA IF EXISTS `testing_system` ;

CREATE SCHEMA IF NOT EXISTS `testing_system` DEFAULT CHARACTER SET cp1251 ;

USE `testing_system` ;



-- -----------------------------------------------------

-- Table `testing_system`.`subjects`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `testing_system`.`subjects` ;



CREATE  TABLE IF NOT EXISTS `testing_system`.`subjects` (

  `idsubjects` INT NOT NULL AUTO_INCREMENT ,

  `name` VARCHAR(50) NOT NULL ,

  `description` TEXT NULL DEFAULT NULL ,

  PRIMARY KEY (`idsubjects`) )

ENGINE = InnoDB

AUTO_INCREMENT = 2

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `testing_system`.`users`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `testing_system`.`users` ;



CREATE  TABLE IF NOT EXISTS `testing_system`.`users` (

  `idusers` INT NOT NULL AUTO_INCREMENT ,

  `type` VARCHAR(45) NOT NULL ,

  `login` VARCHAR(45) NOT NULL ,

  `password` VARCHAR(45) NOT NULL ,

  `name` VARCHAR(45) NOT NULL ,

  `surname` VARCHAR(45) NOT NULL ,

  PRIMARY KEY (`idusers`) ,

  UNIQUE INDEX `login_UNIQUE` (`login` ASC) )

ENGINE = InnoDB

AUTO_INCREMENT = 2

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `testing_system`.`tests`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `testing_system`.`tests` ;



CREATE  TABLE IF NOT EXISTS `testing_system`.`tests` (

  `idtests` INT NOT NULL AUTO_INCREMENT ,

  `users_idusers` INT NOT NULL ,

  `subjects_idsubjects` INT NOT NULL ,

  `name` VARCHAR(50) NOT NULL ,

  `enable` TINYINT(1)  NOT NULL ,

  PRIMARY KEY (`idtests`) ,

  UNIQUE INDEX `idtests_UNIQUE` (`idtests` ASC) ,

  INDEX `fk_tests_subjects` (`subjects_idsubjects` ASC) ,

  INDEX `fk_tests_users` (`users_idusers` ASC) ,

  CONSTRAINT `fk_tests_subjects`

    FOREIGN KEY (`subjects_idsubjects` )

    REFERENCES `testing_system`.`subjects` (`idsubjects` )

    ON DELETE CASCADE

    ON UPDATE NO ACTION,

  CONSTRAINT `fk_tests_users`

    FOREIGN KEY (`users_idusers` )

    REFERENCES `testing_system`.`users` (`idusers` )

    ON DELETE CASCADE

    ON UPDATE NO ACTION)

ENGINE = InnoDB

AUTO_INCREMENT = 5

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `testing_system`.`questions`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `testing_system`.`questions` ;



CREATE  TABLE IF NOT EXISTS `testing_system`.`questions` (

  `idquestions` INT(11) NOT NULL AUTO_INCREMENT ,

  `tests_idtests` INT(11) NOT NULL ,

  `text` TEXT NOT NULL ,

  PRIMARY KEY (`idquestions`) ,

  INDEX `fk_questions_tests1` (`tests_idtests` ASC) ,

  CONSTRAINT `fk_questions_tests1`

    FOREIGN KEY (`tests_idtests` )

    REFERENCES `testing_system`.`tests` (`idtests` )

    ON DELETE CASCADE

    ON UPDATE NO ACTION)

ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `testing_system`.`answers`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `testing_system`.`answers` ;



CREATE  TABLE IF NOT EXISTS `testing_system`.`answers` (

  `idanswers` INT NOT NULL AUTO_INCREMENT ,

  `questions_idquestions` INT NOT NULL ,

  `answer_text` TEXT NOT NULL ,

  `correct` TINYINT(1)  NOT NULL ,

  PRIMARY KEY (`idanswers`) ,

  INDEX `fk_answers_questions1` (`questions_idquestions` ASC) ,

  CONSTRAINT `fk_answers_questions1`

    FOREIGN KEY (`questions_idquestions` )

    REFERENCES `testing_system`.`questions` (`idquestions` )

    ON DELETE CASCADE

    ON UPDATE NO ACTION)

ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8;







SET SQL_MODE=@OLD_SQL_MODE;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



-- -----------------------------------------------------

-- Data for table `testing_system`.`subjects`

-- -----------------------------------------------------

START TRANSACTION;

USE `testing_system`;

INSERT INTO `testing_system`.`subjects` (`idsubjects`, `name`, `description`) VALUES (1, 'Programming', NULL);

INSERT INTO `testing_system`.`subjects` (`idsubjects`, `name`, `description`) VALUES (2, 'History', NULL);



COMMIT;



-- -----------------------------------------------------

-- Data for table `testing_system`.`users`

-- -----------------------------------------------------

START TRANSACTION;

USE `testing_system`;

INSERT INTO `testing_system`.`users` (`idusers`, `type`, `login`, `password`, `name`, `surname`) VALUES (1, 'TUTOR', 'tutor', 'root', 'Тьютор', 'Тьюторович');

INSERT INTO `testing_system`.`users` (`idusers`, `type`, `login`, `password`, `name`, `surname`) VALUES (2, 'STUDENT', 'student', 'root', 'Студент', 'Студентович');

INSERT INTO `testing_system`.`users` (`idusers`, `type`, `login`, `password`, `name`, `surname`) VALUES (3, 'TUTOR', 'tutor2', 'root', 'Тест', 'Тест');



COMMIT;



-- -----------------------------------------------------

-- Data for table `testing_system`.`tests`

-- -----------------------------------------------------

START TRANSACTION;

USE `testing_system`;

INSERT INTO `testing_system`.`tests` (`idtests`, `users_idusers`, `subjects_idsubjects`, `name`, `enable`) VALUES (1, 1, 1, 'Java', 1);

INSERT INTO `testing_system`.`tests` (`idtests`, `users_idusers`, `subjects_idsubjects`, `name`, `enable`) VALUES (2, 1, 1, 'C#', 1);

INSERT INTO `testing_system`.`tests` (`idtests`, `users_idusers`, `subjects_idsubjects`, `name`, `enable`) VALUES (3, 1, 1, 'SQL', 0);



COMMIT;



-- -----------------------------------------------------

-- Data for table `testing_system`.`questions`

-- -----------------------------------------------------

START TRANSACTION;

USE `testing_system`;

INSERT INTO `testing_system`.`questions` (`idquestions`, `tests_idtests`, `text`) VALUES (1, 1, 'Дата офіційного випуску Java ?');

INSERT INTO `testing_system`.`questions` (`idquestions`, `tests_idtests`, `text`) VALUES (2, 1, 'Програми написані на Java транслюються в ?');



COMMIT;



-- -----------------------------------------------------

-- Data for table `testing_system`.`answers`

-- -----------------------------------------------------

START TRANSACTION;

USE `testing_system`;

INSERT INTO `testing_system`.`answers` (`idanswers`, `questions_idquestions`, `answer_text`, `correct`) VALUES (1, 1, '23 травня 1995', 1);

INSERT INTO `testing_system`.`answers` (`idanswers`, `questions_idquestions`, `answer_text`, `correct`) VALUES (2, 1, '1 вересня 1994', 0);

INSERT INTO `testing_system`.`answers` (`idanswers`, `questions_idquestions`, `answer_text`, `correct`) VALUES (3, 1, '5 квітня 2001', 0);

INSERT INTO `testing_system`.`answers` (`idanswers`, `questions_idquestions`, `answer_text`, `correct`) VALUES (4, 2, 'Машинні коди', 0);

INSERT INTO `testing_system`.`answers` (`idanswers`, `questions_idquestions`, `answer_text`, `correct`) VALUES (5, 2, 'Байт-код', 1);

INSERT INTO `testing_system`.`answers` (`idanswers`, `questions_idquestions`, `answer_text`, `correct`) VALUES (6, 2, 'Программу написанну на С++', 0);



COMMIT;
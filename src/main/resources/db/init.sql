CREATE DATABASE jps;

CREATE USER 'jps'@'localhost'
  IDENTIFIED BY 'jps';

GRANT ALL PRIVILEGES ON jps.* TO 'jps'@'localhost';

CREATE TABLE jps.participants (
  id            INT          NOT NULL AUTO_INCREMENT,
  first_name    VARCHAR(256) NULL,
  last_name     VARCHAR(256) NULL,
  email         VARCHAR(256) NULL,
  ticket_number VARCHAR(256) NULL,
  company_name  VARCHAR(256) NULL,
  PRIMARY KEY (id)
)
  DEFAULT CHARSET = utf8;
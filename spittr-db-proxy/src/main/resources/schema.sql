DROP TABLE IF EXISTS reference ;
DROP TABLE IF EXISTS users ;

BEGIN;

CREATE TABLE users
  (
     username  VARCHAR(30) NOT NULL,
     password  VARCHAR(100) NOT NULL,
     authority  VARCHAR(50) NOT NULL,
     enabled   BOOLEAN DEFAULT TRUE,
     PRIMARY KEY (username)
  ) ;

CREATE TABLE reference
  (
     id        BIGSERIAL NOT NULL,
     email     VARCHAR(255),
     firstname VARCHAR(255),
     lastname  VARCHAR(255),
     password  VARCHAR(255),
     ts        DATE,
     username  VARCHAR(255),
     deleted   BOOLEAN DEFAULT FALSE,
     PRIMARY KEY (id)
  ) ;
COMMIT;
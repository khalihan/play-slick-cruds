# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "CAT" ("id" BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,  "name" VARCHAR NOT NULL,"color" VARCHAR NOT NULL);

# --- !Downs

drop table "CAT";


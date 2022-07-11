/*==============================================================*/
/* Nom de BD :  market                                         */
/*                                                             */
/*==============================================================*/

create table PRODUIT
(
   ID                   bigint(20) not null AUTO_INCREMENT,
   NOM_PRODUIT           varchar(100),
   primary key (ID)
);

create table CONDITIONNEMENT
(
   ID                 bigint(20) not null AUTO_INCREMENT,
   NOM_CONDITIONNEMENT   varchar(100),
   primary key (ID)
);


create table CONDITIONNEMENTPRODUIT
(  IDCP                 bigint(20) not null AUTO_INCREMENT ,
   IDP                   bigint(20) ,
   IDC                  bigint(20),
   QUANTITE             int,
   PRIX                 decimal (19,4),
   DATE_EFFECTIVE        date,
   primary key (IDCP)
);


create table CONDPRODUITSAVE
(
   IDCPS                bigint(20) not null AUTO_INCREMENT,
   IDCP                 bigint(20),
   QUANTITE             int,
   PRIX                 decimal (19,4),
   DATE_EFFECTIVE        date,
   DATE_FIN_EFFECTIVE     date,
   primary key (IDCPS)
);


alter table CONDITIONNEMENTPRODUIT add constraint FK_ASSOCIATION_1 foreign key (IDP)
      references PRODUIT (ID) on delete restrict on update restrict;

alter table CONDITIONNEMENTPRODUIT add constraint FK_ASSOCIATION_2 foreign key (IDC)
      references CONDITIONNEMENT (ID) on delete restrict on update restrict;

alter table CONDPRODUITSAVE add constraint FK_ASSOCIATION_3 foreign key (IDCP)
      references CONDITIONNEMENTPRODUIT (IDCP) on delete restrict on update restrict;

alter table CONDITIONNEMENTPRODUIT add column QUANTITEPOURBONUS int default 0;

alter table CONDITIONNEMENTPRODUIT add column QUANTITEBONIFIEE int default 0;


alter table CONDPRODUITSAVE add column QUANTITEPOURBONUS int default 0;

alter table CONDPRODUITSAVE add column QUANTITEBONIFIEE int default 0;
	  
	  
	  

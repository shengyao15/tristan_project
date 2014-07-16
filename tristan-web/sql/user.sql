-- Create table
create table T_USER
(
  ID         NUMBER(10) not null,
  AGE        NUMBER(10),
  CITY       VARCHAR2(255 CHAR),
  GENDER     VARCHAR2(1 CHAR),
  HEIGHT     FLOAT,
  INTEREST   VARCHAR2(255 CHAR),
  SPECIALITY VARCHAR2(255 CHAR),
  USERNAME   VARCHAR2(32 CHAR),
  WEIGHT     FLOAT
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_USER
  add primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );


use company;
DROP  TABLE IF EXISTS  techStaff;
DROP  TABLE IF EXISTS  technicalInjection;
DROP  TABLE IF EXISTS  equipment;
DROP  TABLE IF EXISTS  productionSite;
DROP  TABLE IF EXISTS equipment_productionSite;
DROP  TABLE IF EXISTS equipment_technicalInjection;
DROP  TABLE IF EXISTS techStaff_technicalInjection;

CREATE TABLE techStaff (
 id VARCHAR(50) PRIMARY KEY,
 snp VARCHAR(100),
 position VARCHAR(50)
 );
 

CREATE TABLE technicalInjection (
 id VARCHAR(50) PRIMARY KEY,
 dateTI DATE,
 result VARCHAR(100),
 causeOfFailure VARCHAR(100)
);

CREATE TABLE equipment (
 id VARCHAR(50) PRIMARY KEY,
 title VARCHAR(100)
 );
 


CREATE TABLE productionSite (
 id VARCHAR(50) PRIMARY KEY,
 name VARCHAR(100)
);

CREATE TABLE  equipment_productionSite(
idEquipment VARCHAR(20) Primary Key,
idProductionSite Varchar (20)
);

INSERT INTO equipment_productionSite(idEquipment, idProductionSite ) VALUES('1','1');

CREATE TABLE  equipment_technicalInjection(
idEquipment VARCHAR(20) Primary Key, 
idTechnicalInjection Varchar (20)
);

INSERT INTO equipment_technicalInjection(idEquipment, idTechnicalInjection ) VALUES('1','1');

CREATE TABLE techStaff_technicalInjection (
idTechStaff VARCHAR(20),
idTechnicalInjection  VARCHAR(20)
);

INSERT INTO techStaff_technicalInjection(idTechStaff, idTechnicalInjection) VALUES('1','1'); 


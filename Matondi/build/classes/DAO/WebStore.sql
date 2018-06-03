DROP TABLE Organisateur CASCADE;
DROP TABLE Evenement CASCADE;
DROP TABLE Jour CASCADE;
DROP TABLE Groupe CASCADE;
DROP TABLE Creneau CASCADE;
DROP TABLE Presence CASCADE;

-- -----------------------------------------------------------------------------
-- - Construction de la table des organisateurs                               ---
-- -----------------------------------------------------------------------------
CREATE TABLE Organisateur (
	idOrga				serial		    PRIMARY KEY ,
	loginOrga			text		    NOT NULL,
	password			varchar(200)	NOT NULL
);

INSERT INTO Organisateur (loginOrga, password, isAdmin) VALUES ('matondi',  '236a0317cf95dba08cb4c4444547f7dd98a74375f0d27d717ce55248f0aa3aeb', false );


-- -----------------------------------------------------------------------------
-- - Construction de la tables des evenements                    ---
-- -----------------------------------------------------------------------------
CREATE TABLE Evenement (
    idEvent         serial      PRIMARY KEY,
	nomEvent	  	text		UNIQUE NOT NULL,
	dateEvent		date		NOT NULL,
	villeEvent		text	,
	rueEvent		text	,
	numRueEvent		integer	,
	nbrDeJourEvent	integer		NOT NULL,
	idOrgaEvent		integer		REFERENCES Organisateur (idOrga)
);

-- -----------------------------------------------------------------------------
-- - Construction de la tables des jours                             ---
-- -----------------------------------------------------------------------------
CREATE TABLE Jour (
	idJour			serial		PRIMARY KEY ,
	dateJour		date		NOT NULL ,
	idEventJour		int       	NOT NULL REFERENCES Evenement(idEvent)
);

-- -----------------------------------------------------------------------------
-- - Construction de la tables des groupes                             ---
-- -----------------------------------------------------------------------------
CREATE TABLE Groupe (
    idGroupe        serial      PRIMARY KEY,
    nomGroupe       text        UNIQUE NOT NULL,
    nbrMusicien     integer     NOT NULL,
    styleGroupe     text        NOT NULL ,
    mailGroupe      text        NOT NULL ,
    telephoneGroupe text        NOT NULL
);

-- -----------------------------------------------------------------------------
-- - Construction de la tables des creneaux                             ---
-- -----------------------------------------------------------------------------
CREATE TABLE Creneau (
	idCreneau		    serial		PRIMARY KEY ,
	jourCreneau	       	integer		NOT NULL REFERENCES Jour(idJour) ,
	idGroupeCreneau		integer    	NOT NULL REFERENCES Groupe(idGroupe),
	styleMusiqueCreneau	text		NOT NULL,
	heureDebut          integer     NOT NULL,
	minuteDebut         integer     NOT NULL,
	heurefin            integer     NOT NULL,
	minuteFin           integer     NOT NULL
);

-- -----------------------------------------------------------------------------
-- - Construction de la tables des presences                             ---
-- -----------------------------------------------------------------------------
CREATE TABLE Presence (
	idPresence              serial		PRIMARY KEY ,
	jourPresence	        integer		NOT NULL REFERENCES Jour(idJour) ,
	idGroupePresence     	int         NOT NULL REFERENCES Groupe(idGroupe),
	deffraiementPresence	integer		NOT NULL
);



-- ----------------------------------------------------------------------------
-- - Creation des TRIGGERS                                                ---
-- -----------------------------------------------------------------------------

-------------------------- Trigger pour creation d'un evenement---------------------------------------

DROP FUNCTION create_day() CASCADE;

CREATE OR REPLACE FUNCTION create_day() RETURNS trigger AS $create_day$
DECLARE 
    i int := 0;
BEGIN
    LOOP
        INSERT INTO Jour (dateJour, idEventJour) VALUES (NEW.dateEvent+i, NEW.idEvent);
        i:=i+1;
        EXIT WHEN i=NEW.nbrDeJourEvent;
    END LOOP;
    RETURN null;
END;
$create_day$ LANGUAGE 'plpgsql';

DROP TRIGGER IF EXISTS create_day on public.Evenement;
CREATE TRIGGER create_day AFTER INSERT ON Evenement
FOR EACH ROW 
    EXECUTE PROCEDURE create_day();


    
    
---------------------- Trigger pour modification d'un evenement-----------------------------------------------------

CREATE OR REPLACE FUNCTION modify_day() RETURNS trigger AS $modify_day$
DECLARE 
    i int := 0;
BEGIN
    IF NEW.dateEvent != OLD.dateEvent THEN
        LOOP
            UPDATE Jour SET dateJour = NEW.dateEvent+i WHERE idEventJour=NEW.idEvent AND dateJour=OLD.dateEvent+i;
            i:=i+1;
            EXIT WHEN i=OLD.nbrDeJourEvent;
        END LOOP;
    END IF;
    
    IF NEW.nbrDeJourEvent != OLD.nbrDeJourEvent THEN
        DELETE FROM Jour WHERE eventJour=NEW.nomEvent AND dateJour > NEW.dateEvent+(NEW.nbrDeJourEvent-1);
    END IF;
    RETURN null;

END;
$modify_day$ LANGUAGE 'plpgsql';



DROP TRIGGER IF EXISTS modify_day on public.Evenement;
CREATE TRIGGER modify_day AFTER UPDATE ON Evenement
FOR EACH ROW 
    WHEN(NEW.dateEvent != OLD.dateEvent OR NEW.nbrDeJourEvent!=OLD.nbrDeJourEvent)
        EXECUTE PROCEDURE modify_day();

        
        
------------------------------ Trigger pour la suppression d'un événement -------------------------------------------------------

CREATE OR REPLACE FUNCTION del_day() RETURNS trigger AS $del_day$
DECLARE 
BEGIN
    DELETE FROM Jour WHERE eventJour=OLD.nomEvent;
    RETURN null;

END;
$del_day$ LANGUAGE 'plpgsql';



DROP TRIGGER IF EXISTS del_day on public.Evenement;
CREATE TRIGGER del_day BEFORE DELETE ON Evenement
FOR EACH ROW 
    EXECUTE PROCEDURE del_day();        






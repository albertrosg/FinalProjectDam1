--Create table user--
CREATE TABLE user (
	id int(2) AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    license_number VARCHAR(12) NOT NULL,
    user_name VARCHAR(20 NOT NULL),
    password VARCHAR(100) NOT NULL)
    ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--Create table owner--
CREATE TABLE owner (
    ownerId INT(6) AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(9) UNIQUE NOT NULL,
    email VARCHAR(50) NOT NULL,
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    phoneNumber VARCHAR(9) NOT NULL,
    address VARCHAR(50) NOT NULL)
    ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--Create table pet--
CREATE TABLE pet (
    chipNumber VARCHAR(20) PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    type VARCHAR(10) NOT NULL,
    race VARCHAR(20) NOT NULL, 
    weight DOUBLE,
    birthDate DATE NOT NULL,
    ownerId INT(6) NOT NULL,
    KEY FK_OWNER_ID (ownerId),
    CONSTRAINT ownerId FOREIGN KEY (ownerId) REFERENCES owner(ownerId))
    ENGINE=INNODB DEFAULT CHARSET=utf8;

--Create table story--
CREATE TABLE story (
    storyId INT(6) AUTO_INCREMENT PRIMARY KEY,
    chipNumber VARCHAR(20),
    KEY FK_CHIPNUMBER (chipNumber),
    CONSTRAINT chipNumber FOREIGN KEY (chipNumber) REFERENCES pet(chipNumber))
    ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--Create table entry--
CREATE TABLE entry (
    entryId INT(10) AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    comment VARCHAR(500),
    storyId INT(6) NOT NULL,
    KEY FK_STORYID (storyId),
    CONSTRAINT storyId FOREIGN KEY (storyId) REFERENCES story(storyId))
    ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


--Create table diary--
CREATE TABLE diary (
    diaryId INT(6) AUTO_INCREMENT PRIMARY KEY,
    userId int(2),
    KEY FK_USERID (userId),
    CONSTRAINT userId FOREIGN KEY (userId) REFERENCES user(id))
    ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--Create table visit--
CREATE TABLE visit (
    visitId INT(6) AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(20) NOT NULL,
    hour TIME NOT NULL,
    date DATE NOT NULL,
    diaryId INT(6),
    chipNumber VARCHAR(20),
    KEY FK_DIARYIDVISIT (diaryId),
    CONSTRAINT diaryIdVisit FOREIGN KEY (diaryId) REFERENCES diary(diaryId),
    KEY FK_CHIPNUMBERVISIT (chipNumber),
    CONSTRAINT chipNumberVisit FOREIGN KEY (chipNumber) REFERENCES pet(chipNumber))
    ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--Create table user_pet--
CREATE TABLE user_pet (
    userId int(2),
    chipNumber VARCHAR(20),    
    CONSTRAINT user_pet_pk PRIMARY KEY (userId, chipNumber))
    ENGINE=INNODB DEFAULT CHARSET=utf8;

--Create table product--
CREATE TABLE product (
    productId VARCHAR(10) PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    price DOUBLE NOT NULL,
    stock INT(4),
    criticalStock INT(2) NOT NULL)
    ENGINE=INNODB DEFAULT CHARSET=utf8;

--Create table order--
CREATE TABLE orders (
    orderId INT(4) AUTO_INCREMENT PRIMARY KEY,
    date DATE,
    amount INT(4),
    deliveryNote VARCHAR(20),
    orderNum INT(10),
    productId VARCHAR(10),
    KEY FK_PRODUCTORDER (productId),
    CONSTRAINT productIdOrder FOREIGN KEY (productId) REFERENCES product(productId))
    ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


--Create table type--
CREATE TABLE typePet(
    typeId int(6) AUTO_INCREMENT PRIMARY KEY,
    nameType VARCHAR(10))
    ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--Create table race--
CREATE TABLE racePet(
    raceId int(6) AUTO_INCREMENT PRIMARY KEY,
    nameRace VARCHAR(60),
    typeId INT(6),
    KEY FK_TYPEID (typeId),
    CONSTRAINT typeId FOREIGN KEY (typeId) REFERENCES typePet(typeId))
    ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--Insert pet type--
INSERT into typepet (nameType) VALUES ('Perro');
INSERT into typepet (nameType) VALUES ('Gato');
INSERT into typepet (nameType) VALUES ('Ave');
INSERT into typepet (nameType) VALUES ('Reptil');

--Insert dogs race--
INSERT INTO racepet (nameRace, typeId) VALUES ('Affenpinscher', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Afgano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Akita Inu', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Akita Americano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Alano Español', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('American Foxhound', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('American Pit Bull Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('American Staffordshire Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Antiguo perro de muestra Danés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Australian Silky Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Azawakh', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Barbet', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Basenji', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Basset Hound', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Basset artesiano de Normandia', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Basset azul de Gascuña', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Basset leonado de Bretaña', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Beagle', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Beagle-Harrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bedlington Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Berger de Picardie', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bichón Maltés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bichón Boloñés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bichón Frisé', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bichón Habanero', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Billy', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Black And Tan Coonhound', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bloodhound', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bobtail', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Border Collie', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Border Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Borzoi (Galgo Ruso)', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Boston Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Boyero Australiano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Boyero de Flandes', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Boyero de las Ardenas', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Braco San Germain', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Braco alemán de pelo corto', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Braco alemán de pelo duro', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Braco de Auvernia', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Braco de Weimar', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Braco del Ariège', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Braco del Bourbonnais', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Braco francés, tipo Gascuña, talla grande', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Braco francés, tipo Pirineos talla pequeña', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Braco italiano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Briard', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Brittany', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Broholmer', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Buhund Noruego', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bull Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bull Terrier Miniatura', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bulldog', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bulldog Francés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bull Mastín', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bóxer', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Cairn Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Cane Corso', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Caniche (o Poodle)', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Carlino', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Chihuahua', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Chin Japonés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Chow Chow', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Cirneco Del Etna', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Clumber Spaniel', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Cocker Americano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Cocker Spaniel', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Collie', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Collie Barbudo', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Collie Smooth', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Corgi Galés Cárdigan', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Corgi Galés Pembroke', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Cotón de Tuléar', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Curly Coated Retriever (de pelo rizado)', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Dandie Dinmont Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Deerhound Escocés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Doberman', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Dogo Argentino', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Dogo Mallorquín', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Dogo de Burdeos', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Drever', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Dálmata', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Elkhound Noruego', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Epagneul breton', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Eurasier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Faraón Hound', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Field Spaniel', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Fila Brasileiro', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Fila de San Miguel', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Fox Terrier Pelo Duro', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Fox Terrier Toy', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Fox Terrier de Pelo Liso', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Foxhound Americano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Foxhound Inglés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Galgo Español', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Galgo Italiano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Galés Terrier Galés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Gascon Saintongeois', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Glen Of Imaal Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Golden Retriever', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Gran Basset Grifón vendeano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Gran Danés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Greyhound', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Grifón Belga', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Grifón de muestra eslovaco de pelo duro', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Grifón de Bruselas', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Grifón de muestra Korthals de pelo duro', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Grifón de muestra bohemio de pelo duro', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Hamiltonstovare', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Harrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Hokkaïdo', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Hovawart', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Husky Siberiano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Irish Soft Coated Wheaten Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Jack Russell Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Jamthund', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Kai', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Karjalankarhukoira', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Keeshond', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Kerry Blue Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Kishu', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Komondor', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Korea Jinco Dog', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Kromfohrländer', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Kuvasz', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Labrador Retriever', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Lakeland Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Landseer', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Laïka Ruso-Europeo', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Laïka de Siberia oriental', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Lebrel Húngaro', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Leonberger', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Lhasa Apso', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Lundehund noruego', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Löwchen', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Malamute de Alaska', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Mastín', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Mastín Napolitano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Mastín Tibetano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Mudi', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Münsterländer grande', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Münsterländer pequeño', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Otterhound', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Papillon', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Parson Russell Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pastor Alemán', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pastor Australiano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pastor Belga', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pastor Catalán', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pastor Polaco de Tierras Bajas', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pastor de Anatolia', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pastor de Beauce', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pastor de Los Pirineos', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pastor de Shetland', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pastor yugoslavo de Charplanina', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pequeño Basset Grifón vendeano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pequeño Brabanzón', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pequeño perro holandés para la caza acuática', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pequinés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perdiguero de Burgos', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perdiguero de Drente', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perdiguero portugués', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro Crestado Chino', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro De Agua Español', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro Esquimal Americano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro Esquimal Canadiense', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro Lobo Checoslovaco', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro Pastor Croato', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro Pastor Mallorquín', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro Smous holandés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro boyero de Entlebuch Entlebucher', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de Agua Americano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de Aguas Portugués', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de Canaan', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de Castro Laboreiro', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de Groenlandia', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de Montaña Appenzell', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de Montaña Bernés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de Montaña Gran Suizo', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de Montaña de Los Pirineos', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de Montaña de la Sierra de la Estrella', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de Montaña del Atlas', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de Pastor islandés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de muestra alemán de pelo cerdoso', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de pastor bergamasco', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de pastor de Asia Central', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de pastor de Karst', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de pastor de Rusia Meridional', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de pastor del Cáucaso', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de pastor polaco de Podhale', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de pastor polaco de las Llanuras', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro de pastor portugués', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro lobo de Saarloos', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro pastor Maremmano-Abruzzese', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro sin Pelo del Perú', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Perro sin pelo Mexicano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pinscher', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pinscher Miniatura', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pinscher austriaco', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Plott Hound', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Podenco Canario', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Podenco Ibicenco', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Podenco Portugués', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pointer', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pointer Alemán de Pelo Corto', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pointer Alemán de Pelo Duro', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Poitevin', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pomerania', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Porcelaine', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pudelpointer', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Puli', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Pumi', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Rafeiro del Alentejo', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Rastreador de Hannover', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Rastreador montañés de Baviera', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Retriever de La Bahía de Chesapeake', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Retriever de Nueva Escocia', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Retriever de Pelo Liso', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Ridgeback de Rodesia', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Rottweiler', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso Artesiano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso Estirio de pelo duro', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso Halden', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso Italiano (de pelo corto)', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso Italiano (de pelo duro)', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso Schiller', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso alemán', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso austriaco negro y fuego', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso de Bosnia de pelo cerdoso', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso de Hygen', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso de Smaland', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso de Transilvania', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso de las Montañas de Montenegro', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso del Tirol', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso eslovaco', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso español', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso finlandés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso francés tricolor', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso noruego', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso polaco', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso serbio', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso suizo', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sabueso tricolor serbio', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Saluki (Galgo Persa)', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Samoyedo', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('San Bernardo', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Schapendoes neerlandés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Schipperke', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Schnauzer', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Schnauzer Gigante', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Schnauzer Miniatura', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Setter Gordon', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Setter Inglés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Setter Irlandés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Setter irlandés rojo', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Setter irlandés rojo y blanco', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Shar Pei', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Shiba', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('ShihTzu', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Shikoku', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Silky Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sloughi (galgo árabe)', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Spaniel Cavalier King Charles', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Spaniel Picardo', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Spaniel Tibetano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Spaniel continental enano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Spaniel de Agua Irlandés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Spaniel de Pont-Audemer', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Spaniel de Sussex', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Spinone Italiano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Spitz Alemán', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Spitz Finlandés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Spitz Japonés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Springer Spaniel Galés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Springer Spaniel Inglés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Spitz de Norrbotten', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Stabyhoun', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Staffordshire Bull Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Tchuvatch eslovaco', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Teckel', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Tejonero alpino', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Tejonero de Westfalia', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Terranova', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Terrier Australiano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Terrier Brasileño', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Terrier Cesky', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Terrier Escocés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Terrier Irlandés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Terrier Sealyham Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Terrier Tibetano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Terrier West Highland', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Terrier cazador alemán', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Terrier de Airedale', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Terrier de Norfolk', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Terrier de Norwich', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Terrier de Skye', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Terrier japonés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Thai Ridgeback Dog', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Tosa Inu', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Valhund Sueco', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Vizsla', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Volpino Italiano', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Wetterhound', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Wheaten Terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Whippet', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Wolfhound Irlandés', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Yorkshire terrier', 1);
INSERT INTO racepet (nameRace, typeId) VALUES ('Mestizo', 1);

--Insert cats race--
INSERT INTO racepet (nameRace, typeId) VALUES ('Abisinio', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Angora turco', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('American curl', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('American Shorthair', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('American wirehair', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Azul ruso', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Balines', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bengalí', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bobtail japonés', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bombay', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Bosque de Noruega', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('British Shorthair', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Burmese', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('California spangled', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Chartreux o Cartujo', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Cornish rex', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Devon Rex', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Exótico', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Habana', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Himalayo', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Javanés', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Korat', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Maine coon', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Manx', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Mau egipcio', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Nibelungo', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Ocicat', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Oriental de pelo corto', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Persa', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Ragdoll', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Scottish fold', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Selkirk rex', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Siamés', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Singapur', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Snowshoe', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Somalí', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Sphynx', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Tiffanie', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Tonkinés', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Van turco', 2);
INSERT INTO racepet (nameRace, typeId) VALUES ('Mestizo', 2);

--Insert birds race--
INSERT INTO racepet (nameRace, typeId) VALUES ('Agapornis', 3);
INSERT INTO racepet (nameRace, typeId) VALUES ('Cacatúa', 3);
INSERT INTO racepet (nameRace, typeId) VALUES ('Canario', 3);
INSERT INTO racepet (nameRace, typeId) VALUES ('Carolina', 3);
INSERT INTO racepet (nameRace, typeId) VALUES ('Cotorra argentina', 3);
INSERT INTO racepet (nameRace, typeId) VALUES ('Cotorra de Kramer', 3);
INSERT INTO racepet (nameRace, typeId) VALUES ('Diamante mandarín', 3);
INSERT INTO racepet (nameRace, typeId) VALUES ('Gallina', 3);
INSERT INTO racepet (nameRace, typeId) VALUES ('Gallo', 3);
INSERT INTO racepet (nameRace, typeId) VALUES ('Guacamayo', 3);
INSERT INTO racepet (nameRace, typeId) VALUES ('Loro', 3);
INSERT INTO racepet (nameRace, typeId) VALUES ('Paloma', 3);
INSERT INTO racepet (nameRace, typeId) VALUES ('Periquito', 3);
INSERT INTO racepet (nameRace, typeId) VALUES ('Tortola', 3);

--Insert reptils race--
INSERT INTO racepet (nameRace, typeId) VALUES ('Camaleón', 4);
INSERT INTO racepet (nameRace, typeId) VALUES ('Gecko', 4);
INSERT INTO racepet (nameRace, typeId) VALUES ('Iguana', 4);
INSERT INTO racepet (nameRace, typeId) VALUES ('Serpiente', 4);
INSERT INTO racepet (nameRace, typeId) VALUES ('Tortuga', 4);

--Trigger for create diary of the user--
DELIMITER //
CREATE TRIGGER createDiary
AFTER INSERT ON `user`
FOR EACH ROW
BEGIN
    INSERT INTO `diary` (`userId`) VALUES (NEW.id);
END //
DELIMITER ;

--Trigger for modify amount in product--
DELIMITER //
CREATE TRIGGER modifyAmount
AFTER INSERT ON `orders`
FOR EACH ROW
BEGIN 
    UPDATE `product` SET `stock` = `stock` + NEW.amount WHERE `productId` = NEW.productId;
END //
DELIMITER ;

--Trigger for remove orders when remove products--
DELIMITER //
CREATE TRIGGER removeOrder
BEFORE DELETE ON `product`
FOR EACH ROW
BEGIN
    DELETE FROM `orders` WHERE `productId` = OLD.productId;
END //
DELIMITER ;

--Trigger for remove diary when remove users--
DELIMITER //
CREATE TRIGGER removeDiary
BEFORE DELETE ON `user`
FOR EACH ROW
BEGIN
    DELETE FROM `diary` WHERE `userId` = OLD.id;
END //
DELIMITER ;

--Alter table visit so that the hour is unique--
ALTER TABLE visit ADD CONSTRAINT unique_hour_date UNIQUE (hour, date);
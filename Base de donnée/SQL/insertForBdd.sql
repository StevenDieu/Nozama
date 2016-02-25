INSERT INTO `nozama`.`artiste` (`id_artiste`,`name`) VALUES (1,'Rihanna');
INSERT INTO `nozama`.`artiste` (`id_artiste`,`name`) VALUES (2,'Yall');

INSERT INTO `nozama`.`product` (`id_product`,`name`,`description`,`url_picture`) VALUES (1,'Work','Le nouveau clip de Rihanna a été dévoilé ce lundi 22 février. Pour sa troisième collaboration avec le rappeur Drake, la chanteuse, a choisi de mettre l\'accent sur une musique inspirée de la dance-hall jamaïcaine. Dans une ambiance chaude, la princesse du R\'n\'B, qui a fêté ses 28 ans le 20 février, s\'illustre comme à son habitude par des pas de danse sexy et maîtrisés. Rihanna reste ainsi fidèle à ses origines caribéennes puisque l\'atmosphère du clip est directement venue des soirées barbadienne et le \"twerk\" y trouve largement sa place. Vêtue d\'une robe au couleur rouge, jaune et vert, l\'artiste surprend encore avec sa provocation réfléchie et accepté par des fans toujours plus nombreux.','Work.jpg');
INSERT INTO `nozama`.`product` (`id_product`,`name`,`description`,`url_picture`) VALUES (2,'Hundred Miles',NULL,'HundredMiles.jpg');
INSERT INTO `nozama`.`product` (`id_product`,`name`,`description`,`url_picture`) VALUES (3,'Les nouvelles aventures d\'Aladin','Une nouvelle histoire d\'Aladin à la conquête du coeur de sa belle demoiselle !','aladin.jpg');
INSERT INTO `nozama`.`product` (`id_product`,`name`,`description`,`url_picture`) VALUES (4,'Steak','Mr Oizo livre un OVNI déjanté avec Eric et Ramzy méconnaissables.','steak.jpg');

INSERT INTO `nozama`.`movie` (`id_movie`,`id_product`,`type`, `date_released`) VALUES (1,3,'Comedie', '2012-12-31');
INSERT INTO `nozama`.`movie` (`id_movie`,`id_product`,`type`, `date_released`) VALUES (2,4,'Comedie', '2012-12-31');

INSERT INTO `nozama`.`single` (`id_single`, `date_released`, `id_product`,`type`, `id_artiste`) VALUES ('1', '2012-12-31', '1','Electro', '1');
INSERT INTO `nozama`.`single` (`id_single`, `date_released`, `id_product`,`type`, `id_artiste`) VALUES ('2', '2012-12-31', '2','Electro', '2');

INSERT INTO `nozama`.`album` (`id_album`, `id_product`, `id_artiste`,`type`, `date_released`) VALUES ('1', '1', '1','Electro', '2012-12-31');
INSERT INTO `nozama`.`album` (`id_album`, `id_product`, `id_artiste`,`type`, `date_released`) VALUES ('2', '2', '2','Electro', '2012-12-31');

INSERT INTO `nozama`.`type_support_movie` (`id_type_support`,`name_support`,`price`,`id_movie`) VALUES (1,'DVD',15,1);
INSERT INTO `nozama`.`type_support_movie` (`id_type_support`,`name_support`,`price`,`id_movie`) VALUES (2,'DVD',5,2);

INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('1', 'CD', '1', '1');
INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('2', 'CD', '1', '2');
INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('3', 'VINYLE', '3', '1');
INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('4', 'VINYLE', '3', '2');

INSERT INTO `nozama`.`type_support_album` (`id_type_support`, `name_support`, `price`, `id_album`) VALUES ('1', 'CD', '10', '1');
INSERT INTO `nozama`.`type_support_album` (`id_type_support`, `name_support`, `price`, `id_album`) VALUES ('2', 'CD', '10', '2');
INSERT INTO `nozama`.`type_support_album` (`id_type_support`, `name_support`, `price`, `id_album`) VALUES ('3', 'VINYLE', '20', '1');
INSERT INTO `nozama`.`type_support_album` (`id_type_support`, `name_support`, `price`, `id_album`) VALUES ('4', 'VINYLE', '20', '2');
INSERT INTO `nozama`.`artiste` (`id_artiste`,`name`) VALUES (1,'Rihanna');
INSERT INTO `nozama`.`artiste` (`id_artiste`,`name`) VALUES (2,'Yall');
INSERT INTO `nozama`.`artiste` (`id_artiste`,`name`) VALUES (3,'Adele');

INSERT INTO `nozama`.`product` (`id_product`, `date_released`,`name_tag_date_released`,`name`,`description`,`url_picture`) VALUES (1,'2016-01-27','work-2016','Work','Le nouveau clip de Rihanna a été dévoilé ce lundi 22 février. Pour sa troisième collaboration avec le rappeur Drake, la chanteuse, a choisi de mettre l\'accent sur une musique inspirée de la dance-hall jamaïcaine. Dans une ambiance chaude, la princesse du R\'n\'B, qui a fêté ses 28 ans le 20 février, s\'illustre comme à son habitude par des pas de danse sexy et maîtrisés. Rihanna reste ainsi fidèle à ses origines caribéennes puisque l\'atmosphère du clip est directement venue des soirées barbadienne et le \"twerk\" y trouve largement sa place. Vêtue d\'une robe au couleur rouge, jaune et vert, l\'artiste surprend encore avec sa provocation réfléchie et accepté par des fans toujours plus nombreux.','Work.jpg');
INSERT INTO `nozama`.`product` (`id_product`, `date_released`,`name_tag_date_released`,`name`,`description`,`url_picture`) VALUES (2,'2015-10-20','hundred-miles-2015','Hundred Miles',NULL,'HundredMiles.jpg');
INSERT INTO `nozama`.`product` (`id_product`, `date_released`,`name_tag_date_released`,`name`,`description`,`url_picture`) VALUES (3,'2016-01-27','les-nouvelles-aventures-d-Aladin-2016','Les nouvelles aventures d\'Aladin','Une nouvelle histoire d\'Aladin à la conquête du coeur de sa belle demoiselle !','aladin.jpg');
INSERT INTO `nozama`.`product` (`id_product`, `date_released`,`name_tag_date_released`,`name`,`description`,`url_picture`) VALUES (4,'2007-06-20','steak-2007','Steak','Mr Oizo livre un OVNI déjanté avec Eric et Ramzy méconnaissables.','steak.jpg');
INSERT INTO `nozama`.`product` (`id_product`, `date_released`,`name_tag_date_released`,`name`,`description`,`url_picture`) VALUES (5,'2015-11-20','25-2007','25',null,'25.jpg');

INSERT INTO `nozama`.`product` (`id_product`, `date_released`,`name_tag_date_released`,`name`,`description`,`url_picture`) VALUES (6,'2015-11-20','hello-2007','Hello',null,'25.jpg');
INSERT INTO `nozama`.`product` (`id_product`, `date_released`,`name_tag_date_released`,`name`,`description`,`url_picture`) VALUES (7,'2015-11-20','send-my-love-2007','Send My Love (To Your New Lover)',null,'25.jpg');
INSERT INTO `nozama`.`product` (`id_product`, `date_released`,`name_tag_date_released`,`name`,`description`,`url_picture`) VALUES (8,'2015-11-20','i-miss-you-2007','I Miss You',null,'25.jpg');
INSERT INTO `nozama`.`product` (`id_product`, `date_released`,`name_tag_date_released`,`name`,`description`,`url_picture`) VALUES (9,'2015-11-20','when-we-were-young-2007','When We Were Young',null,'25.jpg');
INSERT INTO `nozama`.`product` (`id_product`, `date_released`,`name_tag_date_released`,`name`,`description`,`url_picture`) VALUES (10,'2015-11-20','remedy-2007','Remedy',null,'25.jpg');
INSERT INTO `nozama`.`product` (`id_product`, `date_released`,`name_tag_date_released`,`name`,`description`,`url_picture`) VALUES (11,'2015-11-20','water-under-the-bridge-2007','Water Under the Bridge',null,'25.jpg');
INSERT INTO `nozama`.`product` (`id_product`, `date_released`,`name_tag_date_released`,`name`,`description`,`url_picture`) VALUES (12,'2015-11-20','river-lea-2007','River Lea',null,'25.jpg');
INSERT INTO `nozama`.`product` (`id_product`, `date_released`,`name_tag_date_released`,`name`,`description`,`url_picture`) VALUES (13,'2015-11-20','love-in-the-dark-2007','Love in the Dark',null,'25.jpg');
INSERT INTO `nozama`.`product` (`id_product`, `date_released`,`name_tag_date_released`,`name`,`description`,`url_picture`) VALUES (14,'2015-11-20','million-years-ago-2007','Million Years Ago',null,'25.jpg');
INSERT INTO `nozama`.`product` (`id_product`, `date_released`,`name_tag_date_released`,`name`,`description`,`url_picture`) VALUES (15,'2015-11-20','all-i-ask-2007','All I Ask',null,'25.jpg');
INSERT INTO `nozama`.`product` (`id_product`, `date_released`,`name_tag_date_released`,`name`,`description`,`url_picture`) VALUES (16,'2015-11-20','sweetest-devotio-2007','Sweetest Devotion',null,'25.jpg');

INSERT INTO `nozama`.`movie` (`id_movie`,`id_product`,`type`) VALUES (1,3,'Comedie');
INSERT INTO `nozama`.`movie` (`id_movie`,`id_product`,`type`) VALUES (2,4,'Comedie');

INSERT INTO `nozama`.`single` (`id_single`, `id_product`,`type`, `id_artiste`) VALUES ('3', '6','PopRockInde', '3');
INSERT INTO `nozama`.`single` (`id_single`, `id_product`,`type`, `id_artiste`) VALUES ('4', '7','PopRockInde', '3');
INSERT INTO `nozama`.`single` (`id_single`, `id_product`,`type`, `id_artiste`) VALUES ('5', '8','PopRockInde', '3');
INSERT INTO `nozama`.`single` (`id_single`, `id_product`,`type`, `id_artiste`) VALUES ('6', '9','PopRockInde', '3');
INSERT INTO `nozama`.`single` (`id_single`, `id_product`,`type`, `id_artiste`) VALUES ('7', '10','PopRockInde', '3');
INSERT INTO `nozama`.`single` (`id_single`, `id_product`,`type`, `id_artiste`) VALUES ('8', '11','PopRockInde', '3');
INSERT INTO `nozama`.`single` (`id_single`, `id_product`,`type`, `id_artiste`) VALUES ('9', '12','PopRockInde', '3');
INSERT INTO `nozama`.`single` (`id_single`, `id_product`,`type`, `id_artiste`) VALUES ('10', '13','PopRockInde', '3');
INSERT INTO `nozama`.`single` (`id_single`, `id_product`,`type`, `id_artiste`) VALUES ('11', '14','PopRockInde', '3');
INSERT INTO `nozama`.`single` (`id_single`, `id_product`,`type`, `id_artiste`) VALUES ('12', '15','PopRockInde', '3');
INSERT INTO `nozama`.`single` (`id_single`, `id_product`,`type`, `id_artiste`) VALUES ('13', '16','PopRockInde', '3');


INSERT INTO `nozama`.`single` (`id_single`, `id_product`,`type`, `id_artiste`) VALUES ('1', '1','Electro', '1');
INSERT INTO `nozama`.`single` (`id_single`, `id_product`,`type`, `id_artiste`) VALUES ('2', '2','Electro', '2');

INSERT INTO `nozama`.`album` (`id_album`, `id_product`, `id_artiste`,`type`) VALUES ('1', '1', '1','Electro');
INSERT INTO `nozama`.`album` (`id_album`, `id_product`, `id_artiste`,`type`) VALUES ('2', '2', '2','Electro');
INSERT INTO `nozama`.`album` (`id_album`, `id_product`, `id_artiste`,`type`) VALUES ('3', '5', '3','PopRockInde');

INSERT INTO `nozama`.`type_support_movie` (`id_type_support`,`name_support`,`price`,`id_movie`) VALUES (1,'DVD',15,1);
INSERT INTO `nozama`.`type_support_movie` (`id_type_support`,`name_support`,`price`,`id_movie`) VALUES (2,'DVD',5,2);

INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('5', 'CD', '1.29', '3');
INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('6', 'CD', '1.29', '4');
INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('7', 'CD', '1.29', '5');
INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('8', 'CD', '1.29', '6');
INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('9', 'CD', '1.29', '7');
INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('10', 'CD', '1.29', '8');
INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('11', 'CD', '1.29', '9');
INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('12', 'CD', '1.29', '10');
INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('13', 'CD', '1.29', '11');
INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('14', 'CD', '1.29', '12');
INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('15', 'CD', '1.29', '13');


INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('1', 'CD', '1', '1');
INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('2', 'CD', '1', '2');
INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('3', 'VINYLE', '3', '1');
INSERT INTO `nozama`.`type_support_single` (`id_type_support`, `name_support`, `price`, `id_single`) VALUES ('4', 'VINYLE', '3', '2');

INSERT INTO `nozama`.`type_support_album` (`id_type_support`, `name_support`, `price`, `id_album`) VALUES ('5', 'DONWLOAD', '12.80', '5');
INSERT INTO `nozama`.`type_support_album` (`id_type_support`, `name_support`, `price`, `id_album`) VALUES ('1', 'CD', '10', '1');
INSERT INTO `nozama`.`type_support_album` (`id_type_support`, `name_support`, `price`, `id_album`) VALUES ('2', 'CD', '10', '2');
INSERT INTO `nozama`.`type_support_album` (`id_type_support`, `name_support`, `price`, `id_album`) VALUES ('3', 'VINYLE', '20', '1');
INSERT INTO `nozama`.`type_support_album` (`id_type_support`, `name_support`, `price`, `id_album`) VALUES ('4', 'VINYLE', '20', '2');

INSERT INTO `nozama`.`album_has_single` (`id_album_has_single`, `id_single`, `id_album`) VALUES ('1', '3', '3');
INSERT INTO `nozama`.`album_has_single` (`id_album_has_single`, `id_single`, `id_album`) VALUES ('2', '4', '3');
INSERT INTO `nozama`.`album_has_single` (`id_album_has_single`, `id_single`, `id_album`) VALUES ('3', '5', '3');
INSERT INTO `nozama`.`album_has_single` (`id_album_has_single`, `id_single`, `id_album`) VALUES ('4', '6', '3');
INSERT INTO `nozama`.`album_has_single` (`id_album_has_single`, `id_single`, `id_album`) VALUES ('5', '7', '3');
INSERT INTO `nozama`.`album_has_single` (`id_album_has_single`, `id_single`, `id_album`) VALUES ('6', '8', '3');
INSERT INTO `nozama`.`album_has_single` (`id_album_has_single`, `id_single`, `id_album`) VALUES ('7', '9', '3');
INSERT INTO `nozama`.`album_has_single` (`id_album_has_single`, `id_single`, `id_album`) VALUES ('8', '10', '3');
INSERT INTO `nozama`.`album_has_single` (`id_album_has_single`, `id_single`, `id_album`) VALUES ('9', '11', '3');
INSERT INTO `nozama`.`album_has_single` (`id_album_has_single`, `id_single`, `id_album`) VALUES ('10', '12', '3');
INSERT INTO `nozama`.`album_has_single` (`id_album_has_single`, `id_single`, `id_album`) VALUES ('11', '13', '3');
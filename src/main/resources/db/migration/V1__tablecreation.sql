CREATE TABLE role (
  role_id bigint NOT NULL AUTO_INCREMENT,
  role_name varchar(255) NOT NULL,
  PRIMARY KEY (role_id)
);

CREATE TABLE user (
  owner_id bigint NOT NULL AUTO_INCREMENT,
  city varchar(255) DEFAULT NULL,
  created_at datetime DEFAULT NULL,
  is_active int DEFAULT NULL,
  is_deleted int DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  phone_number bigint DEFAULT NULL,
  updated_at datetime DEFAULT NULL,
  user_name varchar(255) DEFAULT NULL,
  user_type varchar(255) DEFAULT NULL,
  PRIMARY KEY (owner_id)
);

CREATE TABLE vehicletype (
  vehicletype_id bigint NOT NULL AUTO_INCREMENT,
  created_at datetime DEFAULT NULL,
  is_active int DEFAULT NULL,
  is_deleted int DEFAULT NULL,
  updated_at datetime DEFAULT NULL,
  vehicletype_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (vehicletype_id)
);


CREATE TABLE vehicle (
  vehicle_id bigint NOT NULL AUTO_INCREMENT,
  created_at datetime DEFAULT NULL,
  is_active int DEFAULT NULL,
  is_deleted int DEFAULT NULL,
  registration_number varchar(255) DEFAULT NULL,
  updated_at datetime DEFAULT NULL,
  vehicle_name varchar(255) DEFAULT NULL,
  fk_vehicletype_id bigint NOT NULL,
  fk_user_id bigint NOT NULL,
  PRIMARY KEY (vehicle_id)
);

CREATE TABLE loaded (
  loaded_id bigint NOT NULL,
  created_at datetime DEFAULT NULL,
  is_active int DEFAULT NULL,
  is_deleted int DEFAULT NULL,
  load_name varchar(255) DEFAULT NULL,
  source_from varchar(255) DEFAULT NULL,
  to_destination varchar(255) DEFAULT NULL,
  updated_at datetime DEFAULT NULL,
  fk_vehicle_id bigint NOT NULL,
   PRIMARY KEY (loaded_id)
);


CREATE TABLE userrole (
  user_role_id bigint NOT NULL AUTO_INCREMENT,
  fk_owner_id bigint NOT NULL,
  fk_role_id bigint NOT NULL,
  PRIMARY KEY (user_role_id)
);


alter table userrole
add foreign key (fk_owner_id) references user(owner_id),
add foreign key (fk_role_id) references role(role_id);

alter table loaded
add foreign key (fk_vehicle_id) references vehicle(vehicle_id);

alter table vehicle
add foreign key (fk_vehicletype_id) references vehicletype(vehicletype_id),
add foreign key (fk_user_id) references user(owner_id);



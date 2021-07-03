-- Borrar tablas existentes
drop schema IF EXISTS starwars CASCADE;
-- Borrar procedimientos creados
DROP FUNCTION  IF EXISTS  buscarPersonaje  CASCADE;
-- crear esquema de starwars
create schema starwars
-- Crear tabla people
    CREATE TABLE starwars.people
    (
        people_id  SERIAL,
        name       varchar(60),
        height     varchar(20),
        mass       varchar(20),
        hair_color varchar(20),
        skin_color varchar(20),
        birth_year varchar(20),
        gender     varchar(20),
        homeworld  int,
        film       int [],
        species    int [],
        vehicles   int [],
        starships  int [],
        created    varchar(60),
        updated    varchar(60),
        url        varchar(60),
        PRIMARY KEY (people_id)
        );

-- Crear tabla films
CREATE TABLE starwars.films
(
    films_id      int,
    title         varchar(50),
    opening_crawl varchar(1000),
    director      varchar(50),
    producer      varchar(60),
    release_date  varchar(50),
    characters    int [],
    planets       int [],
    starships     int [],
    vehicles      int [],
    species       int [],
    created       varchar(50),
    updated       varchar(50),
    url           varchar(50),
    PRIMARY KEY (films_id)
);


-- Crear tabla planets
CREATE TABLE starwars.planets
(
    planets_id      int,
    name            varchar(50),
    rotation_period varchar(50),
    orbital_period  varchar(50),
    diameter        varchar(50),
    climate         varchar(50),
    gravity         varchar(50),
    terrain         varchar(50),
    surface_water   varchar(50),
    population      varchar(50),
    residents       int [],
    films           int [],
    created         varchar(50),
    updated         varchar(50),
    url             varchar(50),
    PRIMARY KEY (planets_id)
);

-- Crear tabla starships
CREATE TABLE starwars.starships
(
    starships_id           int,
    name                   varchar(50),
    model                  varchar(50),
    manufactures           varchar(80),
    cost_in_credits        varchar(50),
    length                 varchar(50),
    max_atmosphering_speed varchar(50),
    crew                   varchar(50),
    passengers             varchar(50),
    cargo_capacity         varchar(50),
    consumables            varchar(50),
    hyperdrive_rating      varchar(50),
    MGLT                   varchar(50),
    starships_class        varchar(50),
    pilots                 int [],
    films                  int [],
    created                varchar(50),
    updated                varchar(50),
    url                    varchar(50),
    PRIMARY KEY (starships_id)
);

-- Crear tabla species
CREATE TABLE starwars.species
(
    species_id       int,
    name             varchar(50),
    classification   varchar(50),
    designation      varchar(50),
    average_height   varchar(50),
    skin_color       varchar(50),
    hair_colors      varchar(50),
    eye_colors       varchar(50),
    average_lifespan varchar(50),
    homeworld        int,
    language         varchar(50),
    people           int [],
    film             int [],
    created          varchar(50),
    updated          varchar(50),
    url              varchar(50),
    PRIMARY KEY (species_id)
);


-- Crear tabla vehicles
CREATE TABLE starwars.vehicles
(
    vehicles_id            int,
    name                   varchar(50),
    model                  varchar(50),
    manufactures           varchar(80),
    cost_in_credits        varchar(50),
    length                 varchar(50),
    max_atmosphering_speed varchar(50),
    crew                   varchar(50),
    passengers             varchar(50),
    cargo_capacity         varchar(50),
    consumables            varchar(50),
    vehicles_class         varchar(50),
    pilots                 int [],
    films                  int [],
    created                varchar(50),
    updated                varchar(50),
    url                    varchar(50),
    PRIMARY KEY (vehicles_id)
);

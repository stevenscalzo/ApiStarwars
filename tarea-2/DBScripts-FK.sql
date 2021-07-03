
-- Alterar tablas para agregar FK

ALTER TABLE starwars.people
 ADD CONSTRAINT fk_planets FOREIGN KEY (homeworld) REFERENCES starwars.planets(planets_id),
 ADD CONSTRAINT fk_films FOREIGN KEY (film) REFERENCES starwars.films(films_id),
 ADD CONSTRAINT fk_species FOREIGN KEY (species) REFERENCES starwars.species(species_id),
 ADD CONSTRAINT fk_vehicles FOREIGN KEY (vehicles) REFERENCES starwars.vehicles(vehicles_id),
 ADD CONSTRAINT fk_starships FOREIGN KEY (starships) REFERENCES starwars.starships(starships_id);

ALTER TABLE starwars.films
 ADD CONSTRAINT fk_people FOREIGN KEY (characters) REFERENCES starwars.people(people_id),
 ADD CONSTRAINT fk_planets FOREIGN KEY (planets) REFERENCES starwars.planets(planets_id),
 ADD CONSTRAINT fk_starships FOREIGN KEY (starships) REFERENCES starwars.starships(starships_id),
 ADD CONSTRAINT fk_vehicles FOREIGN KEY (vehicles) REFERENCES starwars.vehicles(vehicles_id),
 ADD CONSTRAINT fk_species FOREIGN KEY (species) REFERENCES starwars.species(species_id);

ALTER TABLE starwars.planets
 ADD CONSTRAINT fk_people FOREIGN KEY (residents) REFERENCES starwars.people(people_id),
 ADD CONSTRAINT fk_films FOREIGN KEY (films) REFERENCES starwars.films(films_id);

ALTER TABLE starwars.starships
 ADD CONSTRAINT fk_people FOREIGN KEY (pilots) REFERENCES starwars.people(people_id),
 ADD CONSTRAINT fk_films FOREIGN KEY (films) REFERENCES starwars.films(films_id);

ALTER TABLE starwars.vehicles
 ADD CONSTRAINT fk_people FOREIGN KEY (pilots) REFERENCES starwars.people(people_id),
 ADD CONSTRAINT fk_films FOREIGN KEY (films) REFERENCES starwars.films(films_id);

ALTER TABLE starwars.species
 ADD CONSTRAINT fk_planets FOREIGN KEY (homeworld) REFERENCES starwars.planets(planets_id),
 ADD CONSTRAINT fk_people FOREIGN KEY (people) REFERENCES starwars.people(people_id),
 ADD CONSTRAINT fk_films FOREIGN KEY (film) REFERENCES starwars.films(films_id);


import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {FilmsService} from '../../services/films.service';
import {Subscription} from 'rxjs';
import {SearchData} from '../../models/SearchData';
import {Film} from '../../models/Film';
import {Respuesta} from '../../models/Respuesta';
import {People} from '../../models/People';
import {PeoplesService} from '../../services/peoples.service';
import {Planets} from '../../models/Planet';
import {PlanetsService} from '../../services/planets.service';

@Component({
  selector: 'app-busqueda',
  templateUrl: './busqueda.component.html',
  styleUrls: ['./busqueda.component.css']
})
export class BusquedaComponent implements OnInit {

  starWarsForm!: FormGroup;
  public films: Film[] = [];
  public peoples: People[] = [];
  public planets: Planets[] = [];
  public respuesta: Respuesta = new Respuesta();
  private subscriptions: Subscription[] = [];
  public submittedFilm: Film = new Film();
  public submittedPeople: People = new People();
  public submittedPlanets: Planets = new Planets();
  private submitted = false;

  constructor(private formBuilder: FormBuilder,
              private filmsService: FilmsService,
              private peopleService: PeoplesService,
              private planetsService: PlanetsService) {
  }

  ngOnInit(): void {
    this.initializeStarWarsForm();
  }


  private initializeStarWarsForm(): void {
    this.starWarsForm = this.formBuilder.group({
      table: this.formBuilder.control('', [Validators.required]),
      operation: this.formBuilder.control('', [Validators.required]),
      id: this.formBuilder.control('', [Validators.required]),
    });
  }

  public submit(): void {
    const searchData: SearchData = new SearchData();
    searchData.id = this.starWarsForm.controls.id.value;
    searchData.table = this.starWarsForm.controls.table.value;
    searchData.operation = this.starWarsForm.controls.operation.value;

    if (this.starWarsForm.valid) {
      this.cleanTables();
      this.respuesta = new Respuesta();
      if (searchData.table === 'films') {
        this.handleFilmsTable(searchData);
      }
      if (searchData.table === 'people') {
        this.handlePeopleTable(searchData);
      }
      if (searchData.table === 'planets') {
        this.handlePlanetsTable(searchData);
      }
    }
  }

  private handleFilmsTable(searchData: SearchData): void {
    switch (searchData.operation) {
      case '1': {
        this.getAllFilms(searchData);
        break;
      }
      case '2': {
        this.getFilmById(searchData);
        break;
      }
      case '3': {
        this.createFilm(searchData);
        break;
      }
      case '4': {
        this.updateFilm(searchData);
        break;
      }
      case '5': {
        this.deleteFilm(searchData);
        break;
      }
    }
  }

  private handlePeopleTable(searchData: SearchData): void {
    switch (searchData.operation) {
      case '1': {
        this.getAllPeople(searchData);
        break;
      }
      case '2': {
        this.getPeopleById(searchData);
        break;
      }
      case '3': {
        this.createPeople(searchData);
        break;
      }
      case '4': {
        this.updatePeople(searchData);
        break;
      }
      case '5': {
        this.deletePeople(searchData);
        break;
      }
    }
  }

  private handlePlanetsTable(searchData: SearchData): void {
    switch (searchData.operation) {
      case '1': {
        this.getAllPlanet(searchData);
        break;
      }
      case '2': {
        this.getPlanetById(searchData);
        break;
      }
      case '3': {
        this.createPlanet(searchData);
        break;
      }
      case '4': {
        this.updatePlanet(searchData);
        break;
      }
      case '5': {
        this.deletePlanet(searchData);
        break;
      }
    }
  }

  isIdRequired(): boolean {
    const operation = this.starWarsForm.controls.operation.value;
    return operation === '2' || operation === '4' || operation === '5';
  }

  public isFilmObjectRequired(): boolean {
    return this.isObjectRequired() && this.starWarsForm.controls.table.value === 'films';
  }

  public isPeopleObjectRequired(): boolean {
    return this.isObjectRequired() && this.starWarsForm.controls.table.value === 'people';
  }

  isPlanetObjectRequired(): boolean {
    return this.isObjectRequired() && this.starWarsForm.controls.table.value === 'planets';
  }


  private isObjectRequired(): boolean {
    const operation = this.starWarsForm.controls.operation.value;
    return (operation === '3' || operation === '4') && !this.submitted;
  }


  validateRequiredId(): void {
    if (!this.isIdRequired()) {
      this.starWarsForm.controls.id.setValue('');
      this.starWarsForm.controls.id.disable();
    } else {
      this.starWarsForm.controls.id.setValue('');
      this.starWarsForm.controls.id.enable();
    }
  }

  private getAllFilms(searchData: SearchData): void {
    this.subscriptions.push(
      this.filmsService.getAllFilms(searchData).subscribe((response) => {
        if (response instanceof Array) {
          this.films = response as Film[];
        } else {
          this.respuesta = response as Respuesta;
        }
      }));
  }

  private getFilmById(searchData: SearchData): void {
    this.subscriptions.push(
      this.filmsService.getFilmById(searchData).subscribe((response) => {
        if (response) {
          this.films.push(response as Film);
          this.respuesta = response as Respuesta;
        }
      }));
  }

  private deleteFilm(searchData: SearchData): void {
    this.subscriptions.push(
      this.filmsService.deleteFilmById(searchData).subscribe((response) => {
        if (response) {
          this.respuesta = response as Respuesta;
        }
      }));
  }

  private createFilm(searchData: SearchData): void {
    this.submitted = true;
    this.subscriptions.push(
      this.filmsService.createFilm(searchData, this.submittedFilm).subscribe((response) => {
        if (response) {
          this.films.push(response.film as Film);
          this.respuesta = response as Respuesta;
          this.submittedFilm = new Film();
          this.submitted = false;
        }
      }));
  }

  private updateFilm(searchData: SearchData): void {
    this.submitted = true;
    this.subscriptions.push(
      this.filmsService.updateFilm(searchData, this.submittedFilm).subscribe((response) => {
        if (response) {
          this.films.push(response.film as Film);
          this.respuesta = response as Respuesta;
          this.submittedFilm = new Film();
          this.submitted = false;
        }
      }));
  }

  private getAllPeople(searchData: SearchData): void {
    this.subscriptions.push(
      this.peopleService.getAllPeople(searchData).subscribe((response) => {
        if (response instanceof Array) {
          this.peoples = response as People[];
        } else {
          this.respuesta = response as Respuesta;
        }
      }));
  }

  private getPeopleById(searchData: SearchData): void {
    this.subscriptions.push(
      this.peopleService.getPeopleById(searchData).subscribe((response) => {
        if (response) {
          this.peoples.push(response as People);
          this.respuesta = response as Respuesta;
        }
      }));
  }

  private deletePeople(searchData: SearchData): void {
    this.subscriptions.push(
      this.peopleService.deletePeopleById(searchData).subscribe((response) => {
        if (response) {
          this.respuesta = response as Respuesta;
        }
      }));
  }

  private createPeople(searchData: SearchData): void {
    this.submitted = true;
    this.subscriptions.push(
      this.peopleService.createPeople(searchData, this.submittedPeople).subscribe((response) => {
        if (response) {
          this.peoples.push(response.people as People);
          this.respuesta = response as Respuesta;
          this.submittedPeople = new People();
          this.submitted = false;
        }
      }));
  }

  private updatePeople(searchData: SearchData): void {
    this.submitted = true;
    this.subscriptions.push(
      this.peopleService.updatePeople(searchData, this.submittedPeople).subscribe((response) => {
        if (response) {
          this.peoples.push(response.people as People);
          this.respuesta = response as Respuesta;
          this.submittedPeople = new People();
          this.submitted = false;
        }
      }));
  }

  private getAllPlanet(searchData: SearchData): void {
    this.subscriptions.push(
      this.planetsService.getAllPlanets(searchData).subscribe((response) => {
        if (response instanceof Array) {
          this.planets = response as Planets[];
        } else {
          this.respuesta = response as Respuesta;
        }
      }));
  }

  private getPlanetById(searchData: SearchData): void {
    this.subscriptions.push(
      this.planetsService.getPlanetsById(searchData).subscribe((response) => {
        if (response) {
          this.planets.push(response as Planets);
          this.respuesta = response as Respuesta;
        }
      }));
  }

  private deletePlanet(searchData: SearchData): void {
    this.subscriptions.push(
      this.planetsService.deletePlanetsById(searchData).subscribe((response) => {
        if (response) {
          this.respuesta = response as Respuesta;
        }
      }));
  }

  private createPlanet(searchData: SearchData): void {
    this.submitted = true;
    this.subscriptions.push(
      this.planetsService.createPlanets(searchData, this.submittedPlanets).subscribe((response) => {
        if (response) {
          this.planets.push(response.planet as Planets);
          this.respuesta = response as Respuesta;
          this.submittedPlanets = new Planets();
          this.submitted = false;
        }
      }));
  }

  private updatePlanet(searchData: SearchData): void {
    this.submitted = true;
    this.subscriptions.push(
      this.planetsService.updatePlanets(searchData, this.submittedPlanets).subscribe((response) => {
        if (response) {
          this.planets.push(response.planet as Planets);
          this.respuesta = response as Respuesta;
          this.submittedPlanets = new Planets();
          this.submitted = false;
        }
      }));
  }

  public onKeyTitle(value: string): void {
    this.submittedFilm.title = value;
  }

  public onKeyDirector(value: string): void {
    this.submittedFilm.director = value;
  }

  public onKeyProducer(value: string): void {
    this.submittedFilm.producer = value;
  }

  public onKeyReleaseDate(value: string): void {
    this.submittedFilm.releaseDate = value;
  }

  public onKeyOpeningCrawl(value: string): void {
    this.submittedFilm.openingCrawl = value;
  }

  public onKeyEpisodeId(value: string): void {
    this.submittedFilm.episodeId = value;
  }

  public onKeyPeopleName(value: string): void {
    this.submittedPeople.name = value;
  }

  public onKeyPeopleDOB(value: string): void {
    this.submittedPeople.birthYear = value;
  }

  public onKeyPeopleEyeColor(value: string): void {
    this.submittedPeople.eyeColor = value;
  }

  public onKeyPeopleGender(value: string): void {
    this.submittedPeople.gender = value;
  }

  public onKeyPeopleHairColor(value: string): void {
    this.submittedPeople.hairColor = value;
  }

  public onKeyPeopleHeight(value: string): void {
    this.submittedPeople.height = value;
  }

  public onKeyPeopleMass(value: string): void {
    this.submittedPeople.mass = value;
  }

  public onKeyPeopleSkinColor(value: string): void {
    this.submittedPeople.skinColor = value;
  }

  public onKeyPlanetName(value: string): void {
    this.submittedPlanets.name = value;
  }

  public onKeyPlanetDiameter(value: string): void {
    this.submittedPlanets.diameter = value;
  }

  public onKeyRotationPeriod(value: string): void {
    this.submittedPlanets.rotationPeriod = value;
  }

  public onKeyOrbitalPeriod(value: string): void {
    this.submittedPlanets.orbitalPeriod = value;
  }

  public onKeyGravity(value: string): void {
    this.submittedPlanets.gravity = value;
  }

  public onKeyPopulation(value: string): void {
    this.submittedPlanets.population = value;
  }

  public onKeyClimate(value: string): void {
    this.submittedPlanets.climate = value;
  }

  public onKeyTerrain(value: string): void {
    this.submittedPlanets.terrain = value;
  }

  public onKeySurfaceWater(value: string): void {
    this.submittedPlanets.surfaceWater = value;
  }

  private cleanTables(): void{
    this.films = [];
    this.peoples = [];
    this.planets = [];
  }
}

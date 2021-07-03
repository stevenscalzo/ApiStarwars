import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {SearchData} from '../models/SearchData';
import {Observable, of} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {Planets} from '../models/Planet';

@Injectable({
  providedIn: 'root'
})
export class PlanetsService {

  constructor(private http: HttpClient) {
  }


  public getAllPlanets(formData: SearchData): Observable<any> {
    return this.http.get<any>('http://localhost:8080/api/' + formData.table, {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*'
      }),
      observe: 'response'
    }).pipe(
      map((response) => {
        return response.body;
      }),
      catchError((error) => {
        return of(error.error);
      })
    );
  }

  public getPlanetsById(formData: SearchData): Observable<any> {
    return this.http.get<any>('http://localhost:8080/api/' + formData.table + '/' + formData.id, {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*'
      }),
      observe: 'response'
    }).pipe(
      map((response) => {
        return response.body;
      }),
      catchError((error) => {
        return of(error.error);
      })
    );
  }

  public deletePlanetsById(formData: SearchData): Observable<any> {
    return this.http.delete<any>('http://localhost:8080/api/' + formData.table + '/' + formData.id, {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*'
      }),
      observe: 'response'
    }).pipe(
      map((response) => {
        return response.body;
      }),
      catchError((error) => {
        return of(error.error);
      })
    );
  }

  public createPlanets(formData: SearchData, submittedPlanets: Planets): Observable<any> {
    return this.http.post<any>('http://localhost:8080/api/' + formData.table, submittedPlanets,
      {
        headers: new HttpHeaders({
          'Access-Control-Allow-Origin': '*'
        }),
        observe: 'response',
        responseType: 'json'
      })
      .pipe(
        map((response) => {
          return response.body;
        }),
        catchError((error) => {
          return of(error.error);
        })
      );
  }

  updatePlanets(formData: SearchData, submittedPlanets: Planets): Observable<any> {
    return this.http.put<any>('http://localhost:8080/api/' + formData.table + '/' + formData.id, submittedPlanets,
      {
        headers: new HttpHeaders({
          'Access-Control-Allow-Origin': '*'
        }),
        observe: 'response',
        responseType: 'json'
      })
      .pipe(
        map((response) => {
          return response.body;
        }),
        catchError((error) => {
          return of(error.error);
        })
      );
  }
}

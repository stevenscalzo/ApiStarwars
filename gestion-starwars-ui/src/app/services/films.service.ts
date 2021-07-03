import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Film} from '../models/Film';
import {SearchData} from '../models/SearchData';
import {catchError, map} from 'rxjs/operators';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FilmsService {

  constructor(private http: HttpClient) {
  }


  public getAllFilms(formData: SearchData): Observable<any> {
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

  public getFilmById(formData: SearchData): Observable<any> {
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

  public deleteFilmById(formData: SearchData): Observable<any> {
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

  public createFilm(formData: SearchData, submittedFilm: Film): Observable<any> {
    return this.http.post<any>('http://localhost:8080/api/' + formData.table, submittedFilm,
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

  updateFilm(formData: SearchData, submittedFilm: Film): Observable<any> {
    return this.http.put<any>('http://localhost:8080/api/' + formData.table + '/' + formData.id, submittedFilm,
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

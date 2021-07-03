import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {SearchData} from '../models/SearchData';
import {Observable, of} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {People} from '../models/People';

@Injectable({
  providedIn: 'root'
})
export class PeoplesService {

  constructor(private http: HttpClient) {
  }


  public getAllPeople(formData: SearchData): Observable<any> {
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

  public getPeopleById(formData: SearchData): Observable<any> {
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

  public deletePeopleById(formData: SearchData): Observable<any> {
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

  public createPeople(formData: SearchData, submittedPeople: People): Observable<any> {
    return this.http.post<any>('http://localhost:8080/api/' + formData.table, submittedPeople,
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

  updatePeople(formData: SearchData, submittedPeople: People): Observable<any> {
    return this.http.put<any>('http://localhost:8080/api/' + formData.table + '/' + formData.id, submittedPeople,
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

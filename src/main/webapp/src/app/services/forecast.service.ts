import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Forecast } from './forecast';

@Injectable({
  providedIn: 'root'
})
export class ForecastService {

  private url = "http://localhost:8080/forecast";

  constructor(private http: HttpClient) { }

  getForecast(): Observable<Forecast[]> {
    return this.http.get<Forecast[]>(this.url)
        .pipe(
          catchError(this.handleError<Forecast[]>('getForecast', []))
        );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
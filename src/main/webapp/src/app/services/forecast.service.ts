import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Forecast } from './forecast';
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class ForecastService {

  private url = "http://localhost:8080/forecast";

  constructor(private http: HttpClient, private datePipe:DatePipe) { }

  getForecast(date:Date): Observable<Forecast[]> {

    let parameters = new HttpParams()
    parameters = parameters.append("date", this.datePipe.transform(date, 'ddMMyyyy'));

    return this.http.get<Forecast[]>(this.url, {params: parameters})
        .pipe(
          catchError(this.handleError<Forecast[]>('getForecast', []))
        );
  }

  getForecastRange(dateStart:Date, dateEnd:Date): Observable<Forecast[]> {

    let parameters = new HttpParams()
    parameters = parameters.append("dateStart", this.datePipe.transform(dateStart, 'ddMMyyyy'));
    parameters = parameters.append("dateEnd", this.datePipe.transform(dateEnd, 'ddMMyyyy'));

    return this.http.get<Forecast[]>(this.url, {params: parameters})
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
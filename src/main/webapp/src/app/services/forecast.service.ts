import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Forecast } from './forecast';
import { DatePipe } from '@angular/common';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ForecastService {

  private url = environment.serverUrl + "/forecast";

  constructor(private http: HttpClient, private datePipe:DatePipe) { }

  getForecast(date:Date): Observable<Forecast[]> {

    let parameters = new HttpParams()
    parameters = parameters.append("date", this.datePipe.transform(date, 'ddMMyyyy'));

    return this.http.get<Forecast[]>(this.url, {params: parameters})
        .pipe(
          catchError(this.handleError<Forecast[]>('getForecast', []))
        );
  }

  getForecastResume(date:Date): Observable<Forecast[]> {

    let parameters = new HttpParams()
    parameters = parameters.append("date", this.datePipe.transform(date, 'ddMMyyyy'));

    return this.http.get<Forecast[]>(this.url + "/resume", {params: parameters})
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
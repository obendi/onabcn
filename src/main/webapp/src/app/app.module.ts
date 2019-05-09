import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { registerLocaleData } from '@angular/common';
import localeEs from '@angular/common/locales/es';
import localeCa from '@angular/common/locales/ca';

registerLocaleData(localeEs, 'es');
registerLocaleData(localeCa, 'ca');

import { AppComponent } from './app.component';
import { ForecastChartComponent } from './forecast-chart/forecast-chart.component';

import { ChartsModule } from 'ng2-charts';
import { ForecastService } from './services/forecast.service';
import { HttpClientModule } from '@angular/common/http';
import { CurrentTimeComponent } from './current-time/current-time.component';
import { DatePipe } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    ForecastChartComponent,
    CurrentTimeComponent
  ],
  imports: [
    BrowserModule, BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ChartsModule
  ],
  providers: [
    ForecastService, 
    DatePipe,
    {provide: LOCALE_ID, useValue: "en"}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

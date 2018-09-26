import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ForecastChartComponent } from './forecast-chart/forecast-chart.component';

import { ChartsModule } from 'ng2-charts';
import { ForecastService } from './services/forecast.service';
import { HttpClientModule } from '@angular/common/http';
import { CurrentTimeComponent } from './current-time/current-time.component';
import { DatePipe } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule, MatDatepickerModule, MatNativeDateModule, MatInputModule, MatButtonModule, MatCardModule } from '@angular/material';

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
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    ChartsModule
  ],
  providers: [ForecastService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ForecastChartComponent } from './forecast-chart/forecast-chart.component';

import { ChartsModule } from 'ng2-charts';
import { ForecastService } from './services/forecast.service';
import { HttpClientModule } from '@angular/common/http';
import { CurrentTimeComponent } from './current-time/current-time.component';

@NgModule({
  declarations: [
    AppComponent,
    ForecastChartComponent,
    CurrentTimeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ChartsModule
  ],
  providers: [ForecastService],
  bootstrap: [AppComponent]
})
export class AppModule { }

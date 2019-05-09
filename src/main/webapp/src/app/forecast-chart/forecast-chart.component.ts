import { Component, OnInit } from '@angular/core';
import { ForecastService } from '../services/forecast.service';
import { Forecast } from '../services/forecast';

import { FormControl } from '@angular/forms';

import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-forecast-chart',
  templateUrl: './forecast-chart.component.html',
  styleUrls: ['./forecast-chart.component.scss']
})
export class ForecastChartComponent implements OnInit {

  constructor(private forecastService: ForecastService) { }

  ngOnInit() {
    this.tomorrow.setDate(this.today.getDate()+1);
    this.dayAfterTomorrow.setDate(this.today.getDate()+2);

    this.getForecastToday();
  }

  data:Forecast[];

  dateFrom = new FormControl(new Date());
  dateTo = new FormControl(new Date());

  private datePipe:DatePipe = new DatePipe('es');

  today:Date = new Date();
  tomorrow:Date = new Date();
  dayAfterTomorrow:Date = new Date();

  // lineChart
  public lineChartData:Array<any> = [
    {data: [], label: 'Mar total'},
    {data: [], label: 'Mar primario'}
  ];
  public lineChartLabels:Array<any> = [];
  public lineChartOptions:any = {
    responsive: true,
    aspectRatio: 2,
    scales: {
      yAxes: [{
        display: true,
        ticks: {
          suggestedMin:0,
          suggestedMax:1.5,
          beginAtZero: true,
          callback: function(value, index, values) {
            return value + " m";
          }
        }
      }]
    },
    tooltips: {
      enabled: true,
      mode: 'single',
      callbacks: {
        label: function(tooltipItems, data) {
          return tooltipItems.yLabel + 'm';
        }
      }
    }
  };
  public lineChartColors:Array<any> = [
   { 
      backgroundColor: 'rgba(0,105,148,0.2)',
      borderColor: 'rgba(0,105,148,1)',
      pointBackgroundColor: 'rgba(0,105,148,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(0,105,148,0.8)'
    },
    {
      borderColor: 'rgba(0,105,148,0.4)',
      pointBackgroundColor: 'rgba(0,105,148,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(0,105,148,0.8)',
      fill: false,
      borderDash: [5, 5]
    }
  ];
  public lineChartLegend:boolean = true;
  public lineChartType:string = 'line';

  public getForecastToday():void {
    this.getForecast(this.today);
  }

  public getForecastTomorrow():void {
    this.getForecast(this.tomorrow);
  }

  public getForecastDayAfterTomorrow():void {
    this.getForecast(this.dayAfterTomorrow);
  }
  
  public getForecast(date:Date):void {
      this
        .forecastService
        .getForecast(date)
        .subscribe((data: Forecast[]) => {

          let _lineChartLabels:Array<any> = [];
          let _height:Array<any> = [];
          let _windSwell:Array<any> = [];
          let _primarySwell:Array<any> = [];
          let _secondarySwell:Array<any> = [];
          let _windSpeed:Array<any> = [];

          for (let index=0; index<data.length; index++) {
            _lineChartLabels.push(this.datePipe.transform(data[index].date, 'HH:mm'));
            _height.push(data[index].totalHeight);
            _windSwell.push(data[index].windSwell);
            _primarySwell.push(data[index].primarySwell);
            _secondarySwell.push(data[index].secondarySwell);
            _windSpeed.push(data[index].windSpeed);
          }

          this.lineChartLabels = _lineChartLabels;
          this.lineChartData[0].data = _primarySwell;
          this.lineChartData[1].data = _height;

          this.data = data;
      });
  }

  public flipDirection(direction:number):number {
    if (direction<180) {
      return direction+180;
    }
    else {
      return direction-180;
    }
  }

  public getWindSpeedColorClass(windSpeed:number):string {
    if (windSpeed > 10) {
      return "red";
    }
    else if(windSpeed > 5) {
      return "yellow";
    }
    else {
      return "green";
    }
  }
}

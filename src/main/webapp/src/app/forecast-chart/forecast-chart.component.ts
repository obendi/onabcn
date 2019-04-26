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
    //this.getForecast();
  }

  data:Forecast[];

  pdate:any;
  dateFrom = new FormControl(new Date());
  dateTo = new FormControl(new Date());
  dateService:Date;

  private datePipe:DatePipe = new DatePipe('en-US');

  // lineChart
  public lineChartData:Array<any> = [
    {data: [], label: 'Mar total'},
    //{data: [], label: 'Mar viento'},
    {data: [], label: 'Mar primario'}
    //{data: [], label: 'Mar secundario'},
    //{data: [], label: 'Velocidad viento'}
  ];
  public lineChartLabels:Array<any> = [];
  public lineChartOptions:any = {
    responsive: true,
    scales: {
      yAxes: [{
        display: true,
        ticks: {
          suggestedMin:0,
          suggestedMax:2.0,
          beginAtZero: true
        }
      }]
    },
    tooltips: {
      enabled: true,
      mode: 'single',
      callbacks: {
        label: function(tooltipItems, data) {
          console.log(data);
          return tooltipItems.yLabel + 'm';
        }
      }
    }
  };
  public lineChartColors:Array<any> = [
   /*  { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    } */
  ];
  public lineChartLegend:boolean = true;
  public lineChartType:string = 'line';

  public getForecastToday():void {
    this.getForecast(new Date());
  }

  public getForecastTomorrow():void {
    var today = new Date();
    var tomorrow = new Date();
    tomorrow.setDate(today.getDate()+1);

    this.getForecast(tomorrow);
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
            _lineChartLabels.push(this.datePipe.transform(data[index].date, 'EEE HH:mm'));
            _height.push(data[index].height);
            _windSwell.push(data[index].windSwell);
            _primarySwell.push(data[index].primarySwell);
            _secondarySwell.push(data[index].secondarySwell);
            _windSpeed.push(data[index].windSpeed);
          }

          this.lineChartLabels = _lineChartLabels;
          this.lineChartData[0].data = _height;
          //this.lineChartData[1].data = _windSwell;
          this.lineChartData[1].data = _primarySwell;
         // this.lineChartData[3].data = _secondarySwell;
          //this.lineChartData[4].data = _windSpeed;

          this.data = data;
      });
  }
 
  // events
  public chartClicked(e:any):void {
    console.log(e);
  }
 
  public chartHovered(e:any):void {
    console.log(e);
  }
}

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-current-time',
  templateUrl: './current-time.component.html',
  styleUrls: ['./current-time.component.scss']
})
export class CurrentTimeComponent implements OnInit {

  public now:Number;

  constructor() { }

  ngOnInit() {

    setInterval(() => {
      this.now = Date.now();
    }, 1);

  }
}
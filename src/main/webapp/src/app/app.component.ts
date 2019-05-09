import { Component, Inject, LOCALE_ID } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'onabcn-angular';

  languageList = [
    { code: 'en', label: 'English'},
    { code: 'es', label: 'Castellano'},
    { code: 'ca', label: 'Català' }
  ]

  constructor(@Inject(LOCALE_ID) protected localeId: string) {}
}

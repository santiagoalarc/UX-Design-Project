import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {

  DAYS_OF_THE_WEEK: string[] = [
    'DOMINGO', 'LUNES', 'MARTES', 'MIÉRCOLES', 'JUEVES', 'VIERNES', 'SÁBADO'
  ];
  MONTHS_OF_THE_YEAR: string[] = [
    'ENERO', 'FEBRERO', 'MARZO', 'ABRIL', 'MAYO', 'JUNIO', 'JULIO', 'AGOSTO', 'SEPTIEMBRE', 'OCTUBRE', 'NOVIEMBRE', 'DICIEMBRE'
  ];

  currentTime: string = '';
  verboseDate: string = '';
  isAm: boolean = false;

  constructor() {
  }

  ngOnInit(): void {
    this.refreshTimer();
    setInterval(() => {
      this.refreshTimer();
    }, 1_000);
  }

  private refreshTimer() {
    const date: Date = new Date();

    let hours = date.getHours();
    const minutes = this.padZero(date.getMinutes());
    const seconds = this.padZero(date.getSeconds());
    this.isAm = hours <= 12;

    // Convert to 12-hour format
    hours = hours % 12;
    hours = hours ? hours : 12; // the hour '0' should be '12'

    this.currentTime = `${this.padZero(hours)}:${minutes}:${seconds}`;
    this.formatDate(date);
  }

  padZero(value: number): string {
    return value < 10 ? `0${value}` : `${value}`;
  }

  formatDate(date: Date): void {
    const dayOfWeek = this.DAYS_OF_THE_WEEK[date.getDay()]; // Day of the week in Spanish
    const day = this.padZero(date.getDate()); // Day of the month
    const month = this.MONTHS_OF_THE_YEAR[date.getMonth()]; // Month in Spanish
    const year = date.getFullYear(); // Year

    this.verboseDate = `${dayOfWeek} ${day} DE ${month}, ${year}`;
  }

}

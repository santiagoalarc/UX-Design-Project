import { Component, OnInit } from '@angular/core';

declare let google: any;

@Component({
  selector: 'app-monitor',
  standalone: true,
  imports: [],
  templateUrl: './monitor.component.html',
  styleUrl: './monitor.component.css'
})
export class MonitorComponent implements OnInit {

  totalUsers: string = '1.6k';
  usersWithAlarms: string = '1.4k';
  usersWithoutAlarms: string = '210';

  constructor() {
  }

  ngOnInit(): void {
    google.charts.load("current", {packages: ["corechart", "bar"]})
    google.charts.setOnLoadCallback(this.drawWeeklyGlobalUsage);
    google.charts.setOnLoadCallback(this.drawMostFrequentSounds);
  }

  drawWeeklyGlobalUsage(): void {
    let data = google.visualization.arrayToDataTable([
         ['Dia', 'Uso',],
         ['L', 117,],            // RGB value
         ['M', 111,],            // English color name
         ['M', 56,],
         ['J', 23,],
         ['V', 34,],
         ['S', 13,],
         ['D', 9,],
      ]);

    let options = {
      title: 'Uso Semanal Global',
      hAxis: {
        title: 'Time of Day',
        format: 'h:mm a',
        viewWindow: {
          min: [7, 30, 0],
          max: [17, 30, 0]
        }
      },
      vAxis: {
        title: 'Numero de usuarios'
      }
    };

    let chart = new google.visualization.ColumnChart(document.getElementById('weekly_global_usage'));

    chart.draw(data, options);
  }

  drawMostFrequentSounds(): void {
    let data = google.visualization.arrayToDataTable([
      ['PalustrE', 'Suma Ejecuciones Usuarios',],
      ['Ingenieril', 67],
      ['E2E with Mario :0', 2],
      ['Dorime', 42],
      ['Himno Nacional ', 100],
      ['RetroWave', 34],
    ]);

    let options = {
      title: 'Sonidos mas frequentes',
      fontName: 'IBM Plex Sans',
      height: '12rem',
      chartArea: {
        width: '50%'
      },
      hAxis: {
        title: 'Usuarios usandolos',
        minValue: 0
      },
      vAxis: {
        title: 'Alarmas',
      }
    };

    let chart = new google.visualization.BarChart(document.getElementById('most_frequent_sounds'));

    chart.draw(data, options);
  }


}

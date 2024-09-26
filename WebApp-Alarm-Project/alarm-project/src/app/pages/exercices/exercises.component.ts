import { Component, OnInit } from '@angular/core';

declare let google: any;

@Component({
  selector: 'app-exercices',
  standalone: true,
  imports: [],
  templateUrl: './exercises.component.html',
  styleUrl: './exercises.component.css'
})
export class ExercisesComponent implements OnInit {

  ngOnInit(): void {
    google.charts.load("current", {packages: ["corechart", "bar"]})
    google.charts.setOnLoadCallback(this.drawMultSeries);
  }

  drawMultSeries(): void {
    let data = google.visualization.arrayToDataTable([
      ['Ejercicio', 'Suma Ejecuciones Usuarios',],
      ['Sentadillas', 67],
      ['Saltos Triples', 56],
      ['Burpees', 42],
      ['Squats', 32],
      ['Press de Banca', 34],
    ]);

    let options = {
      fontName: 'IBM Plex Sans',
      height: '12rem',
      chartArea: {
        width: '50%'
      },
      hAxis: {
        title: 'Total Ejecuciones',
        minValue: 0
      },
      vAxis: {
        title: 'Ejercicios',
      }
    };

    let chart = new google.visualization.BarChart(document.getElementById('chart_div'));

    chart.draw(data, options);
  }

}

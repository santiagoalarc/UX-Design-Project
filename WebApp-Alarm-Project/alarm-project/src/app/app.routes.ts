import { Routes } from '@angular/router';
import { SoundsComponent } from "./pages/sounds/sounds.component";
import { HomeComponent } from "./pages/home/home.component";
import { ExercisesComponent } from "./pages/exercices/exercises.component";
import { MonitorComponent } from "./pages/monitor/monitor.component";
import { ConfigComponent } from "./pages/config/config.component";

export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'sounds', component: SoundsComponent },
  { path: 'exercises', component: ExercisesComponent },
  { path: 'monitor', component: MonitorComponent },
  { path: 'config', component: ConfigComponent },
  { path: '**', redirectTo: '/home' } // Wildcard route
];

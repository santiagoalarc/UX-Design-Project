import { Component } from '@angular/core';
import { MatIcon } from "@angular/material/icon";
import { MatChip, MatChipSet } from "@angular/material/chips";
import { NgClass } from "@angular/common";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-side-bar',
  standalone: true,
  imports: [
    MatIcon,
    MatChip,
    MatChipSet,
    NgClass
  ],
  templateUrl: './side-bar.component.html',
  styleUrl: './side-bar.component.scss'
})
export class SideBarComponent {

  currentRoute: string = '';

  constructor(private router: Router, private route: ActivatedRoute) {
    this.router.events.subscribe(() => {
      this.currentRoute = this.router.url;
    });
  }

  isActive(routeName: string): boolean {
    return this.currentRoute.toLowerCase().startsWith(routeName.toLowerCase());
  }

  navigateTo(routeName: string): void {
    this.router.navigate([routeName]).then(r => {});
  }

}

import { Component } from '@angular/core';
import { MatButton } from "@angular/material/button";
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow,
  MatHeaderRowDef,
  MatNoDataRow,
  MatRow,
  MatRowDef,
  MatTable,
  MatTableDataSource
} from "@angular/material/table";
import { MatSort, MatSortHeader } from "@angular/material/sort";
import { MatRadioButton, MatRadioGroup } from "@angular/material/radio";
import { FormControl, FormsModule } from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";
import { NewPresetDialogComponent } from "./new-preset/new-preset-dialog.component";

@Component({
  selector: 'app-config',
  standalone: true,
  imports: [
    MatButton,
    MatCell,
    MatCellDef,
    MatColumnDef,
    MatHeaderCell,
    MatHeaderRow,
    MatHeaderRowDef,
    MatRow,
    MatRowDef,
    MatSort,
    MatSortHeader,
    MatTable,
    MatHeaderCellDef,
    MatNoDataRow,
    MatRadioGroup,
    FormsModule,
    MatRadioButton
  ],
  templateUrl: './config.component.html',
  styleUrl: './config.component.css'
})
export class ConfigComponent {

  dataSource: MatTableDataSource<any> = new MatTableDataSource<any>([
    {id: 1, hours: null, minutes: 10}
  ]);

  displayedColumns: string[] = ['name', 'edit', 'delete'];

  selectedAlarmSound: string;

  sounds: string[] = ['Modular', 'Angular', 'NiggaPlease', 'Monody', 'Pap U:'];

  constructor(public dialog: MatDialog) {
    this.selectedAlarmSound = this.sounds[0];
  }

  edit(id: string) {
    let find = this.dataSource.data.find((item) => item.id == id);

    const dialogRef = this.dialog.open(NewPresetDialogComponent, {
      width: '24rem',
      height: '42vh',
      data: find,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const index = this.dataSource.data.findIndex(item => item.id === id);
        if (index !== -1) {
          this.dataSource.data[index].hours = result.hours;
          this.dataSource.data[index].minutes = result.minutes;
          this.dataSource.data = [...this.dataSource.data];
        }
      }
    });
  }

  remove(id: number | string) {
    this.dataSource.data = this.dataSource.data.filter(item => item.id != id);
  }

  openNewPresetDialog() {
    const dialogRef = this.dialog.open(NewPresetDialogComponent, {
      width: '24rem',
      height: '42vh',
      data: null,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        result.id = this.dataSource.data.reduce((max, item) => (item.id > max ? item.id : max), 0) + 1;
        this.dataSource.data = [...this.dataSource.data, result];
      }
    });
  }

}

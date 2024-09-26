import { Component } from '@angular/core';
import { MatButton } from "@angular/material/button";
import { MatFormField, MatLabel } from "@angular/material/form-field";
import { MatAutocomplete, MatAutocompleteTrigger, MatOption } from "@angular/material/autocomplete";
import { FormControl, ReactiveFormsModule } from "@angular/forms";
import { MatInput } from "@angular/material/input";
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow, MatHeaderRowDef, MatNoDataRow, MatRow, MatRowDef,
  MatTable, MatTableDataSource
} from "@angular/material/table";
import { MatSort, MatSortHeader } from "@angular/material/sort";
import { MatPaginator } from "@angular/material/paginator";
import { MatDialog } from "@angular/material/dialog";
import { NewSoundDialogComponent } from "./new-sound-dialog/new-sound-dialog.component";

interface Sound {
  id: number
  name: string
  duration: string
}

@Component({
  selector: 'app-sounds',
  standalone: true,
  imports: [
    MatButton,
    MatFormField,
    MatAutocomplete,
    MatOption,
    ReactiveFormsModule,
    MatAutocompleteTrigger,
    MatInput,
    MatLabel,
    MatTable,
    MatSort,
    MatColumnDef,
    MatHeaderCell,
    MatCell,
    MatHeaderCellDef,
    MatCellDef,
    MatHeaderRow,
    MatRow,
    MatRowDef,
    MatHeaderRowDef,
    MatNoDataRow,
    MatPaginator,
    MatSortHeader
  ],
  templateUrl: './sounds.component.html',
  styleUrl: './sounds.component.css'
})
export class SoundsComponent {

  soundControl: FormControl;

  dataSource: MatTableDataSource<any> = new MatTableDataSource<Sound>([
    {
      id: 1,
      name: 'Chipi Chipi Chapa Chapa',
      duration: '3:33'
    }
  ]);

  displayedColumns: string[] = ['name', 'duration', 'delete', 'edit', 'play'];

  hasFilterApplied: boolean = false;

  constructor(public dialog: MatDialog) {
    this.soundControl = new FormControl<any>('');

    this.dataSource.filterPredicate = (data: Sound, filter: string) => {
      return data.name.toLowerCase().startsWith(filter) || data.duration.startsWith(filter);
    };
  }

  applyFilter() {
    this.dataSource.filter = this.soundControl.value.trim().toLowerCase();
    this.hasFilterApplied = true;
  }

  clearFilter() {
    this.dataSource.filter = '';
    this.hasFilterApplied = false;
    this.soundControl.setValue('');
  }

  edit(id: string): void {
    let find = this.dataSource.data.find((item) => item.id == id);

    const dialogRef = this.dialog.open(NewSoundDialogComponent, {
      width: '24rem',
      height: '42vh',
      data: find,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const index = this.dataSource.data.findIndex(item => item.id === id);
        if (index !== -1) {
          this.dataSource.data[index].name = result.name;
          this.dataSource.data[index].duration = result.duration;
          this.dataSource.data = [...this.dataSource.data];
        }
      }
    });
  }

  remove(id: string): void {
    this.dataSource.data = this.dataSource.data.filter(item => item.id != id);
  }

  play(name: string): void {

  }

  addNewSound() {
    const dialogRef = this.dialog.open(NewSoundDialogComponent, {
      width: '24rem',
      height: '42vh',
      data: null,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result && this.dataSource.data.length < 5) {
        result.id = this.dataSource.data.reduce((max, item) => (item.id > max ? item.id : max), 0) + 1;
        result.name += `(${result.id})`
        this.dataSource.data = [...this.dataSource.data, result];
      }
    });
  }

}

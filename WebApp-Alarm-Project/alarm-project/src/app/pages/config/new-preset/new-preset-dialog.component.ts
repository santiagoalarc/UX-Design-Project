import { Component, Inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogModule,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import { MatFormField, MatFormFieldModule, MatLabel } from "@angular/material/form-field";
import { MatInput, MatInputModule } from "@angular/material/input";
import { MatButton } from "@angular/material/button";
import {
  DatetimeAdapter,
  MAT_DATETIME_FORMATS,
  MatDatetimepickerModule,
  MatNativeDatetimeModule
} from "@mat-datetimepicker/core";
import { MatNativeDateModule } from "@angular/material/core";
import { MatMomentDatetimeModule, MomentDatetimeAdapter } from "@mat-datetimepicker/moment";
import { Moment } from "moment/moment";
import moment from 'moment';

export const CUSTOM_MAT_DATETIME_FORMATS = {
  parse: {
    datetimeInput: 'HH:mm',
  },
  display: {
    datetimeInput: 'HH:mm',
    dateInput: 'HH:mm',
    timeInput: 'HH:mm',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  }
};

@Component({
  selector: 'app-new-preset',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormField,
    MatInput,
    MatDialogActions,
    MatButton,
    MatDialogTitle,
    MatLabel,

    MatDialogModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatDatetimepickerModule,
    MatNativeDatetimeModule,

    MatMomentDatetimeModule,
    MatNativeDateModule
  ],
  providers: [
    {provide: DatetimeAdapter, useClass: MomentDatetimeAdapter},
    {provide: MAT_DATETIME_FORMATS, useValue: CUSTOM_MAT_DATETIME_FORMATS}
  ],
  templateUrl: './new-preset-dialog.component.html',
  styleUrl: './new-preset-dialog.component.scss'
})
export class NewPresetDialogComponent {

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<NewPresetDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    let existingValue = '';
    if (data) {
      existingValue = moment()
        .hours(!data.hours? 0 : data.hours)
        .minutes(!data.minutes? 0 : data.minutes)
        .format('YYYY-MM-DD HH:mm');
    }
    this.formGroup = this.fb.group({
      datetimeCtrl: new FormControl(existingValue, [Validators.required])
    });
  }

  formGroup: FormGroup;

  onSubmit(): void {
    if (this.formGroup.valid) {
      let time: Moment = this.formGroup.controls['datetimeCtrl'].value;

      this.dialogRef.close({
        hours: time.get("hour") == 0? null : time.get("hour"),
        minutes: time.get("minute") == 0? null : time.get("minute"),
      });
    }
  }

  onCancel(): void {
    this.dialogRef.close(null);
  }

}

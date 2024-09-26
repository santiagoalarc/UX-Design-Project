import { Component, Inject } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { MatError, MatFormField, MatFormFieldModule, MatHint, MatLabel } from "@angular/material/form-field";
import { MatInput, MatInputModule } from "@angular/material/input";
import { MatButton } from "@angular/material/button";
import { NgIf } from "@angular/common";
import { NewPresetDialogComponent } from "../../config/new-preset/new-preset-dialog.component";
import { Moment } from "moment";

@Component({
  selector: 'app-new-sound-dialog',
  standalone: true,
  imports: [
    MatDialogContent,
    MatDialogTitle,
    ReactiveFormsModule,
    MatFormField,
    MatInput,
    MatButton,
    NgIf,
    MatLabel,
    MatHint,
    MatError,
    MatDialogActions,
    MatFormFieldModule,
    MatInputModule
  ],
  templateUrl: './new-sound-dialog.component.html',
  styleUrl: './new-sound-dialog.component.css'
})
export class NewSoundDialogComponent {

  formGroup: FormGroup;

  mockFileName: string | boolean = false;

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<NewPresetDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.formGroup = this.fb.group({
      file: [null, Validators.required]
    });
  }

  onFileChange(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.formGroup.patchValue({
        file: file
      });
    }
  }

  onSubmit() {
    if (this.formGroup.valid) {
      const formData = new FormData();
      formData.append('file', this.formGroup.get('file')?.value);

      // Handle file upload logic here (e.g., send to server)
      console.log('File uploaded:', this.formGroup.get('file')?.value);
    }
  }

  onCancel() {
    this.dialogRef.close(null);
  }

  submit() {
    this.dialogRef.close({
      name: 'hanchinchen hanchiee chan',
      duration: '4:44'
    });
  }

  uploadFile() {
    this.mockFileName = 'chipi chipi chapa chapa (amongus ver).mp3';
  }

}

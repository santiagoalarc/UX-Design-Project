import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewPresetDialogComponent } from './new-preset-dialog.component';

describe('NewPresetComponent', () => {
  let component: NewPresetDialogComponent;
  let fixture: ComponentFixture<NewPresetDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewPresetDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewPresetDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

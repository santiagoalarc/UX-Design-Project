import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewSoundDialogComponent } from './new-sound-dialog.component';

describe('NewSoundDialogComponent', () => {
  let component: NewSoundDialogComponent;
  let fixture: ComponentFixture<NewSoundDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewSoundDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewSoundDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

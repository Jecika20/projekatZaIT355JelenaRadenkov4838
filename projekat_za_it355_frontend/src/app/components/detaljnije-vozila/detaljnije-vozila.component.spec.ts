import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetaljnijeVozilaComponent } from './detaljnije-vozila.component';

describe('DetaljnijeVozilaComponent', () => {
  let component: DetaljnijeVozilaComponent;
  let fixture: ComponentFixture<DetaljnijeVozilaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DetaljnijeVozilaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetaljnijeVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

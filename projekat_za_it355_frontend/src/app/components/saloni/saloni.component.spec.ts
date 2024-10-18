import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaloniComponent } from './saloni.component';

describe('SaloniComponent', () => {
  let component: SaloniComponent;
  let fixture: ComponentFixture<SaloniComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SaloniComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SaloniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

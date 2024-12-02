import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RezervacijeKlijenataComponent } from './rezervacije-klijenata.component';

describe('RezervacijeKlijenataComponent', () => {
  let component: RezervacijeKlijenataComponent;
  let fixture: ComponentFixture<RezervacijeKlijenataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RezervacijeKlijenataComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RezervacijeKlijenataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

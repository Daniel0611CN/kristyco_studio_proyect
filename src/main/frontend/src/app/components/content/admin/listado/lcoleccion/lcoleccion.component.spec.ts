import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LcoleccionComponent } from './lcoleccion.component';

describe('LcoleccionComponent', () => {
  let component: LcoleccionComponent;
  let fixture: ComponentFixture<LcoleccionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LcoleccionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LcoleccionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

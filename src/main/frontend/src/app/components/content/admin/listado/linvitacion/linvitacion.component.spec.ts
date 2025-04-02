import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LinvitacionComponent } from './linvitacion.component';

describe('LinvitacionComponent', () => {
  let component: LinvitacionComponent;
  let fixture: ComponentFixture<LinvitacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LinvitacionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LinvitacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

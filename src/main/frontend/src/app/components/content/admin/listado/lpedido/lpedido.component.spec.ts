import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LpedidoComponent } from './lpedido.component';

describe('LpedidoComponent', () => {
  let component: LpedidoComponent;
  let fixture: ComponentFixture<LpedidoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LpedidoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LpedidoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

export interface Pago {
  id: string;
  monto: number;
  fecha: Date
  estadoPago: string;
  metodoPago: string;
}

export class Pago {
  id: string;
  monto: number;
  fecha: Date;
  estadoPago: string;
  metodoPago: string;

  constructor(id = '', monto = 0, fecha = new Date(), estadoPago = 'PENDIENTE', metodoPago = 'EFECTIVO') {
    this.id = id;
    this.monto = monto;
    this.fecha = fecha;
    this.estadoPago = estadoPago;
    this.metodoPago = metodoPago;
  }
}

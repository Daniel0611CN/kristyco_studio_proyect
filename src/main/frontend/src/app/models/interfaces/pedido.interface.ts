export interface Pago {
  estadoPago: string;
  metodoPago: string;
}

export interface Usuario {
  id: number;
  nombre: string;
}

export interface Producto {
  id: number;
  nombre: string;
  cantidad: number;
}

export interface Pedido {
  id: number;
  total: number;
  costeEnvio: number;
  fecha: string;
  estadoPedido: string;
  pago: Pago;
  usuario: Usuario;
  productos: Producto[];
}

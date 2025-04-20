import { Pago } from "./pago.interface";
import { Producto } from "./producto.interface";
import { Usuario } from "./usuario.interface";

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

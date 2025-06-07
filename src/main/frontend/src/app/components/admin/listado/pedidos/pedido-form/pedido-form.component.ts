import { Component, inject, Input, signal } from '@angular/core';
import { PedidoService } from '../../../../../services/pedido.service';
import { Producto } from '../../../../../models/interfaces/entities/producto.interface';
import { FormsModule } from '@angular/forms';
import { SwalService } from '../../../../shared/services/swal.service';

interface Option {
  option: string;
  label: string;
}

interface Form {
  col?: boolean;
  key: string;
  label: string;
  span?: string;
  type?: string;
  placeholder?: string;
  readonly?: boolean;
  options?: Option[];
}

@Component({
  selector: 'app-pedido-form',
  imports: [FormsModule],
  templateUrl: './pedido-form.component.html'
})
export class PedidoFormComponent {

  swalService = inject(SwalService);
  pedidoService = inject(PedidoService);

  @Input() selectedItem: any = null;
  @Input() originalItem: any = null;
  @Input() productosPedidos = signal<Producto[]>([]);

  form: Form[] = [
    { col: true, key: 'total', label: 'Coste Total', span: '€', type: 'number' },
    { col: true, key: 'costeEnvio', label: 'Coste de Envío', span: '€', type: 'number' },
    { key: 'fecha', label: 'Fecha de Registro', type: 'text', placeholder: '', readonly: true },
    { key: 'direccion', label: 'Dirección', span: 'C/ nº', type: 'text', placeholder: 'Dirección', readonly: true },
    { key: 'estado', label: 'Estado de Envío', options: [
      { option: 'PENDIENTE', label: 'Pendiente' },
      { option: 'EN_CAMINO', label: 'En Camino' },
      { option: 'ENTREGADO', label: 'Entregado' },
      { option: 'CANCELADO', label: 'Cancelado' }
    ]},
    { key: 'usuario', label: 'Usuario' },
    { col: true, key: 'metodoPago', label: 'Método de Pago' },
    { col: true, key: 'estadoPago', label: 'Estado del Pago' },
    { key: 'productos', label: 'Productos' }
  ];

  guardarCambios(): void {
    if (!this.selectedItem) return;
    const id: string = this.selectedItem.id;
    let pedido = this.selectedItem;
    this.pedidoService.update(id, pedido).subscribe({
      next: () => {
        this.closeButton();
        this.swalService.showSuccess('Éxito', 'Se ha editado el pedido correctamente.');
      },
      error: (err) => console.error('Error al actualizar el pedido', err)
    });
  }

  cancelarEdicion(): void {
    if (this.originalItem && this.selectedItem) Object.assign(this.selectedItem, this.originalItem);
    this.selectedItem = null;
    this.originalItem = null;
  }

  closeButton(): void {
    const closeBtn = document.querySelector('#exampleModalVer .btn-close') as HTMLElement;
    if (closeBtn) closeBtn.click();
  }

}

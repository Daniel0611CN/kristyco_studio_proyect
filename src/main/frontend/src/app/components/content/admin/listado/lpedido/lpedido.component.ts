import { Component, OnInit } from '@angular/core';
import { PedidoService } from '../../../../../services/pedido/pedido.service';
import { CommonModule } from '@angular/common';
import { EstadoPedidoPipe } from '../../../../../pipes/estado.pedido.pipe';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';
import { bootstrapApplication } from '@angular/platform-browser';

@Component({
  selector: 'app-lpedido',
  imports: [CommonModule, EstadoPedidoPipe, FormsModule],
  templateUrl: './lpedido.component.html',
  styleUrl: './lpedido.component.css'
})
export class LpedidoComponent implements OnInit {
  title: string = 'Listado de Pedidos';
  columnas: { key: string, label: string }[] = [
    { key: 'id', label: 'ID' },
    { key: 'total', label: 'Total' },
    { key: 'costeEnvio', label: 'Coste de Envío' },
    { key: 'fecha', label: 'Fecha' },
    { key: 'direccion', label: 'Dirección' },
    { key: 'estado', label: 'Estado' }
  ];

  total: any = { key: 'total', label: 'Coste Total', span: '€', type: 'number' };
  costeEnvio: any = { key: 'costeEnvio', label: 'Coste de Envío', span: '€', type: 'number' };

  formNgFor: any[] = [
    { key: 'fecha', label: 'Fecha de Registro', type: 'text', placeholder: '' },
    { key: 'direccion', label: 'Dirección', span: 'C/ nº', type: 'text', placeholder: 'Dirección' },
  ];

  estado: any = { key: 'estado', label: 'Estado de Envío',
    options: [
      { option: 'PENDIENTE', label: 'Pendiente' },
      { option: 'EN_CAMINO', label: 'En Camino' },
      { option: 'ENTREGADO', label: 'Entregado' },
      { option: 'CANCELADO', label: 'Cancelado' }
    ]
  };

  extraForm: any[] = [
    { key: 'usuario', label: 'Usuario' },
    { key: 'metodo', label: 'Método de Pago' },
    { key: 'estado', label: 'Estado del Pago' },
    { key: 'productos', label: 'Productos' }
  ];

  data: any[] = [];
  selectedItem: any = null;
  totalElements: number = 0;
  currentPage: number = 0;
  pageSize: number = 10;

  constructor(private pedidoService: PedidoService) {}

  get totalPages(): number {
    return Math.ceil(this.totalElements / this.pageSize);
  }

  ngOnInit(): void {
    this.listarPedidos();
  }

  cambiarPagina(nuevaPagina: number): void {
    if (nuevaPagina >= 0 && nuevaPagina < this.totalPages) {
      this.currentPage = nuevaPagina;
      this.listarPedidos();
    }
  }

  private listarPedidos() {
    this.pedidoService.all(this.currentPage, this.pageSize).subscribe({
      next: (response: any) => {
        this.data = response.content;
        console.log(this.data);
        this.totalElements = response.totalElements;
      },
      error: (err) => {
        console.error('Error al obtener los pedidos:', err);
      }
    });
  }

  prepararEdicion(row: any): void {
    this.selectedItem = { row };
    this.selectedItem = this.selectedItem.row;
  }

  guardarCambios(message: string, title: string = '¡Éxito!'): void {
    if (!this.selectedItem) return;
    const id: number = this.selectedItem.id;
    let pedido = this.selectedItem;
    this.pedidoService.updatePedido(id, pedido).subscribe({
      next: () => {
        return Swal.fire({
          title: title,
          text: message,
          icon: 'success',
          confirmButtonText: 'Aceptar',
        })
      },
      error: (err) => {
        console.error('Error al actualizar el pedido', err);
      }
    })

  }

  cancelarEdicion() {
    this.selectedItem = null;
  }


  prueba(message: string, title: string = '¡Éxito!') {

  }

}

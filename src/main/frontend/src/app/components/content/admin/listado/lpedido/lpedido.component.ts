import { Component, OnInit } from '@angular/core';
import { PedidoService } from '../../../../../services/pedido/pedido.service';
import { CommonModule } from '@angular/common';
import { EstadoPedidoPipe } from '../../../../../pipes/estado.pedido.pipe';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-lpedido',
  imports: [CommonModule, EstadoPedidoPipe],
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
  data: any[] = [];
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
        this.totalElements = response.totalElements;
      },
      error: (err) => {
        console.error('Error al obtener los pedidos:', err);
      }
    });
  }

  prueba(message: string, title: string = '¡Éxito!') {
    return Swal.fire({
      title: title,
      text: message,
      icon: 'success',
      confirmButtonText: 'Aceptar'
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PedidoService } from '../../../../../services/pedido/pedido.service';
import { Pedido } from '../../../../../models/interfaces/pedido.interface';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-lpedido',
  imports: [CommonModule],
  templateUrl: './lpedido.component.html',
  styleUrl: './lpedido.component.css'
})
export class LpedidoComponent implements OnInit {
  title: string = 'Listado de Pedidos';
  columnas: { key: string, label: string }[] = [
    { key: 'id', label: 'ID' },
    { key: 'total', label: 'Total' },
    { key: 'coste_envio', label: 'Coste de Envío' },
    { key: 'fecha', label: 'Fecha' },
    { key: 'direccion', label: 'Dirección' },
    { key: 'estado', label: 'Estado' }
  ];
  data: any[] = [];
  totalElements: number = 0;
  currentPage: number = 0;
  pageSize: number = 10;

  constructor(private pedidoService: PedidoService) {} // Quitamos ActivatedRoute si no lo usas

  ngOnInit(): void {
    this.listarPedidos();
  }

  // private listarPedidos() {
  //   this.pedidoService.all(this.currentPage, this.pageSize).subscribe({
  //     next: (response: any) => {
  //       this.data = response.content;
  //       this.totalElements = response.totalElements;
  //       console.log('Pedidos obtenidos:', this.data);
  //     },
  //     error: (err) => {
  //       console.error('Error al obtener los pedidos:', err);
  //     }
  //   });
  // }

  private listarPedidos() {
    this.pedidoService.all(this.currentPage, this.pageSize).subscribe({
      next: (response: any) => {
        if (response && response.content) {
          this.data = response.content;
          this.totalElements = response.totalElements;
          console.log('Pedidos obtenidos:', this.data);
        } else {
          console.error('Respuesta inesperada:', response);
        }
      },
      error: (err) => {
        console.error('Error al obtener los pedidos:', err);
      }
    });
  }



  // Método para cambiar de página
  cambiarPagina(nuevaPagina: number) {
    if (nuevaPagina >= 0 && nuevaPagina < Math.ceil(this.totalElements / this.pageSize)) {
      this.currentPage = nuevaPagina;
      this.listarPedidos();
    }
  }

}

import { InvitacionService } from '../../../../../services/invitacion.service';
import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-linvitacion',
  imports: [CommonModule],
  templateUrl: './linvitacion.component.html'
})
export class LinvitacionComponent {
  title: string = 'Listado de Invitaciones';
  columnas: { key: string, label: string }[] = [
    { key: 'id', label: 'ID' },
    { key: 'nombre', label: 'Nombre' },
    { key: 'descripcion', label: 'Descripción' },
    { key: 'precio', label: 'Precio' },
    { key: 'stock', label: 'Stock' }
  ];
  data: any[] = [];
  totalElements: number = 0;
  currentPage: number = 0;
  pageSize: number = 10;

  invitacionService = inject(InvitacionService);

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
    this.invitacionService.all(this.currentPage, this.pageSize).subscribe({
      next: (response: any) => {
        this.data = response.content;
        this.totalElements = response.totalElements;
      },
      error: (err) => {
        console.error('Error al obtener las invitaciones:', err);
      }
    });
  }
}

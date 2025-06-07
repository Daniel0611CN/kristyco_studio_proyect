import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';
import { Page } from '../../../../models/interfaces/page.interface';
import { PedidoService } from "../../../../services/pedido.service";
import { EstadoPedidoPipe } from "../../../../pipes/estadoPedido.pipe";
import { SearchComponent } from "../../../shared/components/tables/search/search.component";
import { Pedido } from '../../../../models/interfaces/entities/pedido.interface';
import { PaginatorComponent } from "../../../shared/components/tables/paginator/paginator.component";
import { ColumnSortComponent } from '../../../shared/components/tables/column-sort/column-sort.component';
import { Producto } from '../../../../models/interfaces/entities/producto.interface';
import { PedidoFormComponent } from "./pedido-form/pedido-form.component";
import { SwalService } from '../../../shared/services/swal.service';

@Component({
  selector: 'app-pedidos',
  imports: [CommonModule, FormsModule, SearchComponent, ColumnSortComponent, PaginatorComponent, EstadoPedidoPipe, PedidoFormComponent],
  templateUrl: './pedidos.component.html'
})
export class ListadoPedidoComponent implements OnInit {

  swalService = inject(SwalService);
  pedidoService = inject(PedidoService);

  readonly title = signal<string>('Listado de Pedidos');
  searchText = signal<string>('');

  pedidos = signal<Pedido[]>([]);
  productosPedidos = signal<Producto[]>([]);

  selectedItem: Pedido | null = null;
  originalItem: Pedido | null = null;

  pageSize = signal<number>(10);
  totalSize = signal<number>(0);
  page = signal<number>(1);

  orderOutput?: { fieldQuery: string, order: string };
  orderNames: string[] = ['id', 'costeEnvio', 'direccion', 'estado', 'fecha', 'total'];
  orderNamesClear: any = { id: false, costeEnvio: false, direccion: false, estado: false, fecha: false, total: false };

  prepararEdicion(row: any): void {
    this.originalItem = JSON.parse(JSON.stringify(row));
    this.selectedItem = row;
    if (this.selectedItem) {
      this.productosPedidos.set(this.selectedItem.productos);
    }
  }

  ngOnInit() {
   this.getAllByPage();
  }

  processData(data: Page<Pedido> | Pedido[]) {
    if ((<Page<Pedido>>data).content !== undefined) {
      this.totalSize.set((<Page<Pedido>>data).totalElements);
      this.pedidos.set((<Page<Pedido>>data).content);
    } else {
      this.totalSize.set((<Pedido[]>data).length);
      this.pedidos.set((<Pedido[]>data).slice((this.page() - 1) * this.pageSize(), this.page() * this.pageSize()));
    }
  }

  getAllByPage(search?: string) {
    this.pedidoService.getWithFilters(this.orderOutput, this.page(), search)
      .subscribe({
        next: (data) => this.processData(data),
        error: (error) => console.error('Error al obtener los pedidos', error)
      });
  }

  delete(id: string): void {
    this.pedidoService.delete(id).subscribe({
      next: () => {
        this.pedidos.set(this.pedidos().filter((ped) => ped.id !== id));
        this.swalService.showSuccess('!Borrado¡', 'El pedido ha sido archivado correctamente.', 1500, false);
      },
      error: () => this.swalService.showError('Error', 'No se ha podido eliminar el pedido, por favor, inténtelo de nuevo.')
    });
  }

  showDelete(id: string) {
    this.swalService.showWarning('¿Estás seguro de que quieres archivar este pedido?', 'Sí, archivar').then((result) => {
      if (result.isConfirmed) {
        this.delete(id);
      }
    });
  }

  onSearch(searchData: { searchText: string }) {
    this.page.set(1);
    this.searchText.set(searchData.searchText);
    this.getAllByPage(this.searchText());
  }

  onChangePage(page: number) {
    this.page.set(page);
    this.getAllByPage(this.searchText());
  }

  onChangeOrder(orderOutput: {fieldQuery: string, order: string}) {
    this.orderOutput = orderOutput;
    this.toggleOrderNamesClear(this.orderOutput.fieldQuery);
    this.page.set(1);
    this.getAllByPage(this.searchText());
  }

  onClearOrder() {
    this.orderOutput = undefined;
    this.toggleOrderNamesClear();
    this.getAllByPage(this.searchText());
  }

  private toggleOrderNamesClear(exclude?: string) {
    for (const key of this.orderNames) {
      if (!exclude || key !== exclude) this.orderNamesClear[key] = !this.orderNamesClear[key];
    }
  }

}

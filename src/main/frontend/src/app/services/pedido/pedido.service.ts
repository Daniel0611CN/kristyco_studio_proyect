import { Pedido } from '../../models/interfaces/entities/pedido.interface';
import { environment } from '../../../environments/environment.development';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Page } from '../../models/interfaces/page.interface';
import { StorageService } from '../storage/storage.service';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {
  private apiPedidoUrl = environment.apiUrl + '/pedidos';

  httpClient = inject(HttpClient);
  storageService = inject(StorageService);

  getWithFilters(orderOutput?: { fieldQuery: string; order: string }, page?: number, search?: string, size: number = 10): Observable<Page<Pedido> | Pedido[]> {
    let queryParams: { sort?: string; page?: number; size?: number; direccion?: string; estado?: string; } = {};

    if (orderOutput) {
      queryParams.sort = orderOutput.fieldQuery + ',' + orderOutput.order;
    }

    if (page) {
      queryParams.page = page - 1;
      queryParams.size = size;
    }

    if (search && search != 'PENDIENTE' && search != 'EN_CAMINO' && search != 'ENTREGADO' && search != 'CANCELADO') {
      queryParams.direccion = search;
    } else if (search) {
      queryParams.estado = search;
    }

    const token = this.storageService.getUser()?.token;
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token || ''}`
    });

    return this.httpClient.get<Page<Pedido> | Pedido[]>(this.apiPedidoUrl, { headers, params: queryParams});
  }

  get(id: string): Observable<Pedido> {
    return this.httpClient.get<Pedido>(`${this.apiPedidoUrl}/${id}`);
  }

  create(pedido: Pedido): Observable<Pedido> {
    return this.httpClient.post<Pedido>(this.apiPedidoUrl, pedido);
  }

  update(id: string, pedido: Pedido): Observable<Pedido> {
    pedido.id = id;
    return this.httpClient.put<Pedido>(`${this.apiPedidoUrl}/${id}`, pedido);
  }

  delete(id: string): Observable<void> {
    return this.httpClient.delete<void>(`${this.apiPedidoUrl}/${id}`);
  }

}

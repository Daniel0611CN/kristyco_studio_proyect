import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../storage/storage.service';
import { Pedido } from '../../models/interfaces/entities/pedido.interface';
import { environment } from '../../../environments/environment.development';
import { Page } from '../../models/interfaces/page.interface';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {
  private apiPedidoUrl = environment.apiUrl + '/pedidos';

  httpClient = inject(HttpClient);
  storageService = inject(StorageService);

  // private get token(): string {
  //   return this.storageService.getUser()?.token || '';
  // }

  // private buildParams(page: number, size: number): HttpParams {
  //   return new HttpParams()
  //     .set('page', page.toString())
  //     .set('size', size.toString());
  // }

  // private buildHttpOptions(params?: HttpParams): { headers: HttpHeaders, params?: HttpParams } {
  //   return {
  //     headers: new HttpHeaders({
  //       'Content-Type': 'application/json',
  //       'Authorization': `Bearer ${this.token}`
  //     }),
  //     params
  //   };
  // }

  // all(page: number = 0, size: number = 10): Observable<any> {
  //   if (!this.token) {
  //     return new Observable(observer => {
  //       observer.error('No hay token disponible.');
  //       observer.complete();
  //     });
  //   }

  //   const params = this.buildParams(page, size);
  //   const httpOptions = this.buildHttpOptions(params);

  //   return this.httpClient.get<any>(this.apiPedidoUrl, httpOptions);
  // }

  // updatePedido(id: number, pedido: Pedido): Observable<any> {
  //   if (!this.token) {
  //     return new Observable(observer => {
  //       observer.error('No hay token disponible.');
  //       observer.complete();
  //     });
  //   }

  //   const httpOptions = this.buildHttpOptions();

  //   return this.httpClient.put<any>(
  //     this.apiPedidoUrl + '/' + id,
  //     pedido,
  //     httpOptions
  //   )
  // }

  getWithFilters(orderOutput?: {fieldQuery: string, order: string}, page?: number, search?: string): Observable<Page<Pedido> | Pedido[]> {

    let queryParams: any = {};

    if (orderOutput) {
      queryParams.sort = orderOutput.fieldQuery + ',' +orderOutput.order;
    }

    if (page) {
      queryParams.page = page-1;
    }

    if (search) {
      queryParams.nombre_like = search;
    }

    return this.httpClient.get<Page<Pedido> | Pedido[]>(this.apiPedidoUrl, { params: queryParams });

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

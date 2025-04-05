import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {
  private apiPedidoUrl = 'http://localhost:8080/api/v1/pedidos/';

  constructor(private httpClient: HttpClient, private storageService: StorageService) {}

  private get token(): string {
    return this.storageService.getUser()?.token || '';
  }

  private buildParams(page: number, size: number): HttpParams {
    return new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
  }

  private buildHttpOptions(params: HttpParams): { headers: HttpHeaders, params: HttpParams } {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      }),
      params
    };
  }

  all(page: number = 0, size: number = 10): Observable<any> {
    if (!this.token) {
      return new Observable(observer => {
        observer.error('No hay token disponible.');
        observer.complete();
      });
    }

    const params = this.buildParams(page, size);
    const httpOptions = this.buildHttpOptions(params);

    return this.httpClient.get<any>(this.apiPedidoUrl, httpOptions);
  }
}

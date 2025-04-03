import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {
  private apiPedidoUrl = 'http://localhost:8080/v1/api/pedidos/';

  constructor(private httpClient: HttpClient) {}

  private obtenerToken(): string {
    const token = localStorage.getItem('token');
    console.log('Token obtenido:', token);
    return token || '';
  }

  all(page: number = 0, size: number = 10): Observable<any> {
    const token = this.obtenerToken();
    if (!token) {
      console.error('No hay token disponible.');
      return new Observable(observer => {
        observer.error('No hay token disponible.');
        observer.complete();
      });
    }

    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      }),
      params
    };

    return this.httpClient.get<any>(this.apiPedidoUrl, httpOptions);
  }
}

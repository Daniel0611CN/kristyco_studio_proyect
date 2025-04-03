import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ColeccionService {
  private apiPedidoUrl = 'http://localhost:8080/api/v1/categorias/';

  constructor(private httpClient: HttpClient) {}
}

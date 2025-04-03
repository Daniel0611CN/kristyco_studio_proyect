import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class InvitacionService {
  private apiPedidoUrl = 'http://localhost:8080/api/v1/productos/';

  constructor(private httpClient: HttpClient) {}
}

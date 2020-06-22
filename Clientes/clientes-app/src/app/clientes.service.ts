import { Cliente } from './clientes/cliente';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  apiURL: string = environment.apiURLBase + '/api/clientes';
  constructor(private http: HttpClient) { }

  getCliente(): Cliente {
    const cliente: Cliente = {
      cpf: '123456789',
      dataCadastro: Date.now().toString(),
      nome: 'Fulano',
      id: 1
    };
    return cliente;
  }

  salvar(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(this.apiURL, cliente);
  }

  obterClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.apiURL);
  }

  obterClientePeloId(id: any): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.apiURL}/${id}`);
  }

  atualizarCliente(id: any, cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(`${this.apiURL}/${id}`, cliente);
  }

  deletarCliente(id: any): Observable<any> {
    return this.http.delete<any>(`${this.apiURL}/${id}`);
  }
}

import { Cliente } from './clientes/cliente';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

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
    return this.http.post<Cliente>('http://localhost:8080/api/clientes', cliente);
  }

  obterClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>('http://localhost:8080/api/clientes');
  }

  obterClientePeloId(id: any): Observable<Cliente> {
    return this.http.get<Cliente>(`http://localhost:8080/api/clientes/${id}`);
  }

  atualizarCliente(id: any, cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(`http://localhost:8080/api/clientes/${id}`, cliente);
  }

  deletarCliente(id: any): Observable<any> {
    return this.http.delete<any>(`http://localhost:8080/api/clientes/${id}`);
  }
}

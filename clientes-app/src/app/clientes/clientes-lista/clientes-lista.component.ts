import { ClientesService } from './../../clientes.service';
import { Cliente } from './../cliente';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.css']
})
export class ClientesListaComponent implements OnInit {
  clientes: Cliente[] = [];
  clienteSelecionado: Cliente;
  mensagemSucesso: string;
  mensagemErro: string;
  constructor(private service: ClientesService, private router: Router) { }

  ngOnInit(): void {
    this.service
      .obterClientes()
      .subscribe(result => this.clientes = result);
  }

  novoCadastro() {
    this.router.navigate(['/clientes-form']);
  }

  editarCadastro(id: any) {
    this.router.navigate([`/clientes-form/${id}`]);
  }

  confirmaDeletar(cliente: Cliente) {
    this.clienteSelecionado = cliente;
  }

  deletarCliente(cliente: Cliente) {
    this.service
      .deletarCliente(cliente.id)
      .subscribe(result => {
        this.mensagemSucesso = 'Cliente deletado com sucesso!';
        this.ngOnInit();
      }, err => {
        this.mensagemErro = 'Ocorreu um erro ao deletar o cliente.';
      });
  }

}

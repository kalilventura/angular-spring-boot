import { Router, ActivatedRoute } from '@angular/router';
import { ClientesService } from './../../clientes.service';
import { Cliente } from './../cliente';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {
  clientForm: FormGroup;
  success = false;
  errors: string[];
  cliente: Cliente = new Cliente();
  constructor(private fb: FormBuilder, private service: ClientesService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.editarCliente(this.activatedRoute.snapshot.paramMap.get('id'));
    this.buildForm();

  }

  editarCliente(id: any) {
    if (id) {
      this.service
        .obterClientePeloId(id)
        .subscribe(result => {
          this.cliente = result;
        },
          err => {
            console.error(err);
          });
    }
  }

  buildForm() {
    this.clientForm = this.fb.group({
      id: [''],
      dataCadastro: [''],
      nome: ['', [Validators.required]],
      cpf: ['', [Validators.required]],
    });
  }

  onSubmit() {
    if (this.cliente.id) {
      this.service
        .atualizarCliente(this.cliente.id, this.clientForm.value)
        .subscribe(result => {
          this.success = true;
          this.errors = [];
        }, (err) => {
          // console.error(err.error.errors);
          this.success = false;
          this.errors = err.error.errors;
        }, () => setTimeout(() => this.success = false, 900));
    }
    else {
      this.service
        .salvar(this.clientForm.value)
        .subscribe((response) => {
          console.log(response);
          this.cliente = response;
          this.success = true;
          this.errors = [];
        },
          (err) => {
            // console.error(err.error.errors);
            this.success = false;
            this.errors = err.error.errors;
          }, () => {
            setTimeout(() => this.success = false, 900);
          });
    }
  }

  voltarParaListagem() {
    this.router.navigate(['/clientes-lista']);
  }

}

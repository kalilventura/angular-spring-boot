import { Router } from '@angular/router';
import { AuthService } from './../../auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  usuarioLogado: string;
  constructor(private service: AuthService, private route: Router) { }

  ngOnInit(): void {
    this.usuarioLogado = this.service.getUsuarioAutenticado();
  }

  logout() {
    this.service.encerrarSessao();
    this.route.navigate(['/login']);
  }

}

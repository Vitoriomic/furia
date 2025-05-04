// src/app/auth/auth.component.ts
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './auth.component.html'
})
export class AuthComponent {
  private auth = new AuthService();

  isLogin = true;

  // Campos comuns
  email = '';
  senha = '';

  // Campos para cadastro
  nome = '';
  dataNascimento = '';
  regiao = '';
  redeSocial = '';
  timesFavoritos = ''; // "1, 2, 4"

  estados = [
    'Acre', 'Alagoas', 'Amapá', 'Amazonas', 'Bahia', 'Ceará',
    'Distrito Federal', 'Espírito Santo', 'Goiás', 'Maranhão', 'Mato Grosso',
    'Mato Grosso do Sul', 'Minas Gerais', 'Pará', 'Paraíba', 'Paraná',
    'Pernambuco', 'Piauí', 'Rio de Janeiro', 'Rio Grande do Norte',
    'Rio Grande do Sul', 'Rondônia', 'Roraima', 'Santa Catarina',
    'São Paulo', 'Sergipe', 'Tocantins'
  ];

  toggle() {
    this.isLogin = !this.isLogin;
  }

  submit() {
    if (this.isLogin) {
      this.auth.login(this.email, this.senha);
    } else {
      const ids = this.timesFavoritos
        .split(',')
        .map(id => parseInt(id.trim()))
        .filter(id => !isNaN(id));

      this.auth.register(
        this.nome,
        this.dataNascimento,
        this.regiao,
        this.redeSocial,
        this.email,
        this.senha,
        ids
      );
    }
  }
}
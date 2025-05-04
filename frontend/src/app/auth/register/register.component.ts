import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './register.component.html'
})
export class RegisterComponent {
  nome = '';
  dataNascimento = '';
  regiao = '';
  redeSocial = '';
  email = '';
  senha = '';
  timesFavoritos = '';
  estados = [
    'Acre', 'Alagoas', 'Amapá', 'Amazonas', 'Bahia', 'Ceará',
    'Distrito Federal', 'Espírito Santo', 'Goiás', 'Maranhão', 'Mato Grosso',
    'Mato Grosso do Sul', 'Minas Gerais', 'Pará', 'Paraíba', 'Paraná',
    'Pernambuco', 'Piauí', 'Rio de Janeiro', 'Rio Grande do Norte',
    'Rio Grande do Sul', 'Rondônia', 'Roraima', 'Santa Catarina',
    'São Paulo', 'Sergipe', 'Tocantins'
  ];
  private auth = new AuthService();

  submit() {
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

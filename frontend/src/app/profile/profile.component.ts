import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { NgSelectModule } from '@ng-select/ng-select';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, FormsModule, NgSelectModule],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  usuario = {
    nome: '',
    email: '',
    dataNascimento: '',
    regiao: '',
    redeSocial: '',
    timesFavoritos: [] as number[]
  };

  estados = [
    'Acre', 'Alagoas', 'Amapá', 'Amazonas', 'Bahia', 'Ceará',
    'Distrito Federal', 'Espírito Santo', 'Goiás', 'Maranhão', 'Mato Grosso',
    'Mato Grosso do Sul', 'Minas Gerais', 'Pará', 'Paraíba', 'Paraná',
    'Pernambuco', 'Piauí', 'Rio de Janeiro', 'Rio Grande do Norte',
    'Rio Grande do Sul', 'Rondônia', 'Roraima', 'Santa Catarina',
    'São Paulo', 'Sergipe', 'Tocantins'
  ];

  timesDisponiveis = [
    { id: 1, nome: 'Counter-Strike 2' },
    { id: 2, nome: 'Valorant' },
    { id: 3, nome: 'Furia FC (Fut7)' },
    { id: 4, nome: 'League of Legends' }
  ];
  
  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    const token = localStorage.getItem('token');
  
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
  
    this.http.get<any>('http://localhost:8080/usuarios/me', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    }).subscribe({
      next: user => {
        this.usuario = {
          nome: user.nome,
          email: user.email,
          dataNascimento: user.dataNascimento,
          regiao: user.regiao,
          redeSocial: user.redeSocial,
          timesFavoritos: user.timesFavoritosIds || []
        };        
        console.log('Favoritos recebidos:', this.usuario.timesFavoritos);
      },
      error: err => {
        console.error('Erro ao buscar perfil', err);
        localStorage.removeItem('token');
        this.router.navigate(['/login']);
      }
    });
  }
  
  onCheckboxChange(event: Event, id: number): void {
    const checked = (event.target as HTMLInputElement).checked;
    const index = this.usuario.timesFavoritos.indexOf(id);
  
    if (checked && index === -1) {
      this.usuario.timesFavoritos.push(id);
    } else if (!checked && index > -1) {
      this.usuario.timesFavoritos.splice(index, 1);
    }
  }
  
  

  salvar(): void {
    const token = localStorage.getItem('token');
  
    this.http.put('http://localhost:8080/usuarios/me', {
      nome: this.usuario.nome,
      dataNascimento: this.usuario.dataNascimento,
      regiao: this.usuario.regiao,
      redeSocial: this.usuario.redeSocial,
      timesFavoritosIds: this.usuario.timesFavoritos
    }, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    }).subscribe({
      next: () => alert('Perfil atualizado com sucesso!'),
      error: () => alert('Erro ao atualizar o perfil.')
    });
  }
}  
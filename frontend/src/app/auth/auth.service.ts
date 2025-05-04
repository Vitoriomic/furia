// src/app/auth/auth.service.ts
import { inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

const API_URL = 'http://localhost:8080'; // ajuste se necessário

export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);

  login(email: string, senha: string) {
    this.http.post<{ token: string }>(`${API_URL}/auth/login`, { email, senha })
      .subscribe({
        next: ({ token }) => {
          localStorage.setItem('token', token);
          this.router.navigate(['/games']);
        },
        error: () => alert('Erro ao fazer login. Verifique os dados.')
      });
  }

  register(
    nome: string,
    dataNascimento: string,
    regiao: string,
    redeSocial: string,
    email: string,
    senha: string,
    timesFavoritosIds: number[]
  ) {
    const payload = {
      nome,
      dataNascimento,
      regiao,
      redeSocial,
      email,
      senha,
      timesFavoritosIds
    };
  
    this.http.post('http://localhost:8080/usuarios', payload)
      .subscribe({
        next: () => {
          alert('Cadastro realizado com sucesso!');
          this.router.navigate(['/auth']);
        },
        error: () => alert('Erro ao se cadastrar.')
      });
  }  

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/auth']);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }
}

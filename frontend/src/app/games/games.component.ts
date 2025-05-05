import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-games',
  standalone: true,
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.scss'],
  imports: [CommonModule, FormsModule]
})
export class GamesComponent implements OnInit {
  jogos: any[] = [];
  jogoSelecionado: any = null;
  filtroSelecionado: string = '';
  resultadoVotacao: any = null;
  tiposFiltro: string[] = ['Counter-Strike 2', 'Valorant', 'Furia FC (Fut7)'];

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }  

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.carregarJogos();
  }

  carregarJogos() {
    this.http.get<any[]>('http://localhost:8080/jogos').subscribe({
      next: (dados) => this.jogos = dados,
      error: () => alert('Erro ao carregar os jogos.')
    });
  }

  formatarData(data: string): string {
    const date = new Date(data);
    return date.toLocaleDateString('pt-BR', { day: '2-digit', month: 'long', year: 'numeric' }).toUpperCase();
  }

  formatarHora(data: string): string {
    const date = new Date(data);
    return date.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' });
  }

  abrirModal(jogo: any) {
    this.jogoSelecionado = jogo;
  
    this.http.get(`http://localhost:8080/votos/${jogo.id}/resultado`).subscribe({
      next: (resultado) => {
        this.resultadoVotacao = resultado;
      },
      error: () => {
        this.resultadoVotacao = null;
      }
    });
  }
  

  fecharModal() {
    this.jogoSelecionado = null;
  }

  votar(opcao: 'FURIA' | 'EMPATE' | 'ADVERSARIO') {
    if (!this.jogoSelecionado) return;
  
    const token = localStorage.getItem('token');
    if (!token) {
      alert('Você precisa estar logado para votar.');
      return;
    }
  
    const jogoId = this.jogoSelecionado.id;
    const url = `http://localhost:8080/votos/${jogoId}?opcao=${opcao}`;
  
    this.http.post(url, {}).subscribe({
      next: () => {
        alert('Voto computado com sucesso!');
        this.fecharModal();
      },
      error: (err) => {
        if (err.status === 401) {
          alert('Você precisa estar logado para votar.');
        } else {
          alert('Erro ao enviar o voto.');
        }
      }
    });
  }  
  

  jogosFiltrados(): any[] {
    if (!this.filtroSelecionado) return this.jogos;
    return this.jogos.filter(j => j.timeFuria === this.filtroSelecionado);
  }
}

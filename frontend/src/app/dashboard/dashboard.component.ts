import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { DashboardMapComponent } from './dashboard-map/dashboard-map.component';
import { DashboardFavoriteTeamsComponent } from './dashboard-ranking/dashboard-teams.component';
import { DashboardSocialComponent } from './dashboard-social/dashboard-social.component';


@Component({
  selector: 'app-dashboard',
  standalone: true,
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  imports: [CommonModule, DashboardMapComponent,
     DashboardFavoriteTeamsComponent, DashboardSocialComponent]
})
export class DashboardComponent implements OnInit {
  ranking: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<any[]>('http://localhost:8080/usuarios/ranking-estados').subscribe({
      next: data => this.ranking = data,
      error: err => alert('Erro ao buscar ranking de estados')
    });
  }

  ngAfterViewInit(): void {
    const estadosDestacados = ['SP', 'RJ', 'MG'];
    estadosDestacados.forEach(uf => {
      const el = (document.querySelector(`object`) as HTMLObjectElement)?.contentDocument?.getElementById(uf);
      if (el) {
        el.classList.add('estado-destaque');
      }
    });
  }
  
}


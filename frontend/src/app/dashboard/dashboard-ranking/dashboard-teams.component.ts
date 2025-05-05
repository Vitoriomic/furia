import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard-favorite-teams',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard-teams.component.html',
  styleUrls: ['./dashboard-teams.component.scss']
})
export class DashboardFavoriteTeamsComponent implements OnInit {
  ranking: { time: string, quantidade: number }[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<any[]>('http://localhost:8080/usuarios/publico').subscribe(data => {
      const countMap: Record<string, number> = {};

      data.forEach(user => {
        user.timesFavoritos.forEach((time: string) => {
          countMap[time] = (countMap[time] || 0) + 1;
        });
      });

      this.ranking = Object.entries(countMap)
        .map(([time, quantidade]) => ({ time, quantidade }))
        .sort((a, b) => b.quantidade - a.quantidade);
    });
  }
}

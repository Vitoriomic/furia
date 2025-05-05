import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard-social',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard-social.component.html',
  styleUrls: ['./dashboard-social.component.scss']
})
export class DashboardSocialComponent implements OnInit {
  redes: { nome: string; redeSocial: string }[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<any[]>('http://localhost:8080/usuarios/publico').subscribe(data => {
      this.redes = data.map(user => ({
        nome: user.nome,
        redeSocial: user.redeSocial
      }));
    });
  }
}

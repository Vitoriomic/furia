import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { HttpClient } from '@angular/common/http';
import { CommonModule, NgIf } from '@angular/common';

@Component({
  selector: 'app-dashboard-map',
  standalone: true,
  imports: [NgIf, CommonModule],
  templateUrl: './dashboard-map.component.html',
  styleUrls: ['./dashboard-map.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class DashboardMapComponent implements OnInit {
  svgContent: SafeHtml = '';
  tooltipVisible = false;
  tooltipX = 0;
  tooltipY = 0;
  tooltipText = '';

  torcedores: Record<string, number> = {};

  estadoToSigla: Record<string, string> = {
    'ACRE': 'AC',
    'ALAGOAS': 'AL',
    'AMAPÁ': 'AP',
    'AMAZONAS': 'AM',
    'BAHIA': 'BA',
    'CEARÁ': 'CE',
    'DISTRITO FEDERAL': 'DF',
    'ESPÍRITO SANTO': 'ES',
    'GOIÁS': 'GO',
    'MARANHÃO': 'MA',
    'MATO GROSSO': 'MT',
    'MATO GROSSO DO SUL': 'MS',
    'MINAS GERAIS': 'MG',
    'PARÁ': 'PA',
    'PARAÍBA': 'PB',
    'PARANÁ': 'PR',
    'PERNAMBUCO': 'PE',
    'PIAUÍ': 'PI',
    'RIO DE JANEIRO': 'RJ',
    'RIO GRANDE DO NORTE': 'RN',
    'RIO GRANDE DO SUL': 'RS',
    'RONDÔNIA': 'RO',
    'RORAIMA': 'RR',
    'SANTA CATARINA': 'SC',
    'SÃO PAULO': 'SP',
    'SERGIPE': 'SE',
    'TOCANTINS': 'TO',
    'BRASÍLIA': 'DF'
  };
  
  normalizeEstado(nome: string): string {
    return nome
      .toLowerCase()
      .normalize('NFD') 
      .replace(/[\u0300-\u036f]/g, '') 
      .trim();
  }
  

  constructor(private sanitizer: DomSanitizer, private http: HttpClient) {}

  ngOnInit(): void {
    this.loadSVG();
    this.fetchData();
  }

  fetchData(): void {
    this.http.get<any[]>('http://localhost:8080/usuarios/ranking-estados').subscribe(data => {
      this.torcedores = {};
      data.forEach(item => {
        const estadoNormalizado = this.normalizeEstado(item.estado);
        this.torcedores[estadoNormalizado] = item.quantidade;
      });
      this.updateEventHandlers();
    });
  }

  loadSVG(): void {
    fetch('/assets/br.svg')
      .then(response => response.text())
      .then(svg => {
        this.svgContent = this.sanitizer.bypassSecurityTrustHtml(svg);
        setTimeout(() => this.updateEventHandlers(), 0);
      });
  }

  updateEventHandlers(): void {
    const svgElement = document.querySelector('#svg-container svg');
    if (!svgElement) return;
  
    svgElement.querySelectorAll<SVGElement>('path').forEach(stateEl => {
      const rawName = stateEl.getAttribute('name')?.trim();
      if (!rawName) return;
  
      const stateName = this.normalizeEstado(rawName);
  
      stateEl.classList.add('state');
      stateEl.setAttribute('fill', '#2c2c2c');
  
      stateEl.addEventListener('mousemove', (event: MouseEvent) => {
        const count = this.torcedores[stateName] || 0;
        this.tooltipText = `${rawName}: ${count} torcedores`;
        this.tooltipVisible = true;
        this.tooltipX = event.clientX + 10;
        this.tooltipY = event.clientY + 10;
      });
  
      stateEl.addEventListener('mouseleave', () => {
        this.tooltipVisible = false;
      });
    });
  }
  
  
  
}

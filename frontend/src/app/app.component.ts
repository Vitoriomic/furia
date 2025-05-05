import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RouterOutlet, RouterModule } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule, NgIf],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  constructor(public router: Router) {}
}

// src/app/app.routes.ts
import { Routes } from '@angular/router';
import { AuthComponent } from './auth/auth.component';
import { ProfileComponent } from './profile/profile.component';
import { GamesComponent } from './games/games.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { authGuard } from './auth/auth.guard';

export const routes: Routes = [
  { path: 'auth', component: AuthComponent },
  { path: 'games', component: GamesComponent }, // público
  { path: 'dashboard', component: DashboardComponent }, // público
  { path: 'profile', component: ProfileComponent, canActivate: [authGuard] }, // restrito
  { path: '', redirectTo: 'games', pathMatch: 'full' },
  { path: '**', redirectTo: 'games' }
];

import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { GamesComponent } from './games/games.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProfileComponent } from './profile/profile.component';
import { authGuard } from './auth/auth.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'games', component: GamesComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'perfil', component: ProfileComponent, canActivate: [authGuard] },
  { path: '', redirectTo: 'games', pathMatch: 'full' },
  { path: '**', redirectTo: 'games' }
];

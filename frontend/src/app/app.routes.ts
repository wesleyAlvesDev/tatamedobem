import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';
import { AppShellComponent } from './core/layout/app-shell/app-shell.component';
import { LoginPageComponent } from './modules/auth/pages/login-page/login-page.component';
import { RegisterPageComponent } from './modules/auth/pages/register-page/register-page.component';
import { DashboardPageComponent } from './modules/dashboard/pages/dashboard-page/dashboard-page.component';
import { StudentsPageComponent } from './modules/students/pages/students-page/students-page.component';
import { AttendancePageComponent } from './modules/attendance/pages/attendance-page/attendance-page.component';
import { ReportsPageComponent } from './modules/reports/pages/reports-page/reports-page.component';

export const routes: Routes = [
  { path: 'auth/login', component: LoginPageComponent },
  {
    path: '',
    component: AppShellComponent,
    canActivate: [authGuard],
    children: [
      { path: '', component: DashboardPageComponent },
      { path: 'alunos', component: StudentsPageComponent },
      { path: 'frequencia', component: AttendancePageComponent },
      { path: 'relatorios', component: ReportsPageComponent },
      { path: 'usuarios/cadastro', component: RegisterPageComponent }
    ]
  },
  { path: '**', redirectTo: '' }
];

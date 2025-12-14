import { Routes } from '@angular/router';

import { LoginComponent } from './auth/login/login';
import { RegisterComponent } from './auth/register/register';
import { ClassesListComponent } from './classes/classes-list/classes-list';
import { BookingListComponent } from './booking/booking-list/booking-list';

import { authGuard } from './auth/auth-guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  {
    path: 'classes',
    component: ClassesListComponent,
    canActivate: [authGuard]
  },
  {
    path: 'booking',
    component: BookingListComponent,
    canActivate: [authGuard]
  },

  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', redirectTo: 'login' }
];

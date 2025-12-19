import { Routes } from '@angular/router';

import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import {ClassComponent} from './classes/class.component';
import { BookingListComponent } from './booking/booking.component';

import { authGuard } from './auth/auth-guard';
import {classResolver} from './classes/class.resolver';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  {
    path: 'classes',
    component: ClassComponent,
    canActivate: [authGuard],
    resolve: {
      sessions: classResolver
    }
  },
  {
    path: 'booking',
    component: BookingListComponent,
    canActivate: [authGuard]
  },

  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: 'login' }
];

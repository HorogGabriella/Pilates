import { Routes } from '@angular/router';

import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import {ClassComponent} from './classes/class.component';
import { BookingComponent } from './booking/booking.component';

import { authGuard } from './auth/auth-guard';
import {classResolver} from './classes/class.resolver';
import {bookingResolver} from './booking/booking.resolver';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  {
    path: 'classes',
    component: ClassComponent,
    canActivate: [authGuard],
    resolve: {
      data: classResolver
    },
    runGuardsAndResolvers: 'always'
  },

  {
    path: 'booking',
    component: BookingComponent,
    canActivate: [authGuard],
    resolve: {
      bookings: bookingResolver
    },
    runGuardsAndResolvers: 'always'
  },

  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: 'login' }
];

import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { ClassComponent } from './class/class.component';
import { BookingComponent } from './booking/booking.component';
import { authGuard } from './auth/auth.guard';

export const routes: Routes = [
  { path: 'registration', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },

  {
    path: 'class',
    component: ClassComponent,
    canActivate: [authGuard]
  },

  {
    path: 'booking',
    component: BookingComponent,
    canActivate: [authGuard]
  },

  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

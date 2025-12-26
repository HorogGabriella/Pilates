import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from './auth.service';
import { map, catchError, of } from 'rxjs';

export const authGuard: CanActivateFn = () => {
  const router = inject(Router);
  const token = localStorage.getItem('token');

  if (!token) {
    router.navigate(['/login']);
    return false;
  }
  return true;
};

export const adminGuard: CanActivateFn = () => {
  const auth = inject(AuthService);
  const router = inject(Router);

  const token = localStorage.getItem('token');
  if (!token) {
    router.navigate(['/login']);
    return false;
  }

  return auth.getMe().pipe(
    map(me => {
      const isAdmin = (me.roles ?? []).includes('ADMIN');
      if (!isAdmin) router.navigate(['/classes']);
      return isAdmin;
    }),
    catchError(() => {
      router.navigate(['/login']);
      return of(false);
    })
  );
};

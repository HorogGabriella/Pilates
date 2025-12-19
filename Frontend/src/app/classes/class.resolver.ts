import { ResolveFn } from '@angular/router';
import { inject } from '@angular/core';
import { forkJoin } from 'rxjs';
import { ClassService } from './class.service';
import { HttpClient } from '@angular/common/http';

export const classResolver: ResolveFn<any> = () => {
  const classes = inject(ClassService);
  const http = inject(HttpClient);

  return forkJoin({
    sessions: classes.getAll(),
    bookings: http.get<any[]>('http://localhost:8080/api/foglalas/sajat')
  });
};

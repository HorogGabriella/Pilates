import { ResolveFn } from '@angular/router';
import { inject } from '@angular/core';
import { ClassService } from './class.service';

export const classResolver: ResolveFn<any> = () => {
  const service = inject(ClassService);
  return service.getAll();
};

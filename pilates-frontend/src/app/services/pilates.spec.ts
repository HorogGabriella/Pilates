import { TestBed } from '@angular/core/testing';

import { PilatesService } from './pilates.service';

describe('Pilates', () => {
  let service: PilatesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PilatesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

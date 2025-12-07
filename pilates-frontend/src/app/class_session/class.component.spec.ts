import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ClassComponent } from './class.component';

describe('ClassComponent', () => {
  let component: ClassComponent;
  let fixture: any;
  let httpMock: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        ClassComponent,
        HttpClientTestingModule
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ClassComponent);
    component = fixture.componentInstance;
    httpMock = TestBed.inject(HttpTestingController);

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load sessions from API', () => {
    const mockData = [
      {
        id: 1,
        type: 'Pilates kezdő',
        trainerName: 'Kiss Anna',
        startTime: '2025-01-10T10:00:00',
        capacity: 10,
        bookedspots: 3
      }
    ];

    const req = httpMock.expectOne('http://localhost:8080/api/classes');
    expect(req.request.method).toBe('GET');
    req.flush(mockData);

    expect(component.sessions.length).toBe(1);
    expect(component.sessions[0].classtype).toBe('Pilates kezdő');
  });

  afterEach(() => {
    httpMock.verify();
  });
});

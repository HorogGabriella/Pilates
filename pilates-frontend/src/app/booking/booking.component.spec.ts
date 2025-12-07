import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { BookingComponent } from './booking.component';

describe('BookingComponent', () => {
  let component: BookingComponent;
  let fixture: any;
  let httpMock: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        BookingComponent,
        HttpClientTestingModule
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(BookingComponent);
    component = fixture.componentInstance;
    httpMock = TestBed.inject(HttpTestingController);
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should load bookings from API', () => {
    const mockData = [
      { foglalasId: 1, oraId: 2, resztvevoNeve: "Teszt Elek" }
    ];

    const req = httpMock.expectOne('http://localhost:8080/api/foglalas');
    expect(req.request.method).toBe('GET');
    req.flush(mockData);

    expect(component.foglalasok.length).toBe(1);
    expect(component.foglalasok[0].resztvevoNeve).toBe("Teszt Elek");
  });

  afterEach(() => {
    httpMock.verify();
  });
});

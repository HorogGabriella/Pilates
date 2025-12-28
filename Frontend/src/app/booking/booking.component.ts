import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { HttpClient } from '@angular/common/http';
import { finalize } from 'rxjs/operators';

export interface BookingDto {
  foglalasId: number;
  oraId: number;
  oratipus: string;
  oktato: string;
  idopont: string;
}

@Component({
  selector: 'app-booking',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  bookings: BookingDto[] = [];
  message = '';
  errorMessage = '';

  cancellingId: number | null = null;

  private readonly api = 'http://localhost:8080/api/foglalas';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private auth: AuthService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.route.data.subscribe(data => {
      const bookings = (data['bookings'] ?? []) as BookingDto[];
      this.bookings = this.sortByDate(bookings);
    });
  }

  private sortByDate(list: BookingDto[]): BookingDto[] {
    return [...list].sort(
      (a, b) => new Date(a.idopont).getTime() - new Date(b.idopont).getTime()
    );
  }

  private refreshFromBackend(): void {
    this.http.get<BookingDto[]>(`${this.api}/sajat`).subscribe({
      next: (data) => (this.bookings = this.sortByDate(data)),
      error: () => {
      }
    });
  }

  cancelBooking(foglalasId: number): void {
    this.message = '';
    this.errorMessage = '';

    if (!confirm('Biztosan le szeretnéd mondani ezt az órát?')) return;

    const id = Number(foglalasId);
    this.cancellingId = id;

    this.bookings = this.bookings.filter(b => Number(b.foglalasId) !== id);

    this.http
      .delete<void>(`${this.api}/${id}`)
      .pipe(finalize(() => (this.cancellingId = null)))
      .subscribe({
        next: () => {
          this.message = 'Foglalás sikeresen lemondva.';
          setTimeout(() => (this.message = ''), 1500);

          this.refreshFromBackend();
        },
        error: (err) => {
          this.errorMessage =
            (typeof err?.error === 'string' ? err.error : '') ||
            `Nem sikerült lemondani. (${err.status})`;

          this.refreshFromBackend();
        }
      });
  }

  logout(): void {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}

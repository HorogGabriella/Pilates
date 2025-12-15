import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-booking',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingListComponent implements OnInit {

  bookings: any[] = [];
  message: string = '';
  errorMessage: string = '';

  constructor(
    private http: HttpClient,
    private auth: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadBookings();
  }

  loadBookings(): void {
    this.message = '';
    this.errorMessage = '';

    this.http
      .get<any[]>('http://localhost:8080/api/foglalas/sajat')
      .subscribe({
        next: (data) => {
          this.bookings = data.sort(
            (a, b) =>
              new Date(a.datum).getTime() -
              new Date(b.datum).getTime()
          );
        },
        error: () => {
          this.errorMessage = 'Nem sikerült betölteni a foglalásaidat.';
        }
      });
  }

  cancelBooking(bookingId: number): void {
    this.message = '';
    this.errorMessage = '';

    if (!confirm('Biztosan le szeretnéd mondani ezt az órát?')) {
      return;
    }

    this.http
      .delete(`http://localhost:8080/api/foglalas/${bookingId}`)
      .subscribe({
        next: () => {
          this.message = 'Foglalás sikeresen lemondva.';
          this.loadBookings(); // 🔁 frissítés
        },
        error: () => {
          this.errorMessage = 'Nem sikerült lemondani a foglalást.';
        }
      });
  }

  logout(): void {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}

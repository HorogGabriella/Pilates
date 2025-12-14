import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClassesService } from '../classes.service';
import { PilatesClass } from '../class';
import { Router, RouterModule } from '@angular/router';
import { Auth } from '../../auth/auth';

@Component({
  selector: 'app-classes-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './classes-list.html',
  styleUrls: ['./classes-list.css']
})
export class ClassesListComponent implements OnInit {

  classes: PilatesClass[] = [];
  myBookedClassIds: number[] = [];
  message = '';
  errorMessage = '';

  constructor(
    private classesService: ClassesService,
    private auth: Auth,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadClasses();
    this.loadMyBookings();
  }

  loadClasses(): void {
    this.classesService.getClasses().subscribe(data => {
      this.classes = data.sort(
        (a, b) =>
          new Date(a.datum).getTime() -
          new Date(b.datum).getTime()
      );
    });
  }

  loadMyBookings(): void {
    this.classesService.getMyBookings().subscribe(bookings => {
      this.myBookedClassIds = bookings.map(b => b.oraId);
    });
  }

  isAlreadyBooked(c: PilatesClass): boolean {
    return this.myBookedClassIds.includes(c.id);
  }

  getFreeSlots(c: PilatesClass): number {
    return c.kapacitas - c.foglalt;
  }

  bookClass(c: PilatesClass): void {
    this.message = '';
    this.errorMessage = '';

    this.classesService.bookClass(c.id).subscribe({
      next: () => {
        this.message = 'Sikeres jelentkezés!';
        this.loadClasses();
        this.loadMyBookings();
      },
      error: () => {
        this.errorMessage = 'Erre az órára már jelentkeztél vagy nincs hely.';
      }
    });
  }

  logout(): void {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}

import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
/*import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
 */
//import { } from '../auth/auth.service';
import {Router, RouterLink} from '@angular/router';
//import {EventsService} from '../events.service';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [
    FormsModule,
    /*
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
     */
    RouterLink
  ],
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  name = '';
  email = '';
  password = '';

  constructor(
    private authService: AuthService,
    private eventService: EventsService,
    private router: Router) {}

  onRegister() {
    this.authService.register(this.name, this.email, this.password).subscribe(
      (response: string) => {
        localStorage.setItem('token', response)
        console.log('Registration Successful');
        this.router.navigate(['/login']);
      },
      error => {
        console.error('Hiba történt a regisztráció során', error);
      }
    );
  }
}

import { Component, ChangeDetectorRef } from '@angular/core';
import { environment } from '../../environments/environment';
import { Router, RouterModule } from '@angular/router';
import {getUserById, User, authenticateUser} from "../Middleware/User";


@Component ({
    selector: 'app-login',
    imports: [RouterModule],
    standalone: true,
    templateUrl: './Login.component.html',
    styleUrls: ["./Login.component.css"],
})

export class LoginComponent {
    username : string = "";
    password : string = "";
    user : any;
    constructor(private router: Router, private cdRef: ChangeDetectorRef) {}

    updateUsername(event: Event) : void {
        const input = (event.target as HTMLInputElement).value;
        this.username = input;
    }

    updatePassword(event: Event) : void {
        const input = (event.target as HTMLInputElement).value;
        this.password = input;
    }

    async authenticate() {
      console.log(this.username);
      console.log(this.password);

      this.user = await authenticateUser(this.username, this.password);

      console.log(this.user)

      if (this.user) {
        this.router.navigateByUrl(`/adventurer/${this.user.id}`).then(() => {
          window.location.reload();
        });
        //this.router.navigate(['/adventurer', this.user.id]); // Navigates to /user/:id
      } else {
        return null;
      }

      return null;
    }
}

import { Component, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import {createUser, User, checkLoggedIn, logOutUser} from "../Middleware/User"


@Component ({
    selector: 'app-navbar',
    templateUrl: './Navbar.component.html',
    standalone: true,
    imports: [CommonModule, RouterModule],
    styleUrls: ["./Navbar.component.css"],
})

export class NavBarComponent {
  drawerVisibility : boolean = false;
  user : any = null;
  constructor(private router: Router, private cdRef: ChangeDetectorRef) {}


  async ngOnInit() {
    this.user = await checkLoggedIn()

    if (this.user != null) {
      console.log(this.user)
    }
  }

  updateDrawerVisibility() {
    this.drawerVisibility = !(this.drawerVisibility)
  }

  async logoutSubmitHandler() {
    var logged_out = false;
    logged_out = await logOutUser();
    if (logged_out) {
      this.router.navigate(['/login']); // Navigates to /user/:id
      this.user = null;
      this.cdRef.detectChanges();
    }
  }
}


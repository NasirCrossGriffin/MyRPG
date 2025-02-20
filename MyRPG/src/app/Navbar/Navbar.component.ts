import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component ({
    selector: 'app-navbar',
    templateUrl: './Navbar.component.html',
    standalone: true,
    imports: [CommonModule, RouterModule],
    styleUrls: ["./Navbar.component.css"],
})

export class NavBarComponent {
  drawerVisibility : boolean = false;

  updateDrawerVisibility() {
    this.drawerVisibility = !(this.drawerVisibility)
  }
}


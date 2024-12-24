import { Component } from '@angular/core';

@Component ({
    selector: 'app-login',
    templateUrl: './Login.component.html',
    styleUrls: ["./Login.component.css"],
})

export class LoginComponent {
    username : string = "";
    password : string = "";

    updateUsername(event: Event) : void {
        const input = (event.target as HTMLInputElement).value;
        this.username = input;
    }

    updatePassword(event: Event) : void {
        const input = (event.target as HTMLInputElement).value;
        this.password = input;
    }

    authenticate() : void {
        console.log(this.username);
        console.log(this.password);
    }
}
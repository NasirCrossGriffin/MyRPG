import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { environment } from '../../environments/environment';

@Component ({
    selector: 'app-adventurer',
    imports: [CommonModule],
    standalone: true,
    templateUrl: './Adventurer.component.html',
    styleUrls: ["./Adventurer.component.css"],
})

export class AdventurerComponent {
    loadingimage : string = "/jokerrunning.gif"
    loaded : boolean = false;
    className: string = "";
    subClassName: string ="";
    userId: string | null = '';
    advStrength : number = 0;
    advSpeed : number = 0;
    advEndurance : number = 0;
    advMath : number = 0;
    advHistory : number = 0;
    advScience : number = 0;
    advLanguage : number = 0;
    subStrength : number = 0;
    subSpeed : number = 0;
    subEndurance : number = 0;
    subMath : number = 0;
    subHistory : number = 0;
    subScience : number = 0;
    subLanguage : number = 0;
    profilePic : string = "";
    bannerPic : string = "";
    user_name : string = "";
    level : string = "";
    toNextLevel : string = "";
    BASE_URL : string = environment.BASE_URL;

    constructor(private route: ActivatedRoute, private cdRef: ChangeDetectorRef) {}

    async ngOnInit() {
        this.userId = this.route.snapshot.paramMap.get('id'); // Get the parameter
        console.log('User ID:', this.userId);
        const userresponse = await fetch(`${this.BASE_URL}/api/users/${this.userId}`, {
            headers : {'Content-Type' : 'application/json'},
            method : 'GET'
        })

        if (userresponse.ok) {
            const user = await userresponse.json();
            console.log(user);
            this.profilePic = user.profilePic;
            this.bannerPic = user.bannerPic;
            this.user_name = user.username;
            this.className = user.className;
            this.subClassName= user.subClassName;
            if (this.className === "Scholar") {
                const scholarresponse = await fetch(`${this.BASE_URL}/api/scholar/${user.classId}`,{
                    headers : {'Content-Type' : 'application/json'},
                    method : 'GET'
                })
                if (scholarresponse.ok) {
                    const scholar = await scholarresponse.json();
                    console.log(scholar)
                    this.advMath = scholar.math;
                    this.advLanguage = scholar.language;
                    this.advHistory = scholar.history;
                    this.advScience = scholar.science;
                    this.level = scholar.level;
                    this.toNextLevel = scholar.toNextLevel;
                    console.log("All values updated")
                    this.loaded = true;
                    console.log("This is the value of loaded: " + this.loaded);
                }
            }

            if (this.className === "Warrior") {
                const warriorresponse = await fetch(`${this.BASE_URL}/api/scholar/${user.classId}`,{
                    headers : {'Content-Type' : 'application/json'},
                    method : 'GET'
                })
                if (warriorresponse.ok) {
                    const warrior = await warriorresponse.json();
                    this.advStrength = warrior.strength;
                    this.advEndurance = warrior.endurance;
                    this.advSpeed = warrior.speed;
                    console.log("All values updated")
                    this.loaded = true;
                    console.log("This is the value of loaded: " + this.loaded);
                }
            }

            if (this.subClassName === "Scholar") {
                const scholarresponse = await fetch(`${this.BASE_URL}/api/scholar/${user.classId}`,{
                    headers : {'Content-Type' : 'application/json'},
                    method : 'GET'
                })
                if (scholarresponse.ok) {
                    const scholar = await scholarresponse.json();
                    console.log(scholar)
                    this.subMath = scholar.math;
                    this.subLanguage = scholar.language;
                    this.subHistory = scholar.history;
                    this.subScience = scholar.science;
                    console.log("All values updated")
                    this.loaded = true;
                    console.log("This is the value of loaded: " + this.loaded);
                }
            }

            if (this.subClassName === "Warrior") {
                const warriorresponse = await fetch(`${this.BASE_URL}/api/scholar/${user.classId}`,{
                    headers : {'Content-Type' : 'application/json'},
                    method : 'GET'
                })
                if (warriorresponse.ok) {
                    const warrior = await warriorresponse.json();
                    this.subStrength = warrior.strength;
                    this.subEndurance = warrior.endurance;
                    this.subSpeed = warrior.speed;
                    console.log("All values updated")
                    this.loaded = true;
                    console.log("This is the value of loaded: " + this.loaded);
                }
            }

        }

        this.cdRef.detectChanges();
    }
}

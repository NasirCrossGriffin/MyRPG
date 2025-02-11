import { Component } from '@angular/core';
import { environment } from '../../environments/environment';
import { CommonModule } from '@angular/common';

@Component ({
    selector: 'app-signup',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './Signup.component.html',
    styleUrls: ["./Signup.component.css"],
})

export class SignupComponent {
	username : string = "";
	password : string = "";
	email : string = "";
	class : string = "";
	subclass : string =  "";
  profilepic : File | null = null;
  bannerpic : File | null = null;
  profilePicURL : string = "";
  bannerPicURL : string = "";

	BASE_URL : string = environment.BASE_URL;

	updateUsername(e: Event) {
    console.log("Update username accessed")
		const input =  (e.target as HTMLInputElement).value;
		this.username = input;
	}

	updatePassword(e: Event) {
    console.log("Update password accessed")
		const input = (e.target as HTMLInputElement).value;
		this.password = input;
	}

	updateEmail(e: Event) {
    console.log("Update email accessed")
		const input = (e.target as HTMLInputElement).value;
		this.email = input;
	}

	updateClass(e: Event) {
    console.log("Update class accessed")
		const input = (e.target as HTMLInputElement).value;
		this.class = input;
    console.log(this.class)
	}

	updateSubclass(e: Event) {
    console.log("Update subclass accessed")
		const input = (e.target as HTMLInputElement).value;
		this.subclass = input;
    console.log(this.subclass)

	}

  updateProfilePic(e: Event) {
    console.log("Update profilepic accessed")
		const input = e.target as HTMLInputElement;
		if (input.files && input.files.length > 0) {
      this.profilepic = input.files[0]; // Assign the first selected file
      this.profilePicURL = URL.createObjectURL(this.profilepic);
      console.log("Selected file:", this.profilepic);
    } else {
        console.log("No file selected");
    }
	}

  updateBannerPic(e: Event) {
    console.log("Update bannerpic accessed")
		const input = e.target as HTMLInputElement;
		if (input.files && input.files.length > 0) {
      this.bannerpic = input.files[0]; // Assign the first selected file
      this.bannerPicURL = URL.createObjectURL(this.bannerpic);
      console.log("Selected file:", this.bannerpic);
    } else {
        console.log("No file selected");
    }
	}

	async submitHandler () {
    console.log(this.BASE_URL)
    console.log("Button press registered")
    //Logic for if the warrior is chosen
		if (this.class === "Warrior") {
      console.log("Class is warrior")
			const warrior = {
        charName: this.username,
				level: 0,
        toNextLevel : 0,
        experience : 0,
        strength : 0,
				speed : 0,
				stamina : 0,
				skill : 0
			};

			const classresponse = await fetch(`${this.BASE_URL}/api/warrior`, {
					headers: { 'Content-Type': 'application/json'} ,
					method: 'POST',
					body: JSON.stringify(warrior)
			})

			if (classresponse.ok) {
        const newWarrior = await classresponse.json();
        console.log(newWarrior);
        console.log(parseInt(newWarrior.id, 10))
				if (this.subclass === "Scholar") {
					const scholar = {
            charName: this.username,
            level: 0,
            toNextLevel : 0,
            experience : 0,
						math : 0,
						language : 0,
						history : 0,
						science : 0
					};

					const subclassresponse = await fetch(`${this.BASE_URL}/api/scholar`, {
							headers: { 'Content-Type': 'application/json'} ,
							method: 'POST',
							body: JSON.stringify(scholar)
					})

					if (subclassresponse.ok) {
            const newScholar = await subclassresponse.json();
            const profilePicData = new FormData();
            if (this.profilepic) {
              profilePicData.append("file", this.profilepic);
            }

            const bannerPicData = new FormData();
            if (this.bannerpic) {
              bannerPicData.append("file", this.bannerpic);
            }

            const profilepicresponse = await fetch(`${this.BASE_URL}/buckets/insert`, {
							method: 'POST',
							body: profilePicData
					  })

            console.log(profilepicresponse)

            const bannerpicresponse = await fetch(`${this.BASE_URL}/buckets/insert`, {
							method: 'POST',
							body: bannerPicData
					  })

            console.log(bannerpicresponse)

            console.log(newScholar);

            console.log(parseInt(newScholar.id, 10))
            if (profilepicresponse.ok && bannerpicresponse.ok) {
              const profilePicUrl = profilepicresponse.ok ? await profilepicresponse.text() : null;
              const bannerPicUrl = bannerpicresponse.ok ? await bannerpicresponse.text() : null;
              const newuser = {
                username : this.username,
                password : this.password,
                email : this.email,
                profilePic : profilePicUrl,
                bannerPic : bannerPicUrl,
                className : "Warrior",
                classId : parseInt(newWarrior.id, 10),
                subClassName : "Scholar",
                subClassId : parseInt(newScholar.id, 10)
              }

              const usersresponse = await fetch(`${this.BASE_URL}/api/users`, {
                headers: { 'Content-Type': 'application/json'},
                method: 'POST',
                body: JSON.stringify(newuser)
              })

              if (usersresponse.ok) {
                console.log("User created successfully")
              }
            } else {
              console.log(profilepicresponse);
              console.log(bannerpicresponse);
            }
					}
        }
			} else {
        console.log(classresponse.json())
      }
		}


    //Logic for if the scholar is chosen
		if (this.class === "Scholar") {
			const scholar = {
        charName: this.username,
        level: 0,
        toNextLevel : 0,
        experience : 0,
				math : 0,
				language : 0,
				history : 0,
				science : 0
			};

			const classresponse = await fetch(`${this.BASE_URL}/api/scholar`, {
          headers: { 'Content-Type': 'application/json'},
					method: 'POST',
					body: JSON.stringify(scholar)
			})

      if (classresponse.ok) {
        const newScholar = await classresponse.json();
        console.log(newScholar);
        console.log(parseInt(newScholar.id, 10))
        const warrior = {
          charName: this.username,
          level: 0,
          toNextLevel : 0,
          experience : 0,
          strength : 0,
          speed : 0,
          stamina : 0,
          skill : 0
        };

        const subclassresponse = await fetch(`${this.BASE_URL}/api/warrior`, {
            headers: { 'Content-Type': 'application/json'},
            method: 'POST',
            body: JSON.stringify(warrior)
        })

        if (subclassresponse.ok) {
          const profilePicData = new FormData();
          if (this.profilepic) {
            profilePicData.append("file", this.profilepic);
          }

          const bannerPicData = new FormData();
          if (this.bannerpic) {
            bannerPicData.append("file", this.bannerpic);
          }

          const profilepicresponse = await fetch(`${this.BASE_URL}/buckets/insert`, {
            method: 'POST',
            body: profilePicData
          })

          console.log(profilepicresponse)

          const bannerpicresponse = await fetch(`${this.BASE_URL}/buckets/insert`, {
            method: 'POST',
            body: bannerPicData
          })

          console.log(bannerpicresponse)

          const newWarrior = await subclassresponse.json();

          console.log(newWarrior);

          console.log(parseInt(newWarrior.id, 10));

          if (profilepicresponse.ok && bannerpicresponse.ok) {
            const profilePicUrl = profilepicresponse.ok ? await profilepicresponse.text() : null;
            const bannerPicUrl = bannerpicresponse.ok ? await bannerpicresponse.text() : null;
            const user = {
              username : this.username,
              password : this.password,
              email : this.email,
              profilePic : profilePicUrl,
              bannerPic : bannerPicUrl,
              className : "Scholar",
              classId : parseInt(newScholar.id, 10),
              subClassName : "Warrior",
              subClassId : parseInt(newWarrior.id, 10)
            }

            const usersresponse = await fetch(`${this.BASE_URL}/api/users`, {
              headers: { 'Content-Type': 'application/json'} ,
              method: 'POST',
              body: JSON.stringify(user)
            })

            if (usersresponse.ok) {
              const newUser = await usersresponse.json()
              console.log(newUser);
              console.log("User created successfully")
            }
          } else {
            console.log(profilepicresponse);
            console.log(bannerpicresponse);
          }
        }
      } else {
        console.log( await classresponse.json())
      }
		}
	}
}

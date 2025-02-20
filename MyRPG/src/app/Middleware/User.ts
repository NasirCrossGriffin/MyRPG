import { environment } from '../../environments/environment';

const BASE_URL : string = environment.BASE_URL;

export type User = {
  username : String
  password : String;
  email : String;
  level : number;
  toNextLevel : number;
  classId : number;
  profilePic : String;
  bannerPic : String;
}

export async function createUser(protoUser : User) {
  try {
    const userresponse = await fetch (`${BASE_URL}/api/users`,{
      headers : {"content-type" : "application/json"},
      method : "POST",
      body : JSON.stringify(protoUser)
    })

    if (userresponse.ok) {
      const newUser = await userresponse.json();
      return newUser;
    } else {
      return null;
    }
  } catch (err) {
    console.log(err);
    return null;
  }
}

export async function getUserById(userId : String) {
  try {
    const userresponse = await fetch (`${BASE_URL}/api/users/${userId}`,{
      headers : {"content-type" : "application/json"},
      method : "GET",
    })

    if (userresponse.ok) {
      const retrievedUser = await userresponse.json();
      return retrievedUser;
    } else {
      return null;
    }
  } catch (err) {
    console.log(err);
    return null;
  }
}

export async function getAllUsers() {
  try {
    const userresponse = await fetch (`${BASE_URL}/api/users`,{
      headers : {"content-type" : "application/json"},
      method : "GET",
    })

    if (userresponse.ok) {
      const listofusers = await userresponse.json();
      return listofusers;
    } else {
      return null;
    }
  } catch (err) {
    console.log(err);
    return null;
  }
}

import { environment } from '../../environments/environment';

const BASE_URL : string = environment.BASE_URL;

export type characterClass = {
  name : String
}

export async function createCharacterClass(protoCharacterClass : characterClass) {
  try {
    const classresponse = await fetch (`${BASE_URL}/api/class`,{
      headers : {"content-type" : "application/json"},
      method : "POST",
      body : JSON.stringify(protoCharacterClass)
    })

    if (classresponse.ok) {
      const newCharacterClass = await classresponse.json();
      return newCharacterClass;
    } else {
      return null;
    }
  } catch (err) {
    console.log(err);
    return null;
  }
}

export async function getCharacterClassById(classId : String) {
  try {
    const classresponse = await fetch (`${BASE_URL}/api/class/${classId}`,{
      headers : {"content-type" : "application/json"},
      method : "GET",
      })

    if (classresponse.ok) {
      const retrievedCharacterClass = await classresponse.json();
      return retrievedCharacterClass;
    } else {
      return null;
    }
  } catch (err) {
    console.log(err);
    return null;
  }
}


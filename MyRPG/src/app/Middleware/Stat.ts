import { environment } from '../../environments/environment';

const BASE_URL : string = environment.BASE_URL;

export type Stat = {
  name : String
  value : number;
  classId : number;
}

export async function createStat(protoStat : Stat) {
  console.log(JSON.stringify(protoStat))

  try {
    const statresponse = await fetch (`${BASE_URL}/api/stat`,{
      headers : {"content-type" : "application/json"},
      method : "POST",
      body : JSON.stringify(protoStat)
    })

    if (statresponse.ok) {
      console.log("Stat created successfully")
      const newStat = await statresponse.json();
      return newStat;
    } else {
      return null;
    }
  } catch (err) {
    console.log(err);
    return null;
  }
}

export async function getStatsByClassId(classId : String) {
  console.log("The class id specified is " + classId)

  try {
    const statresponse = await fetch (`${BASE_URL}/api/stat/class/${classId}`,{
      headers : {"content-type" : "application/json"},
      method : "GET",
    })

    if (statresponse.ok) {
      console.log("Stat retrieved successfully")
      const listofstats = await statresponse.json();
      return listofstats;
    } else {
      return null;
    }
  } catch (err) {
    console.log(err);
    return null;
  }
}



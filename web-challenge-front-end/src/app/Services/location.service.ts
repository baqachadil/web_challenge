import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private httpClient: HttpClient) { }

  serverlink = "http://localhost:8080"

  getPosition(): Promise<any>
  {
    return new Promise((resolve, reject) => {

      navigator.geolocation.getCurrentPosition(resp => {

          resolve({lng: resp.coords.longitude, lat: resp.coords.latitude});
        },
        err => {
          reject(err);
        });
    });

  }

  setPosition(lat, lon):Observable<any>{
    let jwtToken = localStorage.getItem("JwtToken")
    console.log(jwtToken)
    return this.httpClient.post(this.serverlink+"/setLocation?lat="+lat+"&lon="+lon,null, {headers : new HttpHeaders({'authorization':jwtToken, 'Content-Type':'application/json'})})
  }
}

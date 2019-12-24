import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationServiceService {

  serverlink = "http://localhost:8080"
  jwtToken = null
  constructor(private httpClient: HttpClient) { }


  public login(username, password):Observable<any>{
    return this.httpClient.post(this.serverlink+"/login", {username: username, password: password},
    {
      headers: {'Content-Type': 'application/json'}, 
      observe : 'response'
    })
  }

  public getToken(){
    return localStorage.getItem("JwtToken")
  }

  public logOut(){
    this.jwtToken = null
    localStorage.removeItem("JwtToken")
    localStorage.removeItem("isAdmin")
  }

  public saveToken(token){
    localStorage.setItem("JwtToken", token)
    var decoded = jwt_decode(token); 
    decoded.roles.forEach(role => {
      if(role.authority == 'ADMIN') localStorage.setItem('isAdmin','true')
    });
  }

  public register(username, password):Observable<any>{
    return this.httpClient.post(this.serverlink+"/register",{username: username, password: password},{
      headers: {'Content-Type': 'application/json'}
    })
  }    

  public loadToken(){
    this.jwtToken = localStorage.getItem("JwtToken")
  }

  public getNearbyShops(){
    if(this.jwtToken == null) this.loadToken()
    return this.httpClient.get(this.serverlink+"/shops",{headers:new HttpHeaders({'authorization':this.jwtToken})})
  }

  public likeShop(shop_id){
    if(this.jwtToken == null) this.loadToken()
    return this.httpClient.post(this.serverlink+"/likeShop/"+shop_id,null, {headers : new HttpHeaders({'authorization':this.jwtToken, 'Content-Type':'application/json'})})
  }

  public dislikeShop(shop_id){
    if(this.jwtToken == null) this.loadToken()
    return this.httpClient.post(this.serverlink+"/dislikeShop/"+shop_id,null, {headers : new HttpHeaders({'authorization':this.jwtToken, 'Content-Type':'application/json'})})
  }

  public getPreferredShops(){
    if(this.jwtToken == null) this.loadToken()
    return this.httpClient.get(this.serverlink+"/preferred/", {headers : new HttpHeaders({'authorization':this.jwtToken})})
  }

  public deletePreferredShop(shop_id){
    if(this.jwtToken == null) this.loadToken()
    return this.httpClient.delete(this.serverlink+"/delete/"+shop_id, {headers : new HttpHeaders({'authorization':this.jwtToken})})
  }

}

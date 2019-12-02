import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';
import { User } from '../_models/user';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // store the URL so we can redirect after logging in
  redirectUrl: string;
  private baseUrl = 'http://localhost:8090/ShopStore/';
  private jwtHelper = new JwtHelperService();
  decodedToken: any;
  token: any;
  currentUser: User;
  isLogged: boolean = false;
  constructor(private router: Router, private httpClient: HttpClient) {
  }

  logIn(userlogin: any){
    localStorage.setItem('isLoggedIn', 'true');
    return this.httpClient.post(this.baseUrl + 'login', userlogin)
      .pipe(map((response: any) => {
        if (response) {
          localStorage.setItem('token', response.accessToken);
          this.decodedToken = this.jwtHelper.decodeToken(response.accessToken);
          this.getUserByUsername(response.user.username);
          this.isLogged = true;
        }
      }));
  }
  getUserByUsername(username: string) {
    return this.httpClient.get(this.baseUrl + 'users/' + username)
      .pipe(map((response: any) => {
        if (response) {
          console.log("res:"+response);
          localStorage.setItem('user', JSON.stringify(response));
          this.currentUser = response;
        }
      }));
  }
  loggedIn() {
    this.token = localStorage.getItem('token');
    return !this.jwtHelper.isTokenExpired(this.token)&&this.isLogged;
  }

  logOut(): void {
    this.isLogged = false;
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}

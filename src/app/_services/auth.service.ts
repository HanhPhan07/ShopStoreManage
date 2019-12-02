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
  constructor(private router: Router, private httpClient: HttpClient) {}

  logIn(userlogin: any) {
    localStorage.setItem('isLoggedIn', 'true');
    return this.httpClient.post(this.baseUrl + 'login', userlogin)
      .pipe(map((response: any) => {
        if (response) {
          localStorage.setItem('token', response.accessToken);
          this.decodedToken = this.jwtHelper.decodeToken(response.accessToken);
          localStorage.setItem('user', JSON.stringify(response.userDetail));
          this.currentUser = response.userDetail;
        }
      }));
  }

  loggedIn() {
    this.token = localStorage.getItem('token');
    // console.log(this.jwtHelper.getTokenExpirationDate(this.token));
    // console.log(this.jwtHelper.isTokenExpired(this.token));
    return !this.jwtHelper.isTokenExpired(this.token);
  }

  logOut(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}

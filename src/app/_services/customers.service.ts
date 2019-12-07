import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PaginatedResult } from '../_models/pagination';
import { map } from 'rxjs/operators';
import { KhachHang } from '../_models/khachhang';
@Injectable({
  providedIn: 'root'
})
export class CustomersService {

  constructor( private httpClient: HttpClient ) { }
  getAllKhachHang(): Observable<KhachHang[]> {
    return this.httpClient.get<any>(environment.baseUrl + 'allcustomers');
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { PaginatedResult } from '../_models/pagination';
import { map } from 'rxjs/operators';
import { SanPham } from '../_models/sanpham';
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor( private httpClient: HttpClient ) { }
  getListProduct(): Observable<SanPham[]> {
    return this.httpClient.get<any>(environment.baseUrl + 'allproducts');
  }

}

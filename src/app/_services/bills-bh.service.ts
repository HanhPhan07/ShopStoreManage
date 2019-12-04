import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, fromEventPattern } from 'rxjs';
import { environment } from 'src/environments/environment';

import { HoaDonBanHang } from '../_models/hoadonbanhang';

@Injectable({
  providedIn: 'root'
})
export class BillsBhService {

constructor( private httpClient: HttpClient ) { }
  getListBill(): Observable<HoaDonBanHang> {
    return this.httpClient.get<HoaDonBanHang>(environment.baseUrl + 'billBHs');
  }
}

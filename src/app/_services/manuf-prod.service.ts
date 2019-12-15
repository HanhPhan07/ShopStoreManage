import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { PaginatedResult } from '../_models/pagination';
import { map } from 'rxjs/operators';
import { NhaSanXuat } from '../_models/nhasanxuat';
import { NhaCungCap } from '../_models/nhacungcap';

@Injectable({
  providedIn: 'root'
})
export class ManufProdService {

  constructor( private httpClient: HttpClient) { }
  getListManu(): Observable<NhaSanXuat[]> {
    return this.httpClient.get<any>(environment.baseUrl + 'allNSX');
  }
}

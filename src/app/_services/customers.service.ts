import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PaginatedResult } from '../_models/pagination';
import { map } from 'rxjs/operators';
import { KhachHang } from '../_models/khachhang';
import { KhachHangDTO } from '../_models/khachhangDTO';
@Injectable({
  providedIn: 'root'
})
export class CustomersService {

  constructor( private httpClient: HttpClient ) { }
  getAllKHNonPag(): Observable<KhachHang[]> {
    return this.httpClient.get<any>(environment.baseUrl + 'allcustomers');
  }

  getAllKhachHang(page?: number, pageSize?: number): Observable<PaginatedResult<KhachHangDTO[]>> {
    const paginatedResult: PaginatedResult<KhachHangDTO[]> = new PaginatedResult<KhachHangDTO[]>();
    let params = new HttpParams();
    if (page != null && pageSize != null) {
      params = params.append('pageNumber', (page - 1).toString());
      params = params.append('pageSize', pageSize.toString());
    }
    return this.httpClient.get<any>(environment.baseUrl + 'customers', { observe: 'response', params })
      .pipe(map(response => {
        paginatedResult.result = response.body.content;
        paginatedResult.pagination = {
          currentPage: response.body.pageable.pageNumber + 1,
          totalItems: response.body.totalElements,
          totalPages: response.body.totalPages,
          itemsPerPage: response.body.pageable.pageSize
        };
        return paginatedResult;
        }));
  }
}

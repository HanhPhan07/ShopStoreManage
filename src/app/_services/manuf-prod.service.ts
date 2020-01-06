import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
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
  getManuProductPage(page?: number, pageSize?: number): Observable<PaginatedResult<NhaSanXuat[]>> {
    const paginatedResult: PaginatedResult<NhaSanXuat[]> = new PaginatedResult<NhaSanXuat[]>();
    let params = new HttpParams();
    if (page != null && pageSize != null) {
      params = params.append('pageNumber', (page - 1).toString());
      params = params.append('pageSize', pageSize.toString());
    }
    return this.httpClient.get<any>(environment.baseUrl + 'NSXs', { observe: 'response', params })
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
  addManuProduct(manuProd: NhaSanXuat): Observable<any> {
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.httpClient.post(environment.baseUrl + 'NSXs', manuProd, { headers: headers });
  }
  updateManuProduct(manuProd: NhaSanXuat) {
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.httpClient.put(environment.baseUrl + 'NSXs', manuProd, { headers : headers });
  }
  deleteManuProduct(id: number) {
    return this.httpClient.delete(environment.baseUrl + 'NSXs/' + id);
  }
  getSearchListManuProduct(
    page?: number, pageSize?: number, searchTerm?: string):
    Observable<PaginatedResult<NhaSanXuat[]>> {
      const paginatedResult: PaginatedResult<NhaSanXuat[]> = new PaginatedResult<NhaSanXuat[]>();
      let params = new HttpParams();
      if (page != null && pageSize != null) {
        params = params.append('pageNumber', (page - 1).toString());
        params = params.append('pageSize', pageSize.toString());
      }
      if (searchTerm != null) {
        params = params.append('searchTerm', searchTerm);
      }
      return this.httpClient.get<any>(environment.baseUrl + 'ManuProd/search', { observe: 'response', params })
      .pipe(map(response => {
        if (response.body != null) {
          paginatedResult.result = response.body.content;
          paginatedResult.pagination = {
          currentPage: response.body.pageable.pageNumber + 1,
          totalItems: response.body.totalElements,
          totalPages: response.body.totalPages,
          itemsPerPage: response.body.pageable.pageSize
        };
        }
        return paginatedResult;
      }));
  }
}

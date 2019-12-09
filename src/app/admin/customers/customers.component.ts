import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { UsersService } from 'src/app/_services/users.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomersService } from 'src/app/_services/customers.service';
import { Pagination, PaginatedResult } from 'src/app/_models/pagination';
import { KhachHang } from 'src/app/_models/khachhang';
import { KhachHangDTO } from 'src/app/_models/khachhangDTO';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})

export class CustomersComponent implements OnInit {

  pagination: Pagination;
  searchkey: string;
  modalRef: BsModalRef;
  itemsPerPage = 2;
  listCustomers: KhachHangDTO[];
  baseDataListCustomers: KhachHangDTO[];
  fitlerloaikhachhang: number;
  constructor(
    private modalService: BsModalService,
    private activatedRoute: ActivatedRoute,
    private customers: CustomersService) { }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }
  ngOnInit() {
    this.activatedRoute.data.subscribe(data => {
      this.listCustomers = data.customers.result;
      this.pagination = data.customers.pagination;
    });
    console.log(this.listCustomers);

  }
  getListCustomers() {
    this.customers.getAllKhachHang(
      this.pagination.currentPage, this.pagination.itemsPerPage).subscribe(
        (data: PaginatedResult<KhachHangDTO[]>) => {
          if (typeof(data.pagination) !== 'undefined') {
            this.pagination = data.pagination;
          } else {
              this.pagination = {
                currentPage: 1,
                totalItems: 0,
                totalPages: 0,
                itemsPerPage: this.itemsPerPage
              };
          }
          this.listCustomers = data.result;
      },
        error => console.log(error)
      );

  }

  resetFilter() {
    this.searchkey = '';
  }
  editCusromer(khachhang: KhachHang) { }
  deleteCustomer(ten: string) { }

  pageChanged(event: any): void {
    this.pagination.currentPage = event.page;
    this.getListCustomers();
  }

  getTotalCustomers() {
    if (this.listCustomers != null) {
      return this.listCustomers.length;
    }
  }
  getTotalMoney() {
    let total = 0;
    if (this.listCustomers != null) {
      for (const customers of this.listCustomers) {
        total += customers.tonggia;
      }
    }
    return total;
  }

  getTotalDet() {
    let total = 0;
    if (this.listCustomers != null) {
      for (const customers of this.listCustomers) {
        total += customers.tongno;
      }
    }
    return total;
  }

  filter() {
    if (this.fitlerloaikhachhang == 2) {
      this.listCustomers = this.baseDataListCustomers.filter(this.isDebt);
    } else if (this.fitlerloaikhachhang == 1) {
      this.listCustomers = this.baseDataListCustomers;
    } else {
      this.listCustomers = this.baseDataListCustomers.filter(this.isNonDebt);
    }
  }

  isDebt(customers: KhachHangDTO) {
    return customers.tongno = 0 ;
  }

  isNonDebt(customers: KhachHangDTO) {
    return customers.tongno > 0 ;
  }

  updateListBill(data) {
    this.listCustomers = data; // lưu dữ liệu bên html
    this.baseDataListCustomers = []; // lưu dữ liệu gốc
    if (data != null ) {
      this.listCustomers.forEach(x => {
        this.baseDataListCustomers.push(x);
      });
    }
  }




}

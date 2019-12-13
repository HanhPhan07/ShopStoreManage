import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { UsersService } from 'src/app/_services/users.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomersService } from 'src/app/_services/customers.service';
import { Pagination, PaginatedResult } from 'src/app/_models/pagination';
import { KhachHang } from 'src/app/_models/khachhang';
import { KhachHangDTO } from 'src/app/_models/khachhangDTO';
import { error } from 'util';
import { TabHeadingDirective } from 'ngx-bootstrap';

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
  searchTerm: string;
  customer: KhachHang;
  ten: string;
  makhachhang: string;
  sdt: string;
  email: string;
  diachi: string;
  ngaysinh: Date;
  gioitinh: boolean;

  constructor(
    private modalService: BsModalService,
    private activatedRoute: ActivatedRoute,
    private customersService: CustomersService) { }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }
  ngOnInit() {
    this.searchTerm = '' ;
    this.fitlerloaikhachhang = 0;
    this.activatedRoute.data.subscribe(data => {
      this.listCustomers = data.customers.result;
      this.pagination = data.customers.pagination;
    });

  }
  search() {
    this.customersService.getSearchKhachHang(
      this.pagination.currentPage, this.pagination.itemsPerPage, this.searchTerm ).subscribe(
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
          this.updateListBill(data.result);
      },
        error => console.log(error)
      );
      console.log(this.searchTerm);
      
  }

  getListCustomers() {
    this.customersService.getAllKhachHang(
      this.pagination.currentPage, this.pagination.itemsPerPage ).subscribe(
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
          this.updateListBill(data.result);
      },
      // tslint:disable-next-line: no-shadowed-variable
      error => console.log(error)
      );

  }

  resetFilter() {
    this.searchkey = '';
  }
  editCusromer(khachhang: KhachHang) { }

  pageChanged(event: any): void {
    
    this.pagination.currentPage = event.page;
    this.search();
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
    if (this.fitlerloaikhachhang == 1) {
      this.listCustomers = this.baseDataListCustomers.filter(this.isDebt);
    } else if (this.fitlerloaikhachhang == 2) {
      this.listCustomers = this.baseDataListCustomers;
    } else {
      this.listCustomers = this.baseDataListCustomers.filter(this.isNonDebt);
    }
  }

  isDebt(customers: KhachHangDTO) {
    return customers.tongno === 0 ;
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


  deleteCustomer(makhachhang: string) {
    if (confirm('Bạn có thực sự muốn xóa khách hàng này ?')) {
      this.customersService.deleteCustomer(makhachhang).subscribe(() => {
        this.getListCustomers();
        alert('Xóa thành công !');
      },
        // tslint:disable-next-line: no-shadowed-variable
        error => {
          console.log(error);
          alert('Xóa thất bại !');
        }
    );
    }
  }

  addcustomer(customer: KhachHang) {
    if (!this.checkInputCustomer()) {
      alert('Vui lòng nhập đầy đủ thông tin !' );
    } else {
        this.customer = new KhachHang();
        this.customer.makhachhang = this.makhachhang;
        this.customer.ten = this.ten;
        this.customer.sdt = this.sdt;
        this.customer.email = this.email;
        this.customer.diachi = this.diachi;
        this.customer.ngaysinh = this.ngaysinh;
        this.customer.gioitinh = this.gioitinh;
        this.customersService.addCustomer(this.customer).subscribe(data => {
          alert('Thêm thành công !');
          this.updateListBill(data.result);
        },
          error => console.log(error));
      }
  }

  checkInputCustomer() {
    if (this.ten === undefined ||  this.makhachhang === undefined ||
      this.sdt === undefined || this.gioitinh === undefined ) {return false; }
    return true;
  }
}

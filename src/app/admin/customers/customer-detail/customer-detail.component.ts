import { Component, OnInit, TemplateRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { HoaDonBanHang } from '../../../_models/hoadonbanhang';
import { KhachHang } from 'src/app/_models/khachhang';
import { CustomersService } from 'src/app/_services/customers.service';
import { Pagination, PaginatedResult } from 'src/app/_models/pagination';

@Component({
  selector: 'app-customer-detail',
  templateUrl: './customer-detail.component.html',
  styleUrls: ['./customer-detail.component.scss']
})
export class CustomerDetailComponent implements OnInit {
  id: string;
  pagination: Pagination;
  modalRef: BsModalRef;
  isPurchaseHistory: boolean;
  listOrder: HoaDonBanHang[];
  usersdetail: KhachHang;
  itemsPerPage = 4;
  baseDataListProds: HoaDonBanHang[];
  listCusBill: HoaDonBanHang[];
  activatedRoute: any;
  constructor(
    private route: ActivatedRoute,
    private modalService: BsModalService,
    //private customersBillService: CustomersBillService
  ) {}

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  ngOnInit() {
    this.isPurchaseHistory = true;
    this.route.data.subscribe( data => {
      this.usersdetail = data.khachhang;
    });
    this.route.params.subscribe(params => {
      this.id = params.id;
    });
     
  }

  emitChangeDebt(event: boolean) {
    this.isPurchaseHistory = event;
  }

  getListAllOrder() {
    return this.listOrder;
  }

  getListOrderDebt() {
    return this.listOrder.filter(e => (e.tonggia - e.giamgia - e.khachhangtra) < 0);
  }

  

  updateListProduct(data) {
    this.listCusBill = data;
    this.baseDataListProds = [];
    if (data != null ) {
      this.listCusBill.forEach(x => {
        this.baseDataListProds.push(x);
      });
    }
    console.log(this.listCusBill);
  }

}

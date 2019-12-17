import { Component, OnInit, Output, Input, EventEmitter  } from '@angular/core';
import { HoaDonBanHang } from '../../../../_models/hoadonbanhang';
import { Pagination, PaginatedResult } from 'src/app/_models/pagination';
import { BsModalRef, BsModalService } from 'ngx-bootstrap';
import { ActivatedRoute } from '@angular/router';
import { PurchaseHistoryService } from 'src/app/_services/purchase-history.service';

@Component({
  selector: 'app-purchase-history',
  templateUrl: './purchase-history.component.html',
  styleUrls: ['./purchase-history.component.scss']
})
export class PurchaseHistoryComponent implements OnInit {
  @Input() listOrder: HoaDonBanHang[];
  @Output('chageToDebt') change = new EventEmitter<boolean>();

  id: string;
  pagination: Pagination;
  modalRef: BsModalRef;
  isPurchaseHistory: boolean;
  itemsPerPage = 4;
  baseDataListBills: HoaDonBanHang[];
  listCusBill: HoaDonBanHang[];
  activatedRoute: ActivatedRoute;
  constructor(
    private route: ActivatedRoute,
    private purchaseHistoryService: PurchaseHistoryService
  ) {}

  ngOnInit() {
    this.listCusBill = new Array();
    this.baseDataListBills = [];
    this.route.params.subscribe(params => {
      this.id = params.id;
    });
    
    this.pagination = {
              currentPage: 1,
              totalItems: 0,
              totalPages: 0,
              itemsPerPage: this.itemsPerPage
            };
    this.getCustomerBills(this.id);
  }


  toggleToDebt(value: boolean) {
    this.change.emit(value);
  }

  getCustomerBills(makhachhang: string) {
    this.purchaseHistoryService.getListCustomerBill (
      makhachhang, this.pagination.currentPage, this.pagination.itemsPerPage).subscribe(
        (data: PaginatedResult<HoaDonBanHang[]>) => {
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
          this.updateListProduct(data.result);
        },
        error => console.log(error)
      );
  }

  updateListProduct(data) {
    this.listCusBill = data;
    this.baseDataListBills = [];
    if (data != null ) {
      this.listCusBill.forEach(x => {
        this.baseDataListBills.push(x);
      });
    }
    console.log(data);
  }

}

import { Component, OnInit, TemplateRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { HoaDonBanHang } from '../../../_models/hoadonbanhang';
@Component({
  selector: 'app-customer-detail',
  templateUrl: './customer-detail.component.html',
  styleUrls: ['./customer-detail.component.scss']
})
export class CustomerDetailComponent implements OnInit {
  id: string;
  modalRef: BsModalRef;
  isPurchaseHistory: boolean;
  listOrder: HoaDonBanHang[];
  constructor(
    private route: ActivatedRoute,
    private modalService: BsModalService
  ) {}

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  ngOnInit() {
    this.isPurchaseHistory = true;
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
}

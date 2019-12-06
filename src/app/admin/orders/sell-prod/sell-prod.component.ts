import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
@Component({
  selector: 'app-sell-prod',
  templateUrl: './sell-prod.component.html',
  styleUrls: ['./sell-prod.component.scss']
})
export class SellProdComponent implements OnInit {
  modalRef: BsModalRef;
  keySearchProd: string;
  constructor(private modalService: BsModalService) { }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }
  ngOnInit() {
  }

  searchProd() {

  }

}

import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {
  modalRefAddProd: BsModalRef;
  constructor(private modalService: BsModalService) { }

  openModal(template: TemplateRef<any>) {
    this.modalRefAddProd = this.modalService.show(template);
  }
  ngOnInit() {
  }

}

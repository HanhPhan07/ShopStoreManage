import { Component, OnInit, Output, Input, EventEmitter  } from '@angular/core';
import { SanPham } from 'src/app/_models/sanpham';

@Component({
  selector: 'app-choose-prod',
  templateUrl: './choose-prod.component.html',
  styleUrls: ['./choose-prod.component.scss']
})
export class ChooseProdComponent implements OnInit {
  @Input() listProd: SanPham[];
  @Output('SubmitChooseProd') submit = new EventEmitter<string>();

  currentProd: SanPham;
  constructor() { }

  ngOnInit() {
    if (this.listProd != null) {
      this.currentProd = this.listProd[0];
    }
  }

  changeCurrentProd(sp: SanPham) {
    this.currentProd = sp;
  }

  submitAddProd(masp) {
    this.submit.emit(masp);
  }
}

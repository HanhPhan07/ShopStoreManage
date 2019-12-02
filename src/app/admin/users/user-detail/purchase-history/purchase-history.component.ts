import { Component, OnInit, Output, Input, EventEmitter  } from '@angular/core';
import { HoaDonBanHang } from '../../../../_models/hoadonbanhang';

@Component({
  selector: 'app-purchase-history',
  templateUrl: './purchase-history.component.html',
  styleUrls: ['./purchase-history.component.scss']
})
export class PurchaseHistoryComponent implements OnInit {
  @Input() listOrder: HoaDonBanHang[];
  @Output('chageToDebt') change = new EventEmitter<boolean>();
  constructor() { }

  ngOnInit() {
  }

  toggleToDebt(value: boolean) {
    this.change.emit(value);
  }

}

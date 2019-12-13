import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { HoaDonBanHang } from '../../../../_models/hoadonbanhang';
@Component({
  selector: 'app-debt',
  templateUrl: './debt.component.html',
  styleUrls: ['./debt.component.scss']
})
export class DebtComponent implements OnInit {
  @Input() listOrder: HoaDonBanHang[];
  @Output('chageToPurchaseHistory') change = new EventEmitter<boolean>();
  constructor() { }

  ngOnInit() {
  }

  toggleToPurchaseHistory(value: boolean) {
    this.change.emit(value);
  }

  debt(){
    return 1;
  }
}

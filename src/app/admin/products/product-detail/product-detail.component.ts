import { Component, OnInit } from '@angular/core';
import { SanPham } from 'src/app/_models/sanpham';
import { Pagination } from 'src/app/_models/pagination';
import { BsModalRef } from 'ngx-bootstrap';
import { ChiTietDanhMuc } from 'src/app/_models/chitietdanhmuc';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.scss']
})
export class ProductDetailComponent implements OnInit {
  currentProd: SanPham;
  prodUpdate: SanPham;
  id: string;
  pagination: Pagination;
  modalRef: BsModalRef;
  isPurchaseHistory: boolean;
  listCateDetail: ChiTietDanhMuc[];
  proddetail: SanPham;
  itemsPerPage = 4;
  //baseDataListProds: HoaDonBanHang[];
  //listCusBill: HoaDonBanHang[];
  activatedRoute: any;

  constructor() { }

  ngOnInit() {
  }

}

import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { Observable, of } from 'rxjs';
import { TypeaheadMatch } from 'ngx-bootstrap';
import { mergeMap } from 'rxjs/operators';
import { ProductService } from 'src/app/_services/product.service';
import { SanPham } from 'src/app/_models/sanpham';
import { ActivatedRoute, Router } from '@angular/router';
import { HoaDonBanHang } from 'src/app/_models/hoadonbanhang';
import { ChiTietHoaDonBH } from 'src/app/_models/chitiethoadonbh';
import { KhachHang } from 'src/app/_models/khachhang';
import { User } from 'src/app/_models/user';
import { BillsBhService } from 'src/app/_services/bills-bh.service';
import { BillDetailBhService } from 'src/app/_services/bill-detail-bh.service';
@Component({
  selector: 'app-sell-prod',
  templateUrl: './sell-prod.component.html',
  styleUrls: ['./sell-prod.component.scss']
})


export class SellProdComponent implements OnInit {
  modalRef: BsModalRef;
  asyncSelected: string;
  asyncSelectedKhachHang: string;
  typeaheadLoading: boolean;
  dataSource: Observable<any>;
  dataSourceKhachHang: Observable<any>;
  statesComplex: SanPham[];
  statesComplexKhachHang: KhachHang[];
  hoadonbanhang: HoaDonBanHang;
  listchitiethoadon: ChiTietHoaDonBH[];
  chitiethoadon: ChiTietHoaDonBH;
  khachhang: KhachHang;
  i: number;
  methodPay: number;
  giamgiaBill: number;
  khachduaBill: number;
  currentUser: User;
  constructor(
    private modalService: BsModalService,
    private productService: ProductService,
    private activatedRoute: ActivatedRoute,
    private billService: BillsBhService,
    private detailBillService: BillDetailBhService,
    private router: Router
    ) {}

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }
  ngOnInit() {
    this.i = 0;
    this.giamgiaBill = 0;
    this.khachduaBill = 0;
    this.listchitiethoadon = [];
    this.currentUser = JSON.parse(localStorage.getItem('user'));
    this.activatedRoute.data.subscribe(data => {
      this.statesComplex = data.prods;
      this.statesComplexKhachHang = data.custs;
    });
    this.dataSource = Observable.create((observer: any) => {
      // Runs on every search
      observer.next(this.asyncSelected);
    })
    .pipe(
        mergeMap((token: string) => this.getStatesAsObservable(token))
    );
    this.dataSourceKhachHang = Observable.create((observer: any) => {
      // Runs on every search
      observer.next(this.asyncSelectedKhachHang);
    })
    .pipe(
        mergeMap((token: string) => this.getStatesAsObservableKhachHang(token))
    );

  }

  // Function for get Product
  loadProducts() {
    this.productService.getListProduct().subscribe(data => {
      this.statesComplex = data;
    },
      error => console.log(error)
    );
  }


  getStatesAsObservable(token: string): Observable<any> {
    const query = new RegExp(token, 'i');

    return of(
      this.statesComplex.filter((state: any) => {
        return query.test(state.name) || query.test(state.masp);
      })
    );
  }
  getStatesAsObservableKhachHang(token: string): Observable<any> {
    const query = new RegExp(token, 'i');
    return of(
      this.statesComplexKhachHang.filter((state: any) => {
        return query.test(state.makhachhang) || query.test(state.ten) || query.test(state.sdt);
      })
    );
  }

  changeTypeaheadLoading(e: boolean): void {
    this.typeaheadLoading = e;
  }

  typeaheadOnSelect(e: TypeaheadMatch): void {
    // init ChiTietHoaDon
    this.chitiethoadon = new ChiTietHoaDonBH();
    const prod = this.findProdByMaSP(e.value);
    if (prod !== undefined) {
      this.chitiethoadon.sanpham = prod;
      this.chitiethoadon.giamgia = 0;
      this.chitiethoadon.soluong = 1;
      this.chitiethoadon.gia = this.chitiethoadon.sanpham.giaban * this.chitiethoadon.soluong;
      this.i++;
      //this.chitiethoadon.id = this.i;
      // add ChiTietHoaDon to HoaDon
      this.listchitiethoadon.push(this.chitiethoadon);
    }
    this.asyncSelected = '';
  }

  typeaheadOnSelectKhachHang(e: TypeaheadMatch): void {
    this.khachhang = this.findCustByMaKH( e.value);
  }

  findProdByMaSP(masp: string): SanPham {
    return this.statesComplex.filter(x => {
      return this.listchitiethoadon.find( y => y.sanpham.masp === masp ) == null;
    })
    .find( x => x.masp === masp );
  }

  findCustByMaKH(makh: string): KhachHang {
    return this.statesComplexKhachHang.find( x => x.makhachhang === makh );
  }

  getTotalProdCost() {
    let total = 0;
    this.listchitiethoadon.forEach(x => {
      total += x.sanpham.giaban * x.soluong - x.giamgia;
    });
    return total;
  }
  getTotalCost() {
    return this.getTotalProdCost() - this.giamgiaBill;
  }
  getDebt() {
    return this.getTotalCost() - this.khachduaBill;
  }

  checkoutBill() {
    if (this.listchitiethoadon.length === 0) {
      alert('Bạn chưa thêm sản phẩm nào. Vui lòng thêm sản phẩm trước khi Lưu hóa đơn!');
    } else {
      this.hoadonbanhang = new HoaDonBanHang();
      this.hoadonbanhang.createdAt = new Date();
      this.hoadonbanhang.giamgia = this.giamgiaBill;
      this.hoadonbanhang.khachhang = this.khachhang;
      this.hoadonbanhang.khachhangtra = this.khachduaBill;
      this.hoadonbanhang.loaithanhtoan = this.methodPay;
      this.hoadonbanhang.nguoitao = this.currentUser;
      this.hoadonbanhang.mahoadon = '';
      this.hoadonbanhang.tonggia = this.getTotalCost();
      let currentBillID = 0;
      if (!this.checkInputKhachhang()) {
        alert('Vui lòng chọn khách hàng');
      } else {
        this.billService.postBill(this.hoadonbanhang).subscribe(data => {
          currentBillID = data.id;
          this.listchitiethoadon.forEach(x => x.id_hoadon = currentBillID);
          this.detailBillService.postDetailsBill(this.listchitiethoadon).subscribe(data => {
            alert('Lưu thành công');
            this.router.navigate(['/admin/orders']);
          },
            error => console.log(error));
        },
        error => console.log(error));
      }
    }
  }

  deleteChitietBill(index: number) {
    this.listchitiethoadon.splice(index, 1);
  }

  checkInputKhachhang() {
    if (this.khachhang === undefined ) {return false; }
    return true;
  }


}


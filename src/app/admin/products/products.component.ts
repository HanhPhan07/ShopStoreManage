import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { Pagination, PaginatedResult } from 'src/app/_models/pagination';
import { SanPham } from 'src/app/_models/sanpham';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/_services/product.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ChiTietDanhMuc } from 'src/app/_models/chitietdanhmuc';
import { NhaCungCap } from 'src/app/_models/nhacungcap';
import { NhaSanXuat } from 'src/app/_models/nhasanxuat';
import { CateProductService } from 'src/app/_services/cate-product.service';
import { DanhMucSP } from 'src/app/_models/danhmucsp';
import { ManufProdService } from 'src/app/_services/manuf-prod.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {
  modalRefAddProd: BsModalRef;
  pagination: Pagination;
  listProds: SanPham[];
  searchTerm: string;
  fitlerdanhmucsp: number;
  fitlernhasx: number;
  baseDataListProds: SanPham[];
  filterStatus: number;
  listStatusProd = [
    'Đang kinh doanh',
    'Đã ngừng kinh doanh'
  ];
  listCateProd: DanhMucSP[];
  listManufProd: NhaSanXuat[];
  itemsPerPage = 4;
  listSubTrTableProd = [];
  productAdd: SanPham;
  addProdForm = new FormGroup({
    masp: new FormControl({value: ''}),
    tensp: new FormControl('', Validators.required),
    soluong: new FormControl('', Validators.required),
    giavon: new FormControl('', Validators.required),
    giaban: new FormControl('', Validators.required),
    danhmuc: new FormControl('', Validators.required),
    nhasx: new FormControl('', Validators.required),
    hot: new FormControl('', Validators.required),
    new: new FormControl('', Validators.required),
    display: new FormControl('', Validators.required),
    anhsp: new FormControl(''),
    motasp: new FormControl('')
  });
    masp: string;
    tensp: string;
    soluong: number;
    giavon: number;
    giaban: number;
    danhmuc: ChiTietDanhMuc;
    nhasx: NhaSanXuat;
    hot: boolean;
    new: boolean;
    display: boolean;
    anhsp: string;
    motasp: string;
  constructor(
    private modalService: BsModalService,
    private productService: ProductService,
    private router: Router,
    private cateProdService: CateProductService,
    private manufProdService: ManufProdService,
    private activatedRoute: ActivatedRoute) { }

  openModal(template: TemplateRef<any>) {
    this.modalRefAddProd = this.modalService.show(template);
  }
  ngOnInit() {
    this.fitlerdanhmucsp = 0;
    this.fitlernhasx = 0;
    this.filterStatus = 0;
    this.listProds = new Array();
    this.pagination = {
      currentPage: 1,
      totalItems: 0,
      totalPages: 0,
      itemsPerPage: this.itemsPerPage
    };
    this.resetFilter();
    this.baseDataListProds = [];
    this.activatedRoute.data.subscribe(data => {
        this.listProds = data.product.result;
        this.pagination = data.product.pagination;
        //this.listCateProd = data.cates;
     });
    this.getListCateProd();
    this.getListManufProd();
  }

  createProduct() {
    this.addProdForm.controls['masp'].value();
    this.addProdForm.controls['tensp'].value();
    this.addProdForm.controls['soluong'].value();
    this.addProdForm.controls['giavon'].value();
    this.addProdForm.controls['giaban'].value();
    this.addProdForm.controls['danhmuc'].value();
    this.addProdForm.controls['nhasx'].value();
    this.addProdForm.controls['hot'].value();
    this.addProdForm.controls['new'].value();
    this.addProdForm.controls['display'].value();
    this.addProdForm.controls['anhsp'].value();
    this.addProdForm.controls['motasp'].value();
  }
  toggleChiTietSanPham(id: number) {
    this.listSubTrTableProd[id] = !this.listSubTrTableProd[id];
  }

  getListCateProd() {
    this.cateProdService.getListCateArr().subscribe(
      data => {
        this.listCateProd = data;
      }
    );
  }
  getListManufProd() {
    this.manufProdService.getListManu().subscribe(
      data => {
        this.listManufProd = data;
      }
    );
  }


  editProduct(maSP: number) {
    this.router.navigate(['/admin/products/' + maSP]);
  }
  deleteProduct(maSP: number) {
    if (confirm('Bạn thực sự muốn xóa sản phẩm này?')) {
      this.productService.deleteProduct(maSP).subscribe(() => {
        this.getListProduct();
        alert('Xóa thành công!');
      },
        error => {
          console.log(error);
          alert('Xóa thất bại!');
        }
      );
    }
  }
  getListProduct() {
    this.productService.getProductPage (
      this.pagination.currentPage, this.pagination.itemsPerPage).subscribe(
        (data: PaginatedResult<SanPham[]>) => {
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
    this.listProds = data;
    this.baseDataListProds = [];
    this.listSubTrTableProd = [];
    if (data != null ) {
      this.listProds.forEach(x => {
        this.baseDataListProds.push(x);
        this.listSubTrTableProd.push(false);
      });
    }
    console.log(this.listProds);
  }
  resetFilter() {
    this.searchTerm = '';
  }
  search() {
        if (typeof (this.searchTerm) === 'undefined' || this.searchTerm === null) {
          this.searchTerm = '';
        }
        this.productService.getSearchListProduct (
          this.pagination.currentPage, this.pagination.itemsPerPage, this.searchTerm).subscribe(
            (data: PaginatedResult<SanPham[]>) => {
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
  filter() {
    if (this.filterStatus == 2) {
      this.listProds = this.baseDataListProds.filter(this.isNonSale);
    } else if (this.filterStatus == 0) {
      this.listProds = this.baseDataListProds;
    } else {
      this.listProds = this.baseDataListProds.filter(this.isSale);
    }
  }
  isSale(sanpham: SanPham){
    return sanpham.trangthai == 1;
  }
  isNonSale(sanpham: SanPham){
    return sanpham.trangthai == 2;
  }
  pageChanged(event: any): void {
    this.pagination.currentPage = event.page;
    this.search();
  }

    checkInputProduct() {
      if (this.masp === undefined || this.tensp === undefined || this.soluong === undefined ||
        this.giavon === undefined || this.giaban === undefined ||
        this.danhmuc === undefined || this.nhasx === undefined ||
        this.hot === undefined || this.new === undefined || this.display === undefined) { return false; }
      return true;
    }
}
